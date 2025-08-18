package com.devsShop.common;

import com.devsShop.config.security.model.TokenPayloadVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.devsShop.common.Constants.ACCESS_TOKEN;

@Component
@RequestScope
public class RequestHeaders {

    private final Map<String, String> headers = new HashMap<>();
    private TokenPayloadVo tokenPayloadVo;

    public RequestHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            headers.put(name, value);
        }
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public Map<String, String> getAll() {
        return new HashMap<>(headers);
    }
}
