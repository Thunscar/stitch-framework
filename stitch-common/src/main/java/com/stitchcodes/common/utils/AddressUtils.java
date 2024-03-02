package com.stitchcodes.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.stitchcodes.common.config.StitchConfig;
import com.stitchcodes.common.constant.CommonConstants;
import com.stitchcodes.common.constant.OperateConstants;
import com.stitchcodes.common.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: stitch
 * @Date: 2023/5/7 15:40
 * @Description: 获取用户地址类
 */
public class AddressUtils {

    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    //通过IP地址查询用户位置请求路径
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";
    /*未知区域*/
    private static final String UNKNOWN = "UnKnown";

    public static String getRealAddressByIp(String ip) {
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        if (StitchConfig.isAddressEnabled()) {
            try {
                StringBuilder param = new StringBuilder();
                param.append("ip=").append(ip).append("&json=true");
                String resultString = HttpUtils.sendGet(IP_URL, param.toString(), CommonConstants.GBK);
                JSONObject result = JSON.parseObject(resultString);
                String region = (String) result.get("pro");
                String city = (String) result.get("city");
                return String.format("%s %s", region, city);
            } catch (Exception e) {
                log.error(LogUtils.format(OperateConstants.GET_ADDRESS, "ip:{}", ResultEnum.FAILED, "obtain the address error"), ip, e);
            }
        }
        return UNKNOWN;
    }
}
