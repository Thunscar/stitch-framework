package com.stitchcodes.core.domain;

import com.stitchcodes.common.annotation.Excel;
import com.stitchcodes.common.domain.BaseEntity;

import javax.validation.constraints.Size;

/**
 * 系统配置表
 *
 * @TableName sys_config
 */
public class SysConfig extends BaseEntity {

    private final Long serialVersionUID = 1L;

    /**
     * 参数主键
     */
    @Excel(name = "参数主键", cellType = Excel.ColumnType.NUMERIC)
    private Long configId;

    /**
     * 参数名称
     */
    @Excel(name = "参数名称", width = 24)
    private String configName;

    /**
     * 参数键
     */
    @Excel(name = "参数键名", width = 24)
    private String configKey;

    /**
     * 参数值
     */
    @Excel(name = "参数值")
    private String configValue;

    /**
     * 参数类型是否内置(Y内置N非内置)
     */
    @Excel(name = "是否内置", readConvertExp = "(Y是N否)")
    private String configType;

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


    /**
     * 参数主键
     */
    public Long getConfigId() {
        return configId;
    }

    /**
     * 参数主键
     */
    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    /**
     * 参数名称
     */
    @Size(min = 0, max = 100, message = "The Parameter Name Can not Exceed 100 Characters")
    public String getConfigName() {
        return configName;
    }

    /**
     * 参数名称
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * 参数键
     */
    @Size(min = 0, max = 100, message = "The Parameter Key Can not Exceed 100 Characters")
    public String getConfigKey() {
        return configKey;
    }

    /**
     * 参数键
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    /**
     * 参数值
     */
    @Size(min = 0, max = 500, message = "The Parameter Value Can not Exceed 100 Characters")
    public String getConfigValue() {
        return configValue;
    }

    /**
     * 参数值
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    /**
     * 参数类型是否内置(Y内置N非内置)
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * 参数类型是否内置(Y内置N非内置)
     */
    public void setConfigType(String configType) {
        this.configType = configType;
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
        SysConfig other = (SysConfig) that;
        return (this.getConfigId() == null ? other.getConfigId() == null : this.getConfigId().equals(other.getConfigId())) && (this.getConfigName() == null ? other.getConfigName() == null : this.getConfigName().equals(other.getConfigName())) && (this.getConfigKey() == null ? other.getConfigKey() == null : this.getConfigKey().equals(other.getConfigKey())) && (this.getConfigValue() == null ? other.getConfigValue() == null : this.getConfigValue().equals(other.getConfigValue())) && (this.getConfigType() == null ? other.getConfigType() == null : this.getConfigType().equals(other.getConfigType())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser())) && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime())) && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getConfigId() == null) ? 0 : getConfigId().hashCode());
        result = prime * result + ((getConfigName() == null) ? 0 : getConfigName().hashCode());
        result = prime * result + ((getConfigKey() == null) ? 0 : getConfigKey().hashCode());
        result = prime * result + ((getConfigValue() == null) ? 0 : getConfigValue().hashCode());
        result = prime * result + ((getConfigType() == null) ? 0 : getConfigType().hashCode());
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
        sb.append(", configId=").append(configId);
        sb.append(", configName=").append(configName);
        sb.append(", configKey=").append(configKey);
        sb.append(", configValue=").append(configValue);
        sb.append(", configType=").append(configType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}