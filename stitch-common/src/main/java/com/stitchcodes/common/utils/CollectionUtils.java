package com.stitchcodes.common.utils;


import java.util.Collection;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2023/5/15 17:14
 * @Description: 集合工具类
 */
public class CollectionUtils {

    /**
     * 是否为空
     *
     * @param c 集合
     * @return 是否为空
     */
    public static <T> boolean isEmpty(Collection<T> c) {
        return c.isEmpty();
    }

    /**
     * 是否非空
     *
     * @param c 集合
     * @return 是否非空
     */
    public static <T> boolean isNotEmpty(Collection<T> c) {
        return !isEmpty(c);
    }

}
