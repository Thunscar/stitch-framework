package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.core.domain.SysDictType;
import com.stitchcodes.core.service.SysDictTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2024/2/12 02:01
 * @Description:
 */
@RestController
@RequestMapping("/sys/dict/type")
public class SysDictTypeController extends BaseController {

    @Resource
    private SysDictTypeService dictTypeService;

    //查询字典类型列表
    @GetMapping("/list")
    public TableData list(SysDictType dictType) {
        startPage();
        List<SysDictType> sysDictTypes = dictTypeService.selectDictTypeList(dictType);
        return getTableData(sysDictTypes);
    }

    //查询字典类型详细信息
    @GetMapping("{dictTypeId}")
    public AjaxResult getById(@PathVariable Long dictTypeId) {
        return AjaxResult.success(dictTypeService.selectDictTypeById(dictTypeId));
    }

    //创建字典类型
    @PostMapping
    public AjaxResult create(@RequestBody SysDictType sysDictType) {
        //检查类型是否唯一
        dictTypeService.checkDictTypeUnique(sysDictType);
        return toAjax(dictTypeService.createSysDictType(sysDictType));
    }

    //修改字典类型
    @PutMapping
    public AjaxResult update(@RequestBody SysDictType sysDictType) {
        //检查类型是否唯一
        dictTypeService.checkDictTypeUnique(sysDictType);
        return toAjax(dictTypeService.updateSysDictType(sysDictType));
    }

    //删除字典类型
    @DeleteMapping
    public AjaxResult delete(Long[] dictTypeIds) {
        return toAjax(dictTypeService.deleteBatchSysDictType(dictTypeIds));
    }

    @PostMapping("/refresh")
    public AjaxResult refreshCache() {
        dictTypeService.resetDictCache();
        return success();
    }
}
