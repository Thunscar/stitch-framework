package com.stitchcodes.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: stitch
 * @Date: 2023/5/1 21:38
 * @Description: 类中实体字段Excel映射注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Excels {
    Excel[] excels();
}
