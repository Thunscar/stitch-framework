package com.stitchcodes.common.utils;

import java.util.Date;

/**
 * @Author: stitch
 * @Date: 2023/5/9 19:24
 * @Description: 日期工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static Date getNowDate() {
        return new Date();
    }

    public static Date parseDate(String text) {
        try {
            if (StringUtils.isEmpty(text)) {
                return null;
            } else {
                return parseDate(text, parsePatterns);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
