package com.stitchcodes.core.mapper;

import com.stitchcodes.core.domain.SysRole;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:11
 * @Entity generator.domain.SysRole
 */
public interface SysRoleMapper {


    /**
     * 查询用户角色信息
     *
     * @param userId 用户ID
     * @return 用户角色信息
     */
    List<SysRole> selectRoleByUserId(Long userId);

    /**
     * 查询角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    List<SysRole> selectRoleList(SysRole role);

    /**
     * 通过ID查询角色信息
     *
     * @param roleId 角色ID
     * @return 结果
     */
    SysRole selectRoleById(Long roleId);

    /**
     * 移除某个角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int removeRoleById(Long roleId);

    /**
     * 批量移除某个角色
     *
     * @param roleIds 角色ID数据
     * @return 结果
     */
    int removeRoleBatch(Long[] roleIds);

    /**
     * 更新角色基本信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(SysRole role);

    /**
     * 插入角色数据
     *
     * @param role 角色ID
     * @return 结果
     */
    int insertRole(SysRole role);

    /**
     * 根据角色权限标识符查找角色
     *
     * @param roleKey 角色Key
     * @return 结果
     */
    SysRole selectSysRoleByKey(String roleKey);


    /**
     * 根据角色名称查找角色
     *
     * @param roleName 角色名称
     * @return
     */
    SysRole selectSysRoleByName(String roleName);

    /**
     * 查询已分配给用户的角色列表
     *
     * @param sysRole 角色信息
     * @return
     */
    List<SysRole> selectAllocatedRoles(SysRole sysRole);

    /**
     * 查询未分配给用户的角色列表
     *
     * @param sysRole 角色信息
     * @return
     */
    List<SysRole> selectUnallocatedRoles(SysRole sysRole);

}




