package com.stitchcodes.core.mapper;

import com.stitchcodes.core.domain.SysDept;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_dept(部门表)】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:10
 * @Entity generator.domain.SysDept
 */
public interface SysDeptMapper {

    /**
     * 查询部门列表
     *
     * @param sysDept 系统部门列表
     * @return 结果
     */
    List<SysDept> selectDeptList(SysDept sysDept);

    /**
     * 通过角色查询部门ID列表
     *
     * @param roleId 角色ID
     * @return 结果
     */
    List<Long> selectDeptListByRoleId(Long roleId);

    /**
     * 通过ID查询部门信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    SysDept selectDeptById(Long deptId);

    /**
     * 检查部门是否有用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int checkDeptHasUser(Long deptId);

    /**
     * 检查部门是否有子部门
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int hasChildrenDept(Long deptId);

    /**
     * 查询部门下的子部门列表
     *
     * @param deptId 部门ID
     * @return 结果
     */
    List<SysDept> selectChildrenByDeptId(Long deptId);

    /**
     * 查询子部门数量
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int selectNormalChildrenByDeptId(Long deptId);

    /**
     * 检查部门名称唯一性
     *
     * @param deptId   部门ID
     * @param parentId 所属父级部门ID
     * @return 结果1
     */
    SysDept checkDeptNameUnique(SysDept dept);

    /**
     * 新增一个部门
     *
     * @param sysDept 部门信息
     * @return 结果
     */
    int insertDept(SysDept sysDept);

    /**
     * 更新部门信息
     *
     * @param sysDept 部门信息
     * @return 结果
     */
    int updateDept(SysDept sysDept);

    /**
     * 删除部门信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int deleteDept(Long deptId);

    /**
     * 查询无效的部门父级ID
     *
     * @param dept 部门信息
     * @return 结果
     */
    List<Long> selectUnValidParentId(SysDept dept);

}




