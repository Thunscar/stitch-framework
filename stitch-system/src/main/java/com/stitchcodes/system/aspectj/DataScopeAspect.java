package com.stitchcodes.system.aspectj;

import com.stitchcodes.common.annotation.DataScope;
import com.stitchcodes.common.domain.BaseEntity;
import com.stitchcodes.common.utils.CollectionUtils;
import com.stitchcodes.common.utils.ConvertUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysRole;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.domain.model.LoginUser;
import com.stitchcodes.core.utils.AuthUtils;
import com.stitchcodes.system.security.PermissionContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.stitchcodes.common.constant.DataScopeConstant.*;

/**
 * @Author: stitch
 * @Date: 2023/5/7 21:34
 * @Description: 数据权限过滤
 */
@Aspect
@Component
public class DataScopeAspect {

    @Before("@annotation(controllerDataScope)")
    public void doBefore(JoinPoint point, DataScope controllerDataScope) {
        clearDataScope(point);
        handleDataScope(point, controllerDataScope);
    }

    protected void handleDataScope(final JoinPoint joinPoint, DataScope controllerDataScope) {
        LoginUser loginUser = AuthUtils.getLoginUser();
        if (ObjectUtils.isNotNull(loginUser)) {
            SysUser user = loginUser.getUser();
            //只有非管理员才需要进行数据过滤
            if (ObjectUtils.isNotNull(user) && !user.isAdmin()) {
                String perms = StringUtils.defaultIfEmpty(controllerDataScope.permission(), PermissionContextHolder.getContext());
                StringBuilder sql = new StringBuilder();
                List<String> conditions = new ArrayList<>();
                String deptAlias = controllerDataScope.deptAlias();
                String userAlias = controllerDataScope.userAlias();

                for (SysRole role : user.getRoles()) {
                    String dataScope = role.getDataScope();
                    if (!DATA_SCOPE_CUSTOM.equals(dataScope) && conditions.contains(dataScope)) {
                        //已处理
                        continue;
                    }
                    if (StringUtils.isNotEmpty(perms) && CollectionUtils.isNotEmpty(role.getPerms()) && !StringUtils.containsAny(role.getPerms(), ConvertUtils.toStrArray(perms))) {
                        //该角色无此查询权限
                        continue;
                    }
                    if (DATA_SCOPE_ALL.equals(dataScope)) {
                        //清空拼接的sql
                        sql = new StringBuilder();
                        conditions.add(dataScope);
                        break;
                    }
                    if (DATA_SCOPE_CUSTOM.equals(dataScope)) {
                        String formatSql = String.format(" OR %s.dept_id IN (SELECT dept_id FROM sys_role_dept WHERE role_id = %s)", deptAlias, role.getRoleId());
                        sql.append(formatSql);
                    }
                    if (DATA_SCOPE_DEPT.equals(dataScope)) {
                        String formatSql = String.format(" OR %s.dept_id = %s", deptAlias, user.getDeptId());
                        sql.append(formatSql);
                    }
                    if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope)) {
                        String formatSql = String.format(" OR %s.dept_id IN (SELECT dept_id FROM sys_dept WHERE dept_id = %s OR find_in_set( %s , ancestors ))", deptAlias, user.getDeptId(), user.getDeptId());
                        sql.append(formatSql);
                    }
                    if (DATA_SCOPE_SELF.equals(dataScope)) {
                        if (StringUtils.isNotEmpty(userAlias)) {
                            //具有个人查询条件
                            String formatSql = String.format(" OR %s.user_id = %s", userAlias, user.getUserId());
                            sql.append(formatSql);
                        } else {
                            //不具有个人查询条件 不可查出数据
                            String formatSql = String.format(" OR %s.dept_id = 0", deptAlias);
                            sql.append(formatSql);
                        }
                    }
                    conditions.add(dataScope);
                }

                if (CollectionUtils.isEmpty(conditions)) {
                    //无访问权限 不可查询数据
                    String formatSql = String.format(" OR %s.dept_id = 0", deptAlias);
                    sql.append(formatSql);
                }

                if (StringUtils.isNotEmpty(sql.toString())) {
                    Object entityParam = joinPoint.getArgs()[0];
                    if (ObjectUtils.isNotNull(entityParam) && entityParam instanceof BaseEntity) {
                        BaseEntity baseEntity = (BaseEntity) entityParam;
                        String joinSql = " AND (" + sql.substring(4) + ")";
                        baseEntity.getParams().put(DATA_SCOPE, joinSql);
                    }
                }
            }
        }
    }

    //清空参数中的dataScope
    private void clearDataScope(final JoinPoint joinPoint) {
        Object entityParam = joinPoint.getArgs()[0];
        if (ObjectUtils.isNotNull(entityParam) && entityParam instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entityParam;
            baseEntity.getParams().put(DATA_SCOPE, "");
        }
    }

}
