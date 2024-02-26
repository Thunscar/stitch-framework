package com.stitchcodes.common.utils;

import com.stitchcodes.common.constant.CacheConstants;

/**
 * @Author: stitch
 * @Date: 2024/2/26 15:27
 * @Description: redis缓存Key
 */
public class RedisKeyUtil {

    /**
     * 获取字典缓存Key值
     *
     * @param dictType 字典类型
     * @return 字典缓存Key值
     */
    public static String getDictCacheKey(String dictType) {
        return CacheConstants.DICT_KEY + dictType;
    }
}
