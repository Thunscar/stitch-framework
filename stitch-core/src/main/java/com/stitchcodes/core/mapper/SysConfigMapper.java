package com.stitchcodes.core.mapper;

import com.stitchcodes.core.domain.SysConfig;

import java.util.List;

/**
 * @author chenwei
 * @description 针对表【sys_config(系统配置表)】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:10
 * @Entity generator.domain.SysConfig
 */
public interface SysConfigMapper {

    /**
     * 通过configId获取configKey获取参数配置
     *
     * @param sysConfig 参数配置实体
     * @return
     */
    SysConfig selectSysConfig(SysConfig sysConfig);

    /**
     * 模糊查询参数列表
     *
     * @param sysConfig 参数配置实体
     * @return 模糊查询后的参数列表
     */
    List<SysConfig> selectSysConfigList(SysConfig sysConfig);

    /**
     * 通过参数ID查询参数信息
     *
     * @param configId 参数ID
     * @return 系统参数信息
     */
    SysConfig selectSysConfigById(Long configId);

    /**
     * 更新参数配置
     *
     * @param sysConfig 参数配置实体
     * @return 更新数据数量
     */
    int updateSysConfig(SysConfig sysConfig);

    /**
     * 新增参数配置
     *
     * @param sysConfig 参数配置信息
     * @return 新增数据数量
     */
    int insertSysConfig(SysConfig sysConfig);

    /**
     * 删除系统参数配置
     *
     * @param configId 参数配置ID
     * @return 删除数据数量
     */
    int deleteSysConfig(Long configId);

    /**
     * 检查参数键是否唯一
     *
     * @param configKey 参数键名
     * @return 系统参数配置
     */
    SysConfig checkSysConfigKeyUnique(String configKey);

}




