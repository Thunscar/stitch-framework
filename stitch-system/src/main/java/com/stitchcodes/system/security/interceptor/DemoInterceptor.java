package com.stitchcodes.system.security.interceptor;

import com.alibaba.fastjson2.JSON;
import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.config.StitchConfig;
import com.stitchcodes.common.constant.HttpStatus;
import com.stitchcodes.common.utils.ServletUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2024/3/2 17:20
 * @Description: 演示模式拦截器
 */
@Component
public class DemoInterceptor implements HandlerInterceptor {

    //需要拦截的请求方式
    private static final List<String> METHOD_INTERCEPTORS = new ArrayList<>();
    //需要放行的url
    private static final List<String> URL_PASS = new ArrayList<>();

    @Resource
    private StitchConfig stitchConfig;

    @PostConstruct
    public void init() {
        METHOD_INTERCEPTORS.add("POST");
        METHOD_INTERCEPTORS.add("PUT");
        METHOD_INTERCEPTORS.add("DELETE");
        URL_PASS.add("/sys/login");
        URL_PASS.add("/sys/logout");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //若开启了演示模式
        if (stitchConfig.isDemoEnabled()) {
            if (METHOD_INTERCEPTORS.contains(request.getMethod()) && !URL_PASS.contains(request.getRequestURI())) {
                AjaxResult result = new AjaxResult(HttpStatus.WARN, "演示模式,不可操作");
                ServletUtils.sendString(response, JSON.toJSONString(result));
                return false;
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
