package com.stitchcodes.core.service.impl;

import com.stitchcodes.core.domain.SysLoginInfo;
import com.stitchcodes.core.mapper.SysLoginInfoMapper;
import com.stitchcodes.core.service.SysLoginInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_login_info】的数据库操作Service实现
 * @createDate 2023-04-28 13:20:02
 */
@Service
public class SysLoginInfoServiceImpl implements SysLoginInfoService {

    @Resource
    private SysLoginInfoMapper loginInfoMapper;

    @Override
    public int insertSysLoginInfo(SysLoginInfo sysLoginInfo) {
        return loginInfoMapper.insertSysLoginInfo(sysLoginInfo);
    }

    @Override
    public List<SysLoginInfo> getSysLoginInfoList(SysLoginInfo sysLoginInfo) {
        return loginInfoMapper.selectSysLoginInfoList(sysLoginInfo);
    }
}




