package com.stitchcodes.common.annotation;

import com.stitchcodes.common.enums.BusinessType;
import com.stitchcodes.common.enums.UserType;

import java.lang.annotation.*;

/**
 * @Author: stitch
 * @Date: 2024/3/24 16:54
 * @Description: 记录操作日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 操作主题
     */
    String title() default "";

    /**
     * 操作类型
     */
    BusinessType BusinessType() default BusinessType.OTHER;

    /**
     * 操作人类型
     */
    UserType OperatorType() default UserType.MANAGER;


    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestParam() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseParam() default true;

    /**
     * 需要排除的请求的参数
     */
    String[] excludeRequestParam() default {};


}
