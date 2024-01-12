package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.annotation.DataScope;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.utils.CollectionUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.SpringUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysRole;
import com.stitchcodes.core.domain.SysRoleMenu;
import com.stitchcodes.core.mapper.SysRoleMapper;
import com.stitchcodes.core.mapper.SysRoleMenuMapper;
import com.stitchcodes.core.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author stitch
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
 * @createDate 2023-04-28 13:20:02
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;
    @Resource
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> sysRoles = roleMapper.selectRoleByUserId(userId);
        HashSet<String> perms = new HashSet<>();
        for (SysRole sysRole : sysRoles) {
            if (ObjectUtils.isNotNull(sysRole) && StringUtils.isNotEmpty(sysRole.getRoleKey())) {
                perms.addAll(Arrays.asList(sysRole.getRoleKey().trim().split(",")));
            }
        }
        return perms;
    }

    @Override
    @DataScope(deptAlias = "d")
    public List<SysRole> selectSysRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public SysRole selectSysRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    @Override
    public void checkRoleDataScope(Long roleId) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(roleId);
        List<SysRole> sysRoles = SpringUtils.getAopProxy(this).selectSysRoleList(sysRole);
        if (CollectionUtils.isEmpty(sysRoles)) {
            throw new StitchException("您没有访问该角色的权限");
        }
    }

    @Override
    @Transactional
    public int createSysRole(SysRole role) {
        //保存角色信息
        roleMapper.insertRole(role);
        //保存角色功能权限
        return saveRoleMenu(role);
    }

    @Override
    public int saveRoleMenu(SysRole role) {
        int result = 1;
        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
        for (Long menuId : role.getMenuIds()) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(role.getRoleId());
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenus.add(sysRoleMenu);
        }
        if (CollectionUtils.isNotEmpty(sysRoleMenus)) {
            result = roleMenuMapper.insertRoleMenu(sysRoleMenus);
        }
        return result;
    }

    @Override
    public void checkRoleKeyUnique(SysRole role) {
        Long roleId = ObjectUtils.isNull(role.getRoleId()) ? -1L:role.getRoleId();
        SysRole queryRole = selectRoleByKey(role.getRoleKey());
        if (ObjectUtils.isNotNull(queryRole) && queryRole.getRoleId().longValue() != roleId.longValue()) {
            throw new StitchException("角色权限标识符不唯一");
        }
    }

    @Override
    public void checkRoleNameUnique(SysRole role) {
        Long roleId = ObjectUtils.isNull(role.getRoleId()) ? -1L:role.getRoleId();
        SysRole queryRole = selectRoleByName(role.getRoleName());
        if (ObjectUtils.isNotNull(queryRole) && queryRole.getRoleId().longValue() != roleId.longValue()) {
            throw new StitchException("角色名称不唯一");
        }
    }

    @Override
    public SysRole selectRoleByKey(String roleKey) {
        return roleMapper.selectSysRoleByKey(roleKey);
    }

    @Override
    public SysRole selectRoleByName(String roleName) {
        return roleMapper.selectSysRoleByName(roleName);
    }

    @Override
    @Transactional
    public int removeSysRoleBatch(Long[] roleIds) {
        roleMapper.removeRoleBatch(roleIds);
        return roleMenuMapper.deleteAuthorizedMenus(roleIds);
    }

    @Override
    public int updateSysRole(SysRole role) {
        roleMapper.updateRole(role);
        roleMenuMapper.deleteAuthorizedMenu(role.getRoleId());
        return saveRoleMenu(role);
    }

    @Override
    public Long[] selectAuthorizedMenu(Long roleId) {
        return roleMenuMapper.selectAuthorizedMenu(roleId);
    }
}




