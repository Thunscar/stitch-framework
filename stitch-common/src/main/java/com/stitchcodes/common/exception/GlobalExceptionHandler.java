package com.stitchcodes.common.exception;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.enums.ResultEnum;
import com.stitchcodes.common.utils.LogUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.stitchcodes.common.constant.OperateConstants.RUNTIME_EXCEPTION_HANDLER;
import static com.stitchcodes.common.constant.OperateConstants.STITCH_EXCEPTION_HANDLER;

/**
 * @Author: stitch
 * @Date: 2023/4/29 18:53
 * @Description: 全局异常捕获类
 */
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 运行时异常捕获，避免抛到前端
     *
     * @param e runtimeException
     * @return AjaxResult
     */
    @ExceptionHandler(value = RuntimeException.class)
    public AjaxResult runtimeExceptionHandler(RuntimeException e) {
        log.error(LogUtils.format(RUNTIME_EXCEPTION_HANDLER, "", ResultEnum.FAILED, "Catch Runtime Exceptions"), e);
        return AjaxResult.error("Operate Fail");
    }

    /**
     * 程序自定义异常捕获，将message返回
     *
     * @param e 自定义异常信息
     * @return AjaxResult
     */
    @ExceptionHandler(value = StitchException.class)
    public AjaxResult stitchExceptionHandler(StitchException e) {
        log.warn(LogUtils.format(STITCH_EXCEPTION_HANDLER, "", ResultEnum.FAILED, "Catch Business Exceptions"), e.getMessage());
        return ObjectUtils.isNull(e.getCode()) ? AjaxResult.error(e.getMessage()) : AjaxResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 自定义验证异常捕获
     *
     * @param e BindException
     * @return AjaxResult
     */
    @ExceptionHandler(value = BindException.class)
    public AjaxResult bindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return AjaxResult.error(message);
    }

}
