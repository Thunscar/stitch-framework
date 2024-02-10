package com.stitchcodes.common.constant;

/**
 * @Author: stitch
 * @Date: 2023/4/29 18:45
 * @Description: 基础常量
 */
public class UserConstants {

    /*是内置参数*/
    public static final String YES = "Y";

    /*不唯一*/
    public static final boolean UN_UNIQUE = false;

    /*唯一*/
    public static final boolean UNIQUE = true;

    /*密码最短位数*/
    public static final int PASSWORD_MIN_LENGTH = 6;

    /*密码最长长度*/
    public static final int PASSWORD_MAX_LENGTH = 20;

    /*用户名最小长度*/
    public static final int USERNAME_MIN_LENGTH = 4;

    /*用户名最大长度*/
    public static final int USERNAME_MAX_LENGTH = 20;

    /*密码错误次数限制默认值*/
    public static final int PASSWORD_ERROR_COUNT_DEFAULT = 5;

    /*密码错误默认锁定时间*/
    public static final int ACCOUNT_LOCKED_TIME_DEFAULT = 30;

    /*用户登录成功*/
    public static final String LOGIN_SUCCESS = "Login Success";

    /*用户登录失败*/
    public static final String LOGIN_FAIL = "Login Fail";

    /*用户登出*/
    public static final String LOGOUT = "Logout";

    /*用户注册*/
    public static final String REGISTER = "Register";

    /*根路由ID*/
    public static final long ROOT_PARENT_ID = 0L;

    /*目录类型*/
    public static final String DIR_TYPE = "M";

    /*菜单类型*/
    public static final String MENU_TYPE = "M";

    /*非外链菜单*/
    public static final String NO_FRAME = "0";

    /*展示菜单*/
    public static final String MENU_VISIBLE = "1";

    /*缓存*/
    public static final String CACHE = "1";

    /*是外链*/
    public static final String IS_LINK = "1";

    /*Layout布局*/
    public static final String LAYOUT = "Layout";

    /*外链*/
    public static final String OUTER_LINK = "OuterLink";

    /*父目录视图*/
    public static final String PARENT_MENU = "ParentView";

    /*停用状态*/
    public static final String STATUS_BLOCK = "1";

    /*根部父级部门ID*/
    public static final Long ROOT_DEPT_PARENT = 0L;

    /*非系统用户*/
    public static final String NON_SYSTEM_USER = "11";

    /*账号初始密码*/
    public static final String ACCOUNT_INIT_PASSWORD = "001010";
}
