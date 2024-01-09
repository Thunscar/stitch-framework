package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.annotation.DataScope;
import com.stitchcodes.common.constant.UserConstants;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.utils.CollectionUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.SpringUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysRole;
import com.stitchcodes.core.mapper.SysRoleMapper;
import com.stitchcodes.core.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenwei
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
 * @createDate 2023-04-28 13:20:02
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

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
    public boolean createSysRole(SysRole role) {

        return false;
    }

    @Override
    public boolean checkRoleKeyUnique(SysRole role) {
        SysRole queryRole = selectRoleByKey(role.getRoleKey());
        if (ObjectUtils.isNotNull(queryRole) && queryRole.getRoleId().longValue() != role.getRoleId()) {
            return UserConstants.UN_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public SysRole selectRoleByKey(String roleKey) {
        return roleMapper.selectSysRoleByKey(roleKey);
    }

    @Override
    public boolean removeSysRole(Long roleId) {
        //TODO：需要同步删除角色关联表信息
        return roleMapper.removeRoleById(roleId) > 0;
    }

    @Override
    public boolean removeSysRoleBatch(Long[] roleIds) {
        //TODO：需要同步删除角色关联表信息
        return roleMapper.removeRoleBatch(roleIds) > 0;
    }

    @Override
    public boolean updateSysRole(SysRole role) {
        return roleMapper.updateRole(role) > 0;
    }
}




