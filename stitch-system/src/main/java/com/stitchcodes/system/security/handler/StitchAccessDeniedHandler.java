package com.stitchcodes.system.security.handler;

import com.alibaba.fastjson2.JSONObject;
import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.constant.HttpStatus;
import com.stitchcodes.common.utils.ServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: stitch
 * @Date: 2024/3/23 11:15
 * @Description:
 */
@Component
public class StitchAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String message = String.format("权限不足，拒绝访问", request.getRequestURI());
        ServletUtils.sendString(response, JSONObject.toJSONString(new AjaxResult(HttpStatus.ACCESS_DENIED, message)));
    }
}
