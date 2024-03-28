package com.stitchcodes.core.mapper;


import com.stitchcodes.core.domain.SysOperateLog;

import java.util.List;

/**
* @author stitch
* @description 针对表【sys_operate_log(系统操作日志表)】的数据库操作Mapper
* @createDate 2023-04-29 14:12:11
* @Entity generator.domain.SysOperateLog
*/
public interface SysOperateLogMapper {


    /**
     * 添加操作日志
     * @param operateLog 操作日志
     * @return
     */
    int insertOperateLog(SysOperateLog operateLog);


    /**
     * 查询操作日志
     * @param operateLog 操作日志
     * @return
     */
    List<SysOperateLog> selectOperateLogList(SysOperateLog operateLog);

}




