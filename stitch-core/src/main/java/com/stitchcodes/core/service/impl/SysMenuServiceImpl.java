package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.constant.UserConstants;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.utils.CollectionUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysMenu;
import com.stitchcodes.core.domain.vo.MetaVo;
import com.stitchcodes.core.domain.vo.RouterVo;
import com.stitchcodes.core.mapper.SysMenuMapper;
import com.stitchcodes.core.service.SysMenuService;
import com.stitchcodes.core.utils.AuthUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author chenwei
 * @description 针对表【sys_menu(系统菜单表)】的数据库操作Service实现
 * @createDate 2023-04-28 13:20:02
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public Set<String> selectMenuPermissionByRoleId(Long roleId) {
        List<String> menuPerms = menuMapper.selectMenuPermsByRoleId(roleId);
        HashSet<String> menuPermSet = new HashSet<>();
        for (String menuPerm : menuPerms) {
            if (StringUtils.isNotEmpty(menuPerm)) {
                menuPermSet.addAll(Arrays.asList(menuPerm.trim().split(",")));
            }
        }
        return menuPermSet;
    }

    @Override
    public Set<String> selectMenuPermissionByUserId(Long userId) {
        List<String> menuPerms = menuMapper.selectMenuPermsByUserId(userId);
        HashSet<String> menuPermSet = new HashSet<>();
        for (String menuPerm : menuPerms) {
            if (StringUtils.isNotEmpty(menuPerm)) {
                menuPermSet.addAll(Arrays.asList(menuPerm.trim().split(",")));
            }
        }
        return menuPermSet;
    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus;
        if (AuthUtils.isAdmin(userId)) {
            menus = menuMapper.selectMenuTreeAll();
        } else {
            menus = menuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0L);
    }

    @Override
    public List<RouterVo> buildRouterVos(List<SysMenu> sysMenus) {
        List<RouterVo> routerVos = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            RouterVo routerVo = new RouterVo();
            routerVo.setName(getRouterName(sysMenu));
            routerVo.setPath(sysMenu.getPath());
            routerVo.setComponent(getComponent(sysMenu));
            routerVo.setQuery(sysMenu.getQuery());
            routerVo.setAlwaysShow(true);
            routerVo.setVisible(UserConstants.MENU_VISIBLE.equals(sysMenu.getVisible()));
            MetaVo meta = new MetaVo();
            meta.setTitle(sysMenu.getMenuName());
            meta.setIcon(sysMenu.getIcon());
            meta.setNoCache(UserConstants.IS_CACHE.equals(sysMenu.getIsCache()));
            meta.setLink(UserConstants.IS_LINK.equals(sysMenu.getIsFrame()) ? sysMenu.getPath() : null);
            routerVo.setMeta(meta);
            List<SysMenu> childMenu = sysMenu.getChildrenMenu();
            if (CollectionUtils.isNotEmpty(childMenu) && UserConstants.DIR_TYPE.equals(sysMenu.getMenuType())) {
                List<RouterVo> childRouters = buildRouterVos(childMenu);
                routerVo.setChildren(childRouters);
            }
            routerVos.add(routerVo);
        }
        return routerVos;
    }

    @Override
    public List<SysMenu> selectSysMenuList(SysMenu menu, Long userId) {
        List<SysMenu> menus = null;
        if (AuthUtils.isAdmin(userId)) {
            //管理员
            menus = menuMapper.selectMenuList(menu);
        } else {
            //非管理员
            menu.getParams().put("userId", userId);
            menus = menuMapper.selectMenuListByUserId(menu);
        }
        return menus;
    }

    @Override
    public SysMenu selectSysMenuById(Long menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    @Override
    public boolean createSysMenu(SysMenu menu) {
        if (menu.getParentId() != UserConstants.ROOT_PARENT_ID) {
            SysMenu parentMenu = selectSysMenuById(menu.getParentId());
            if (ObjectUtils.isNull(parentMenu)) {
                throw new StitchException("上级菜单不存在,不可创建菜单");
            }
            if (parentMenu.getStatus().equals(UserConstants.STATUS_BLOCK)) {
                throw new StitchException("上级菜单已停用,不可创建菜单");
            }
        }
        return menuMapper.insertMenu(menu) > 0;
    }

    @Override
    public boolean checkSysMenuNameUnique(SysMenu menu) {
        return menuMapper.selectSysMenuByName(menu).isEmpty();
    }

    @Override
    public boolean hasChildrenMenu(SysMenu menu) {
        return menuMapper.selectCountByParentId(menu.getMenuId()) > 0;
    }

    @Override
    public boolean removeSysMenu(Long menu) {
        return menuMapper.deleteMenu(menu) > 0;
    }

    @Override
    public boolean updateSysMenu(SysMenu menu) {
        return menuMapper.updateMenu(menu) > 0;
    }

    @Override
    public void checkParentIdValid(SysMenu menu) {
        List<Long> unValidIds = menuMapper.selectUnValidParentId(menu);
        if (unValidIds.contains(menu.getParentId())) {
            throw new StitchException("不合法的父级菜单,请重新选择");
        }
    }

    @Override
    public void checkPathValid(SysMenu menu) {

    }

    private List<SysMenu> getChildPerms(List<SysMenu> menus, long parentId) {
        List<SysMenu> resultMenus = new ArrayList<>();
        Iterator<SysMenu> menuIterator = menus.iterator();
        while (menuIterator.hasNext()) {
            SysMenu menu = menuIterator.next();
            if (menu.getParentId() == parentId) {
                List<SysMenu> childMenu = getChildPerms(menus, menu.getMenuId());
                menu.setChildrenMenu(childMenu);
                resultMenus.add(menu);
            }
        }
        return resultMenus;
    }

    private String getRouterName(SysMenu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    private String getComponent(SysMenu menu) {
        String component = UserConstants.LAYOUT;
        if (isParentView(menu)) {
            component = UserConstants.PARENT_MENU;
        }
        if (isOuterLink(menu)) {
            component = UserConstants.OUTER_LINK;
        }
        if (isFuncView(menu)) {
            component = menu.getComponent();
            if (StringUtils.isEmpty(component)) {
                component = menu.getPath();
            }
        }
        return component;
    }

    private boolean isMenuFrame(SysMenu menu) {
        return UserConstants.ROOT_PARENT_ID == menu.getParentId() && UserConstants.MENU_TYPE.equals(menu.getMenuType()) && UserConstants.NO_FRAME.equals(menu.getIsFrame());
    }

    // 是父目录
    private boolean isParentView(SysMenu menu) {
        return UserConstants.DIR_TYPE.equals(menu.getMenuType()) && CollectionUtils.isNotEmpty(menu.getChildrenMenu());
    }

    // 是外链
    private boolean isOuterLink(SysMenu menu) {
        return UserConstants.OUTER_LINK.equals(menu.getIsFrame()) && StringUtils.isNotEmpty(menu.getPath());
    }

    // 是功能菜单
    private boolean isFuncView(SysMenu menu) {
        return UserConstants.DIR_TYPE.equals(menu.getMenuType()) && CollectionUtils.isEmpty(menu.getChildrenMenu());
    }
}




