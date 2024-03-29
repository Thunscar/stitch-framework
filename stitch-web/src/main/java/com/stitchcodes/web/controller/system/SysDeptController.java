package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.annotation.Log;
import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.enums.BusinessType;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.core.domain.SysDept;
import com.stitchcodes.core.service.SysDeptService;
import com.stitchcodes.core.utils.AuthUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: stitch
 * @Date: 2023/6/6 18:26
 * @Description: 系统机构控制器
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends BaseController {

    @Resource
    private SysDeptService deptService;

    //查询机构列表
    @PreAuthorize("@pm.hasPerms('sys:dept:list')")
    @GetMapping("/list")
    public AjaxResult list(SysDept dept) {
        return AjaxResult.success(deptService.selectSysDeptList(dept));
    }

    //获取机构信息
    @PreAuthorize("@pm.hasPerms('sys:dept:query')")
    @GetMapping("{deptId}")
    public AjaxResult getDeptById(@PathVariable Long deptId) {
        //检查部门数据权限
        deptService.checkDeptDataScope(deptId);
        return AjaxResult.success(deptService.selectSysDeptById(deptId));
    }

    //创建机构
    @Log(title = "创建机构",BusinessType = BusinessType.INSERT)
    @PreAuthorize("@pm.hasPerms('sys:dept:create')")
    @PostMapping
    public AjaxResult create(@RequestBody SysDept dept) {
        //检查参数键名是否唯一
        deptService.checkDeptNameUnique(dept);
        //检查上级部门是否合法
        deptService.checkParentIdValid(dept);
        dept.setCreateUser(AuthUtils.getLoginUserName());
        return toAjax(deptService.createSysDept(dept));
    }

    //更新机构
    @Log(title = "更新机构",BusinessType = BusinessType.UPDATE)
    @PreAuthorize("@pm.hasPerms('sys:dept:update')")
    @PutMapping
    public AjaxResult update(@RequestBody SysDept dept) {
        //检查参数键名是否唯一
        deptService.checkDeptNameUnique(dept);
        //检查上级部门是否合法
        deptService.checkParentIdValid(dept);
        dept.setUpdateUser(AuthUtils.getLoginUserName());
        return toAjax(deptService.updateSysDept(dept));
    }

    //删除机构
    @Log(title = "删除机构",BusinessType = BusinessType.DELETE)
    @PreAuthorize("@pm.hasPerms('sys:dept:delete')")
    @DeleteMapping("{deptId}")
    public AjaxResult delete(@PathVariable Long deptId) {
        deptService.checkDeptDataScope(deptId);
        if (deptService.hasChildrenDept(deptId)) {
            throw new StitchException("该部门存在子部门,不可删除");
        }
        if (deptService.hasUserForDept(deptId)) {
            throw new StitchException("该部门存在用户,不可删除");
        }
        return toAjax(deptService.removeSysDept(deptId));
    }

}
