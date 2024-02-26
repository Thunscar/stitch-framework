package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysMenu;
import com.stitchcodes.core.domain.vo.RouterVo;

import java.util.List;
import java.util.Set;

/**
 * @author stitch
 * @description 针对表【sys_menu(系统菜单表)】的数据库操作Service
 * @createDate 2023-04-28 13:20:02
 */
public interface SysMenuService {

    /**
     * 通过角色ID查询菜单权限
     *
     * @param roleId 角色ID
     * @return 权限字符串列表
     */
    Set<String> selectMenuPermissionByRoleId(Long roleId);


    /**
     * 通过用户ID查询菜单权限
     *
     * @param userId 用户ID
     * @return 权限字符串列表
     */
    Set<String> selectMenuPermissionByUserId(Long userId);


    /**
     * 通过用户ID查询树形菜单列表
     *
     * @param userId 用户ID
     * @return 树形菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 构建路由视图类
     *
     * @param sysMenus 系统菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildRouterVos(List<SysMenu> sysMenus);

    /**
     * 通过菜单信息和用户ID查询菜单列表
     *
     * @param menu   菜单信息
     * @param userId 用户ID
     * @return 查询结果
     */
    List<SysMenu> selectSysMenuList(SysMenu menu, Long userId);

    /**
     * 根据ID查询系统菜单列表
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    SysMenu selectSysMenuById(Long menuId);

    /**
     * 创建系统菜单
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int createSysMenu(SysMenu menu);

    /**
     * 检查菜单名称是否唯一
     *
     * @param menu 菜单
     * @return true(唯一) false(不唯一)
     */
    void checkSysMenuNameUnique(SysMenu menu);

    /**
     * 检查菜单是否有子菜单
     *
     * @param menu 菜单信息
     * @return 结果
     */
    boolean hasChildrenMenu(SysMenu menu);

    /**
     * 移除菜单
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int removeSysMenu(Long menu);

    /**
     * 更新系统菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int updateSysMenu(SysMenu menu);


    /**
     * 检查父ID是否合法
     *
     * @param menu 菜单信息
     */
    void checkParentIdValid(SysMenu menu);

    /**
     * 检查菜单路径是否唯一
     *
     * @param menu 菜单信息
     */
    void checkSysMenuPathUnique(SysMenu menu);
}
