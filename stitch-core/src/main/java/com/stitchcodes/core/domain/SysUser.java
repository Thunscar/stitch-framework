package com.stitchcodes.core.domain;

import com.stitchcodes.common.annotation.Excel;
import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户表
 *
 * @TableName sys_user
 */
public class SysUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户账号
     */
    @Excel(name = "用户名")
    private String userName;

    /**
     * 用户昵称
     */
    @Excel(name = "昵称")
    private String nikeName;

    /**
     * 用户类型(00系统用户)
     */
    private String userType;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 电话号码
     */
    @Excel(name = "电话")
    private String phone;

    /**
     * 用户性别(0男1女2未知)
     */
    @Excel(name = "性别",readConvertExp = "(0男1女2未知)")
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号状态(0正常1停用)
     */
    @Excel(name = "账号状态",readConvertExp = "(0正常1停用)")
    private String status;

    /**
     * 是否删除
     */
    private String isDelete;

    /**
     * 最后登录IP
     */
    @Excel(name = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Excel(name = "最后登录时间")
    private Date loginTime;

    /**
     * 所属部门信息
     */
    private SysDept dept;

    /**
     * 用户角色信息
     */
    private List<SysRole> roles;

    private Long roleId;

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    /**
     * 判断用户是否是管理员
     */
    public boolean isAdmin() {
        return this.userId != null && 1L == this.userId;
    }

    /**
     * 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 部门ID
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 部门ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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
     * 用户昵称
     */
    public String getNikeName() {
        return nikeName;
    }

    /**
     * 用户昵称
     */
    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    /**
     * 用户类型(00系统用户)
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 用户类型(00系统用户)
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 用户性别(0男1女2未知)
     */
    public String getSex() {
        return sex;
    }

    /**
     * 用户性别(0男1女2未知)
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 账号状态(0正常1停用)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 账号状态(0正常1停用)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 是否删除
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 最后登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 最后登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 最后登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 最后登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
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
        SysUser other = (SysUser) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId())) && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId())) && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName())) && (this.getNikeName() == null ? other.getNikeName() == null : this.getNikeName().equals(other.getNikeName())) && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType())) && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail())) && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone())) && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex())) && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar())) && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete())) && (this.getLoginIp() == null ? other.getLoginIp() == null : this.getLoginIp().equals(other.getLoginIp())) && (this.getLoginTime() == null ? other.getLoginTime() == null : this.getLoginTime().equals(other.getLoginTime())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser())) && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime())) && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getNikeName() == null) ? 0 : getNikeName().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getLoginIp() == null) ? 0 : getLoginIp().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", deptId=").append(deptId);
        sb.append(", userName=").append(userName);
        sb.append(", nikeName=").append(nikeName);
        sb.append(", userType=").append(userType);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", sex=").append(sex);
        sb.append(", avatar=").append(avatar);
        sb.append(", password=").append(password);
        sb.append(", status=").append(status);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}