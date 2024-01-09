package com.stitchcodes.common.constant;

/**
 * @Author: stitch
 * @Date: 2023/4/29 21:25
 * @Description: 系统参数管理key
 */
public class ConfigConstants {

    /*验证码开关参数Key*/
    public static final String VERIFY_CODE_CONFIG = "sys.account.captchaEnabled";

    /*密码错误次数上限*/
    public static final String PASSWORD_ERROR_COUNT_LIMIT = "sys.account.passwordErrorCount";

    /*登录IP黑名单*/
    public static final String LOGIN_IP_BLACK_LIST = "sys.login.IpBlackList";

    /*账号锁定时间*/
    public static final String ACCOUNT_LOCKED_TIME = "sys.account.lockedTime";

    /*账号初始密码*/
    public static final String ACCOUNT_INIT_PASSWORD = "sys.login.initPassword";


}
