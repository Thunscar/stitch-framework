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


    @GetMapping("/list")
    public TableData list(SysPost sysPost) {
        startPage();
        List<SysPost> sysPosts = postService.selectSysPost(sysPost);
        return getTableData(sysPosts);
    }

    @GetMapping("{postId}")
    public AjaxResult getById(@PathVariable Long postId) {
        return success(postService.selectSysPostById(postId));
    }

    @DeleteMapping("{postIds}")
    public AjaxResult delete(@PathVariable Long[] postIds) {
        return toAjax(postService.deletePostByIds(postIds));
    }

    @PostMapping
    public AjaxResult add(@RequestBody SysPost sysPost) {
        //检查岗位编码是否唯一
        postService.checkPostCodeUnique(sysPost);
        //设置创建人
        sysPost.setCreateUser(AuthUtils.getLoginUserName());
        return toAjax(postService.createSysPost(sysPost));
    }

    @PutMapping
    public AjaxResult update(@RequestBody SysPost sysPost) {
        //检查岗位编码是否唯一
        postService.checkPostCodeUnique(sysPost);
        //设置更新人
        sysPost.setUpdateUser(AuthUtils.getLoginUserName());
        return toAjax(postService.updateSysPost(sysPost));
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPost sysPost) throws IOException {
        List<SysPost> sysPosts = postService.selectSysPost(sysPost);
        ExcelUtil<SysPost> excelUtil = new ExcelUtil<>(SysPost.class);
        excelUtil.exportExcel("岗位数据", sysPosts, response);
    }

}
