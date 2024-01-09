package com.stitchcodes.system.manager.factory;

import com.stitchcodes.common.constant.CommonConstants;
import com.stitchcodes.common.constant.UserConstants;
import com.stitchcodes.common.utils.*;
import com.stitchcodes.core.domain.SysLoginInfo;
import com.stitchcodes.core.domain.SysOperateLog;
import com.stitchcodes.core.service.SysLoginInfoService;
import com.stitchcodes.core.service.SysOperateLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * @Author: stitch
 * @Date: 2023/5/7 15:25
 * @Description: 异步工厂(产生任务用)
 */
public class AsyncFactory {

    private static final Logger sys_user_log = LoggerFactory.getLogger("system-user");

    /**
     * 创建记录登录日志任务
     *
     * @param username  登录用户名
     * @param status    状态
     * @param operation 操作
     * @param message   消息内容
     * @param args      日志参数
     * @return 记录登录日志的任务
     */
    public static TimerTask recordLoginInfo(final String username, final String status, final String operation, final String message, final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr();
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIp(ip);
                StringBuilder sb = new StringBuilder();
                sb.append(LogUtils.getBlock(ip));
                sb.append(LogUtils.getBlock(address));
                sb.append(LogUtils.getBlock(operation));
                sb.append(LogUtils.getBlock(message));
                sb.append(LogUtils.getBlock(status));
                sys_user_log.info(sb.toString(), args);
                //获取用户基本信息
                String os = userAgent.getOperatingSystem().getName();
                String browser = userAgent.getBrowser().getName();
                //创建系统访问日志实体
                SysLoginInfo sysLoginInfo = new SysLoginInfo();
                sysLoginInfo.setUserName(username);
                sysLoginInfo.setIpaddr(ip);
                sysLoginInfo.setLoginLocation(address);
                sysLoginInfo.setBrowser(browser);
                sysLoginInfo.setOs(os);
                sysLoginInfo.setMsg(message);
                sysLoginInfo.setOperation(operation);
                //设置日志状态
                if (StringUtils.equalsAny(status, UserConstants.LOGIN_SUCCESS, UserConstants.LOGOUT, UserConstants.REGISTER)) {
                    sysLoginInfo.setStatus(CommonConstants.SUCCESS);
                } else if (StringUtils.equalsAny(status, UserConstants.LOGIN_FAIL)) {
                    sysLoginInfo.setStatus(CommonConstants.FAIL);
                }
                SpringUtils.getBean(SysLoginInfoService.class).insertSysLoginInfo(sysLoginInfo);
            }
        };
    }

    /**
     * 创建记录操作日志的任务
     *
     * @param operateLog 操作日志信息
     * @return 记录操作日志的任务
     */
    public static TimerTask recordOperateLog(final SysOperateLog operateLog) {
        return new TimerTask() {
            @Override
            public void run() {
                operateLog.setOperateLocation(AddressUtils.getRealAddressByIp(operateLog.getOperateIp()));
                SpringUtils.getBean(SysOperateLogService.class).insertOperateLog(operateLog);
            }
        };
    }


}
