package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysDictData;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_dict_data】的数据库操作Service
 * @createDate 2023-04-28 13:20:02
 */
public interface SysDictDataService {


    /**
     * 根据字典类型查询字典数据
     * 先查缓存，查不到再查数据库
     *
     * @param dictType 字典类型Code
     * @return 字典数据列表
     */
    List<SysDictData> selectDictDataByDictType(String dictType);


    /**
     * 查询字典数据信息
     * 直接查询数据库
     *
     * @param dictData 字典数据信息
     * @return
     */
    List<SysDictData> selectDictDataList(SysDictData dictData);


    /**
     * 根据字典编码查询字典数据
     *
     * @param dictCode 字典编码
     * @return
     */
    SysDictData selectDictDataByCode(Long dictCode);

    /**
     * 创建字典数据
     *
     * @param dictData 字典数据
     * @return
     */
    int createDictData(SysDictData dictData);

    /**
     * 更新字典数据
     *
     * @param dictData 字典数据
     * @return
     */
    int updateDictData(SysDictData dictData);

    /**
     * 批量删除字典数据
     *
     * @param dictCodes 字典Code数组
     * @return
     */
    int deleteDictData(String dictType, Long[] dictCodes);


    /**
     * 刷新一个字典类型的缓存数据
     *
     * @param dictType 字典类型
     */
    void refreshCache(String dictType);

    /**
     * 检查字典数据标签是否重复
     * @param dictData 字典数据
     */
    void checkDictLabelUnique(SysDictData dictData);

}
