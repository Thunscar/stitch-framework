package com.stitchcodes.common.domain.model;

/**
 * @Author: stitch
 * @Date: 2023/5/6 10:56
 * @Description:
 */
public class LoginBody {

    private String username;

    private String password;

    private String uuid;

    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
