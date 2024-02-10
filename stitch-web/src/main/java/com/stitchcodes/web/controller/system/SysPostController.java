package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.excel.ExcelUtil;
import com.stitchcodes.core.domain.SysPost;
import com.stitchcodes.core.service.SysPostService;
import com.stitchcodes.core.utils.AuthUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2024/1/11 06:50
 * @Description:
 */
@RestController
@RequestMapping("/sys/post")
public class SysPostController extends BaseController {

    @Resource
    private SysPostService postService;


    //查询岗位列表
    @GetMapping("/list")
    public TableData list(SysPost sysPost) {
        startPage();
        List<SysPost> sysPosts = postService.selectSysPost(sysPost);
        return getTableData(sysPosts);
    }

    //获取岗位信息
    @GetMapping("{postId}")
    public AjaxResult getById(@PathVariable Long postId) {
        return success(postService.selectSysPostById(postId));
    }

    //批量删除岗位
    @DeleteMapping("{postIds}")
    public AjaxResult delete(@PathVariable Long[] postIds) {
        return toAjax(postService.deletePostByIds(postIds));
    }

    //创建岗位
    @PostMapping
    public AjaxResult create(@RequestBody SysPost sysPost) {
        //检查岗位编码是否唯一
        postService.checkPostCodeUnique(sysPost);
        //设置创建人
        sysPost.setCreateUser(AuthUtils.getLoginUserName());
        return toAjax(postService.createSysPost(sysPost));
    }

    //更新岗位信息
    @PutMapping
    public AjaxResult update(@RequestBody SysPost sysPost) {
        //检查岗位编码是否唯一
        postService.checkPostCodeUnique(sysPost);
        //设置更新人
        sysPost.setUpdateUser(AuthUtils.getLoginUserName());
        return toAjax(postService.updateSysPost(sysPost));
    }

    //导出岗位列表到excel
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPost sysPost) throws IOException {
        List<SysPost> sysPosts = postService.selectSysPost(sysPost);
        ExcelUtil<SysPost> excelUtil = new ExcelUtil<>(SysPost.class);
        excelUtil.exportExcel("岗位数据", sysPosts, response);
    }

}
