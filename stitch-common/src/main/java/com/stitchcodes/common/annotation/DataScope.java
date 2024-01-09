package com.stitchcodes.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: stitch
 * @Date: 2023/5/7 21:28
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataScope {

    /**
     * 部门表别名
     */
    String deptAlias() default "";

    /**
     * 用户表别名
     */
    String userAlias() default "";

    /**
     * 权限字符（用于多个角色匹配符合要求的权限）默认根据权限字符@ss获取，多个权限用逗号分隔
     */
    String permission() default "";
}
