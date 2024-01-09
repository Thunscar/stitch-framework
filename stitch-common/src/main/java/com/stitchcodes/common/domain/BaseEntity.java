package com.stitchcodes.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.stitchcodes.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: stitch
 * @Date: 2023/4/28 23:36
 * @Description:
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    @JsonIgnore
    private String searchValue;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "创建时间", width = 20)
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "更新时间", width = 20)
    private Date updateTime;

    /**
     * 备注
     */
    @Excel(name = "备注", width = 100)
    private String remark;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params = new HashMap<>();

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            return new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
