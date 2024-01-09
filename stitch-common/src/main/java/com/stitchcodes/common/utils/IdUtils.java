package com.stitchcodes.common.utils;

import java.util.UUID;

/**
 * @Author: stitch
 * @Date: 2023/5/8 13:10
 * @Description: Id工具类
 */
public class IdUtils {

    /**
     * 生成UUID
     *
     * @return uuid
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成tokenUUID
     *
     * @return tokenUUID
     */
    public static String generateTokenUUID() {
        return generateUUID().replaceAll("-", "");
    }

}
