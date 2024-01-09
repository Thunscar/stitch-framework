package com.stitchcodes.common.utils;

import com.stitchcodes.common.enums.ResultEnum;

/**
 * @Author: stitch
 * @Date: 2023/4/29 19:10
 * @Description:
 */
public class LogUtils {

    /**
     * 格式化日志
     *
     * @param operate 操作类型
     * @param target  操作目标
     * @param result  操作结果
     * @param message 操作消息
     * @return 格式化后的日志字符串
     */
    public static String format(String operate, String target, ResultEnum result, String message) {
        StringBuffer sb = new StringBuffer();
        sb.append("Operate:[");
        sb.append(operate);
        sb.append("]");
        sb.append("Target:[");
        sb.append(target);
        sb.append("]");
        sb.append("Result:[");
        sb.append(result.toString());
        sb.append("]");
        sb.append("Message:[");
        sb.append(message);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 给消息添加[]符号
     *
     * @param msg 消息体内容
     * @return 添加[]]符号后的消息
     */
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
