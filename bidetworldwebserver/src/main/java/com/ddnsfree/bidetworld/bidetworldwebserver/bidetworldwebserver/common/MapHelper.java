package com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class MapHelper {
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
        }
        if (StringUtils.isEmpty(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String createBidetInfoPin(Map<Double, Double> bidetInfoMap) {
        return "";
    }
}
