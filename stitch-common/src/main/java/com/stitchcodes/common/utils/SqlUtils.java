package com.stitchcodes.common.utils;

import com.stitchcodes.common.exception.StitchException;

/**
 * @Author: stitch
 * @Date: 2023/4/30 16:27
 * @Description:
 */
public class SqlUtils {

    /**
     * 防止OrderBy注入检查
     * 仅支持小写字母，大写字母，数字，空格，逗号，点
     */
    public static final String ORDER_BY_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    public static String escapeOrderBySql(String sql) {
        if (StringUtils.isNotEmpty(sql) && !isValidOrderBySql(sql)) {
            throw new StitchException("The SQL statement is invalidThe SQL statement is invalid");
        }
        return sql;
    }

    public static boolean isValidOrderBySql(String sql) {
        return sql.matches(ORDER_BY_PATTERN);
    }
}
