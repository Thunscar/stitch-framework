package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.excel.ExcelUtil;
import com.stitchcodes.core.domain.SysRole;
import com.stitchcodes.core.service.SysRoleService;
import com.stitchcodes.core.utils.AuthUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2023/6/17 16:59
 * @Description: 角色控制器
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService roleService;

    @GetMapping("/list")
    public TableData getRoleList(SysRole role) {
        startPage();
        List<SysRole> sysRoles = roleService.selectSysRoleList(role);
        return getTableData(sysRoles);
    }

    @GetMapping("/{roleId}")
    public AjaxResult getRoleById(@PathVariable Long roleId) {
        //检查角色的访问权限
        roleService.checkRoleDataScope(roleId);
        SysRole sysRole = roleService.selectSysRoleById(roleId);
        return AjaxResult.success(sysRole);
    }

    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds) {
        //检查角色的访问权限
        for (Long roleId : roleIds) {
            roleService.checkRoleDataScope(roleId);
        }
        //移除角色信息
        roleService.removeSysRoleBatch(roleIds);
        return AjaxResult.success();
    }

    @PostMapping
    public AjaxResult create(@RequestBody SysRole role) {
        //检查角色Key是否重复
        roleService.checkRoleKeyUnique(role);

        role.setCreateUser(AuthUtils.getLoginUserName());
        roleService.createSysRole(role);
        return AjaxResult.success();
    }

    @PutMapping
    public AjaxResult update(@RequestBody SysRole role) {
        roleService.checkRoleKeyUnique(role);

        role.setUpdateUser(AuthUtils.getLoginUserName());
        roleService.updateSysRole(role);
        return AjaxResult.success();
    }

    @PostMapping("/export")
    public void export(SysRole role, HttpServletResponse response) throws IOException {
        List<SysRole> sysRoles = roleService.selectSysRoleList(role);
        ExcelUtil<SysRole> excelUtil = new ExcelUtil<>(SysRole.class);
        excelUtil.exportExcel("角色信息", sysRoles, response);
    }
}
