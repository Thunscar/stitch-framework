package com.stitchcodes.common.enums;

/**
 * @Author: stitch
 * @Date: 2023/5/9 19:00
 * @Description: 用户状态
 */
public enum UserStatus {

    NORMAL("0", "正常"), BLOCKED("1", "停用");

    private String code;
    private String desc;

    UserStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
