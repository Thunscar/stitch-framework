package com.stitchcodes.system.aspectj;

import com.alibaba.fastjson2.JSON;
import com.stitchcodes.common.annotation.Log;
import com.stitchcodes.common.enums.OperateStatus;
import com.stitchcodes.common.filter.JsonExcludeFilter;
import com.stitchcodes.common.utils.IpUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.ServletUtils;
import com.stitchcodes.common.utils.StringUtils;
import com.stitchcodes.core.domain.SysOperateLog;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.domain.model.LoginUser;
import com.stitchcodes.core.utils.AuthUtils;
import com.stitchcodes.system.manager.AsyncManager;
import com.stitchcodes.system.manager.factory.AsyncFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * @Author: stitch
 * @Date: 2024/3/24 17:13
 * @Description:
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 必须排除记录日志的字段
     */
    private static final String[] EXCLUDE_SENSITIVITY = {"password", "oldPassword", "newPassword"};

    /**
     * 操作耗时记录
     */
    private static final ThreadLocal<Long> OPERATE_TIME = new NamedThreadLocal<>("CostTime");


    /**
     * 执行请求前
     */
    @Before(value = "@annotation(controllerLog)")
    public void doBefore(Log controllerLog) {
        OPERATE_TIME.set(System.currentTimeMillis());
    }

    /**
     * 请求执行后
     */
    @AfterReturning(value = "@annotation(controllerLog)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object result) {
        handleLog(joinPoint, controllerLog, result, null);
    }

    /**
     * 执行异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, null, e);
    }


    /**
     * 处理保存日志
     */
    protected void handleLog(JoinPoint joinPoint, Log log, Object result, Exception e) {

        try {
            SysOperateLog operateLog = new SysOperateLog();

            //保存操作人
            operateLog.setTitle(log.title());
            operateLog.setBusinessType(String.valueOf(log.BusinessType().ordinal()));
            operateLog.setOperateType(String.valueOf(log.OperatorType().ordinal()));
            LoginUser loginUser = AuthUtils.getLoginUser();
            if (ObjectUtils.isNotNull(loginUser)) {
                operateLog.setOperateUser(loginUser.getUsername());
                SysUser user = loginUser.getUser();
                if (ObjectUtils.isNotNull(user) && ObjectUtils.isNotNull(user.getDept())) {
                    operateLog.setDeptName(user.getDept().getDeptName());
                }
            }

            //保存ip地址
            operateLog.setOperateIp(IpUtils.getIpAddr());
            //保存请求信息
            HttpServletRequest request = ServletUtils.getRequest();
            operateLog.setOperateUrl(StringUtils.substring(request.getRequestURI(), 0, 100));
            operateLog.setRequestMethod(request.getMethod());
            //请求方法
            String method = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
            operateLog.setMethod(method);

            //保存请求参数
            if (log.isSaveRequestParam()) {
                saveRequestParam(operateLog, joinPoint, log);
            }

            //保存请求结果
            if (log.isSaveResponseParam() && ObjectUtils.isNotNull(result)) {
                saveResponseParam(operateLog, result);
            }


            //记录操作结果
            if (ObjectUtils.isNotNull(e)) {
                operateLog.setErrorMsg(StringUtils.substring("Error Info:" + e.getMessage(), 0, 255));
                operateLog.setStatus(String.valueOf(OperateStatus.FAILED.ordinal()));
            } else {
                operateLog.setStatus(String.valueOf(OperateStatus.SUCCESS.ordinal()));
            }

            //记录操作时间
            operateLog.setOperateTime(System.currentTimeMillis() - OPERATE_TIME.get());

            //异步记录日志
            AsyncManager.me().execute(AsyncFactory.recordOperateLog(operateLog));
        } catch (Exception ex) {
            LOG.error("Record Operate Log Error, Error Message: " + ex.getMessage(), ex);
        } finally {
            OPERATE_TIME.remove();
        }
    }

    //保存请求参数：排除需要排除项
    private void saveRequestParam(SysOperateLog operateLog, JoinPoint joinPoint, Log log) {
        //获取请求参数
        Map<String, String> params = ServletUtils.getRequestParam();
        //排除需要剔除的参数
        String requestMethod = ServletUtils.getRequest().getMethod();
        if (!params.isEmpty()) {
            String jsonParams = JSON.toJSONString(params, excludePropertyFilter(log.excludeRequestParam()));
            operateLog.setOperateParam(StringUtils.substring(jsonParams, 0, 2000));
        } else if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            StringBuilder jsonParams = new StringBuilder();
            Object[] args = joinPoint.getArgs();
            if (ObjectUtils.isNotNull(args)) {
                for (Object o : args) {
                    if (ObjectUtils.isNotNull(o) && !isCrossExclude(o)) {
                        //需要被添加进请求参数中
                        try {
                            jsonParams.append(JSON.toJSONString(o, excludePropertyFilter(log.excludeRequestParam())));
                            jsonParams.append(" ");
                        } catch (Exception e) {
                            LOG.error("Description Failed to convert the request parameter to JSON format: " + e.getMessage(), e, o);
                        }
                    }
                }
            }
            operateLog.setOperateParam(StringUtils.substring(jsonParams.toString().trim(), 0, 2000));
        }
    }

    //保存响应结果
    private void saveResponseParam(SysOperateLog operateLog, Object result) {
        operateLog.setJsonResult(StringUtils.substring(JSON.toJSONString(result), 0, 2000));
    }


    /**
     * 排除敏感属性
     */
    private JsonExcludeFilter excludePropertyFilter(String[] excludeProperties) {
        return new JsonExcludeFilter().addExcludes(ArrayUtils.addAll(EXCLUDE_SENSITIVITY, excludeProperties));
    }

    /**
     * 判断对象中是否不需要添加入请求参数中
     * 若是文件或特殊请求体，则不需要 返回true 其他返回false
     */
    private boolean isCrossExclude(Object o) {
        Class<?> clazz = o.getClass();
        //对象是数组，判断数组类不是文件
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        //对象是集合，判断集合内容是否是文件
        if (Collection.class.isAssignableFrom(clazz)) {
            Collection c = (Collection) o;
            for (Object itemObj : c) {
                return itemObj instanceof MultipartFile;
            }
        }
        //对象是Map
        if (Map.class.isAssignableFrom(clazz)) {
            Map m = (Map) o;
            for (Object obj : m.entrySet()) {
                Map.Entry entry = (Map.Entry) obj;
                return entry.getValue() instanceof MultipartFile;
            }
        }

        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof BindingResult;
    }


}
