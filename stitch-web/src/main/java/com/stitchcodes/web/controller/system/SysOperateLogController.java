package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.annotation.Log;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.enums.BusinessType;
import com.stitchcodes.common.excel.ExcelUtil;
import com.stitchcodes.core.domain.SysOperateLog;
import com.stitchcodes.core.service.SysOperateLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2024/3/27 01:26
 * @Description: 操作日志控制器
 */
@RestController
@RequestMapping("/sys/operlog")
public class SysOperateLogController extends BaseController {

    @Resource
    private SysOperateLogService operateLogService;

    //查询操作日志
    @GetMapping("/list")
    @PreAuthorize("@pm.hasPerms('sys:operlog:list')")
    public TableData list(SysOperateLog operateLog) {
        startPage();
        List<SysOperateLog> sysOperateLogs = operateLogService.selectOperateLogList(operateLog);
        return getTableData(sysOperateLogs);
    }

    //导出操作日志
    @Log(title = "导出操作日志",BusinessType = BusinessType.EXPORT)
    @PreAuthorize("@pm.hasPerms('sys:operlog:export')")
    @PostMapping("/export")
    public void export(SysOperateLog operateLog, HttpServletResponse response) throws IOException {
        List<SysOperateLog> sysOperateLogList = operateLogService.selectOperateLogList(operateLog);
        ExcelUtil<SysOperateLog> excelUtil = new ExcelUtil<>(SysOperateLog.class);
        excelUtil.exportExcel("系统操作日志", sysOperateLogList, response);
    }
}
