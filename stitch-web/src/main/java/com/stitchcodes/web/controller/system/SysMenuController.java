package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.core.domain.SysMenu;
import com.stitchcodes.core.service.SysMenuService;
import com.stitchcodes.core.utils.AuthUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: stitch
 * @Date: 2023/5/22 13:12
 * @Description: 菜单请求控制器
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

    @Resource
    private SysMenuService menuService;

    @GetMapping("/list")
    public AjaxResult list(SysMenu menu) {
        Long loginUserId = AuthUtils.getLoginUserId();
        List<SysMenu> sysMenus = menuService.selectSysMenuList(menu, loginUserId);
        return AjaxResult.success(sysMenus);
    }

    @GetMapping("/{menuId}")
    public AjaxResult getById(@PathVariable Long menuId) {
        SysMenu sysMenu = menuService.selectSysMenuById(menuId);
        return AjaxResult.success(sysMenu);
    }

    @PostMapping
    public AjaxResult add(@RequestBody SysMenu menu) {
        if (!menuService.checkSysMenuNameUnique(menu)) {
            return AjaxResult.error("菜单名称已存在");
        }
        //检查父级ID合法性
        menuService.checkParentIdValid(menu);

        menu.setCreateUser(AuthUtils.getLoginUserName());
        menuService.createSysMenu(menu);
        return AjaxResult.success();
    }

    @DeleteMapping("/{menuId}")
    public AjaxResult delete(@PathVariable Long menuId) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(menuId);
        if (menuService.hasChildrenMenu(sysMenu)) {
            return AjaxResult.error("请先移除该菜单的子菜单");
        }
        menuService.removeSysMenu(menuId);
        return AjaxResult.success();
    }

    @PutMapping
    public AjaxResult update(@RequestBody SysMenu menu) {
        if (!menuService.checkSysMenuNameUnique(menu)) {
            return AjaxResult.error("菜单名称已存在");
        }
        //检查父级ID合法性
        menuService.checkParentIdValid(menu);
        menu.setUpdateUser(AuthUtils.getLoginUserName());
        menuService.updateSysMenu(menu);
        return AjaxResult.success();
    }
}
