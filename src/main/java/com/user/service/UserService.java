package com.user.service;

import com.user.entity.Authority;
import com.user.entity.BlockedFlag;
import com.user.entity.User;
import com.user.entity.UserPk;
import com.user.model.in.LogInVo;
import com.user.model.in.UserInfoVo;
import com.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserInfo(UserPk userPk) {
        return userRepository.getUserInfo(userPk);
    }

    public boolean logIn(LogInVo logInVo) {
        UserPk userPk = UserPk.builder()
                .userId(logInVo.getUserId())
                .build();

        User dbUser = getUserInfo(userPk);

        if(dbUser == null) return false;

        if(logInVo.getPassword().equals(dbUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public void addUser(UserInfoVo userInfoVo) {

        Optional<User> userAlreadyExists = userRepository.findById(UserPk.builder().userId(userInfoVo.getUserId()).build());
        userAlreadyExists.orElseThrow(() -> new RuntimeException()); // 기존 아이디가 존재하면 throw

        UserPk userPk = UserPk.builder()
                .userId(userInfoVo.getUserId())
                .build();

        User user = User.builder()
                .userPk(userPk)
                .password(userInfoVo.getPassword())
                .userName(userInfoVo.getUserName())
                .nickName(userInfoVo.getNickName())
                .sex(userInfoVo.getSex())
                .phoneNumber(userInfoVo.getPhoneNumber())
                .authority(Authority.USER)
                .blockedFlag(BlockedFlag.NON_BLOCKED)
                .build();

        user = userRepository.save(user);


    }

}
