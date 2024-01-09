package com.stitchcodes.common.exception;

/**
 * @Author: stitch
 * @Date: 2023/4/29 18:47
 * @Description:
 */
public final class StitchException extends RuntimeException {

    /*错误码*/
    private Integer code;

    /*错误消息*/
    private String message;

    /*详细错误消息*/
    private String detailMessage;

    public StitchException() {
    }

    public StitchException(String message) {
        this.message = message;
    }

    public StitchException(String message, int code) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
