package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;
import java.util.Objects;

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
     * 是否删除(0未删除1已删除)
     */
    private String isDelete;

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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysDictType that = (SysDictType) o;
        return Objects.equals(dictId, that.dictId) && Objects.equals(dictName, that.dictName) && Objects.equals(dictType, that.dictType) && Objects.equals(isDelete, that.isDelete) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dictId, dictName, dictType, isDelete, status);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysDictType{");
        sb.append("dictId=").append(dictId);
        sb.append(", dictName='").append(dictName).append('\'');
        sb.append(", dictType='").append(dictType).append('\'');
        sb.append(", isDelete='").append(isDelete).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
