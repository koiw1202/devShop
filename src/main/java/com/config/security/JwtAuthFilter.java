package com.config.security;

import com.common.Constants;
import com.config.security.model.TokenPayloadVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtDecoder jwtDecoder;
    private TokenPayloadVo tokenPayloadVo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String accessToken = request.getHeader(Constants.accessToken);

        // 비회원인 경우
        if(ObjectUtils.isEmpty(accessToken)) filterChain.doFilter(request,response);

        // 회원인 경우
        Jwt jwt = jwtDecoder.decode(accessToken);
        String tokenValue = jwt.getTokenValue();
        ObjectMapper objectMapper = Constants.objectMapper;
        tokenPayloadVo = objectMapper.readValue(tokenValue, TokenPayloadVo.class);

    }

}
















