package com.stitchcodes.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 系统操作日志表
 * @TableName sys_operate_log
 */
public class SysOperateLog extends BaseEntity implements Serializable {
    /**
     * 日志主键
     */
    private Long logId;

    /**
     * 模块标题
     */
    private String title;

    /**
     * 业务类型(0其他1新增2修改3删除)
     */
    private String businessType;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作类型(0其他1后台用户2手机端用户)
     */
    private String operateType;

    /**
     * 操作人
     */
    private String operateUser;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 操作URL
     */
    private String operateUrl;

    /**
     * 操作IP
     */
    private String operateIp;

    /**
     * 操作位置
     */
    private String operateLocation;

    /**
     * 操作参数
     */
    private String operateParam;

    /**
     * 返回结果
     */
    private String jsonResult;

    /**
     * 状态(0正常1异常)
     */
    private String status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateTime;

    /**
     * 消耗时间
     */
    private Long costTime;

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * 日志主键
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * 模块标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 模块标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 业务类型(0其他1新增2修改3删除)
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 业务类型(0其他1新增2修改3删除)
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * 方法名称
     */
    public String getMethod() {
        return method;
    }

    /**
     * 方法名称
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 请求方式
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * 请求方式
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * 操作类型(0其他1后台用户2手机端用户)
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 操作类型(0其他1后台用户2手机端用户)
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 操作人
     */
    public String getOperateUser() {
        return operateUser;
    }

    /**
     * 操作人
     */
    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
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
     * 操作URL
     */
    public String getOperateUrl() {
        return operateUrl;
    }

    /**
     * 操作URL
     */
    public void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl;
    }

    /**
     * 操作IP
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * 操作IP
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    /**
     * 操作位置
     */
    public String getOperateLocation() {
        return operateLocation;
    }

    /**
     * 操作位置
     */
    public void setOperateLocation(String operateLocation) {
        this.operateLocation = operateLocation;
    }

    /**
     * 操作参数
     */
    public String getOperateParam() {
        return operateParam;
    }

    /**
     * 操作参数
     */
    public void setOperateParam(String operateParam) {
        this.operateParam = operateParam;
    }

    /**
     * 返回结果
     */
    public String getJsonResult() {
        return jsonResult;
    }

    /**
     * 返回结果
     */
    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    /**
     * 状态(0正常1异常)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态(0正常1异常)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 错误消息
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 错误消息
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 操作时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysOperateLog that = (SysOperateLog) o;
        return Objects.equals(logId, that.logId) && Objects.equals(title, that.title) && Objects.equals(businessType, that.businessType) && Objects.equals(method, that.method) && Objects.equals(requestMethod, that.requestMethod) && Objects.equals(operateType, that.operateType) && Objects.equals(operateUser, that.operateUser) && Objects.equals(deptName, that.deptName) && Objects.equals(operateUrl, that.operateUrl) && Objects.equals(operateIp, that.operateIp) && Objects.equals(operateLocation, that.operateLocation) && Objects.equals(operateParam, that.operateParam) && Objects.equals(jsonResult, that.jsonResult) && Objects.equals(status, that.status) && Objects.equals(errorMsg, that.errorMsg) && Objects.equals(operateTime, that.operateTime) && Objects.equals(costTime, that.costTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, title, businessType, method, requestMethod, operateType, operateUser, deptName, operateUrl, operateIp, operateLocation, operateParam, jsonResult, status, errorMsg, operateTime, costTime);
    }
}