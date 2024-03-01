package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;
import java.util.Objects;

/**
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
     * 是否删除
     */
    private String isDelete;

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
        SysDictData that = (SysDictData) o;
        return Objects.equals(dictCode, that.dictCode) && Objects.equals(dictSort, that.dictSort) && Objects.equals(dictLabel, that.dictLabel) && Objects.equals(dictValue, that.dictValue) && Objects.equals(dictType, that.dictType) && Objects.equals(cssClass, that.cssClass) && Objects.equals(listClass, that.listClass) && Objects.equals(isDefault, that.isDefault) && Objects.equals(isDelete, that.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dictCode, dictSort, dictLabel, dictValue, dictType, cssClass, listClass, isDefault, isDelete);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysDictData{");
        sb.append("dictCode=").append(dictCode);
        sb.append(", dictSort=").append(dictSort);
        sb.append(", dictLabel='").append(dictLabel).append('\'');
        sb.append(", dictValue='").append(dictValue).append('\'');
        sb.append(", dictType='").append(dictType).append('\'');
        sb.append(", cssClass='").append(cssClass).append('\'');
        sb.append(", listClass='").append(listClass).append('\'');
        sb.append(", isDefault='").append(isDefault).append('\'');
        sb.append(", isDelete='").append(isDelete).append('\'');
        sb.append('}');
        return sb.toString();
    }
}