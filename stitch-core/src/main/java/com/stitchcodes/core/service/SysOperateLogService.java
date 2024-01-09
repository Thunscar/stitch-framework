package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysOperateLog;

/**
 * @author stitch
 * @description 针对表【sys_operate_log(系统操作日志表)】的数据库操作Service
 * @createDate 2023-04-28 13:20:02
 */
public interface SysOperateLogService {

    /**
     * 插入操作日志
     *
     * @param operateLog 操作日志信息
     * @return 插入数据条数
     */
    int insertOperateLog(SysOperateLog operateLog);
}
