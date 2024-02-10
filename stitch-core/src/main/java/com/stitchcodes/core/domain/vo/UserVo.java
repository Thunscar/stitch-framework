package com.stitchcodes.core.domain.vo;

import com.stitchcodes.core.domain.SysUser;

import java.util.Objects;

/**
 * @Author: stitch
 * @Date: 2023/5/15 10:51
 * @Description: 用户视图对象(用于将用户信息返回给前端)
 */
public class UserVo {

    private Long userId;

    private String username;

    private String nickName;

    private String email;

    private String phone;

    private String sex;

    private String avatar;

    public UserVo() {
    }

    public UserVo(SysUser user) {
        this.userId = user.getUserId();
        this.username = user.getUserName();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.sex = user.getSex();
        this.avatar = user.getAvatar();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserVo{");
        sb.append("userId=").append(userId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", sex='").append(sex).append('\'');
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVo userVo = (UserVo) o;
        return Objects.equals(userId, userVo.userId) && Objects.equals(username, userVo.username) && Objects.equals(nickName, userVo.nickName) && Objects.equals(email, userVo.email) && Objects.equals(phone, userVo.phone) && Objects.equals(sex, userVo.sex) && Objects.equals(avatar, userVo.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, nickName, email, phone, sex, avatar);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
