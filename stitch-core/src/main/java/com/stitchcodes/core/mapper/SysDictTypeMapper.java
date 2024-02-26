package com.stitchcodes.core.mapper;

import com.stitchcodes.core.domain.SysDictType;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_dict_type(字典类型表)】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:10
 * @Entity generator.domain.SysDictType
 */
public interface SysDictTypeMapper {

    /**
     * 查询字典类型数据
     *
     * @param dictType 字典类型
     * @return
     */
    List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * 通过ID查询字典类型数据
     *
     * @param dictTypeId 字典类型ID
     * @return
     */
    SysDictType selectSysDictTypeById(Long dictId);

    /**
     * 通过字典类型值查找类型信息
     *
     * @param dictType 字典类型值
     * @return
     */
    SysDictType selectSysDictTypeByCode(String dictType);


    /**
     * 保存字典类型数据
     *
     * @param dictType 字典类型数据
     * @return
     */
    int insertSysDictType(SysDictType dictType);

    /**
     * 更细字典类型
     *
     * @param dictType 字典类型
     * @return
     */
    int updateSysDictType(SysDictType dictType);

    /**
     * 删除字典类型
     *
     * @param dictId 字典类型ID
     * @return
     */
    int deleteSysDictType(Long dictId);

}




