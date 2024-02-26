package com.stitchcodes.core.mapper;


import com.stitchcodes.core.domain.SysDictData;

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
    List<SysDictData> selectSysDictDataByType(String dictType);

}




