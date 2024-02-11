package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.annotation.DataScope;
import com.stitchcodes.common.constant.DataScopeConstant;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.utils.CollectionUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.SpringUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysRole;
import com.stitchcodes.core.domain.SysRoleMenu;
import com.stitchcodes.core.mapper.SysRoleDeptMapper;
import com.stitchcodes.core.mapper.SysRoleMapper;
import com.stitchcodes.core.mapper.SysRoleMenuMapper;
import com.stitchcodes.core.mapper.SysUserRoleMapper;
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
    @Resource
    private SysUserRoleMapper userRoleMapper;
    @Resource
    private SysRoleDeptMapper roleDeptMapper;

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
    @DataScope
    public List<SysRole> selectSysRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public SysRole selectSysRoleContainsMenu(Long roleId) {
        SysRole sysRole = roleMapper.selectRoleById(roleId);
        //查询部门的功能权限
        sysRole.setMenuIds(roleMenuMapper.selectAuthorizedMenu(roleId));
        return sysRole;
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
        long roleId = ObjectUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole queryRole = selectRoleByKey(role.getRoleKey());
        if (ObjectUtils.isNotNull(queryRole) && queryRole.getRoleId() != roleId) {
            throw new StitchException("角色权限标识符不唯一");
        }
    }

    @Override
    public void checkRoleNameUnique(SysRole role) {
        long roleId = ObjectUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole queryRole = selectRoleByName(role.getRoleName());
        if (ObjectUtils.isNotNull(queryRole) && queryRole.getRoleId() != roleId) {
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
    public void checkDeleteOperation(Long roleId) {
        if (userRoleMapper.countUserAllocated(roleId) > 0) {
            SysRole sysRole = roleMapper.selectRoleById(roleId);
            throw new StitchException(String.format("角色[%s]已分配,无法删除", sysRole.getRoleName()));
        }
    }

    @Override
    @Transactional
    public int removeSysRoleBatch(Long[] roleIds) {
        //删除角色菜单关联信息
        roleMenuMapper.deleteAuthorizedMenus(roleIds);
        //删除角色部门关联信息
        roleDeptMapper.removeAllAllocatedDept(roleIds);
        return roleMapper.removeRoleBatch(roleIds);
    }

    @Override
    public int updateSysRole(SysRole role) {
        roleMapper.updateRole(role);
        roleMenuMapper.deleteAuthorizedMenu(role.getRoleId());
        return saveRoleMenu(role);
    }

    @Override
    @Transactional
    public int updateDataScope(SysRole role) {
        int result = 0;
        //清除原有数据权限关系
        Long[] roleIds = {role.getRoleId()};
        result += roleDeptMapper.removeAllAllocatedDept(roleIds);

        //保存新的数据权限关系
        if (role.getDataScope().equals(DataScopeConstant.DATA_SCOPE_CUSTOM) && ObjectUtils.isNotNull(role.getDeptIds()) && role.getDeptIds().length != 0) {
            result += roleDeptMapper.insertAllocatedDept(role.getRoleId(), role.getDeptIds());
        }

        //更新角色数据权限字段
        SysRole safeRole = new SysRole();
        safeRole.setRoleId(role.getRoleId());
        safeRole.setDataScope(role.getDataScope());
        result += roleMapper.updateRole(safeRole);
        return result;
    }

    @Override
    public int allocateUsers(Long roleId, Long[] userIds) {
        return userRoleMapper.insertRoleUsers(roleId, userIds);
    }

    @Override
    public int cancelAllocateUsers(Long roleId, Long[] userIds) {
        return userRoleMapper.removeRoleUsers(roleId, userIds);
    }

    @Override
    public SysRole selectSysRoleContainsDataScope(Long roleId) {
        SysRole sysRole = roleMapper.selectRoleById(roleId);
        //自定义数据权限.将部门列表范围
        if (ObjectUtils.isNotNull(sysRole) && sysRole.getDataScope().equals(DataScopeConstant.DATA_SCOPE_CUSTOM)) {
            sysRole.setDeptIds(roleDeptMapper.selectAllocatedDept(roleId));
        }
        return sysRole;
    }

    @Override
    @DataScope
    public List<SysRole> selectAllocatedRoles(SysRole sysRole) {
        return roleMapper.selectAllocatedRoles(sysRole);
    }

    @Override
    @DataScope
    public List<SysRole> selectUnallocatedRoles(SysRole sysRole) {
        return roleMapper.selectUnallocatedRoles(sysRole);
    }
}




