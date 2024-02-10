package com.stitchcodes.core.domain;

import com.stitchcodes.common.annotation.Excel;
import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    private String nickName;

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
    @Excel(name = "性别", readConvertExp = "(0男1女2未知)")
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
    @Excel(name = "账号状态", readConvertExp = "(0正常1停用)")
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


    private Long[] roleIds;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return Objects.equals(userId, sysUser.userId) && Objects.equals(deptId, sysUser.deptId) && Objects.equals(userName, sysUser.userName) && Objects.equals(nickName, sysUser.nickName) && Objects.equals(userType, sysUser.userType) && Objects.equals(email, sysUser.email) && Objects.equals(phone, sysUser.phone) && Objects.equals(sex, sysUser.sex) && Objects.equals(avatar, sysUser.avatar) && Objects.equals(password, sysUser.password) && Objects.equals(status, sysUser.status) && Objects.equals(isDelete, sysUser.isDelete) && Objects.equals(loginIp, sysUser.loginIp) && Objects.equals(loginTime, sysUser.loginTime) && Objects.equals(dept, sysUser.dept) && Objects.equals(roles, sysUser.roles) && Arrays.equals(roleIds, sysUser.roleIds) && Objects.equals(roleId, sysUser.roleId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userId, deptId, userName, nickName, userType, email, phone, sex, avatar, password, status, isDelete, loginIp, loginTime, dept, roles, roleId);
        result = 31 * result + Arrays.hashCode(roleIds);
        return result;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysUser{");
        sb.append("userId=").append(userId);
        sb.append(", deptId=").append(deptId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append(", userType='").append(userType).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", sex='").append(sex).append('\'');
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", isDelete='").append(isDelete).append('\'');
        sb.append(", loginIp='").append(loginIp).append('\'');
        sb.append(", loginTime=").append(loginTime);
        sb.append(", dept=").append(dept);
        sb.append(", roles=").append(roles);
        sb.append(", roleIds=").append(roleIds == null ? "null" : Arrays.asList(roleIds).toString());
        sb.append(", roleId=").append(roleId);
        sb.append('}');
        return sb.toString();
    }
}