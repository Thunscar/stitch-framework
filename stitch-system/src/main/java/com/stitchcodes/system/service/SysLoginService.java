package com.stitchcodes.system.service;

import com.stitchcodes.common.constant.*;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.redis.RedisCache;
import com.stitchcodes.common.utils.DateUtils;
import com.stitchcodes.common.utils.IpUtils;
import com.stitchcodes.common.utils.ServletUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.domain.model.LoginUser;
import com.stitchcodes.core.service.SysConfigService;
import com.stitchcodes.core.service.SysUserService;
import com.stitchcodes.core.utils.AuthUtils;
import com.stitchcodes.system.manager.AsyncManager;
import com.stitchcodes.system.manager.factory.AsyncFactory;
import com.stitchcodes.system.security.AuthenticationContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: stitch
 * @Date: 2023/5/6 10:59
 * @Description: 系统登录服务
 */
@Service
public class SysLoginService {

    @Resource
    private SysConfigService configService;
    @Resource
    private RedisCache redisCache;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private SysTokenService tokenService;
    @Resource
    private SysUserService userService;

    /**
     * 登录接口
     *
     * @param userName 用户名
     * @param password 密码
     * @param uuid     验证码UUID
     * @param code     验证码
     * @return token
     */
    public String login(String userName, String password, String uuid, String code) {
        //校验验证码
        validateVerifyCode(userName, uuid, code);
        //登录前置校验
        loginPreCheck(userName, password);
        //用户认证
        LoginUser loginUser = loginAuthenticate(userName, password);
        //生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 用户登出
     *
     * @param request 请求体
     */
    public void logout() {
        HttpServletRequest request = ServletUtils.getRequest();
        //获取用户token
        String token = tokenService.getToken(request);
        //移除缓存中的token
        tokenService.removeToken(token);
        //获取用户账号记录日志
        String loginUserName = AuthUtils.getLoginUserName();
        String message = String.format("User Account:[%s] Logout", loginUserName);
        AsyncManager.me().execute(AsyncFactory.recordLoginInfo(loginUserName, UserConstants.LOGOUT, OperateConstants.LOGIN_OPERATION, message));
    }

    /**
     * 登录前置检查
     *
     * @param userName 用户名
     * @param password 密码
     */
    private void loginPreCheck(String userName, String password) {
        //判空校验
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            String message = "Username or Password is Required";
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(userName, UserConstants.LOGIN_FAIL, OperateConstants.LOGIN_OPERATION, message));
            throw new StitchException(message);
        }
//        //用户名长度校验
//        if (userName.length() < UserConstants.USERNAME_MIN_LENGTH || userName.length() > UserConstants.USERNAME_MAX_LENGTH) {
//            String message = "The Username Length Does not Comply With The Rule";
//            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(userName, UserConstants.LOGIN_FAIL, OperateConstants.LOGIN_OPERATION, message));
//            throw new StitchException(message);
//        }
//        //密码长度校验
//        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
//            String message = "The Password Length Does Not Comply With The Rule";
//            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(userName, UserConstants.LOGIN_FAIL, OperateConstants.LOGIN_OPERATION, message));
//            throw new StitchException(message);
//        }
        //IP黑名单检查
        String ipBlackList = configService.getConfigValueByKey(ConfigConstants.LOGIN_IP_BLACK_LIST);
        if (StringUtils.isNotEmpty(ipBlackList) && IpUtils.isMatch(ipBlackList, IpUtils.getIpAddr())) {
            String message = "您已被纳入黑名单";
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(userName, UserConstants.LOGIN_FAIL, OperateConstants.LOGIN_OPERATION, ErrorConstant.IP_BLOCKED));
            throw new StitchException(message);
        }
    }

    /**
     * 登录认证
     *
     * @param userName 用户名
     * @param password 密码
     */
    private LoginUser loginAuthenticate(String userName, String password) {
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new StitchException(e.getMessage());
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        String message = "Login Success";
        AsyncManager.me().execute(AsyncFactory.recordLoginInfo(userName, UserConstants.LOGIN_SUCCESS, OperateConstants.LOGIN_OPERATION, message));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        return loginUser;
    }


    /**
     * 校验验证码是否正确
     *
     * @param userName 用户名
     * @param uuid     验证码UUID
     * @param code     验证码
     */
    private void validateVerifyCode(String userName, String uuid, String code) {
        boolean verifyCodeEnabled = configService.selectVerifyEnabled();
        if (verifyCodeEnabled) {
            String verifyCodeKey = CacheConstants.VERIFY_CODE_KEY + StringUtils.nvl(uuid, "");
            String verifyCode = redisCache.getCacheObject(verifyCodeKey);
            redisCache.deleteObject(verifyCodeKey);
            if (verifyCode == null) {
                String message = "验证码已过期";
                throw new StitchException(message);
            }
            if (!StringUtils.nvl(code).equals(verifyCode)) {
                String message = "验证码错误，请重新输入";
                throw new StitchException(message);
            }
        }
    }

    /**
     * 记录用户登录信息
     *
     * @param userId 用户ID
     */
    private void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginTime(DateUtils.getNowDate());
        sysUser.setLoginIp(IpUtils.getIpAddr());
        userService.updateUserProfile(sysUser);
    }
}
