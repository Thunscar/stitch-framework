package com.stitchcodes.web.controller.system;

import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.core.domain.SysMenu;
import com.stitchcodes.core.service.SysMenuService;
import com.stitchcodes.core.utils.AuthUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    //查询菜单列表
    @GetMapping("/list")
    public AjaxResult list(SysMenu menu) {
        Long loginUserId = AuthUtils.getLoginUserId();
        return AjaxResult.success(menuService.selectSysMenuList(menu, loginUserId));
    }

    //获取菜单信息
    @GetMapping("/{menuId}")
    public AjaxResult getById(@PathVariable Long menuId) {
        return AjaxResult.success(menuService.selectSysMenuById(menuId));
    }

    //创建菜单
    @PostMapping
    public AjaxResult create(@RequestBody SysMenu menu) {
        //检查菜单名称是否唯一
        menuService.checkSysMenuNameUnique(menu);
        //检查父级ID合法性
        menuService.checkParentIdValid(menu);
        //检查菜单路径唯一性
        menuService.checkSysMenuPathUnique(menu);

        menu.setCreateUser(AuthUtils.getLoginUserName());
        return toAjax(menuService.createSysMenu(menu));
    }

    //删除菜单
    @DeleteMapping("/{menuId}")
    public AjaxResult delete(@PathVariable Long menuId) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(menuId);
        if (menuService.hasChildrenMenu(sysMenu)) {
            return AjaxResult.error("请先移除该菜单的子菜单");
        }
        return toAjax(menuService.removeSysMenu(menuId));
    }

    //更新菜单信息
    @PutMapping
    public AjaxResult update(@RequestBody SysMenu menu) {
        //检查菜单名称是否唯一
        menuService.checkSysMenuNameUnique(menu);
        //检查父级ID合法性
        menuService.checkParentIdValid(menu);
        menu.setUpdateUser(AuthUtils.getLoginUserName());
        return toAjax(menuService.updateSysMenu(menu));
    }
}
