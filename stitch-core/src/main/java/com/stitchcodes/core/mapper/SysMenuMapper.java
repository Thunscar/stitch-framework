package com.stitchcodes.core.mapper;


import com.stitchcodes.core.domain.SysMenu;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_menu(系统菜单表)】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:10
 * @Entity generator.domain.SysMenu
 */
public interface SysMenuMapper {

    /**
     * 新增菜单
     *
     * @param sysMenu 菜单信息
     * @return 结果
     */
    int insertMenu(SysMenu sysMenu);

    /**
     * 更新菜单信息
     *
     * @param sysMenu 系统菜单
     * @return 结果
     */
    int updateMenu(SysMenu sysMenu);

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteMenu(Long menuId);

    /**
     * 根据ID查询菜单列表
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    SysMenu selectMenuById(Long menuId);

    /**
     * 通过角色ID查询菜单权限列表
     *
     * @param roleId 角色ID
     * @return 菜单权限列表
     */
    List<String> selectMenuPermsByRoleId(Long roleId);

    /**
     * 通过用户ID查询菜单权限列表
     *
     * @param userId 用户ID
     * @return 菜单权限列表
     */
    List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 查询菜单列表
     *
     * @param menu   菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 根据用户ID和菜单参数查询菜单列表 (用户ID放入params中)
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuListByUserId(SysMenu menu);

    /**
     * 查询所有菜单信息
     *
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeAll();

    /**
     * 通过用户ID查询菜单信息
     *
     * @param userId 用户ID
     * @return 系统菜单
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 根据名称查询系统菜单
     *
     * @param menuName 菜单名称
     * @return 菜单列表
     */
    SysMenu selectSysMenuByName(SysMenu sysMenu);

    /**
     * 根据父ID查询子菜单数量
     *
     * @param parentId 父ID
     * @return 结果
     */
    Long selectCountByParentId(Long parentId);

    /**
     * 查询无效的父级ID
     *
     * @param menu 菜单列表
     * @return 无效的菜单ID列表
     */
    List<Long> selectUnValidParentId(SysMenu menu);


    /**
     * 通过路径查找菜单
     *
     * @param menu 菜单信息
     * @return
     */
    SysMenu selectSysMenuByPath(SysMenu menu);
}




