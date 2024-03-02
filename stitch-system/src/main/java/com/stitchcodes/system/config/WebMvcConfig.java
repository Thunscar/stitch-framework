package com.stitchcodes.system.config;

import com.stitchcodes.system.security.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: stitch
 * @Date: 2024/3/2 17:27
 * @Description:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private DemoInterceptor demoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
