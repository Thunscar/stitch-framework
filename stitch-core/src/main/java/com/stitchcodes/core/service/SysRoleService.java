package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysRole;

import java.util.List;
import java.util.Set;

/**
 * @author chenwei
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service
 * @createDate 2023-04-28 13:20:02
 */
public interface SysRoleService {

    /**
     * 通过用户ID获取用户角色权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRolePermissionByUserId(Long userId);

    /**
     * 查询角色列表
     *
     * @param role 角色信息
     * @return 结果
     */
    List<SysRole> selectSysRoleList(SysRole role);


    /**
     * 查询角色信息
     *
     * @param roleId 角色ID
     * @return 结果
     */
    SysRole selectSysRoleById(Long roleId);

    /**
     * 检查角色的访问权限
     *
     * @param roleId 角色ID
     */
    void checkRoleDataScope(Long roleId);

    /**
     * 创建系统角色
     *
     * @param role 角色信息
     * @return 结果
     */
    boolean createSysRole(SysRole role);


    /**
     * 检查角色Key是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    boolean checkRoleKeyUnique(SysRole role);

    /**
     * 通过角色Key查询角色信息
     *
     * @param roleKey 角色Key
     * @return 角色信息
     */
    SysRole selectRoleByKey(String roleKey);

    /**
     * 移除某个角色信息
     *
     * @param roleId 角色ID
     * @return 结果
     */
    boolean removeSysRole(Long roleId);

    /**
     * 批量删除角色信息
     *
     * @param roleIds 角色信息列表
     * @return 结果
     */
    boolean removeSysRoleBatch(Long[] roleIds);


    /**
     * 更新角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    boolean updateSysRole(SysRole role);

}
