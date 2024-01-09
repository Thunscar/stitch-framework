package com.stitchcodes.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: stitch
 * @Date: 2023/5/6 15:30
 * @Description: ip工具包
 */
public class IpUtils {

    public final static String REGX_0_255 = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
    // 匹配 ip
    public final static String REGX_IP = "((" + REGX_0_255 + "\\.){3}" + REGX_0_255 + ")";
    //匹配通配符IP
    public final static String REGX_IP_WILDCARD = "(((\\*\\.){3}\\*)|(" + REGX_0_255 + "(\\.\\*){3})|(" + REGX_0_255 + "\\." + REGX_0_255 + ")(\\.\\*){2}" + "|((" + REGX_0_255 + "\\.){3}\\*))";
    // 匹配网段
    public final static String REGX_IP_SEG = "(" + REGX_IP + "\\-" + REGX_IP + ")";

    /**
     * 获取用户ip地址
     *
     * @return 用户IP地址
     */
    public static String getIpAddr() {
        return getIpAddr(ServletUtils.getRequest());
    }

    /**
     * 获取Ip地址
     *
     * @param request 请求
     * @return ip地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_Client_IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_Forwarded_FOR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : getMultistageReverseProxyIp(ip);
    }

    /**
     * 从多级反向代理请求中获取第一个不是unknown的ip
     *
     * @param ip ip地址
     * @return IP地址
     */
    public static String getMultistageReverseProxyIp(String ip) {
        if (ip != null && ip.indexOf(',') > 0) {
            final String[] ips = ip.trim().split(",");
            for (String subIp : ips) {
                if (!isUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return StringUtils.substring(ip, 0, 255);
    }

    /**
     * 用户检查IP是否是Unknown
     *
     * @param checkStr 检查的字符串
     * @return 是否为unknown
     */
    public static boolean isUnknown(String checkStr) {
        return StringUtils.isEmpty(checkStr) || "unknown".equalsIgnoreCase(checkStr);
    }

    /**
     * 检查是否为ip
     *
     * @param ip IP地址
     * @return 是否为ip
     */
    public static boolean isIp(String ip) {
        return StringUtils.isNotBlank(ip) && ip.matches(REGX_IP);
    }

    /**
     * 检查是否为通配符结尾的ip
     *
     * @param ip IP地址
     * @return 是否为通配符结尾的ip
     */
    public static boolean isWildCardIp(String ip) {
        return StringUtils.isNotBlank(ip) && ip.matches(REGX_IP_WILDCARD);
    }

    /**
     * 匹配Ip网段
     *
     * @param ip ip地址
     * @return 是否为ip网段
     */
    public static boolean isIpSegment(String ip) {
        return StringUtils.isNotBlank(ip) && ip.matches(REGX_IP_SEG);
    }


    /**
     * 是否匹配通配符ip
     *
     * @param filter 通配符结尾的ip
     * @param ip     需要匹配的ip
     * @return 匹配结果
     */
    public static boolean isMatchWildCardIp(String filter, String ip) {
        String[] ip1 = filter.split("\\.");
        String[] ip2 = ip.split("\\.");
        boolean isMatched = true;
        for (int i = 0; i < ip1.length && !ip1[i].equals("*"); i++) {
            if (!ip1[i].equals(ip2[i])) {
                isMatched = false;
                break;
            }
        }
        return isMatched;
    }

    /**
     * 判断ip是否在网段之中
     *
     * @param filter ip网段过滤
     * @param ip     IP地址
     * @return 是否匹配
     */
    public static boolean isMatchSegmentIp(String filter, String ip) {
        String[] ips = filter.split("-");
        String[] ipl = ips[0].split("\\.");
        String[] ipr = ips[1].split("\\.");
        String[] ipm = ip.split("\\.");
        long ipll = 0L;
        long iprl = 0L;
        long ipml = 0L;
        for (int i = 0; i < 4; i++) {
            ipll = ipll << 8 | Integer.parseInt(ipl[i]);
            iprl = iprl << 8 | Integer.parseInt(ipr[i]);
            ipml = ipml << 8 | Integer.parseInt(ipm[i]);
        }
        if (ipll > ipml) {
            long temp = ipll;
            ipll = iprl;
            iprl = temp;
        }
        return ipml >= ipll && ipml <= iprl;
    }

    /**
     * 是否匹配过滤ip
     *
     * @param filters 需要过滤的IP，用逗号分隔支持结尾通配符*
     * @param ip      需要被检查的ip
     * @return 是否匹配
     */
    public static boolean isMatch(String filters, String ip) {
        if (StringUtils.isEmpty(filters) || StringUtils.isEmpty(ip)) {
            return false;
        }
        String[] ips = filters.split(",");
        for (String ipStr : ips) {
            if (isIp(ipStr) && ipStr.equals(ip)) {
                return true;
            } else if (isWildCardIp(ipStr) && isMatchWildCardIp(ipStr, ip)) {
                return true;
            } else if (isIpSegment(ipStr) && isMatchSegmentIp(ipStr, ip)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查是否是内网IP
     *
     * @param ip ip地址
     * @return 是否为内网ip
     */
    public static boolean internalIp(String ip) {
        byte[] bytes = transToBytesForIpv4(ip);
        return internalIp(bytes) || "127.0.0.1".equals(ip);
    }

    /**
     * 检查是否是内网IP地址
     *
     * @param addr byte类型的IP地址
     * @return 是否为内网IP地址
     */
    public static boolean internalIp(byte[] addr) {
        if (ObjectUtils.isNull(addr) || addr.length < 2) {
            return true;
        }
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                if (b1 == SECTION_6) {
                    return true;
                }
            default:
                return false;
        }
    }

    /**
     * 将IPv4地址转换成字节
     *
     * @param ip IPv4地址
     * @return byte 字节
     */
    public static byte[] transToBytesForIpv4(String ip) {
        if (ip.length() == 0) {
            return null;
        }
        byte[] bytes = new byte[4];
        String[] elements = ip.split("\\.");
        long l;
        int i;
        switch (elements.length) {
            case 1: {
                l = Long.parseLong(elements[0]);
                if ((l < 0L) || (l > 4294967295L)) {
                    return null;
                }
                bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                bytes[3] = (byte) (int) (l & 0xFF);
                break;
            }
            case 2: {
                l = Integer.parseInt(elements[0]);
                if (l < 0L || l > 255L) {
                    return null;
                }
                bytes[0] = (byte) (int) (l & 0xFF);
                l = Long.parseLong(elements[1]);
                if (l < 0L || l > 16777215L) {
                    return null;
                }
                bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                bytes[3] = (byte) (int) (l & 0xFF);
                break;
            }
            case 3: {
                for (i = 0; i < 2; i++) {
                    l = Integer.parseInt(elements[i]);
                    if (l < 0 || l > 255L) {
                        return null;
                    }
                    bytes[i] = (byte) (int) (l & 0xFF);
                }
                l = Long.parseLong(elements[2]);
                if (l < 0L || l > 65535L) {
                    return null;
                }
                bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                bytes[3] = (byte) (int) (l & 0xFF);
                break;
            }
            case 4: {
                for (i = 0; i < 4; i++) {
                    l = Integer.parseInt(elements[i]);
                    if (l < 0L || l > 255L) {
                        return null;
                    }
                    bytes[i] = (byte) (int) (l & 0xFF);
                }
                break;
            }
            default:
                return null;
        }
        return bytes;
    }
}
