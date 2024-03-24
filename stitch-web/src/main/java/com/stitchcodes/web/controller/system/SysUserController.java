package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.excel.ExcelUtil;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysDept;
import com.stitchcodes.core.domain.SysPost;
import com.stitchcodes.core.domain.SysRole;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.service.*;
import com.stitchcodes.core.utils.AuthUtils;
import com.stitchcodes.system.service.SysPasswordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2023/6/9 11:48
 * @Description: 用户管理控制器
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService userService;
    @Resource
    private SysConfigService configService;
    @Resource
    private SysRoleService roleService;
    @Resource
    private SysPostService postService;
    @Resource
    private SysDeptService deptService;

    @Resource
    private SysPasswordService passwordService;

    //查询角色列表
    @PreAuthorize("@pm.hasPerms('sys:user:list')")
    @GetMapping("/list")
    public TableData list(SysUser user) {
        startPage();
        List<SysUser> sysUsers = userService.selectUserList(user);
        return getTableData(sysUsers);
    }

    //获取角色信息
    @PreAuthorize("@pm.hasPerms('sys:user:query')")
    @GetMapping(value = {"/", "{userId}"})
    public AjaxResult getUserInfo(@PathVariable(required = false) Long userId) {
        //校验数据权限
        userService.checkUserDataScope(userId);

        AjaxResult userInfo = new AjaxResult();
        if (ObjectUtils.isNotNull(userId)) {
            //获取角色信息
            SysUser sysUser = userService.selectSafeUser(userId);
            Long[] roleIds = sysUser.getRoles().stream().map(SysRole::getRoleId).filter(ObjectUtils::isNotNull).toArray(Long[]::new);
            sysUser.setRoleIds(roleIds);
            List<SysPost> userPosts = postService.selectSysPostListByUserId(userId);
            sysUser.setPostIds(userPosts.stream().map(SysPost::getPostId).toArray(Long[]::new));
            userInfo.put("user", sysUser);
        }

        //获取部门列表
        List<SysDept> deptList = deptService.selectSysDeptList(new SysDept());
        //获取角色列表
        List<SysRole> roleList = roleService.selectSysRoleList(new SysRole());
        //获取岗位列表
        List<SysPost> postList = postService.selectSysPostList(new SysPost());

        userInfo.put("depts", deptList);
        userInfo.put("roles", roleList);
        userInfo.put("posts", postList);
        return userInfo;
    }

    //创建用户
    @PreAuthorize("@pm.hasPerms('sys:user:create')")
    @PostMapping
    public AjaxResult create(@RequestBody SysUser user) {
        //校验用户名是否唯一
        userService.checkUserNameUnique(user);
        //若密码为空，设置系统默认密码
        if (StringUtils.isEmpty(user.getPassword())) {
            String initPassword = configService.selectAccountInitPassword();
            user.setPassword(initPassword);
        }
        user.setCreateUser(AuthUtils.getLoginUserName());
        return toAjax(userService.insertUser(user));
    }

    //更新用户信息
    @PreAuthorize("@pm.hasPerms('sys:user:update')")
    @PutMapping
    public AjaxResult update(@RequestBody SysUser user) {
        userService.checkUserDataScope(user.getUserId());
        //检查用户名是否唯一
        userService.checkUserNameUnique(user);
        //若密码为空，设置系统默认密码
        if (StringUtils.isEmpty(user.getPassword())) {
            String initPassword = configService.selectAccountInitPassword();
            user.setPassword(initPassword);
        }
        user.setUpdateUser(AuthUtils.getLoginUserName());
        return toAjax(userService.updateUser(user));
    }

    //批量删除用户
    @PreAuthorize("@pm.hasPerms('sys:user:delete')")
    @DeleteMapping("{userIds}")
    public AjaxResult delete(@PathVariable Long[] userIds) {
        //检查数据权限
        for (Long userId : userIds) {
            userService.checkUserDataScope(userId);
        }
        return toAjax(userService.deleteUserByIds(userIds));
    }

    //导出用户信息到excel
    @PreAuthorize("@pm.hasPerms('sys:user:export')")
    @PostMapping("export")
    public void export(HttpServletResponse response, SysUser user) throws IOException {
        List<SysUser> sysUsers = userService.selectUserList(user);
        ExcelUtil<SysUser> excelUtil = new ExcelUtil<>(SysUser.class);
        excelUtil.exportExcel("用户数据", sysUsers, response);
    }

    //重置用户密码(重置后的密码在参数管理中设置)
    @PreAuthorize("@pm.hasPerms('sys:user:resetPassword')")
    @PostMapping("/reset/{userId}")
    public AjaxResult resetPassword(@PathVariable Long userId) {
        //检查数据权限
        userService.checkUserDataScope(userId);
        //获取账户初始密码
        String initPassword = configService.selectAccountInitPassword();

        //将用户密码进行加密
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setPassword(initPassword);
        passwordService.encodeSysUserPassword(sysUser);
        sysUser.setUpdateUser(AuthUtils.getLoginUserName());

        //更新用户密码
        return toAjax(userService.resetUserPassword(sysUser));
    }

    //查询某个角色已分配的用户列表
    @GetMapping("/allocated")
    public TableData allocatedUsers(SysUser sysUser) {
        startPage();
        List<SysUser> sysUsers = userService.selectAllocatedUsers(sysUser);
        return getTableData(sysUsers);
    }

    //查询某个角色可分配的用户列表
    @GetMapping("/unallocated")
    public TableData unAllocatedUsers(SysUser sysUser) {
        startPage();
        List<SysUser> sysUsers = userService.selectUnAllocatedUsers(sysUser);
        return getTableData(sysUsers);
    }

    //批量分配给用户角色
    @PreAuthorize("@pm.hasPerms('sys:user:allocatedRole')")
    @PostMapping("/allocate")
    public AjaxResult allocateRoles(Long userId, Long[] roleIds) {
        //检查用户数据权限
        userService.checkUserDataScope(userId);
        return toAjax(userService.allocateRoles(userId, roleIds));
    }

    //批量取消分配给用户的角色
    @PreAuthorize("@pm.hasPerms('sys:user:allocatedRole')")
    @PostMapping("/allocate/cancel")
    public AjaxResult cancelAllocateRoles(Long userId, Long[] roleIds) {
        //检查用户数据权限
        userService.checkUserDataScope(userId);
        return toAjax(userService.cancelAllocateRoles(userId, roleIds));
    }

    @PreAuthorize("@pm.hasPerms('sys:user:unlock')")
    @PostMapping("/unlock/{userId}")
    public AjaxResult unlockUserAccount(@PathVariable Long userId) {
        //检查用户数据权限
        userService.checkUserDataScope(userId);
        //解锁用户
        SysUser sysUser = userService.selectUserById(userId);
        userService.clearUserLoginCache(sysUser.getUserName());
        return success();
    }
}
