package com.stitchcodes.common.constant;

/**
 * @Author: stitch
 * @Date: 2023/4/28 23:04
 * @Description:
 */
public class CacheConstants {

    private static final String CACHE_PREFIX = "stitch";

    /*系统参数缓存前缀*/
    public static final String CONFIG_KEY = CACHE_PREFIX + "_config:";

    /*字典数据缓存前缀*/
    public static final String DICT_KEY = CACHE_PREFIX + "_dict:";

    /*验证码参数缓存前缀*/
    public static final String VERIFY_CODE_KEY = CACHE_PREFIX + "_verifyCode:";

    /*token缓存前缀*/
    public static final String TOKEN_KEY = CACHE_PREFIX + "_token:";

    /*密码错误次数前缀*/
    public static final String PASS_ERROR_COUNT_KEY = CACHE_PREFIX + "_err_count:";

}
