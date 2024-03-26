package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;

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
    private Long operateTime;

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
    public Long getOperateTime() {
        return operateTime;
    }

    /**
     * 操作时间
     */
    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
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
        SysOperateLog other = (SysOperateLog) that;
        return (this.getLogId() == null ? other.getLogId() == null : this.getLogId().equals(other.getLogId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getBusinessType() == null ? other.getBusinessType() == null : this.getBusinessType().equals(other.getBusinessType()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getRequestMethod() == null ? other.getRequestMethod() == null : this.getRequestMethod().equals(other.getRequestMethod()))
            && (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
            && (this.getOperateUser() == null ? other.getOperateUser() == null : this.getOperateUser().equals(other.getOperateUser()))
            && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
            && (this.getOperateUrl() == null ? other.getOperateUrl() == null : this.getOperateUrl().equals(other.getOperateUrl()))
            && (this.getOperateIp() == null ? other.getOperateIp() == null : this.getOperateIp().equals(other.getOperateIp()))
            && (this.getOperateLocation() == null ? other.getOperateLocation() == null : this.getOperateLocation().equals(other.getOperateLocation()))
            && (this.getOperateParam() == null ? other.getOperateParam() == null : this.getOperateParam().equals(other.getOperateParam()))
            && (this.getJsonResult() == null ? other.getJsonResult() == null : this.getJsonResult().equals(other.getJsonResult()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getErrorMsg() == null ? other.getErrorMsg() == null : this.getErrorMsg().equals(other.getErrorMsg()))
            && (this.getOperateTime() == null ? other.getOperateTime() == null : this.getOperateTime().equals(other.getOperateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogId() == null) ? 0 : getLogId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getBusinessType() == null) ? 0 : getBusinessType().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getRequestMethod() == null) ? 0 : getRequestMethod().hashCode());
        result = prime * result + ((getOperateType() == null) ? 0 : getOperateType().hashCode());
        result = prime * result + ((getOperateUser() == null) ? 0 : getOperateUser().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getOperateUrl() == null) ? 0 : getOperateUrl().hashCode());
        result = prime * result + ((getOperateIp() == null) ? 0 : getOperateIp().hashCode());
        result = prime * result + ((getOperateLocation() == null) ? 0 : getOperateLocation().hashCode());
        result = prime * result + ((getOperateParam() == null) ? 0 : getOperateParam().hashCode());
        result = prime * result + ((getJsonResult() == null) ? 0 : getJsonResult().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getErrorMsg() == null) ? 0 : getErrorMsg().hashCode());
        result = prime * result + ((getOperateTime() == null) ? 0 : getOperateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", title=").append(title);
        sb.append(", businessType=").append(businessType);
        sb.append(", method=").append(method);
        sb.append(", requestMethod=").append(requestMethod);
        sb.append(", operateType=").append(operateType);
        sb.append(", operateUser=").append(operateUser);
        sb.append(", deptName=").append(deptName);
        sb.append(", operateUrl=").append(operateUrl);
        sb.append(", operateIp=").append(operateIp);
        sb.append(", operateLocation=").append(operateLocation);
        sb.append(", operateParam=").append(operateParam);
        sb.append(", jsonResult=").append(jsonResult);
        sb.append(", status=").append(status);
        sb.append(", errorMsg=").append(errorMsg);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}