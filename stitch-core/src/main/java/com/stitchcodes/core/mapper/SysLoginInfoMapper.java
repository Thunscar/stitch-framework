package com.stitchcodes.core.mapper;

import com.stitchcodes.core.domain.SysLoginInfo;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_login_info】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:10
 * @Entity generator.domain.SysLoginInfo
 */
public interface SysLoginInfoMapper {

    /**
     * 新增系统登录日志
     *
     * @param sysLoginInfo 系统登录信息
     * @return 新增数据条数
     */
    int insertSysLoginInfo(SysLoginInfo sysLoginInfo);

    /**
     * 查询系统登录信息
     *
     * @param sysLoginInfo 系统登录信息实体
     * @return 结果
     */
    List<SysLoginInfo> selectSysLoginInfoList(SysLoginInfo sysLoginInfo);

}




