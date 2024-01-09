package com.stitchcodes.system.config;

import com.stitchcodes.common.utils.encode.StitchPasswordEncoder;
import com.stitchcodes.system.security.filter.JwtAuthenticationFilter;
import com.stitchcodes.system.security.handler.AuthenticationEntryPointHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * @Author: stitch
 * @Date: 2023/5/9 17:54
 * @Description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AuthenticationEntryPointHandler entryPointHandler;
    @Resource
    private JwtAuthenticationFilter authenticationFilter;
    @Resource
    private CorsFilter corsFilter;
    @Resource(name = "sysUserDetailsService")
    private UserDetailsService userDetailsService;
    @Resource
    private StitchPasswordEncoder passwordEncoder;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // CSRF禁用，因为不使用session
                .csrf().disable()
                // 禁用HTTP响应标头
                .headers().cacheControl().disable().and()
                // 认证失败处理类
                .exceptionHandling().authenticationEntryPoint(entryPointHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 过滤请求
                .authorizeRequests()
                // 对于登录login 注册register 验证码captchaImage 允许匿名访问
                .antMatchers("/sys/login", "/register", "/verifyImage").permitAll()
                // 静态资源，可匿名访问
                .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll().antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated().and().headers().frameOptions().disable();
//        // 添加Logout filter
//        httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        // 添加JWT filter
        httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加CORS filter
        httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationFilter.class);
        httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
