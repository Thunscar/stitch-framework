package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysDictType;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_dict_type(字典类型表)】的数据库操作Service
 * @createDate 2023-04-28 13:20:02
 */
public interface SysDictTypeService {


    /**
     * 将字典数据加载入缓存
     */
    void loadingDictCache();

    /**
     * 清理字典数据缓存
     */
    void clearDictCache();

    /**
     * 刷新字典数据缓存
     */
    void resetDictCache();


    /**
     * 查询字典类型列表
     *
     * @param dictType 字典类型实体
     * @return
     */
    List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * 通过ID查询字典类型
     *
     * @param dictTypeId 字典类型ID
     * @return
     */
    SysDictType selectDictTypeById(Long dictTypeId);

    /**
     * 创建字典类型
     *
     * @param dictType 字典类型数据
     * @return
     */
    int createSysDictType(SysDictType dictType);

    /**
     * 更细字典类型数据
     *
     * @param dictType 字典类型
     * @return
     */
    int updateSysDictType(SysDictType dictType);

    /**
     * 批量删除系统字典类型
     *
     * @param dictIds 字典类型ID列表
     * @return
     */
    int deleteBatchSysDictType(Long[] dictIds);


    /**
     * 检查字典类型的唯一性
     *
     * @param dictType 字典类型实体
     */
    void checkDictTypeUnique(SysDictType dictType);

}
