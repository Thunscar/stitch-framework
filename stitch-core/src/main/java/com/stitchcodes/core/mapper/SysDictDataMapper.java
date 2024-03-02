package com.stitchcodes.core.mapper;


import com.stitchcodes.core.domain.SysDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_dict_data】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:10
 * @Entity generator.domain.SysDictData
 */
public interface SysDictDataMapper {

    /**
     * 根据字典类型查询字典数据列表
     *
     * @param dictType 字典类型
     * @return
     */
    List<SysDictData> selectSysDictDataList(SysDictData dictData);


    /**
     * 根据code查询字典数据
     *
     * @param dictCode 字典code
     * @return
     */
    SysDictData selectSysDictDataByCode(Long dictCode);

    /**
     * 插入字典数据
     *
     * @param dictData 字典数据实体
     * @return
     */
    int insertSysDictData(SysDictData dictData);

    /**
     * 更新字典数据
     *
     * @param dictData 字典数据实体
     * @return
     */
    int updateSysDictData(SysDictData dictData);

    /**
     * 删除字典数据
     *
     * @param dictIds 字典数据ID数组
     * @return
     */
    int deleteSysDictData(@Param("dictIds") Long[] dictIds);

    /**
     * 根据字典类型与字典标签查询字典数据
     *
     * @param dictType  字典类型
     * @param dictLabel 字典标签
     * @return 字典数据
     */
    SysDictData selectSysDictDataByLabel(@Param("dictType") String dictType,@Param("dictLabel") String dictLabel);


}




