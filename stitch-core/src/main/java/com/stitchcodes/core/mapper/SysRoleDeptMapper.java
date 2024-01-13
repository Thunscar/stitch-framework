package com.stitchcodes.core.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author stitch
 * @description 针对表【sys_role_dept(角色部门关联表)】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:11
 * @Entity generator.domain.SysRoleDept
 */
public interface SysRoleDeptMapper {


    /**
     * 查询已分配数据权限的部门
     *
     * @param roleId 角色ID
     * @return
     */
    Long[] selectAllocatedDept(Long roleId);


    /**
     * 为角色添加部门数据权限
     *
     * @param roleId  角色ID
     * @param deptIds 部门ID
     * @return
     */
    int insertAllocatedDept(@Param("roleId") Long roleId, @Param("deptIds") Long[] deptIds);

    /**
     * 移除角色的部门数据权限
     *
     * @param roleId  角色ID
     * @param deptIds 部门ID
     * @return
     */
    int removeAllocatedDept(@Param("roleId") Long roleId);
}




