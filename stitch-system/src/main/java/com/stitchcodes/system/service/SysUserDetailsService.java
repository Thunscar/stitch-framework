package com.stitchcodes.system.service;

import com.stitchcodes.common.constant.OperateConstants;
import com.stitchcodes.common.constant.UserConstants;
import com.stitchcodes.common.enums.UserStatus;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.domain.model.LoginUser;
import com.stitchcodes.core.service.SysUserService;
import com.stitchcodes.system.manager.AsyncManager;
import com.stitchcodes.system.manager.factory.AsyncFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: stitch
 * @Date: 2023/5/7 21:05
 * @Description: 实现SpringSecurity的UserDetailsService
 */
@Service
public class SysUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserService userService;
    @Resource
    private SysPasswordService passwordService;
    @Resource
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (ObjectUtils.isNull(sysUser)) {
            //账号不存在
            String message = "The Account Does Not Exist";
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, UserConstants.LOGIN_FAIL, OperateConstants.LOGIN_OPERATION, message));
            throw new StitchException(message);
        }
        if (UserStatus.BLOCKED.getCode().equals(sysUser.getStatus())) {
            //账号已停用
            String message = "The Account Has Been Blocked";
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, UserConstants.LOGIN_FAIL, OperateConstants.LOGIN_OPERATION, message));
            throw new StitchException(message);
        }
        passwordService.validate(sysUser);
        return createLoginUser(sysUser);
    }

    public UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
    }
}
