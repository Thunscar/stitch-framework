package com.stitchcodes.core.mapper;

import org.apache.ibatis.annotations.Param;


/**
 * @author stitch
 * @description 针对表【sys_user_role(用户角色关联表)】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:11
 * @Entity generator.domain.SysUserRole
 */
public interface SysUserRoleMapper {

    /**
     * 添加角色用户关联信息
     *
     * @param roleId  角色ID
     * @param userIds 用户ID数组
     * @return
     */
    int insertRoleUsers(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);

    /**
     * 添加角色用户关联信息
     *
     * @param userId  用户ID
     * @param roleIds 角色ID数组
     * @return
     */
    int insertUserRoles(@Param("userId") Long userId, @Param("roleIds") Long[] roleIds);


    /**
     * 移除角色用户关联信息
     *
     * @param roleId  角色ID
     * @param userIds 用户ID
     * @return
     */
    int removeRoleUsers(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);

    /**
     * 移除用户角色关联信息
     *
     * @param userId  用户ID
     * @param roleIds 角色ID列表
     * @return
     */
    int removeUserRoles(@Param("userId") Long userId, @Param("roleIds") Long[] roleIds);


    /**
     * 角色已分配用户的数量
     *
     * @param roleId 角色ID
     * @return
     */
    int countUserAllocated(Long roleId);

    /**
     * 移除用户的角色关联信息
     * @param userIds 用户ID数组
     * @return
     */
    int removeByUserIds(@Param("userIds")Long[] userIds);

}




