package com.stitchcodes.system.service;

import com.stitchcodes.common.constant.CacheConstants;
import com.stitchcodes.common.constant.CommonConstants;
import com.stitchcodes.common.redis.RedisCache;
import com.stitchcodes.common.utils.*;
import com.stitchcodes.core.domain.model.LoginUser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: stitch
 * @Date: 2023/5/7 21:00
 * @Description: token相关服务
 */
@Service
public class SysTokenService {

    /*每分钟毫秒数*/
    private static final long MILLS_MINUTE = 60 * 1000;
    /*令牌有效期刷新阈值*/
    private static final long REFRESH_TOKEN_THRESHOLD = 20 * MILLS_MINUTE;


    @Value("${token.header}")
    private String tokenHeader;

    @Value("${token.secret}")
    private String tokenSecret;

    @Value("${token.expire}")
    private Integer tokenExpire;

    @Resource
    private RedisCache redisCache;

    /**
     * 创建token
     *
     * @param loginUser 当前登录用户
     * @return token
     */
    public String createToken(LoginUser loginUser) {
        String token = IdUtils.generateTokenUUID();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser);
        //生成返回的token
        Map<String, Object> claims = new HashMap<>();
        claims.put(CommonConstants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 从请求中获取token
     *
     * @param request 请求
     * @return token
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (StringUtils.isNotEmpty(token) && token.startsWith(CommonConstants.TOKEN_PREFIX)) {
            token = token.replaceFirst(CommonConstants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 为用户刷新token
     *
     * @param loginUser 当前登录用户
     */
    public void setLoginUser(LoginUser loginUser) {
        if (ObjectUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 移除token
     *
     * @param token token
     */
    public void removeToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            Claims claims = parseToken(token);
            String tokenKey = (String) claims.get(CommonConstants.LOGIN_USER_KEY);
            redisCache.deleteObject(getTokenCacheKey(tokenKey));
        }
    }

    /**
     * 获取当前登录的用户
     *
     * @param request 请求上下文
     * @return 当前登录的用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                String tokenKey = (String) claims.get(CommonConstants.LOGIN_USER_KEY);
                return redisCache.getCacheObject(getTokenCacheKey(tokenKey));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 生成token
     *
     * @param claims 数据声明
     * @return token
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, tokenSecret).compact();
    }

    /**
     * 从token中获取数据声明
     *
     * @param token token
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录用户
     */
    private void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ipaddr = IpUtils.getIpAddr();
        loginUser.setOs(userAgent.getOperatingSystem().getName());
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setIpaddr(ipaddr);
        loginUser.setLoginLocation(AddressUtils.getRealAddressByIp(ipaddr));
    }

    /**
     * 刷新token使用时长(更新redis缓存)
     *
     * @param loginUser 登录用户
     */
    private void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() * tokenExpire * MILLS_MINUTE);
        String tokenCacheKey = getTokenCacheKey(loginUser.getToken());
        redisCache.setCacheObject(tokenCacheKey, loginUser, tokenExpire, TimeUnit.MINUTES);
    }

    /**
     * 验证令牌有效期，若不足20分钟，刷新token
     *
     * @param loginUser 登录用户
     */
    public void verifyToken(LoginUser loginUser) {
        Long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (currentTime - expireTime <= REFRESH_TOKEN_THRESHOLD) {
            refreshToken(loginUser);
        }
    }

    /**
     * 获取存储token的缓存键
     *
     * @param tokenUUID token uuid
     * @return 存储token的缓存key
     */
    private String getTokenCacheKey(String tokenUUID) {
        return CacheConstants.TOKEN_KEY + tokenUUID;
    }

}
