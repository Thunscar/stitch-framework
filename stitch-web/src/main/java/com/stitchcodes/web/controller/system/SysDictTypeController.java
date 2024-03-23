package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.core.domain.SysDictData;
import com.stitchcodes.core.domain.SysDictType;
import com.stitchcodes.core.service.SysDictDataService;
import com.stitchcodes.core.service.SysDictTypeService;
import com.stitchcodes.core.utils.AuthUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private SysDictDataService dictDataService;


    //查询字典类型列表
    @PreAuthorize("@pm.hasPerms('sys:dict:list')")
    @GetMapping("/list")
    public TableData list(SysDictType dictType) {
        startPage();
        List<SysDictType> sysDictTypes = dictTypeService.selectDictTypeList(dictType);
        return getTableData(sysDictTypes);
    }

    //查询字典类型详细信息
    @PreAuthorize("@pm.hasPerms('sys:dict:query')")
    @GetMapping("{dictTypeId}")
    public AjaxResult getById(@PathVariable Long dictTypeId) {
        return AjaxResult.success(dictTypeService.selectDictTypeById(dictTypeId));
    }

    //查询字典数据
    @GetMapping("/init")
    public AjaxResult initDictData() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<SysDictType> sysDictTypeList = dictTypeService.selectDictTypeList(null);
        for (SysDictType dictType : sysDictTypeList) {
            HashMap<String, Object> dictMap = new HashMap<>();
            List<SysDictData> dictData = dictDataService.selectDictDataByDictType(dictType.getDictType());
            dictMap.put("dictTypeCode", dictType.getDictType());
            dictMap.put("dictDataList", dictData);
            result.add(dictMap);
        }
        return AjaxResult.success(result);
    }

    //创建字典类型
    @PreAuthorize("@pm.hasPerms('sys:dict:create')")
    @PostMapping
    public AjaxResult create(@RequestBody SysDictType sysDictType) {
        //检查类型是否唯一
        dictTypeService.checkDictTypeUnique(sysDictType);
        sysDictType.setCreateUser(AuthUtils.getLoginUserName());
        return toAjax(dictTypeService.createSysDictType(sysDictType));
    }

    //修改字典类型
    @PreAuthorize("@pm.hasPerms('sys:dict:update')")
    @PutMapping
    public AjaxResult update(@RequestBody SysDictType sysDictType) {
        //检查类型是否唯一
        dictTypeService.checkDictTypeUnique(sysDictType);
        sysDictType.setUpdateUser(AuthUtils.getLoginUserName());
        return toAjax(dictTypeService.updateSysDictType(sysDictType));
    }

    //删除字典类型
    @PreAuthorize("@pm.hasPerms('sys:dict:delete')")
    @DeleteMapping("/{dictTypeIds}")
    public AjaxResult delete(@PathVariable Long[] dictTypeIds) {
        return toAjax(dictTypeService.deleteBatchSysDictType(dictTypeIds));
    }

    //刷新缓存
    @PreAuthorize("@pm.hasPerms('sys:dict:refresh')")
    @PostMapping("/refresh")
    public AjaxResult refreshCache() {
        dictTypeService.resetDictCache();
        return success();
    }
}
