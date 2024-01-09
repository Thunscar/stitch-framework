package com.stitchcodes.common.utils;

/**
 * @Author: stitch
 * @Date: 2023/4/29 13:04
 * @Description:
 */
public class ConvertUtils {

    /**
     * 将对象转化为字符串
     *
     * @param obj          需要转换对象
     * @param defaultValue 默认值
     * @return 转换后的结果
     */
    public static String toString(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return obj.toString();
    }


    /**
     * 将对象转换为字符串，默认值为null
     *
     * @param obj 对象
     * @return 转换后的字符串
     */
    public static String toString(Object obj) {
        return toString(obj, null);
    }

    /**
     * 将对象转化为字符
     *
     * @param obj          对象实体
     * @param defaultValue 默认值
     * @return 转化后的字符
     */
    public static Character toChar(Object obj, Character defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Character) {
            return (Character) obj;
        }
        String toString = toString(obj, null);
        return StringUtils.isEmpty(toString) ? defaultValue : toString.charAt(0);
    }

    /**
     * 将对象转化为字符，默认值为null
     *
     * @param obj 对象
     * @return 转换后的字符
     */
    public static Character toChar(Object obj) {
        return toChar(obj, null);
    }

    public static Boolean toBool(Object obj, Boolean defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        String string = toString(obj, null);
        if (StringUtils.isEmpty(string)) {
            return defaultValue;
        }
        switch (string) {
            case "true":
                return true;
            case "false":
                return false;
            default:
                return defaultValue;
        }
    }

    /**
     * 转换为bool值
     *
     * @param obj 请求对象
     * @return
     */
    public static Boolean toBool(Object obj) {
        return toBool(obj, null);
    }

    /**
     * 转化为Integer数据
     *
     * @param obj          对象
     * @param defaultValue 默认值
     * @return 转换后的数据
     */
    public static Integer toInt(Object obj, Integer defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        String strValue = toString(obj, null);
        if (StringUtils.isEmpty(strValue)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(strValue);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将对象转化为int 默认值为null
     *
     * @param obj 对象
     * @return 转换后的数据
     */
    public static Integer toInt(Object obj) {
        return toInt(obj, null);
    }

    /**
     * 将字符串按照','分割为字符串数组
     *
     * @param str 字符串
     * @return 字符串数组
     */
    public static String[] toStrArray(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return str.split(",");
    }
}
