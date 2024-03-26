package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.annotation.Log;
import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.enums.BusinessType;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.core.domain.SysDictData;
import com.stitchcodes.core.service.SysDictDataService;
import com.stitchcodes.core.utils.AuthUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
public class SysDictDataController extends BaseController {

    @Resource
    private SysDictDataService dictDataService;

    //从缓存中查询字典数据
    @GetMapping("/{dictType}")
    public AjaxResult getByDictType(@PathVariable String dictType) {
        List<SysDictData> dictDataList = dictDataService.selectDictDataByDictType(dictType);
        if (ObjectUtils.isNull(dictDataList)) {
            dictDataList = new ArrayList<SysDictData>();
        }
        return AjaxResult.success(dictDataList);
    }

    //分页查询数据字典数据
    @PreAuthorize("@pm.hasPerms('sys:dict:list')")
    @GetMapping("/list")
    public TableData list(SysDictData dictData) {
        startPage();
        List<SysDictData> dictDataList = dictDataService.selectDictDataList(dictData);
        return getTableData(dictDataList);
    }

    //通过字典Code查询指点数据
    @GetMapping("/code/{dictCode}")
    public AjaxResult getByCode(@PathVariable Long dictCode) {
        return AjaxResult.success(dictDataService.selectDictDataByCode(dictCode));
    }

    //创建字典数据
    @Log(title = "新增字典数据",BusinessType = BusinessType.INSERT)
    @PreAuthorize("@pm.hasPerms('sys:dict:create')")
    @PostMapping()
    public AjaxResult create(@RequestBody SysDictData dictData) {
        //检查标签是否重复
        dictDataService.checkDictLabelUnique(dictData);
        dictData.setCreateUser(AuthUtils.getLoginUserName());
        return toAjax(dictDataService.createDictData(dictData));
    }

    //更新字典数据
    @Log(title = "修改字典数据",BusinessType = BusinessType.UPDATE)
    @PreAuthorize("@pm.hasPerms('sys:dict:update')")
    @PutMapping
    public AjaxResult update(@RequestBody SysDictData dictData) {
        //检查标签是否重复
        dictDataService.checkDictLabelUnique(dictData);
        dictData.setUpdateUser(AuthUtils.getLoginUserName());
        return toAjax(dictDataService.updateDictData(dictData));
    }

    //删除字典数据
    @Log(title = "删除字典数据",BusinessType = BusinessType.DELETE)
    @PreAuthorize("@pm.hasPerms('sys:dict:delete')")
    @DeleteMapping("/{dictType}/{dictCodes}")
    public AjaxResult delete(@PathVariable String dictType, @PathVariable Long[] dictCodes) {
        return toAjax(dictDataService.deleteDictData(dictType, dictCodes));
    }

}
