package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.api.TableData;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.excel.ExcelUtil;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.service.SysConfigService;
import com.stitchcodes.core.service.SysUserService;
import com.stitchcodes.core.utils.AuthUtils;
import com.stitchcodes.system.service.SysPasswordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2023/6/9 11:48
 * @Description: 用户管理控制器
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService userService;
    @Resource
    private SysConfigService configService;
    @Resource
    private SysPasswordService passwordService;

    @GetMapping("/list")
    public TableData list(SysUser user) {
        startPage();
        List<SysUser> sysUsers = userService.selectUserList(user);
        return getTableData(sysUsers);
    }

    @GetMapping("{userId}")
    public AjaxResult getUserInfo(@PathVariable Long userId) {
        //校验数据权限
        userService.checkUserDataScope(userId);
        SysUser sysUser = userService.selectUserById(userId);
        return AjaxResult.success(sysUser);
    }

    @PostMapping
    public AjaxResult add(@RequestBody SysUser user) {
        //校验用户名是否唯一
        userService.checkUserNameUnique(user);
        user.setCreateUser(AuthUtils.getLoginUserName());
        userService.insertUser(user);
        return AjaxResult.success();
    }

    @PutMapping
    public AjaxResult update(@RequestBody SysUser user) {
        userService.checkUserDataScope(user.getUserId());
        //检查用户名是否唯一
        userService.checkUserNameUnique(user);
        user.setUpdateUser(AuthUtils.getLoginUserName());
        userService.updateUser(user);
        return AjaxResult.success();
    }

    @DeleteMapping("{userIds}")
    public AjaxResult delete(@PathVariable Long[] userIds) {
        //检查数据权限
        for (Long userId : userIds) {
            userService.checkUserDataScope(userId);
        }
        userService.deleteUserByIds(userIds);
        return AjaxResult.success();
    }

    @PostMapping("export")
    public void export(HttpServletResponse response, SysUser user) throws IOException {
        List<SysUser> sysUsers = userService.selectUserList(user);
        ExcelUtil<SysUser> excelUtil = new ExcelUtil<>(SysUser.class);
        excelUtil.exportExcel("用户数据", sysUsers, response);
    }

    @PostMapping("/reset/{userId}")
    public AjaxResult resetPassword(@PathVariable Long userId) {
        //检查数据权限
        userService.checkUserDataScope(userId);
        //获取账户初始密码
        String initPassword = configService.selectAccountInitPassword();

        //将用户密码进行加密
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setPassword(initPassword);
        passwordService.encodeSysUserPassword(sysUser);
        sysUser.setUpdateUser(AuthUtils.getLoginUserName());

        //更新用户密码
        userService.resetUserPassword(sysUser);
        return AjaxResult.success();
    }

    @GetMapping("/")

}
