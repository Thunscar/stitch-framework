package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.annotation.Log;
import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.enums.BusinessType;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.domain.model.LoginUser;
import com.stitchcodes.core.domain.model.PasswordModel;
import com.stitchcodes.core.service.SysUserService;
import com.stitchcodes.core.utils.AuthUtils;
import com.stitchcodes.system.service.SysTokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: stitch
 * @Date: 2024/3/31 21:42
 * @Description: 用户配置信息控制器
 */
@RestController
@RequestMapping("/sys/user/profile")
public class SysUserProfileController extends BaseController {

    @Resource
    private SysUserService userService;
    @Resource
    private SysTokenService tokenService;
    @Resource
    private PasswordEncoder passwordEncoder;

    //获取用户个人信息
    @GetMapping
    public AjaxResult getUserProfile() {
        LoginUser loginUser = AuthUtils.getLoginUser();
        if (ObjectUtils.isNull(loginUser) || ObjectUtils.isNull(loginUser.getUser())) {
            return AjaxResult.error("获取用户信息失败");
        }
        AjaxResult result = new AjaxResult();
        SysUser user = loginUser.getUser();
        SysUser sysUser = new SysUser();
        sysUser.setUserId(user.getUserId());
        sysUser.setUserName(user.getUserName());
        sysUser.setNickName(user.getNickName());
        sysUser.setEmail(user.getEmail());
        sysUser.setPhone(user.getPhone());
        sysUser.setSex(user.getSex());
        sysUser.setAvatar(user.getAvatar());
        sysUser.setCreateTime(user.getCreateTime());
        sysUser.setDept(user.getDept());
        result.put("profile", user);
        String postGroup = userService.selectUserPostGroup(user.getUserId());
        String roleGroup = userService.selectUserRoleGroup(user.getUserId());
        result.put("postGroup", postGroup);
        result.put("roleGroup", roleGroup);
        return result;
    }

    //修改个人信息
    @Log(title = "修改个人信息", BusinessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult editUserProfile(@RequestBody SysUser user) {
        LoginUser loginUser = AuthUtils.getLoginUser();
        if (ObjectUtils.isNull(loginUser) || ObjectUtils.isNull(loginUser.getUser())) {
            return AjaxResult.error("获取用户信息失败");
        }
        //更新用户信息
        String updateUser = AuthUtils.getLoginUserName();
        user.setUpdateUser(updateUser);
        if (userService.updateUserProfile(user) > 0) {
            //更新缓存信息
            SysUser sysUser = loginUser.getUser();
            sysUser.setNickName(user.getNickName());
            sysUser.setPhone(user.getPhone());
            sysUser.setEmail(user.getEmail());
            sysUser.setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error();
    }


    //修改用户密码
    @Log(title = "修改用户密码", BusinessType = BusinessType.UPDATE)
    @PutMapping("/edit/pwd")
    public AjaxResult editUserPassword(@RequestBody PasswordModel passwordModel) {
        //取参数
        String oldPassword = passwordModel.getOldPassword();
        String newPassword = passwordModel.getNewPassword();
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
            return AjaxResult.error("密码为空，修改密码失败");
        }
        LoginUser loginUser = AuthUtils.getLoginUser();
        if (ObjectUtils.isNull(loginUser) || ObjectUtils.isNull(loginUser.getUser())) {
            return AjaxResult.error("获取用户信息失败");
        }
        //检查旧密码是否正确
        SysUser user = AuthUtils.getLoginUser().getUser();
        if (!passwordEncoder.matches(oldPassword,user.getPassword())) {
            return AjaxResult.error("旧密码错误，请重新输入");
        }
        //更新用户密码
        String newPwdEncode = passwordEncoder.encode(newPassword);
        SysUser updateUser = new SysUser();
        updateUser.setUserId(user.getUserId());
        updateUser.setPassword(newPwdEncode);
        updateUser.setUpdateUser(user.getUserName());
        if (userService.updateUserProfile(updateUser) > 0) {
            //更新缓存内容
            user.setPassword(newPwdEncode);
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error();
    }

}
