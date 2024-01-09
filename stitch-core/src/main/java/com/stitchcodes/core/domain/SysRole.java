package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;
import java.util.Set;

/**
 * 角色信息表
 *
 * @TableName sys_role
 */
public class SysRole extends BaseEntity implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer roleSort;

    /**
     * 数据范围(1全部数据权限2自定义数据权限3本部门数据权限4本部门以下数据权限)
     */
    private String dataScope;

    /**
     * 状态(0正常1停用)
     */
    private String status;

    /**
     * 是否删除(0存在1已删除)
     */
    private String isDelete;

    /**
     * 部门组
     */
    private Long[] deptIds;

    /**
     * 菜单组
     */
    private Long menuIds;

    /**
     * 权限组
     */
    private Set<String> perms;

    public Long[] getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(Long[] deptIds) {
        this.deptIds = deptIds;
    }

    public Long getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long menuIds) {
        this.menuIds = menuIds;
    }

    public Set<String> getPerms() {
        return perms;
    }

    public void setPerms(Set<String> perms) {
        this.perms = perms;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 角色权限字符串
     */
    public String getRoleKey() {
        return roleKey;
    }

    /**
     * 角色权限字符串
     */
    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    /**
     * 显示顺序
     */
    public Integer getRoleSort() {
        return roleSort;
    }

    /**
     * 显示顺序
     */
    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    /**
     * 数据范围(1全部数据权限2自定义数据权限3本部门数据权限4本部门以下数据权限)
     */
    public String getDataScope() {
        return dataScope;
    }

    /**
     * 数据范围(1全部数据权限2自定义数据权限3本部门数据权限4本部门以下数据权限)
     */
    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
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
     * 是否删除(0存在1已删除)
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除(0存在1已删除)
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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
        SysRole other = (SysRole) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
                && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
                && (this.getRoleKey() == null ? other.getRoleKey() == null : this.getRoleKey().equals(other.getRoleKey()))
                && (this.getRoleSort() == null ? other.getRoleSort() == null : this.getRoleSort().equals(other.getRoleSort()))
                && (this.getDataScope() == null ? other.getDataScope() == null : this.getDataScope().equals(other.getDataScope()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
                && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getRoleKey() == null) ? 0 : getRoleKey().hashCode());
        result = prime * result + ((getRoleSort() == null) ? 0 : getRoleSort().hashCode());
        result = prime * result + ((getDataScope() == null) ? 0 : getDataScope().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
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
        sb.append(", roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleKey=").append(roleKey);
        sb.append(", roleSort=").append(roleSort);
        sb.append(", dataScope=").append(dataScope);
        sb.append(", status=").append(status);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}