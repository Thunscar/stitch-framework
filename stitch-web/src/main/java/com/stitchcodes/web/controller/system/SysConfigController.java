package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.excel.ExcelUtil;
import com.stitchcodes.common.utils.ConvertUtils;
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

    /**
     * 获取系统参数配置信息
     *
     * @return 系统参数列表
     */
    @PreAuthorize("@permissionManager.hasPermission('system:config:list')")
    @GetMapping("/list")
    public TableData list(SysConfig sysConfig) {
        //开启分页插件
        startPage();
        List<SysConfig> sysConfigList = configService.getSysConfigList(sysConfig);
        return getTableData(sysConfigList);
    }

    /**
     * 通过Id获取系统参数
     *
     * @param configId 系统参数ID
     * @return 系统参数信息
     */
    @GetMapping("/{configId}")
    public AjaxResult getById(@PathVariable Long configId) {
        return success(configService.getConfigValueById(configId));
    }

    /**
     * 通过参数Key获取系统参数值
     *
     * @param configKey 系统参数key
     * @return 系统参数实体
     */
    @GetMapping("/configKey/{configKey}")
    public AjaxResult getByKey(@PathVariable String configKey) {
        String configValueByKey = configService.getConfigValueByKey(configKey);
        return success((Object) ConvertUtils.toString(configValueByKey));
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, SysConfig config) throws IOException {
        List<SysConfig> sysConfigList = configService.getSysConfigList(config);
        ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class);
        util.exportExcel("参数数据", sysConfigList, response);
    }

    /**
     * 新增系统参数信息
     *
     * @param sysConfig 系统参数实体
     * @return 是否创建成功
     */
    @PostMapping
    public AjaxResult add(@Valid @RequestBody SysConfig sysConfig) {
        if (!configService.checkSysConfigKeyUnique(sysConfig)) {
            return error("Failed to Create The Config Because The Parameter key Already Existed");
        }
        sysConfig.setCreateUser(AuthUtils.getLoginUserName());
        return toAjax(configService.insertSysConfig(sysConfig));
    }

    /**
     * 修改系统参数
     *
     * @param sysConfig 系统参数配置实体
     * @return 是否修改成功
     */
    @PutMapping
    public AjaxResult update(@Valid @RequestBody SysConfig sysConfig) {
        if (!configService.checkSysConfigKeyUnique(sysConfig)) {
            return error("Failed to Create The Config Because The Parameter key Already Existed");
        }
        sysConfig.setUpdateUser(AuthUtils.getLoginUserName());
        return toAjax(configService.updateSysConfig(sysConfig));
    }


    /**
     * 删除参数配置
     *
     * @param configIds 参数配置Id数组
     * @return
     */
    @DeleteMapping("/{configIds}")
    public AjaxResult deleteConfig(@PathVariable Long[] configIds) {
        configService.deleteSysConfig(configIds);
        return success();
    }

    /**
     * 刷新参数缓存
     *
     * @return 是否刷新成功
     */
    @PostMapping("/refresh")
    public AjaxResult refreshCache() {
        configService.resetConfigCache();
        return success();
    }

}
