package com.stitchcodes.system.security;

import com.stitchcodes.common.utils.ConvertUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Author: stitch
 * @Date: 2023/5/8 23:26
 * @Description: 权限信息
 */
public class PermissionContextHolder {

    /*权限属性标识*/
    private static final String PERMISSION_CONTEXT_ATTRIBUTES = "PERMISSION_CONTEXT";

    /**
     * 向请求上下文中设置权限信息
     *
     * @param permission 权限字符串
     */
    public static void setContext(String permission) {
        RequestContextHolder.currentRequestAttributes().setAttribute(PERMISSION_CONTEXT_ATTRIBUTES, permission, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 获取请求上下文中的权限信息
     *
     * @return 权限字符串
     */
    public static String getContext() {
        return ConvertUtils.toString(RequestContextHolder.currentRequestAttributes().getAttribute(PERMISSION_CONTEXT_ATTRIBUTES, RequestAttributes.SCOPE_REQUEST));
    }
}
