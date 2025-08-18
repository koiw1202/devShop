package com.devsShop.user.service;

import com.devsShop.config.apiProtocol.DevShopResponseCode;
import com.devsShop.config.error.DevShopException;
import com.devsShop.user.entity.Authority;
import com.devsShop.user.entity.BlockedFlag;
import com.devsShop.user.entity.User;
import com.devsShop.user.entity.UserPk;
import com.devsShop.user.model.in.LogInVo;
import com.devsShop.user.model.in.SingupUserInfoVo;
import com.devsShop.user.model.out.TokenOutVo;
import com.devsShop.user.repository.UserRepository;
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

    public User getUserInfo(UserPk userPk) {
        return userRepository.getUserInfo(userPk);
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