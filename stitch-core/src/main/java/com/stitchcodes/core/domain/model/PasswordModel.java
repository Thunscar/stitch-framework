package com.stitchcodes.core.domain.model;

/**
 * @Author: stitch
 * @Date: 2024/4/1 22:07
 * @Description: 修改密码参数
 */
public class PasswordModel {
    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
