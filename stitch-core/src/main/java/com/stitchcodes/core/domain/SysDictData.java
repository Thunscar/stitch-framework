package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;

/**
 * 
 * @TableName sys_dict_data
 */
public class SysDictData extends BaseEntity implements Serializable {
    /**
     * 字典编码
     */
    private Long dictCode;

    /**
     * 字典排序
     */
    private Integer dictSort;

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 字典键值
     */
    private String dictValue;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 样式属性
     */
    private String cssClass;

    /**
     * 列表回显样式
     */
    private String listClass;

    /**
     * 是否默认(0非默认1默认)
     */
    private String isDefault;

    /**
     * 状态(0正常1停用)
     */
    private String status;

    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    public Long getDictCode() {
        return dictCode;
    }

    /**
     * 字典编码
     */
    public void setDictCode(Long dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 字典排序
     */
    public Integer getDictSort() {
        return dictSort;
    }

    /**
     * 字典排序
     */
    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    /**
     * 字典标签
     */
    public String getDictLabel() {
        return dictLabel;
    }

    /**
     * 字典标签
     */
    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    /**
     * 字典键值
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * 字典键值
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
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
     * 样式属性
     */
    public String getCssClass() {
        return cssClass;
    }

    /**
     * 样式属性
     */
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    /**
     * 列表回显样式
     */
    public String getListClass() {
        return listClass;
    }

    /**
     * 列表回显样式
     */
    public void setListClass(String listClass) {
        this.listClass = listClass;
    }

    /**
     * 是否默认(0非默认1默认)
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 是否默认(0非默认1默认)
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
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
        SysDictData other = (SysDictData) that;
        return (this.getDictCode() == null ? other.getDictCode() == null : this.getDictCode().equals(other.getDictCode()))
            && (this.getDictSort() == null ? other.getDictSort() == null : this.getDictSort().equals(other.getDictSort()))
            && (this.getDictLabel() == null ? other.getDictLabel() == null : this.getDictLabel().equals(other.getDictLabel()))
            && (this.getDictValue() == null ? other.getDictValue() == null : this.getDictValue().equals(other.getDictValue()))
            && (this.getDictType() == null ? other.getDictType() == null : this.getDictType().equals(other.getDictType()))
            && (this.getCssClass() == null ? other.getCssClass() == null : this.getCssClass().equals(other.getCssClass()))
            && (this.getListClass() == null ? other.getListClass() == null : this.getListClass().equals(other.getListClass()))
            && (this.getIsDefault() == null ? other.getIsDefault() == null : this.getIsDefault().equals(other.getIsDefault()))
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
        result = prime * result + ((getDictCode() == null) ? 0 : getDictCode().hashCode());
        result = prime * result + ((getDictSort() == null) ? 0 : getDictSort().hashCode());
        result = prime * result + ((getDictLabel() == null) ? 0 : getDictLabel().hashCode());
        result = prime * result + ((getDictValue() == null) ? 0 : getDictValue().hashCode());
        result = prime * result + ((getDictType() == null) ? 0 : getDictType().hashCode());
        result = prime * result + ((getCssClass() == null) ? 0 : getCssClass().hashCode());
        result = prime * result + ((getListClass() == null) ? 0 : getListClass().hashCode());
        result = prime * result + ((getIsDefault() == null) ? 0 : getIsDefault().hashCode());
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
        sb.append(", dictCode=").append(dictCode);
        sb.append(", dictSort=").append(dictSort);
        sb.append(", dictLabel=").append(dictLabel);
        sb.append(", dictValue=").append(dictValue);
        sb.append(", dictType=").append(dictType);
        sb.append(", cssClass=").append(cssClass);
        sb.append(", listClass=").append(listClass);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}