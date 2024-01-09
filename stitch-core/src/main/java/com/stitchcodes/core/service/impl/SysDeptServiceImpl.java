package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.annotation.DataScope;
import com.stitchcodes.common.constant.UserConstants;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.SpringUtils;
import com.stitchcodes.core.domain.SysDept;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.mapper.SysDeptMapper;
import com.stitchcodes.core.service.SysDeptService;
import com.stitchcodes.core.utils.AuthUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_dept(部门表)】的数据库操作Service实现
 * @createDate 2023-04-28 13:20:02
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Resource
    private SysDeptMapper deptMapper;

    @Override
    @DataScope(deptAlias = "d")
    public List<SysDept> selectSysDeptList(SysDept dept) {
        return deptMapper.selectDeptList(dept);
    }

    @Override
    public void checkDeptDataScope(Long deptId) {
        SysUser user = AuthUtils.getLoginUser().getUser();
        if (!user.isAdmin()) {
            //非管理员
            SysDept sysDept = new SysDept();
            sysDept.setDeptId(deptId);
            List<SysDept> sysDeptList = SpringUtils.getAopProxy(this).selectSysDeptList(sysDept);
            if (sysDeptList.isEmpty()) {
                throw new StitchException("您无访问该机构的权限");
            }
        }
    }

    @Override
    public SysDept selectSysDeptById(Long deptId) {
        return deptMapper.selectDeptById(deptId);
    }

    @Override
    public boolean checkDeptNameUnique(SysDept dept) {
        SysDept sysDept = deptMapper.checkDeptNameUnique(dept);
        if (ObjectUtils.isNotNull(sysDept) && (ObjectUtils.isNull(dept.getDeptId()) || sysDept.getDeptId().longValue() != dept.getDeptId())) {
            return UserConstants.UN_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean createSysDept(SysDept dept) {
        //检查父级部门状态是否正常
        SysDept parentDept = deptMapper.selectDeptById(dept.getParentId());
        if (ObjectUtils.isNull(parentDept) && !UserConstants.ROOT_DEPT_PARENT.equals(dept.getParentId())) {
            throw new StitchException("父级部门不存在,不可创建部门");
        }
        if (ObjectUtils.isNotNull(parentDept) && UserConstants.STATUS_BLOCK.equals(parentDept.getStatus())) {
            throw new StitchException("父级部门已停用,不可创建部门");
        }
        if (ObjectUtils.isNotNull(parentDept)) {
            //维护祖籍部门列表
            dept.setAncestors(parentDept.getAncestors() + "," + parentDept.getDeptId());
        } else {
            dept.setAncestors(UserConstants.ROOT_DEPT_PARENT.toString());
        }
        return deptMapper.insertDept(dept) > 0;
    }

    @Override
    public void checkParentIdValid(SysDept dept) {
        List<Long> unValidParentIds = deptMapper.selectUnValidParentId(dept);
        if (unValidParentIds.contains(dept.getParentId())) {
            throw new StitchException("不合法的父级部门,请重新选择");
        }
    }

    @Override
    public boolean updateSysDept(SysDept dept) {
        if (UserConstants.ROOT_DEPT_PARENT.equals(dept.getParentId())) {
            dept.setAncestors(UserConstants.ROOT_DEPT_PARENT.toString());
        } else {
            SysDept parentDept = deptMapper.selectDeptById(dept.getParentId());
            if (ObjectUtils.isNull(parentDept)) {
                throw new StitchException("父级部门不存在,不可修改部门");
            }
            dept.setAncestors(parentDept.getAncestors() + "," + parentDept.getDeptId());
        }

        return deptMapper.updateDept(dept) > 0;
    }

    @Override
    public boolean hasChildrenDept(Long deptId) {
        return deptMapper.hasChildrenDept(deptId) > 0;
    }

    @Override
    public boolean hasUserForDept(Long deptId) {
        return deptMapper.checkDeptHasUser(deptId) > 0;
    }

    @Override
    public boolean removeSysDept(Long deptId) {
        return deptMapper.deleteDept(deptId) > 0;
    }


}




