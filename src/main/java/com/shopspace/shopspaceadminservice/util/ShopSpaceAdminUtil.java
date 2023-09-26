package com.shopspace.shopspaceadminservice.util;

import com.google.common.net.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

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

    public static MultiValueMap<String, String> getHeaders(Map<String, Collection<String>> oldHeaders) {
        MultiValueMap<String, String> newHeaders = new LinkedMultiValueMap<>();

        for (Map.Entry<String, Collection<String>> entry : oldHeaders.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString().replace("[", "").replace("]", "");

            newHeaders.add(key, value);
        }

        return newHeaders;
    }


    public static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
