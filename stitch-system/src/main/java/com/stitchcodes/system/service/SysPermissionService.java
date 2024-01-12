package com.stitchcodes.system.service;

import com.stitchcodes.common.constant.PermissionConstants;
import com.stitchcodes.core.domain.SysRole;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.service.SysMenuService;
import com.stitchcodes.core.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: stitch
 * @Date: 2023/5/7 21:18
 * @Description: 用户权限服务
 */
@Service
public class SysPermissionService {

    @Resource
    private SysRoleService roleService;
    @Resource
    private SysMenuService menuService;


    /**
     * 获取角色权限
     *
     * @param user 用户
     * @return 权限列表
     */
    public Set<String> getRolePermission(SysUser user) {
        HashSet<String> roles = new HashSet<>();
        if (user.isAdmin()) {
            roles.add(PermissionConstants.ADMIN_ROLE_PERMISSION);
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }

    /**
     * 获取用户菜单权限标识符
     *
     * @param user 用户
     * @return 权限列表
     */
    public Set<String> getMenuPermission(SysUser user) {
        HashSet<String> perms = new HashSet<>();
        if (user.isAdmin()) {
            perms.add(PermissionConstants.ALL_MENU_PERMISSION);
        } else {
            List<SysRole> roles = user.getRoles();
            if (!roles.isEmpty()) {
                for (SysRole role : roles) {
                    Set<String> rolePerms = menuService.selectMenuPermissionByRoleId(role.getRoleId());
                    role.setPerms(rolePerms);
                    perms.addAll(rolePerms);
                }
            } else {
                Set<String> userPerms = menuService.selectMenuPermissionByUserId(user.getUserId());
                perms.addAll(userPerms);
            }
        }
        return perms;
    }
}
