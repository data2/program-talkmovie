package com.muskteer.tm.common.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {


    /*
     * 将IP字符串转换为数值
     * */
    public static long transIPtoValue(String ip) {
        String[] ss = ip.split("\\.");
        if (ss.length != 4) {
            return -1;
        }
        long result = 0;
        result += Long.parseLong(ss[0]) << 24;
        result += Long.parseLong(ss[1]) << 16;
        result += Long.parseLong(ss[2]) << 8;
        result += Long.parseLong(ss[3]);
        return result;
    }

    /*
     * 将数值转换为IP字符串
     */
    public static String transValuetoIP(long value) {
        long ip1 = 0, ip2 = 0, ip3 = 0, ip4 = 0;
        ip4 = value % 256;
        ip3 = (value >> 8) % 256;
        ip2 = (value >> 16) % 256;
        ip1 = (value >> 24) % 256;
        StringBuffer ip = new StringBuffer();
        ip.append(ip1);
        ip.append(".");
        ip.append(ip2);
        ip.append(".");
        ip.append(ip3);
        ip.append(".");
        ip.append(ip4);
        return ip.toString();
    }

    /**
     * 获取用户IP地址
     */

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
