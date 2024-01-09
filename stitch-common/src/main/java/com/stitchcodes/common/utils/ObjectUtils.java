package com.stitchcodes.common.utils;

/**
 * @Author: stitch
 * @Date: 2023/4/29 13:20
 * @Description:
 */
public class ObjectUtils {

    /**
     * 判断对象是否为空
     *
     * @param obj 对象
     * @return 是否为空
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 判断对象是否为非空
     *
     * @param obj 对象
     * @return 是否非空
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 判断对象数组是否为空
     *
     * @param objects 对象数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }
}
