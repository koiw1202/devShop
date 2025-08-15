package com.user.service;

import com.config.apiProtocol.DevShopResponseCode;
import com.config.error.DevShopException;
import com.user.entity.Authority;
import com.user.entity.BlockedFlag;
import com.user.entity.User;
import com.user.entity.UserPk;
import com.user.model.in.LogInVo;
import com.user.model.in.SingupUserInfoVo;
import com.user.model.out.TokenOutVo;
import com.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager em;
    private final AuthenticationManager authenticationManager;
    @Value("${jwt.access-ttl-seconds}") private long accessTtl;
    @Value("${jwt.refresh-ttl-seconds}") private long refreshTtl;
    private final JwtEncoder jwtEncoder;

    public User getUserInfo(UserPk userPk) {
        return userRepository.getUserInfo(userPk);
    }

    /**
     * 로그인
     * @param logInVo
     * @return
     */
    public TokenOutVo logIn(LogInVo logInVo) {
        UserPk userPk = UserPk.builder()
                .userId(logInVo.getUserId())
                .build();

        User dbUser = getUserInfo(userPk);

        if( dbUser == null) throw new DevShopException(DevShopResponseCode.LOGIN_FAIL);

        if(logInVo.getPassword().equals(dbUser.getPassword())) {

            // 토근 발행
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(logInVo.getUserId(), logInVo.getPassword()));

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

    /**
     * 회원가입
     * @param singupUserInfoVo
     */
    @Transactional
    public int addUser(SingupUserInfoVo singupUserInfoVo) {

        Optional<User> userAlreadyExists = userRepository.findById(UserPk.builder().userId(singupUserInfoVo.getUserId()).build());
        if( userAlreadyExists != null) throw new DevShopException(DevShopResponseCode.USER_ALREADY_EXSITS);

        UserPk userPk = UserPk.builder()
                .userId(singupUserInfoVo.getUserId())
                .build();

        User user = User.builder()
                .userPk(userPk)
                .password(singupUserInfoVo.getPassword())
                .userName(singupUserInfoVo.getUserName())
                .nickName(singupUserInfoVo.getNickName())
                .sex(singupUserInfoVo.getSex())
                .phoneNumber(singupUserInfoVo.getPhoneNumber())
                .authority(Authority.USER)
                .blockedFlag(BlockedFlag.NON_BLOCKED)
                .build();

        // insert 수행
        try {
            em.persist(user);
        }catch (Exception e) {
            log.error("회원 가입시 문제가 발생하였습니다. => {}", e);
            throw new DevShopException(DevShopResponseCode.SIGN_UP_ERROR);
        }
        return 0;
    }

}


















































