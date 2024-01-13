package com.stitchcodes.system.security.handler;

import com.alibaba.fastjson2.JSONObject;
import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.constant.HttpStatus;
import com.stitchcodes.common.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: stitch
 * @Date: 2023/5/10 16:47
 * @Description: 认证失败处理类型
 */
@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String message = String.format("Failed to Request URI:[%s], Unauthorized", request.getRequestURI());
        ServletUtils.sendString(response, JSONObject.toJSONString(new AjaxResult(HttpStatus.UNAUTHORIZED, message)));
    }
}
