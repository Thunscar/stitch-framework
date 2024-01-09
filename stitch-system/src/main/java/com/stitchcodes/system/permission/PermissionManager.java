package com.stitchcodes.system.permission;

import com.stitchcodes.common.constant.PermissionConstants;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.model.LoginUser;
import com.stitchcodes.core.utils.AuthUtils;
import com.stitchcodes.system.security.PermissionContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @Author: stitch
 * @Date: 2023/5/8 23:09
 * @Description: 权限管理
 */
@Component
public class PermissionManager {

    /**
     * 判断用户是有拥有某个权限
     *
     * @param permission 权限字符串
     * @return 是有拥有该权限
     */
    public boolean hasPermission(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        LoginUser loginUser = AuthUtils.getLoginUser();
        if (ObjectUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions())) {
            return false;
        }
        PermissionContextHolder.setContext(permission);
        return hasPermission(loginUser.getPermissions(), permission);
    }


    /**
     * 判断权限列表中是否包含权限字符串
     *
     * @param permissions 用户权限字符串列表
     * @param permission  权限字符串
     * @return 是否包含权限字符串
     */
    public boolean hasPermission(Set<String> permissions, String permission) {
        return permissions.contains(PermissionConstants.ALL_MENU_PERMISSION) || permissions.contains(permission.trim());
    }


}
