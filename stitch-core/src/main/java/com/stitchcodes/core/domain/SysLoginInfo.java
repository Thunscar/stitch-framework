package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @TableName sys_login_info
 */
public class SysLoginInfo extends BaseEntity implements Serializable {
    /**
     * 信息ID
     */
    private Long infoId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 登录IP
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 登录浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 操作类型
     */
    private String operation;

    /**
     * 状态(0正常1停用)
     */
    private String status;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 登录时间
     */
    private Date loginTime;

    private static final long serialVersionUID = 1L;

    /**
     * 信息ID
     */
    public Long getInfoId() {
        return infoId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * 信息ID
     */
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    /**
     * 用户账号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户账号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 登录IP
     */
    public String getIpaddr() {
        return ipaddr;
    }

    /**
     * 登录IP
     */
    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    /**
     * 登录地点
     */
    public String getLoginLocation() {
        return loginLocation;
    }

    /**
     * 登录地点
     */
    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    /**
     * 登录浏览器
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 登录浏览器
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * 操作系统
     */
    public String getOs() {
        return os;
    }

    /**
     * 操作系统
     */
    public void setOs(String os) {
        this.os = os;
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

    /**
     * 提示消息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 提示消息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysLoginInfo that = (SysLoginInfo) o;
        return Objects.equals(infoId, that.infoId) && Objects.equals(userName, that.userName) && Objects.equals(ipaddr, that.ipaddr) && Objects.equals(loginLocation, that.loginLocation) && Objects.equals(browser, that.browser) && Objects.equals(os, that.os) && Objects.equals(operation, that.operation) && Objects.equals(status, that.status) && Objects.equals(msg, that.msg) && Objects.equals(loginTime, that.loginTime);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInfoId() == null) ? 0 : getInfoId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getIpaddr() == null) ? 0 : getIpaddr().hashCode());
        result = prime * result + ((getLoginLocation() == null) ? 0 : getLoginLocation().hashCode());
        result = prime * result + ((getBrowser() == null) ? 0 : getBrowser().hashCode());
        result = prime * result + ((getOs() == null) ? 0 : getOs().hashCode());
        result = prime * result + ((getOperation() == null) ? 0 : getOperation().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", infoId=").append(infoId);
        sb.append(", userName=").append(userName);
        sb.append(", ipaddr=").append(ipaddr);
        sb.append(", loginLocation=").append(loginLocation);
        sb.append(", browser=").append(browser);
        sb.append(", os=").append(os);
        sb.append(", operation=").append(operation);
        sb.append(", status=").append(status);
        sb.append(", msg=").append(msg);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}