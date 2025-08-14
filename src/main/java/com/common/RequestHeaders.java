package com.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@RequestScope
public class RequestHeaders {

    private final Map<String, String> headers = new HashMap<>();

    public RequestHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            headers.put(name, value);
        }
    }

    public String get(String name) {
        return headers.get(name);
    }

    public Map<String, String> getAll() {
        return new HashMap<>(headers);
    }
}
