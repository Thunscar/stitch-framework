package com.stitchcodes.core.domain.model;

import com.stitchcodes.core.domain.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @Author: stitch
 * @Date: 2023/5/7 20:08
 * @Description: Security登录用户
 */
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * token令牌
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * token过期时间
     */
    private Long expireTime;

    /**
     * 登录ip地址
     */
    private String ipaddr;

    /**
     * 登录地理位置
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
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private SysUser user;

    public LoginUser() {
    }

    public LoginUser(SysUser user, Set<String> permissions) {
        this.permissions = permissions;
        this.user = user;
    }

    public LoginUser(Long userId, Long deptId, SysUser user, Set<String> permissions) {
        this.userId = userId;
        this.deptId = deptId;
        this.user = user;
        this.permissions = permissions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }


    /**
     * 获取用户密码
     *
     * @return 用户密码
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 获取用户账号
     *
     * @return 用户账号
     */
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 账号是否未过期
     *
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否未锁定
     *
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证（密码）是否过期
     *
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     *
     * @return true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 获取用户权限
     *
     * @return null
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
