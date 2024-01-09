package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;

/**
 * 部门表
 * @TableName sys_dept
 */
public class SysDept extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    private Long deptId;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态(0正常1停用)
     */
    private String status;

    /**
     * 是否删除(0未删除1已删除)
     */
    private String isDelete;

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 主键
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 父级ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父级ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 祖级列表
     */
    public String getAncestors() {
        return ancestors;
    }

    /**
     * 祖级列表
     */
    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    /**
     * 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 显示顺序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 显示顺序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 负责人
     */
    public String getLeader() {
        return leader;
    }

    /**
     * 负责人
     */
    public void setLeader(String leader) {
        this.leader = leader;
    }

    /**
     * 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 部门状态(0正常1停用)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 部门状态(0正常1停用)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 是否删除(0未删除1已删除)
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除(0未删除1已删除)
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysDept other = (SysDept) that;
        return (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getAncestors() == null ? other.getAncestors() == null : this.getAncestors().equals(other.getAncestors()))
            && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
            && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
            && (this.getLeader() == null ? other.getLeader() == null : this.getLeader().equals(other.getLeader()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getAncestors() == null) ? 0 : getAncestors().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getLeader() == null) ? 0 : getLeader().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deptId=").append(deptId);
        sb.append(", parentId=").append(parentId);
        sb.append(", ancestors=").append(ancestors);
        sb.append(", deptName=").append(deptName);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", leader=").append(leader);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", status=").append(status);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}