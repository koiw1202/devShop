package com.devsShop.user.service;

import com.devsShop.config.apiProtocol.DevShopResponseCode;
import com.devsShop.config.error.DevShopException;
import com.devsShop.user.entity.User;
import com.devsShop.user.entity.UserPk;
import com.devsShop.user.model.in.LogInVo;
import com.devsShop.user.model.out.TokenOutVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

/**
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-08-18        koiw1       최초 생성
 */

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final AuthenticationManager authenticationManager;
    @Value("${jwt.access-ttl-seconds}") private long accessTtl;
    @Value("${jwt.refresh-ttl-seconds}") private long refreshTtl;
    private final JwtEncoder jwtEncoder;
    private final UserService userService;

    /**
     * 로그인
     * @param logInVo
     * @return
     */
    public TokenOutVo logIn(LogInVo logInVo) {
        UserPk userPk = UserPk.builder()
                .userId(logInVo.getUserId())
                .build();

        User dbUser = userService.getUserInfo(userPk);

        if( dbUser == null) throw new DevShopException(DevShopResponseCode.LOGIN_FAIL);

        if(logInVo.getPassword().equals(dbUser.getPassword())) {

            // 토근 발행
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInVo.getUserId(), logInVo.getPassword()));

            String roles = auth.getAuthorities().stream()
                    .map(a -> a.getAuthority().replace("ROLE_", "")) // ["ADMIN","USER"] 형태로 만듦
                    .collect(Collectors.joining(" "));

            Instant now = Instant.now();

            // Access Token
            var accessClaims = JwtClaimsSet.builder()
                    .issuer("devShop")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(accessTtl))
                    .subject(auth.getName())
                    .claim("roles", roles.split(" "))   // converter가 ROLE_ 붙여서 매핑
                    .build();
            String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(accessClaims))
                    .getTokenValue();

            // Refresh Token (구분을 위해 typ 넣기 추천)
            var refreshClaims = JwtClaimsSet.builder()
                    .issuer("devShop")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(refreshTtl))
                    .subject(auth.getName())
                    .claim("typ", "refresh")
                    .build();

            String refreshToken = jwtEncoder.encode(JwtEncoderParameters.from(refreshClaims))
                    .getTokenValue();

            return TokenOutVo.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

        } else {
            throw new DevShopException(DevShopResponseCode.LOGIN_FAIL);
        }
    }

}
