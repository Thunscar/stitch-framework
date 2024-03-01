package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.core.domain.SysDictData;
import com.stitchcodes.core.service.SysDictDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2024/2/26 15:21
 * @Description: 字典数据操作控制器
 */
@RestController
@RequestMapping("/sys/dict/data")
public class SysDictDataController {

    @Resource
    private SysDictDataService dictDataService;

    @GetMapping("/{dictType}")
    public AjaxResult getByDictType(@PathVariable String dictType) {
        List<SysDictData> dictDataList = dictDataService.selectDictDataByDictType(dictType);
        if (ObjectUtils.isNull(dictDataList)) {
            dictDataList = new ArrayList<SysDictData>();
        }
        return AjaxResult.success(dictDataList);
    }
}
