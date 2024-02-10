package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.constant.CacheConstants;
import com.stitchcodes.common.constant.UserConstants;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.redis.RedisCache;
import com.stitchcodes.common.utils.ConvertUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysConfig;
import com.stitchcodes.core.mapper.SysConfigMapper;
import com.stitchcodes.core.service.SysConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.stitchcodes.common.constant.ConfigConstants.*;

/**
 * @author stitch
 * @description 针对表【sys_config(系统配置表)】的数据库操作Service实现
 * @createDate 2023-04-28 13:20:02
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Resource
    private SysConfigMapper configMapper;
    @Resource
    private RedisCache redisCache;

    @PostConstruct
    public void init() {
        loadingConfigCache();
    }

    @Override
    public void loadingConfigCache() {
        List<SysConfig> sysConfigs = configMapper.selectSysConfigList(new SysConfig());
        for (SysConfig sysConfig : sysConfigs) {
            redisCache.setCacheObject(getConfigCacheKey(sysConfig.getConfigKey()), sysConfig.getConfigValue());
        }
    }

    @Override
    public void clearConfigCache() {
        Collection<String> keys = redisCache.keys(CacheConstants.CONFIG_KEY + "*");
        redisCache.deleteObject(keys);
    }

    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }

    @Override
    public String getConfigValueByKey(String key) {
        String configValue = ConvertUtils.toString(redisCache.getCacheObject(getConfigCacheKey(key)));
        if (StringUtils.isNotEmpty(configValue)) {
            return configValue;
        }
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigKey(key);
        SysConfig querySysConfig = configMapper.selectSysConfig(sysConfig);
        if (ObjectUtils.isNotNull(querySysConfig)) {
            redisCache.setCacheObject(getConfigCacheKey(key), querySysConfig.getConfigValue());
            return querySysConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public SysConfig getConfigValueById(Long configId) {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigId(configId);
        return configMapper.selectSysConfig(sysConfig);
    }

    @Override
    public List<SysConfig> getSysConfigList(SysConfig config) {
        return ObjectUtils.isNull(config) ? new ArrayList<>() : configMapper.selectSysConfigList(config);
    }

    @Override
    public int insertSysConfig(SysConfig config) {
        int row = configMapper.insertSysConfig(config);
        if (row > 0) {
            redisCache.setCacheObject(getConfigCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    @Override
    public int updateSysConfig(SysConfig config) {
        SysConfig sysConfig = configMapper.selectSysConfigById(config.getConfigId());
        if (!StringUtils.equals(sysConfig.getConfigKey(), config.getConfigKey())) {
            redisCache.deleteObject(getConfigCacheKey(sysConfig.getConfigKey()));
        }
        int row = configMapper.updateSysConfig(config);
        if (row > 0) {
            redisCache.setCacheObject(getConfigCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    @Override
    @Transactional
    public int deleteSysConfig(Long[] configIds) {
        int result = 0;
        for (Long configId : configIds) {
            SysConfig config = getConfigValueById(configId);
            if (StringUtils.equals(UserConstants.YES, config.getConfigType())) {
                throw new StitchException(String.format("Can not Remove The Build-in System Configuration:[%s]", config.getConfigKey()));
            }
            int row = configMapper.deleteSysConfig(configId);
            if (row > 0) {
                redisCache.deleteObject(getConfigCacheKey(config.getConfigKey()));
            }
            result += row;
        }
        return result;
    }

    @Override
    public void checkSysConfigKeyUnique(SysConfig config) {
        Long configId = ObjectUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig sysConfig = configMapper.checkSysConfigKeyUnique(config.getConfigKey());
        if (ObjectUtils.isNotNull(sysConfig) && sysConfig.getConfigId().longValue() != configId.longValue()) {
            throw new StitchException("参数键不唯一");
        }
    }

    @Override
    public boolean selectVerifyEnabled() {
        String configCacheKey = getConfigValueByKey(VERIFY_CODE_CONFIG);
        if (StringUtils.isEmpty(configCacheKey)) {
            return true;
        }
        Boolean bool = ConvertUtils.toBool(configCacheKey);
        return ObjectUtils.isNull(bool) || bool;
    }

    @Override
    public Integer selectPasswordErrorCountUpperLimit() {
        String errCountLimit = getConfigValueByKey(PASSWORD_ERROR_COUNT_LIMIT);
        if (StringUtils.isEmpty(errCountLimit)) {
            return UserConstants.PASSWORD_ERROR_COUNT_DEFAULT;
        }
        return ConvertUtils.toInt(errCountLimit);
    }

    @Override
    public Integer selectAccountLockTime() {
        String lockedTime = getConfigValueByKey(ACCOUNT_LOCKED_TIME);
        if (StringUtils.isEmpty(lockedTime)) {
            return UserConstants.ACCOUNT_LOCKED_TIME_DEFAULT;
        }
        return ConvertUtils.toInt(lockedTime);
    }

    @Override
    public String selectAccountInitPassword() {
        String initPassword = getConfigValueByKey(ACCOUNT_INIT_PASSWORD);
        if (StringUtils.isEmpty(initPassword)) {
            return UserConstants.ACCOUNT_INIT_PASSWORD;
        }
        return initPassword;
    }


    private String getConfigCacheKey(String configKey) {
        return CacheConstants.CONFIG_KEY + configKey;
    }
}




