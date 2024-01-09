package com.stitchcodes.system.security.filter;

import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.core.domain.model.LoginUser;
import com.stitchcodes.core.utils.AuthUtils;
import com.stitchcodes.system.service.SysTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: stitch
 * @Date: 2023/5/10 16:57
 * @Description: token认证过滤器
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private SysTokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (ObjectUtils.isNotNull(loginUser) && ObjectUtils.isNull(AuthUtils.getAuthentication())) {
            //若token有效刷新token
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}
