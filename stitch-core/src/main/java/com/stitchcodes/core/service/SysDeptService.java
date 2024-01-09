package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysDept;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_dept(部门表)】的数据库操作Service
 * @createDate 2023-04-28 13:20:02
 */
public interface SysDeptService {


    /**
     * 查询部门列表
     *
     * @param dept 部门信息(查询条件)
     * @return 结果
     */
    List<SysDept> selectSysDeptList(SysDept dept);

    /**
     * 检查当前用户是否具有该部门数据权限
     *
     * @param deptId 部门ID
     */
    void checkDeptDataScope(Long deptId);

    /**
     * 根据部门ID查询部门信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    SysDept selectSysDeptById(Long deptId);

    /**
     * 检查部门名称是否唯一
     *
     * @param dept 部门信息
     * @return
     */
    boolean checkDeptNameUnique(SysDept dept);

    /**
     * 创建系统部门
     *
     * @param dept 部门实体
     * @return 结果
     */
    boolean createSysDept(SysDept dept);

    /**
     * 检查父级ID合法性
     *
     * @param dept 部门信息
     */
    void checkParentIdValid(SysDept dept);

    /**
     * 更新部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    boolean updateSysDept(SysDept dept);

    /**
     * 是否有子部门
     *
     * @param deptId 部门ID
     * @return 结果
     */
    boolean hasChildrenDept(Long deptId);

    /**
     * 是否有用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    boolean hasUserForDept(Long deptId);

    /**
     * 移除部门
     *
     * @param deptId 部门ID
     * @return 结果
     */
    boolean removeSysDept(Long deptId);

}
