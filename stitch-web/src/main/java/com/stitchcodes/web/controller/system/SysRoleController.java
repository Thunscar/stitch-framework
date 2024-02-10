package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.excel.ExcelUtil;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.core.domain.SysRole;
import com.stitchcodes.core.domain.model.LoginUser;
import com.stitchcodes.core.service.SysRoleService;
import com.stitchcodes.core.utils.AuthUtils;
import com.stitchcodes.system.service.SysPermissionService;
import com.stitchcodes.system.service.SysTokenService;
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
    @Resource
    private SysPermissionService permissionService;
    @Resource
    private SysTokenService tokenService;

    //查询角色列表
    @GetMapping("/list")
    public TableData getRoleList(SysRole role) {
        startPage();
        List<SysRole> sysRoles = roleService.selectSysRoleList(role);
        return getTableData(sysRoles);
    }

    //查询用户可访问的资源(菜单or按钮)
    @GetMapping("/auth/{roleId}")
    public AjaxResult getAuth(@PathVariable Long roleId) {
        //检查角色的访问权限
        roleService.checkRoleDataScope(roleId);
        return success(roleService.selectSysRoleContainsMenu(roleId));
    }

    //查询角色的数据权限 自定义数据权限会返回可访问的部门列表
    @GetMapping("/data/{roleId}")
    public AjaxResult getDataScope(@PathVariable Long roleId) {
        //检查角色的访问权限
        return success(roleService.selectSysRoleContainsDataScope(roleId));
    }

    //批量删除角色
    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds) {
        //检查角色的访问权限
        for (Long roleId : roleIds) {
            roleService.checkRoleDataScope(roleId);
        }
        //检查是否可以被删除(已分配用户的角色不可删除)
        for (Long roleId : roleIds) {
            roleService.checkDeleteOperation(roleId);
        }

        //移除角色信息
        return toAjax(roleService.removeSysRoleBatch(roleIds));
    }

    //创建角色
    @PostMapping
    public AjaxResult create(@RequestBody SysRole role) {
        //检查角色名称是否重复
        roleService.checkRoleNameUnique(role);
        //检查角色Key是否重复
        roleService.checkRoleKeyUnique(role);

        role.setCreateUser(AuthUtils.getLoginUserName());
        return toAjax(roleService.createSysRole(role));
    }

    //更新角色信息
    @PutMapping
    public AjaxResult update(@RequestBody SysRole role) {
        //检查角色数据权限
        roleService.checkRoleDataScope(role.getRoleId());
        //检查角色名称是否重复
        roleService.checkRoleNameUnique(role);
        //检查角色Key是否重复
        roleService.checkRoleKeyUnique(role);

        role.setUpdateUser(AuthUtils.getLoginUserName());
        if (roleService.updateSysRole(role) > 0) {
            LoginUser loginUser = AuthUtils.getLoginUser();
            if (ObjectUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()) {
                loginUser.setPermissions(permissionService.getMenuPermission(loginUser.getUser()));
                tokenService.setLoginUser(loginUser);
            }
            return success();
        }
        return error("修改角色[" + role.getRoleName() + "]失败");
    }

    //导出角色信息到excel
    @PostMapping("/export")
    public void export(SysRole role, HttpServletResponse response) throws IOException {
        List<SysRole> sysRoles = roleService.selectSysRoleList(role);
        ExcelUtil<SysRole> excelUtil = new ExcelUtil<>(SysRole.class);
        excelUtil.exportExcel("角色信息", sysRoles, response);
    }

    //给角色分配用户
    @PostMapping("confer")
    public AjaxResult conferRoles(Long roleId, Long[] userIds) {
        //检查角色数据权限
        roleService.checkRoleDataScope(roleId);
        return toAjax(roleService.conferRole(roleId, userIds));
    }

    //取消角色分配的用户
    @PostMapping("/confer/cancel")
    public AjaxResult cancelConferRoles(Long roleId, Long[] userIds) {
        //检查角色数据权限
        roleService.checkRoleDataScope(roleId);
        return toAjax(roleService.cancelConferRole(roleId, userIds));
    }

    //修改角色数据权限
    @PutMapping("/dataScope")
    public AjaxResult updateDataScope(@RequestBody SysRole sysRole) {
        //检查角色数据权限
        roleService.checkRoleDataScope(sysRole.getRoleId());
        return toAjax(roleService.updateDataScope(sysRole));
    }

    //查询用户已分配的角色列表
    @GetMapping("/allocated")
    public TableData allocatedRoles(SysRole sysRole) {
        startPage();
        List<SysRole> sysRoles = roleService.selectAllocatedRoles(sysRole);
        return getTableData(sysRoles);
    }

    //查询用户未分配的角色列表
    @GetMapping("/unallocated")
    public TableData unAllocatedRoles(SysRole sysRole) {
        startPage();
        List<SysRole> sysRoles = roleService.selectUnallocatedRoles(sysRole);
        return getTableData(sysRoles);
    }

}
