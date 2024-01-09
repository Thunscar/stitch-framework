package com.stitchcodes.common.utils;

import java.util.Collection;

/**
 * @Author: stitch
 * @Date: 2023/4/27 20:04
 * @Description: 字符串工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /*空字符串*/
    public static final String EMPTY = "";
    /*下划线*/
    public static final char UNDER_LINE = '_';

    /**
     * 格式化字符串
     *
     * @param template 字符串模版
     * @param params   参数列表
     * @return 格式化后的字符串
     */
    public static String format(String template, Object... params) {
        if (isEmpty(template) || null == params || params.length == 0) {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * 驼峰转下划线命名
     *
     * @param str 驼峰命名
     * @return 转换后的字符串
     */
    public static String toUnderSourceCase(String str) {
        if (isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else if (Character.isUpperCase(c)) {
                sb.append(UNDER_LINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式转化为驼峰
     *
     * @param str 下划线格式字符串
     * @return 转化后的字符串
     */
    public static String toCamelCase(String str) {
        if (isEmpty(str) || str.indexOf(UNDER_LINE) == -1) {
            return str;
        }
        str = str.toLowerCase();
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
                continue;
            }
            if (c == UNDER_LINE) {
                upperCase = true;
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 获取String参数不为空
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return 结果
     */
    public static String nvl(String str, String defaultValue) {
        return str == null ? defaultValue : str;
    }

    /**
     * 获取String参数不为空
     *
     * @param str 字符串
     * @return 结果
     */
    public static String nvl(String str) {
        return nvl(str, "");
    }

    /**
     * 是否包含一部分字符串
     *
     * @param c   字符串集合
     * @param str 字符串数据
     * @return 前者是否包含一部分后者
     */
    public static boolean containsAny(Collection<String> c, String... str) {
        if (CollectionUtils.isEmpty(c) || ObjectUtils.isEmpty(str)) {
            return false;
        }
        for (String s : str) {
            if (c.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
