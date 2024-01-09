package com.stitchcodes.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: stitch
 * @Date: 2023/4/28 18:39
 * @Description: 应用程序配置文件
 */
@Component
@ConfigurationProperties(prefix = "stitch")
public class StitchConfig {

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyRightYear;

    /**
     * 实例演示开关
     */
    private boolean demoEnabled;

    /**
     * 验证码类型
     */
    private String verifyCodeType;

    /**
     * 获取IP地址开关
     */
    private static boolean addressEnabled;

    /**
     * 文件存储路径
     */
    private String profile;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyRightYear() {
        return copyRightYear;
    }

    public void setCopyRightYear(String copyRightYear) {
        this.copyRightYear = copyRightYear;
    }

    public boolean isDemoEnabled() {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled) {
        this.demoEnabled = demoEnabled;
    }

    public String getVerifyCodeType() {
        return verifyCodeType;
    }

    public void setVerifyCodeType(String verifyCodeType) {
        this.verifyCodeType = verifyCodeType;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        this.addressEnabled = addressEnabled;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
