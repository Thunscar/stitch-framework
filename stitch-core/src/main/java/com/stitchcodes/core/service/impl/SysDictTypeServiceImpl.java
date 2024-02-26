package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.constant.CacheConstants;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.redis.RedisCache;
import com.stitchcodes.common.utils.CollectionUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysDictData;
import com.stitchcodes.core.domain.SysDictType;
import com.stitchcodes.core.mapper.SysDictDataMapper;
import com.stitchcodes.core.mapper.SysDictTypeMapper;
import com.stitchcodes.core.service.SysDictTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.stitchcodes.common.utils.RedisKeyUtil.getDictCacheKey;

/**
 * @author stitch
 * @description 针对表【sys_dict_type(字典类型表)】的数据库操作Service实现
 * @createDate 2023-04-28 13:20:02
 */
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {

    @Resource
    private SysDictTypeMapper dictTypeMapper;
    @Resource
    private SysDictDataMapper dictDataMapper;
    @Resource
    private RedisCache redisCache;

    @PostConstruct
    public void init() {
        loadingDictCache();
    }

    @Override
    public void loadingDictCache() {
        //查询可用的字典类型数据
        SysDictType queryDictType = new SysDictType();
        queryDictType.setStatus("0");
        List<SysDictType> sysDictTypeList = dictTypeMapper.selectDictTypeList(queryDictType);
        for (SysDictType sysDictType : sysDictTypeList) {
            //查询该类型字典数据
            List<SysDictData> sysDictDataList = dictDataMapper.selectSysDictDataByType(sysDictType.getDictType());
            redisCache.setCacheObject(getDictCacheKey(sysDictType.getDictType()), sysDictDataList);
        }
    }

    @Override
    public void clearDictCache() {
        Collection<String> dictKeys = redisCache.keys(CacheConstants.DICT_KEY + "*");
        redisCache.deleteObject(dictKeys);
    }

    @Override
    public void resetDictCache() {
        clearDictCache();
        loadingDictCache();
    }

    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    @Override
    public SysDictType selectDictTypeById(Long dictTypeId) {
        return dictTypeMapper.selectSysDictTypeById(dictTypeId);
    }

    @Override
    public int createSysDictType(SysDictType dictType) {
        //保存字典类型信息
        int result = dictTypeMapper.insertSysDictType(dictType);
        //将新的字典类型数据存入缓存
        if (result > 0) {
            redisCache.setCacheObject(getDictCacheKey(dictType.getDictType()), null);
        }
        return result;
    }

    @Override
    public int updateSysDictType(SysDictType dictType) {
        //查询旧的字典信息
        SysDictType oldDictType = dictTypeMapper.selectSysDictTypeById(dictType.getDictId());
        //更新字典信息
        int result = dictTypeMapper.updateSysDictType(dictType);
        if (result > 0) {
            //查询字典数据
            List<SysDictData> sysDictData = dictDataMapper.selectSysDictDataByType(dictType.getDictType());
            //删除原有的字典类型缓存
            redisCache.deleteObject(getDictCacheKey(oldDictType.getDictType()));
            //添加新的字典类型与数据缓存
            redisCache.setCacheObject(getDictCacheKey(dictType.getDictType()), sysDictData);
        }
        return result;
    }

    @Override
    public int deleteBatchSysDictType(Long[] dictIds) {
        int result = 0;
        for (Long dictId : dictIds) {
            //检查是否可以被删除(有字典数据的不可被删除)
            SysDictType dictType = dictTypeMapper.selectSysDictTypeById(dictId);
            List<SysDictData> dictDataList = dictDataMapper.selectSysDictDataByType(dictType.getDictType());
            if (CollectionUtils.isNotEmpty(dictDataList)) {
                throw new StitchException(StringUtils.format("字典类型[%s]存在字典数据,无法删除", dictType.getDictName()));
            }
            //删除数据库中的字典类型
            int row = dictTypeMapper.deleteSysDictType(dictId);
            //移除缓存中的字典数据
            if (row > 0) {
                redisCache.deleteObject(getDictCacheKey(dictType.getDictType()));
            }
            result += row;
        }
        return result;
    }

    @Override
    public void checkDictTypeUnique(SysDictType dictType) {
        Long dictId = ObjectUtils.isNull(dictType.getDictId()) ? -1L : dictType.getDictId();
        SysDictType sysDictType = dictTypeMapper.selectSysDictTypeByCode(dictType.getDictType());
        if (ObjectUtils.isNotNull(sysDictType) && sysDictType.getDictId().longValue() != dictId) {
            throw new StitchException(StringUtils.format("字典类型[%s]已存在", dictType.getDictType()));
        }
    }

}




