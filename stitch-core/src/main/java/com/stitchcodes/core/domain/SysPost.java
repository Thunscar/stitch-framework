package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;

/**
 * 岗位信息表
 *
 * @TableName sys_post
 */
public class SysPost extends BaseEntity implements Serializable {
    /**
     * 岗位ID
     */
    private Long postId;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 显示排序
     */
    private Integer postSort;

    /**
     * 状态(0正常1停用)
     */
    private String status;

    /**
     * 是否删除
     */
    private String isDelete;


    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * 岗位ID
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * 岗位编码
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 岗位编码
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * 岗位名称
     */
    public String getPostName() {
        return postName;
    }

    /**
     * 岗位名称
     */
    public void setPostName(String postName) {
        this.postName = postName;
    }

    /**
     * 显示排序
     */
    public Integer getPostSort() {
        return postSort;
    }

    /**
     * 显示排序
     */
    public void setPostSort(Integer postSort) {
        this.postSort = postSort;
    }

    /**
     * 状态(0正常1停用)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态(0正常1停用)
     */
    public void setStatus(String status) {
        this.status = status;
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
        SysPost other = (SysPost) that;
        return (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId())) && (this.getPostCode() == null ? other.getPostCode() == null : this.getPostCode().equals(other.getPostCode())) && (this.getPostName() == null ? other.getPostName() == null : this.getPostName().equals(other.getPostName())) && (this.getPostSort() == null ? other.getPostSort() == null : this.getPostSort().equals(other.getPostSort())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser())) && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime())) && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getPostCode() == null) ? 0 : getPostCode().hashCode());
        result = prime * result + ((getPostName() == null) ? 0 : getPostName().hashCode());
        result = prime * result + ((getPostSort() == null) ? 0 : getPostSort().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", postId=").append(postId);
        sb.append(", postCode=").append(postCode);
        sb.append(", postName=").append(postName);
        sb.append(", postSort=").append(postSort);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}