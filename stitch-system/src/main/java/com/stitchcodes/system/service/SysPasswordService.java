package com.stitchcodes.system.service;

import com.stitchcodes.common.constant.CacheConstants;
import com.stitchcodes.common.constant.ErrorConstant;
import com.stitchcodes.common.constant.OperateConstants;
import com.stitchcodes.common.constant.UserConstants;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.redis.RedisCache;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.encode.StitchPasswordEncoder;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.service.SysConfigService;
import com.stitchcodes.system.manager.AsyncManager;
import com.stitchcodes.system.manager.factory.AsyncFactory;
import com.stitchcodes.system.security.AuthenticationContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: stitch
 * @Date: 2023/5/8 20:16
 * @Description: 密码服务实现
 */
@Service
public class SysPasswordService {

    @Resource
    private SysConfigService configService;
    @Resource
    private RedisCache redisCache;
    @Resource
    private StitchPasswordEncoder passwordEncoder;

    /**
     * 获取密码错误次数缓存键值
     *
     * @param username 账号
     * @return 密码次数
     */
    private String getPasswordErrorCountCacheKey(String username) {
        return CacheConstants.PASS_ERROR_COUNT_KEY + username;
    }


    /**
     * 清除密码错误次数
     *
     * @param username 账号
     */
    public void clearPasswordErrorCount(String username) {
        String cacheKey = getPasswordErrorCountCacheKey(username);
        Object errCount = redisCache.getCacheObject(cacheKey);
        if (ObjectUtils.isNotNull(errCount)) {
            redisCache.deleteObject(cacheKey);
        }
    }

    /**
     * 获取密码错误次数
     *
     * @param username 用户名
     * @return 密码错误次数
     */
    private Integer getPasswordErrorCount(String username) {
        String cacheKey = getPasswordErrorCountCacheKey(username);
        Integer errCount = redisCache.getCacheObject(cacheKey);
        return ObjectUtils.isNull(errCount) ? 0 : errCount;
    }

    /**
     * 密码错误次数加一,密码错误次数存储时间为账户锁定时间
     *
     * @param username 用户名
     */
    private void increasePasswordErrorCount(String username, Integer errCount) {
        String cacheKey = getPasswordErrorCountCacheKey(username);
        redisCache.setCacheObject(cacheKey, errCount + 1, configService.selectAccountLockTime(), TimeUnit.MINUTES);
    }

    /**
     * 校验用户是否可以登录成功
     *
     * @param sysUser 系统用户信息
     */
    public void validate(SysUser sysUser) {
        Authentication context = AuthenticationContextHolder.getContext();
        String username = context.getName();
        String password = context.getCredentials().toString();
        Integer errCount = getPasswordErrorCount(username);
        if (ObjectUtils.isNotNull(errCount) && errCount >= configService.selectPasswordErrorCountUpperLimit()) {
            //密码错误次数超出限制
            String message = "密码错误次数已达上限，账号锁定";
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, UserConstants.LOGIN_FAIL, OperateConstants.LOGIN_OPERATION, ErrorConstant.ACCOUNT_LOCKED));
            throw new StitchException(message);
        }
        if (!matches(password, sysUser)) {
            //密码错误
            increasePasswordErrorCount(username, errCount);
            String message = "密码错误";
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, UserConstants.LOGIN_FAIL, OperateConstants.LOGIN_OPERATION, ErrorConstant.PASSWORD_ERROR));
            throw new StitchException(message);
        }
        clearPasswordErrorCount(username);
    }

    /**
     * 将用户的密码进行加密
     *
     * @param user 用户信息
     */
    public void encodeSysUserPassword(SysUser user) {
        String encodePwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePwd);
    }

    /**
     * 校验密码是否正确
     *
     * @param password 密码
     * @param sysUser  系统用户
     * @return 是否正确
     */
    private boolean matches(String password, SysUser sysUser) {
        return passwordEncoder.matches(password, sysUser.getPassword());
    }
}
