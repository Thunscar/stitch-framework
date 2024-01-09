package com.stitchcodes.core.utils;

import com.stitchcodes.common.constant.HttpStatus;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.core.domain.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: stitch
 * @Date: 2023/5/7 22:14
 * @Description: 用户认证工具类
 */
public class AuthUtils {

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 登录用户
     */
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new StitchException("Obtain Login User Information Error", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取当前在线用户账号
     *
     * @return 登录账号
     */
    public static String getLoginUserName() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new StitchException("Obtain Login User Account Error", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取部门ID
     *
     * @return 部门ID
     */
    public static Long getLoginUserDeptId() {
        try {
            return getLoginUser().getDeptId();
        } catch (Exception e) {
            throw new StitchException("Obtain Login User DeptID Error", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    public static Long getLoginUserId() {
        try {
            return getLoginUser().getUser().getUserId();
        } catch (Exception e) {
            throw new StitchException("Obtain Login User ID Error", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 检查用户是否为管理员
     *
     * @param userId 用户ID
     * @return 是否为管理员
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

}
