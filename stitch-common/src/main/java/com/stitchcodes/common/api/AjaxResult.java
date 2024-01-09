package com.stitchcodes.common.api;

import com.stitchcodes.common.constant.HttpStatus;
import com.stitchcodes.common.utils.ObjectUtils;

import java.util.HashMap;

/**
 * @Author: stitch
 * @Date: 2023/4/27 19:55
 * @Description: 统一封装返回结果
 */
public class AjaxResult extends HashMap<String, Object> {

    /* status code */
    public static final String CODE_TAG = "code";
    /* message information*/
    public static final String MSG_TAG = "msg";
    /* response data */
    public static final String DATA_TAG = "data";

    public AjaxResult() {
    }


    public AjaxResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (ObjectUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }


    /* response success result */

    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    public static AjaxResult success(Object data) {
        return AjaxResult.success("Operate Success", data);
    }

    public static AjaxResult success() {
        return AjaxResult.success("Operate Success", null);
    }



    /* response warn result */

    public static AjaxResult warn(String msg, Object data) {
        return new AjaxResult(HttpStatus.WARN, msg, data);
    }

    public static AjaxResult warn(String msg) {
        return AjaxResult.warn(msg, null);
    }


    /* response error result */
    public static AjaxResult error(int code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }

    public static AjaxResult error(String msg, Object data) {
        return AjaxResult.error(HttpStatus.ERROR, msg, data);
    }

    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    public static AjaxResult error(int code, String message) {
        return AjaxResult.error(code, message, null);
    }

    public static AjaxResult error() {
        return AjaxResult.error("Operate error");
    }

    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
