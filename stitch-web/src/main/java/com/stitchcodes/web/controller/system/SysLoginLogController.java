package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.core.domain.SysLoginInfo;
import com.stitchcodes.core.service.SysLoginInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2023/6/14 19:17
 * @Description: 登录日志控制器
 */
@RestController
@RequestMapping("/sys/log/login")
public class SysLoginLogController extends BaseController {

    @Resource
    private SysLoginInfoService loginInfoService;

    //查询登录日志
    @PreAuthorize("@pm.hasPerms('sys:loginlog:list')")
    @GetMapping("/list")
    public TableData getLoginLogList(SysLoginInfo sysLoginInfo){
        startPage();
        List<SysLoginInfo> sysLoginInfoList = loginInfoService.getSysLoginInfoList(sysLoginInfo);
        return getTableData(sysLoginInfoList);
    }

}
