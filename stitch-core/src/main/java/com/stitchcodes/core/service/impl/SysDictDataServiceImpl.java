package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.redis.RedisCache;
import com.stitchcodes.common.utils.CollectionUtils;
import com.stitchcodes.common.utils.ObjectUtils;
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
        if (ObjectUtils.isNotNull(cacheDictData)) {
            return cacheDictData;
        }
        //查数据库中字典数据信息 若查找保存到缓存
        SysDictData dictData = new SysDictData();
        dictData.setDictType(dictType);
        List<SysDictData> sysDictDataList = dictDataMapper.selectSysDictDataList(dictData);
        if (CollectionUtils.isNotEmpty(sysDictDataList)) {
            redisCache.setCacheObject(getDictCacheKey(dictType), sysDictDataList);
            return sysDictDataList;
        }
        return null;
    }

    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        return dictDataMapper.selectSysDictDataList(dictData);
    }

    @Override
    public SysDictData selectDictDataByCode(Long dictCode) {
        return dictDataMapper.selectSysDictDataByCode(dictCode);
    }

    @Override
    public int createDictData(SysDictData dictData) {
        //将字典数据保存入数据库
        int result = dictDataMapper.insertSysDictData(dictData);
        //刷新缓存
        refreshCache(dictData.getDictType());
        return result;
    }

    @Override
    public int updateDictData(SysDictData dictData) {
        //更新字典数据
        int result = dictDataMapper.updateSysDictData(dictData);
        //刷新缓存
        refreshCache(dictData.getDictType());
        return result;
    }

    @Override
    public int deleteDictData(String dictType, Long[] dictCodes) {
        //删除数据库中字典数据
        int result = dictDataMapper.deleteSysDictData(dictCodes);
        //刷新缓存
        refreshCache(dictType);
        return result;
    }

    @Override
    public void refreshCache(String dictType) {
        SysDictData queryDictData = new SysDictData();
        queryDictData.setDictType(dictType);
        List<SysDictData> dictDataList = dictDataMapper.selectSysDictDataList(queryDictData);
        redisCache.setCacheObject(getDictCacheKey(dictType), dictDataList);
    }

    @Override
    public void checkDictLabelUnique(SysDictData dictData) {
        Long dictCode = ObjectUtils.isNull(dictData.getDictCode()) ? -1L : dictData.getDictCode();
        SysDictData sysDictData = dictDataMapper.selectSysDictDataByLabel(dictData.getDictType(), dictData.getDictLabel());
        if (ObjectUtils.isNotNull(sysDictData) && dictCode.longValue() != sysDictData.getDictCode()) {
            throw new StitchException("字典标签已存在,不可重复创建");
        }
    }
}




