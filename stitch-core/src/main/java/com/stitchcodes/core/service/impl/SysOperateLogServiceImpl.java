package com.stitchcodes.core.service.impl;

import com.stitchcodes.core.domain.SysOperateLog;
import com.stitchcodes.core.mapper.SysOperateLogMapper;
import com.stitchcodes.core.service.SysOperateLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author stitch
* @description 针对表【sys_operate_log(系统操作日志表)】的数据库操作Service实现
* @createDate 2023-04-28 13:20:02
*/
@Service
public class SysOperateLogServiceImpl implements SysOperateLogService {

    @Resource
    private SysOperateLogMapper operateLogMapper;

    @Override
    public int insertOperateLog(SysOperateLog operateLog) {
        return 0;
    }
}




