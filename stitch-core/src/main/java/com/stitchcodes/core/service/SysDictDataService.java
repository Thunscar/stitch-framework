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
     *
     * @param dictType 字典类型Code
     * @return 字典数据列表
     */
    List<SysDictData> selectDictDataByDictType(String dictType);

}
