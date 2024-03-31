package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.redis.RedisCache;
import com.stitchcodes.common.utils.RedisKeyUtil;
import com.stitchcodes.core.domain.model.OnlineUser;
import com.stitchcodes.system.service.SysOnlineUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: stitch
 * @Date: 2024/3/30 22:15
 * @Description: 在线统计
 */
@RestController
@RequestMapping("/sys/online/")
public class SysOnlineUserController extends BaseController {

    @Resource
    private SysOnlineUserService onlineUserService;
    @Resource
    private RedisCache redisCache;

    //查询在线人数
    @PreAuthorize("@pm.hasPerms('sys:online:list')")
    @GetMapping("/list")
    public TableData queryOnlineUserList(OnlineUser user) {
        return getTableData(onlineUserService.selectOnlineUserList(user.getIpaddr(), user.getUserName()));
    }

    @PreAuthorize("@pm.hasPerms('sys:online:offline')")
    @PostMapping("/offline/{token}")
    public AjaxResult offline(@PathVariable String token) {
        redisCache.deleteObject(RedisKeyUtil.getTokenCacheKey(token));
        return AjaxResult.success();
    }
}
