package com.stitchcodes.system.security;


import org.springframework.security.core.Authentication;

/**
 * @Author: stitch
 * @Date: 2023/5/7 20:32
 * @Description: 用户身份认证类
 */
public class AuthenticationContextHolder {

    /*创建ThreadLocal*/
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    /**
     * 获取认证信息上下文
     *
     * @return 认证信息上下文
     */
    public static Authentication getContext() {
        return contextHolder.get();
    }

    /**
     * 设置认证信息到上下文中
     *
     * @param authentication 认证信息
     */
    public static void setContext(Authentication authentication) {
        contextHolder.set(authentication);
    }

    /**
     * 清除当前线程的认证信息
     */
    public static void clearContext() {
        contextHolder.remove();
    }

}
