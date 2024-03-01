package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.constant.CommonConstants;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.domain.model.LoginBody;
import com.stitchcodes.core.domain.SysMenu;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.domain.vo.RouterVo;
import com.stitchcodes.core.domain.vo.UserVo;
import com.stitchcodes.core.service.SysMenuService;
import com.stitchcodes.core.utils.AuthUtils;
import com.stitchcodes.system.service.SysLoginService;
import com.stitchcodes.system.service.SysPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @Author: stitch
 * @Date: 2023/5/6 10:54
 * @Description: 登录验证
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends BaseController {

    @Resource
    private SysLoginService loginService;
    @Resource
    private SysPermissionService permissionService;
    @Resource
    private SysMenuService menuService;

    //用户登录
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getUuid(), loginBody.getCode());
        AjaxResult result = AjaxResult.success();
        result.put(CommonConstants.TOKEN, token);
        return result;
    }

    //用户登出
    @PostMapping("/logout")
    public AjaxResult logout() {
        loginService.logout();
        return AjaxResult.success();
    }

    //获取用户信息接口
    @GetMapping("/user/info")
    public AjaxResult getUserInfo() {
        SysUser user = AuthUtils.getLoginUser().getUser();
        UserVo userVo = new UserVo(user);
        Set<String> menus = permissionService.getMenuPermission(user);
        Set<String> roles = permissionService.getRolePermission(user);
        AjaxResult result = new AjaxResult();
        result.put("user", userVo);
        result.put("menus", menus);
        result.put("roles", roles);
        return result;
    }

    //加载路由信息
    @GetMapping("/routers")
    public AjaxResult getRouters() {
        //获取可访问的路由
        Long loginUserId = AuthUtils.getLoginUserId();
        List<SysMenu> sysMenus = menuService.selectMenuTreeByUserId(loginUserId);
        List<RouterVo> routerVos = menuService.buildRouterVos(sysMenus);
        return AjaxResult.success(routerVos);
    }

}
