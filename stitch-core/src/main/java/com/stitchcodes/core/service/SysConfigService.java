package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysConfig;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_config(系统配置表)】的数据库操作Service
 * @createDate 2023-04-28 13:20:02
 */
public interface SysConfigService {

    /**
     * 加载参数缓存
     */
    void loadingConfigCache();

    /**
     * 清空参数缓存
     */
    void clearConfigCache();

    /**
     * 重置参数缓存
     */
    void resetConfigCache();


    /**
     * 通过参数key获取参数值
     *
     * @param configKey 参数key
     * @return 参数值
     */
    String getConfigValueByKey(String configKey);

    /**
     * 通过参数Id查询参数配置
     *
     * @param configId 参数配置Id
     * @return 系统参数配置
     */
    SysConfig getConfigValueById(Long configId);

    /**
     * 获取参数列表
     *
     * @param config 参数配置实体(支持configName模糊查询，参数Key模糊查询，参数类型查询，时间段查询)
     * @return 参数列表数据
     */
    List<SysConfig> getSysConfigList(SysConfig config);

    /**
     * 新增参数配置
     *
     * @param config 参数配置实体
     * @return 新增数据数量
     */
    int insertSysConfig(SysConfig config);

    /**
     * 更新参数配置
     *
     * @param config 参数配置信息
     * @return 更新数据条数
     */
    int updateSysConfig(SysConfig config);

    /**
     * 批量删除系统参数配置
     *
     * @param configIds 系统参数配置数组
     */
    void deleteSysConfig(Long[] configIds);

    /**
     * 检查参数键是否唯一
     *
     * @param config 参数配置实体
     * @return 是否唯一（true唯一false不唯一）
     */
    boolean checkSysConfigKeyUnique(SysConfig config);

    /**
     * 获取验证码开关
     *
     * @return （true 开 false关）
     */
    boolean selectVerifyEnabled();

    /**
     * 获取用户密码错误次数上限
     *
     * @return 密码错误次数上限
     */
    Integer selectPasswordErrorCountUpperLimit();

    /**
     * 获取账号锁定时间
     * @return 锁定时间 单位分钟
     */
    Integer selectAccountLockTime();

    /**
     * 获取账号的初始密码
     * @return 账号初始密码
     */
    String selectAccountInitPassword();


}
