package com.stitchcodes.common.controller;

import com.github.pagehelper.PageInfo;
import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.constant.HttpStatus;
import com.stitchcodes.common.utils.DateUtils;
import com.stitchcodes.common.utils.page.PageUtil;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2023/4/28 16:26
 * @Description: 基础controller
 */
public class BaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 重定向
     */
    public String redirect(String url) {
        return String.format("redirect:%s", url);
    }

    /**
     * 开启分页
     */
    public void startPage() {
        PageUtil.startPage();
    }

    public TableData getTableData(List<?> list) {
        TableData tableData = new TableData();
        tableData.setCode(HttpStatus.SUCCESS);
        tableData.setMsg("Query Success");
        tableData.setList(list);
        tableData.setTotal(new PageInfo<>(list).getTotal());
        return tableData;
    }

    /**
     * 清理分页变量
     */
    public void clearPage() {
        PageUtil.clearPage();
    }

    /**
     * 返回结果
     *
     * @return AjaxResult
     */
    public AjaxResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    public AjaxResult success() {
        return AjaxResult.success();
    }

    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    public AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    public AjaxResult error() {
        return AjaxResult.error();
    }

    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    public AjaxResult error(int code, String message) {
        return AjaxResult.error(code, message);
    }

    public AjaxResult warn(String message) {
        return AjaxResult.warn(message);
    }

    public AjaxResult warn(String message, Object data) {
        return AjaxResult.warn(message, data);
    }
}
