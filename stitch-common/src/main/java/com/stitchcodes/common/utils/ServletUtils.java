package com.stitchcodes.common.utils;

import com.stitchcodes.common.constant.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: stitch
 * @Date: 2023/4/30 14:16
 * @Description: Servlet工具包
 */
public class ServletUtils {

    /**
     * 获取ServletRequestAttribute
     *
     * @return
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes ras = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) ras;
    }

    /**
     * 获取ServletRequest
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取请求参数(String)
     *
     * @param name 参数名称
     * @return
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取请求参数值(String)
     *
     * @param name         请求参数名称
     * @param defaultValue 默认值
     * @return 参数值
     */
    public static String getParameter(String name, String defaultValue) {
        return ConvertUtils.toString(getParameter(name), defaultValue);
    }

    /**
     * 获取integer类型的参数
     *
     * @param name         参数名称
     * @param defaultValue 默认值
     * @return 参数值
     */
    public static Integer getParameter(String name, Integer defaultValue) {
        return ConvertUtils.toInt(getParameter(name), defaultValue);
    }

    /**
     * 将指定数据渲染到前端
     *
     * @param obj 对象
     */
    public static void sendString(HttpServletResponse response, Object obj, int httpStatus) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(httpStatus);
            response.getWriter().print(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将指定数据渲染到前端
     *
     * @param response 响应数据
     * @param str      字符串
     */
    public static void sendString(HttpServletResponse response, String str) {
        sendString(response, str, HttpStatus.SUCCESS);
    }

    /**
     * 获取请求参数
     */
    public static Map<String, String> getRequestParam() {
        Map<String, String> params = new HashMap<>();
        HttpServletRequest request = getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            params.put(entry.getKey(), StringUtils.join(entry.getValue(), ","));
        }
        return params;
    }
}
