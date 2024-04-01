package com.stitchcodes.common.utils.encode;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: stitch
 * @Date: 2023/5/7 21:45
 * @Description: 安全工具类(密码加密)
 */
@Component
public class StitchPasswordEncoder implements PasswordEncoder {

    /*盐*/
    private static final String FIX_SALT = "stitch:";

    /**
     * 生成盐值
     *
     * @param origin 原始信息
     * @return 盐值
     */
    private String generateSalt(CharSequence origin) {
        StringBuilder sb = new StringBuilder();
        if (origin.length() >= 1) {
            sb.append(origin.charAt(0));
            sb.append(origin.charAt(origin.length() - 1));
        }
        return sb.toString();
    }

    /**
     * 密码加密
     *
     * @param rawPassword 密码
     * @return 加密后的密码
     */
    @Override
    public String encode(CharSequence rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(FIX_SALT + rawPassword + generateSalt(rawPassword));
    }

    /**
     * 判断密码是否匹配
     *
     * @param rawPassword     密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(FIX_SALT + rawPassword + generateSalt(rawPassword), encodedPassword);
    }

}
