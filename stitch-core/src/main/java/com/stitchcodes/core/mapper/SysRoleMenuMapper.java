package com.stitchcodes.core.mapper;

import com.stitchcodes.core.domain.SysRoleMenu;

import java.util.List;

/**
* @author stitch
* @description 针对表【sys_role_menu(角色菜单表)】的数据库操作Mapper
* @createDate 2023-04-29 14:12:11
* @Entity generator.domain.SysRoleMenu
*/
public interface SysRoleMenuMapper {

    /**
     * 获取角色被授权的功能菜单
     * @param roleId 角色ID
     * @return
     */
    Long[] selectAuthorizedMenu(Long roleId);

    /**
     * 保存角色菜单关联信息
     * @param roleMenus 角色菜单关联信息
     * @return
     */
    int insertRoleMenu(List<SysRoleMenu> roleMenus);

    /**
     * 删除授权的功能菜单
     * @param roleId 角色ID
     * @return
     */
    int deleteAuthorizedMenu(Long roleId);

    /**
     * 删除角色授权的功能菜单
     * @param roleIds 角色ID数组
     * @return
     */
    int deleteAuthorizedMenus(Long[] roleIds);


}




