package com.shopspace.shopspaceadminservice.util;

import com.google.common.net.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;

public class ShopSpaceAdminUtil {
    public static String getClientIp(HttpServletRequest request) {
        var remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader(HttpHeaders.X_FORWARDED_FOR);
            if (remoteAddr == null || "".equals(remoteAddr)) remoteAddr = request.getRemoteAddr();
        }

        return remoteAddr;
    }

    public static String getUserAgent(HttpServletRequest request) {
        var ua = "";
        if (request != null) ua = request.getHeader(HttpHeaders.USER_AGENT);
        return ua;
    }
}
