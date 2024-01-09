package com.stitchcodes.common.utils;

import com.stitchcodes.common.constant.CommonConstants;
import com.stitchcodes.common.constant.OperateConstants;
import com.stitchcodes.common.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: stitch
 * @Date: 2023/5/7 17:40
 * @Description: Http请求封装类
 */
public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 封装Get请求
     *
     * @param url 请求URL
     * @return 请求结果
     */
    public static String sendGet(String url) {
        return sendGet(url, StringUtils.EMPTY);
    }

    /**
     * 封装Get请求
     *
     * @param url   请求URL
     * @param param 请求参数
     * @return 请求结果
     */
    public static String sendGet(String url, String param) {
        return sendGet(url, param, CommonConstants.UTF8);
    }

    /**
     * 封装Get请求
     *
     * @param url         请求路径
     * @param param       请求参数
     * @param contentType 响应类型
     * @return 请求结果
     */
    public static String sendGet(String url, String param, String contentType) {
        StringBuilder result = new StringBuilder();
        String urlString = null;
        BufferedReader in = null;
        try {
            urlString = StringUtils.isBlank(param) ? url : url + "?" + param;
            log.info(LogUtils.format(OperateConstants.HTTP_SEND_GET, "url:{}", ResultEnum.WAIT, "send get request begin"), urlString);
            URL realUrl = new URL(urlString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info(LogUtils.format(OperateConstants.HTTP_SEND_GET, "url:{},receive:{}", ResultEnum.SUCCESS, "receive result success"), urlString, realUrl);
        } catch (ConnectException e) {
            log.error(LogUtils.format(OperateConstants.HTTP_SEND_GET, "url:{}", ResultEnum.FAILED, "ConnectionException"), urlString, e);
        } catch (IOException e) {
            log.error(LogUtils.format(OperateConstants.HTTP_SEND_GET, "url:{}", ResultEnum.FAILED, "IOException"), urlString, e);
        } catch (Exception e) {
            log.error(LogUtils.format(OperateConstants.HTTP_SEND_GET, "url:{}", ResultEnum.FAILED, "Exception"), urlString, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                log.error(LogUtils.format(OperateConstants.HTTP_SEND_GET, "url:{}", ResultEnum.FAILED, "close the bufferedReader error"), urlString, e);
            }

        }
        return result.toString();
    }

}
