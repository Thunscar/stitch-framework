package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysRole;

import java.util.List;
import java.util.Set;

/**
 * @author stitch
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
     * 查询角色信息包含功能权限信息
     *
     * @param roleId 角色ID
     * @return 结果
     */
    SysRole selectSysRoleContainsMenu(Long roleId);

    /**
     * 检查角色的访问权限
     *
     * @param roleId 角色ID
     */
    void checkRoleDataScope(Long roleId);

    /**
     * 创建系统角色并保存角色菜单授权信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int createSysRole(SysRole role);


    /**
     * 保存角色功能菜单授权信息
     *
     * @param role 角色信息
     * @return
     */
    int saveRoleMenu(SysRole role);


    /**
     * 检查角色Key是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    void checkRoleKeyUnique(SysRole role);


    /**
     * 检查角色名称是否唯一
     *
     * @param role 角色信息
     */
    void checkRoleNameUnique(SysRole role);


    /**
     * 通过角色Key查询角色信息
     *
     * @param roleKey 角色Key
     * @return 角色信息
     */
    SysRole selectRoleByKey(String roleKey);

    /**
     * 通过角色名称查找角色
     *
     * @param roleName 角色名称
     * @return
     */
    SysRole selectRoleByName(String roleName);


    /**
     * 检查是否可以被删除(已分配用户的角色不可被删除)
     *
     * @param role 角色信息
     */
    void checkDeleteOperation(Long roleId);

    /**
     * 批量删除角色信息
     *
     * @param roleIds 角色ID数组
     * @return 结果
     */
    int removeSysRoleBatch(Long[] roleIds);

    /**
     * 更新角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int updateSysRole(SysRole role);

    /**
     * 更新角色数据权限
     * @param role 角色信息
     * @return
     */
    int updateDataScope(SysRole role);


    /**
     * 将角色授权给用户
     *
     * @param roleId  角色ID
     * @param userIds 用户ID数组
     * @return
     */
    int allocateUsers(Long roleId, Long[] userIds);

    /**
     * 取消角色用户授权
     *
     * @param roleId  角色ID
     * @param userIds 用户ID数组
     * @return
     */
    int cancelAllocateUsers(Long roleId, Long[] userIds);


    /**
     * 查询角色信息包含部门数据权限
     *
     * @param roleId 角色信息
     * @return
     */
    SysRole selectSysRoleContainsDataScope(Long roleId);

    /**
     * 查询已分配给用户的角色列表
     * @param sysRole 角色信息（包含用户Id）
     * @return
     */
    List<SysRole> selectAllocatedRoles(SysRole sysRole);

    /**
     * 查询未分配给用户的角色列表
     * @param sysRole 角色信息（包含用户Id）
     * @return
     */
    List<SysRole> selectUnallocatedRoles(SysRole sysRole);

}
