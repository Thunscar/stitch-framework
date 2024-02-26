package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.redis.RedisCache;
import com.stitchcodes.common.utils.CollectionUtils;
import com.stitchcodes.core.domain.SysDictData;
import com.stitchcodes.core.mapper.SysDictDataMapper;
import com.stitchcodes.core.service.SysDictDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.stitchcodes.common.utils.RedisKeyUtil.getDictCacheKey;

/**
 * @author stitch
 * @description 针对表【sys_dict_data】的数据库操作Service实现
 * @createDate 2023-04-28 13:20:02
 */
@Service
public class SysDictDataServiceImpl implements SysDictDataService {
    @Resource
    private SysDictDataMapper dictDataMapper;
    @Resource
    private RedisCache redisCache;

    @Override
    public List<SysDictData> selectDictDataByDictType(String dictType) {
        //查询缓存中的字典数据信息
        List<SysDictData> cacheDictData = redisCache.getCacheObject(getDictCacheKey(dictType));
        if (CollectionUtils.isNotEmpty(cacheDictData)) {
            return cacheDictData;
        }
        //查数据库中字典数据信息 若查找保存到缓存
        List<SysDictData> sysDictDataList = dictDataMapper.selectSysDictDataByType(dictType);
        if (CollectionUtils.isNotEmpty(sysDictDataList)) {
            redisCache.setCacheObject(getDictCacheKey(dictType), sysDictDataList);
            return sysDictDataList;
        }
        return null;
    }
}




