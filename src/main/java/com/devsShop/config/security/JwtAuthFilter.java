package com.devsShop.config.security;

import com.devsShop.common.Constants;
import com.devsShop.config.security.model.TokenPayloadVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
        final String accessToken = request.getHeader(Constants.ACCESS_TOKEN);

        // 비회원인 경우
        if(ObjectUtils.isEmpty(accessToken)) {
            filterChain.doFilter(request,response);
            return;
        }

        // 회원인 경우
        Jwt jwt = jwtDecoder.decode(accessToken);
        String tokenValue = jwt.getTokenValue();
        ObjectMapper objectMapper = Constants.objectMapper;
        TokenPayloadVo tempTokenPayloadVo = objectMapper.readValue(tokenValue, TokenPayloadVo.class);

        tokenPayloadVo.setAuthority(tempTokenPayloadVo.getAuthority());
        tokenPayloadVo.setUserNo(tempTokenPayloadVo.getUserNo());
        tokenPayloadVo.setNickName(tempTokenPayloadVo.getNickName());
    }

}
















