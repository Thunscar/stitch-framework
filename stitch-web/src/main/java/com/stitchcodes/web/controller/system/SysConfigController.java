package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.annotation.Log;
import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.enums.BusinessType;
import com.stitchcodes.common.excel.ExcelUtil;
import com.stitchcodes.core.domain.SysConfig;
import com.stitchcodes.core.service.SysConfigService;
import com.stitchcodes.core.utils.AuthUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2023/4/30 14:06
 * @Description: 系统参数配置
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends BaseController {

    @Resource
    private SysConfigService configService;

    //获取系统参数配置信息
    @PreAuthorize("@pm.hasPerms('system:config:list')")
    @GetMapping("/list")
    public TableData list(SysConfig sysConfig) {
        //开启分页插件
        startPage();
        List<SysConfig> sysConfigList = configService.getSysConfigList(sysConfig);
        return getTableData(sysConfigList);
    }

    //通过Id获取系统参数
    @PreAuthorize("@pm.hasPerms('system:config:query')")
    @GetMapping("/{configId}")
    public AjaxResult getById(@PathVariable Long configId) {
        return success(configService.getConfigValueById(configId));
    }

    //导出参数列表
    @Log(title = "导出参数",BusinessType = BusinessType.EXPORT)
    @PreAuthorize("@pm.hasPerms('system:config:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysConfig config) throws IOException {
        List<SysConfig> sysConfigList = configService.getSysConfigList(config);
        ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>();
        util.exportExcel("参数数据", sysConfigList, response);
    }

    //新增系统参数信息
    @Log(title = "新增参数",BusinessType = BusinessType.INSERT)
    @PreAuthorize("@pm.hasPerms('system:config:create')")
    @PostMapping
    public AjaxResult create(@Valid @RequestBody SysConfig sysConfig) {
        //检查参数键是否唯一
        configService.checkSysConfigKeyUnique(sysConfig);
        sysConfig.setCreateUser(AuthUtils.getLoginUserName());
        return toAjax(configService.insertSysConfig(sysConfig));
    }

    //修改系统参数
    @Log(title = "修改参数",BusinessType = BusinessType.UPDATE)
    @PreAuthorize("@pm.hasPerms('system:config:update')")
    @PutMapping
    public AjaxResult update(@Valid @RequestBody SysConfig sysConfig) {
        //检查参数键是否唯一
        configService.checkSysConfigKeyUnique(sysConfig);
        sysConfig.setUpdateUser(AuthUtils.getLoginUserName());
        return toAjax(configService.updateSysConfig(sysConfig));
    }

    //删除参数配置
    @Log(title = "删除参数",BusinessType = BusinessType.DELETE)
    @PreAuthorize("@pm.hasPerms('system:config:delete')")
    @DeleteMapping("/{configIds}")
    public AjaxResult deleteConfig(@PathVariable Long[] configIds) {
        return toAjax(configService.deleteSysConfig(configIds));
    }

    //刷新参数缓存
    @PreAuthorize("@pm.hasPerms('system:config:refresh')")
    @PostMapping("/refresh")
    public AjaxResult refreshCache() {
        configService.resetConfigCache();
        return success();
    }

}
