package com.stitchcodes.core.mapper;

import com.stitchcodes.core.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
* @author stitch
* @description 针对表【sys_user_role(用户角色关联表)】的数据库操作Mapper
* @createDate 2023-04-29 14:12:11
* @Entity generator.domain.SysUserRole
*/
public interface SysUserRoleMapper {

    /**
     * 添加角色用户关联信息
     * @param roleId 角色ID
     * @param userIds 用户ID
     * @return
     */
    int insertUserRole(@Param("roleId") Long roleId,@Param("userIds") Long[] userIds);


    /**
     * 移除角色用户关联信息
     * @param roleId 角色ID
     * @param userIds 用户ID
     * @return
     */
    int removeUserRole(@Param("roleId") Long roleId,@Param("userIds") Long[] userIds);


    /**
     * 角色已分配用户的数量
     * @param roleId 角色ID
     * @return
     */
    int countUserAllocated(Long roleId);

}




