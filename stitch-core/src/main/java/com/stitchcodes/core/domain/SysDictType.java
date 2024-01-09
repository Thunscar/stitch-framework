package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;

/**
 * 字典类型表
 * @TableName sys_dict_type
 */
public class SysDictType extends BaseEntity implements Serializable {
    /**
     * 字典ID
     */
    private Long dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态(0正常1停用)
     */
    private String status;

    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    public Long getDictId() {
        return dictId;
    }

    /**
     * 字典ID
     */
    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    /**
     * 字典名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 字典名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 字典类型
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * 字典类型
     */
    public void setDictType(String dictType) {
        this.dictType = dictType;
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
        SysDictType other = (SysDictType) that;
        return (this.getDictId() == null ? other.getDictId() == null : this.getDictId().equals(other.getDictId()))
            && (this.getDictName() == null ? other.getDictName() == null : this.getDictName().equals(other.getDictName()))
            && (this.getDictType() == null ? other.getDictType() == null : this.getDictType().equals(other.getDictType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDictId() == null) ? 0 : getDictId().hashCode());
        result = prime * result + ((getDictName() == null) ? 0 : getDictName().hashCode());
        result = prime * result + ((getDictType() == null) ? 0 : getDictType().hashCode());
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
        final StringBuffer sb = new StringBuffer("SysDictType{");
        sb.append("dictId=").append(dictId);
        sb.append(", dictName='").append(dictName).append('\'');
        sb.append(", dictType='").append(dictType).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}