package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.util.List;

/**
 * 系统菜单表
 *
 * @TableName sys_menu
 */
public class SysMenu extends BaseEntity {
    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     *
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 路由参数
     */
    private String query;

    /**
     * 是否外链(0是1否)
     */
    private String isFrame;

    /**
     * 是否缓存(0缓存1不缓存)
     */
    private String isCache;

    /**
     * 菜单类型(M目录C菜单F按钮)
     */
    private String menuType;

    /**
     * 是否展示(0展示1不展示)
     */
    private String visible;

    /**
     * 状态(0正常1停用)
     */
    private String status;

    /**
     * 权限
     */
    private String perms;

    /**
     * 图标名称
     */
    private String icon;

    /**
     * 是否删除
     */
    private String isDelete;

    /**
     * 子菜单
     */
    private List<SysMenu> childrenMenu;

    public List<SysMenu> getChildrenMenu() {
        return childrenMenu;
    }

    public void setChildrenMenu(List<SysMenu> childrenMenu) {
        this.childrenMenu = childrenMenu;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 菜单ID
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     *
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     *
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 显示顺序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 显示顺序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 路由地址
     */
    public String getPath() {
        return path;
    }

    /**
     * 路由地址
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 组件地址
     */
    public String getComponent() {
        return component;
    }

    /**
     * 组件地址
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * 路由参数
     */
    public String getQuery() {
        return query;
    }

    /**
     * 路由参数
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * 是否外链(0否1是)
     */
    public String getIsFrame() {
        return isFrame;
    }

    /**
     * 是否外链(0否1是)
     */
    public void setIsFrame(String isFrame) {
        this.isFrame = isFrame;
    }

    /**
     * 是否缓存(0缓存1不缓存)
     */
    public String getIsCache() {
        return isCache;
    }

    /**
     * 是否缓存(0缓存1不缓存)
     */
    public void setIsCache(String isCache) {
        this.isCache = isCache;
    }

    /**
     * 菜单类型(M目录C菜单F按钮)
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 菜单类型(M目录C菜单F按钮)
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * 是否展示(0展示1不展示)
     */
    public String getVisible() {
        return visible;
    }

    /**
     * 是否展示(0展示1不展示)
     */
    public void setVisible(String visible) {
        this.visible = visible;
    }

    /**
     * 状态(0正常1停用)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态(0正常1停用)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 权限
     */
    public String getPerms() {
        return perms;
    }

    /**
     * 权限
     */
    public void setPerms(String perms) {
        this.perms = perms;
    }

    /**
     * 图标名称
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 图标名称
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysMenu other = (SysMenu) that;
        return (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId())) && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName())) && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId())) && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum())) && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath())) && (this.getComponent() == null ? other.getComponent() == null : this.getComponent().equals(other.getComponent())) && (this.getQuery() == null ? other.getQuery() == null : this.getQuery().equals(other.getQuery())) && (this.getIsFrame() == null ? other.getIsFrame() == null : this.getIsFrame().equals(other.getIsFrame())) && (this.getIsCache() == null ? other.getIsCache() == null : this.getIsCache().equals(other.getIsCache())) && (this.getMenuType() == null ? other.getMenuType() == null : this.getMenuType().equals(other.getMenuType())) && (this.getVisible() == null ? other.getVisible() == null : this.getVisible().equals(other.getVisible())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getPerms() == null ? other.getPerms() == null : this.getPerms().equals(other.getPerms())) && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser())) && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime())) && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getComponent() == null) ? 0 : getComponent().hashCode());
        result = prime * result + ((getQuery() == null) ? 0 : getQuery().hashCode());
        result = prime * result + ((getIsFrame() == null) ? 0 : getIsFrame().hashCode());
        result = prime * result + ((getIsCache() == null) ? 0 : getIsCache().hashCode());
        result = prime * result + ((getMenuType() == null) ? 0 : getMenuType().hashCode());
        result = prime * result + ((getVisible() == null) ? 0 : getVisible().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPerms() == null) ? 0 : getPerms().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", menuName=").append(menuName);
        sb.append(", parentId=").append(parentId);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", path=").append(path);
        sb.append(", component=").append(component);
        sb.append(", query=").append(query);
        sb.append(", isFrame=").append(isFrame);
        sb.append(", isCache=").append(isCache);
        sb.append(", menuType=").append(menuType);
        sb.append(", visible=").append(visible);
        sb.append(", status=").append(status);
        sb.append(", perms=").append(perms);
        sb.append(", icon=").append(icon);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}