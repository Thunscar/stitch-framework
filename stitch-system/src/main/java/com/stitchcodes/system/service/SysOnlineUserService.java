package com.stitchcodes.system.service;

import com.stitchcodes.common.constant.CacheConstants;
import com.stitchcodes.common.redis.RedisCache;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.model.LoginUser;
import com.stitchcodes.core.domain.model.OnlineUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: stitch
 * @Date: 2024/3/30 22:33
 * @Description: 在线统计服务
 */
@Service
public class SysOnlineUserService {

    @Resource
    private RedisCache redisCache;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 查询在线用户
     *
     * @param user 用户信息
     * @return
     */
    public List<OnlineUser> selectOnlineUserList(String ipAddr, String userName) {
        //获取分页信息
        Collection<String> keys = redisCache.keys(CacheConstants.TOKEN_KEY + "*");
        List<OnlineUser> onlineUsers = new ArrayList<>();
        for (String key : keys) {
            LoginUser loginUser = redisCache.getCacheObject(key);
            boolean require = true;

            if (StringUtils.isNotEmpty(userName) && !userName.equals(loginUser.getUsername())) {
                require = false;
            }
            if (StringUtils.isNotEmpty(ipAddr) && !ipAddr.equals(loginUser.getIpaddr())) {
                require = false;
            }

            if (require) {
                onlineUsers.add(getOnlineUser(loginUser));
            }
        }
        Collections.reverse(onlineUsers);
        return onlineUsers;
    }

    private OnlineUser getOnlineUser(LoginUser loginUser) {
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setSessionId(loginUser.getToken());
        onlineUser.setUserId(loginUser.getUserId());
        onlineUser.setUserName(loginUser.getUsername());
        onlineUser.setIpaddr(loginUser.getIpaddr());
        onlineUser.setBrowser(loginUser.getBrowser());
        onlineUser.setOs(loginUser.getOs());
        onlineUser.setLocation(loginUser.getLoginLocation());
        onlineUser.setLoginTime(SDF.format(new Date(loginUser.getLoginTime())));
        if (ObjectUtils.isNotNull(loginUser.getUser().getDept())) {
            onlineUser.setDeptName(loginUser.getUser().getDept().getDeptName());
        }
        return onlineUser;
    }
}
