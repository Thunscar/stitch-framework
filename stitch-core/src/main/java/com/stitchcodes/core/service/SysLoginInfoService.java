package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysLoginInfo;

import java.util.List;

/**
 * @author chenwei
 * @description 针对表【sys_login_info】的数据库操作Service
 * @createDate 2023-04-28 13:20:02
 */
public interface SysLoginInfoService {

    /**
     * 新增一条登录日志
     *
     * @param sysLoginInfo 系统登录信息
     * @return 新增数据条数
     */
    int insertSysLoginInfo(SysLoginInfo sysLoginInfo);

    /**
     * 查询登录日志
     *
     * @param sysLoginInfo 登录信息实体
     * @return 结果
     */
    List<SysLoginInfo> getSysLoginInfoList(SysLoginInfo sysLoginInfo);

}
