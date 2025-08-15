package com.user.service;

import com.config.apiProtocol.DevShopResponseCode;
import com.config.error.DevShopException;
import com.config.security.model.TokenPayloadVo;
import com.user.entity.User;
import com.user.entity.UserPk;
import com.user.model.in.AdminUserListInfo;
import com.user.model.in.UserInfoVo;
import com.user.repository.AdminUserRepository;
import com.user.repository.AdminUserRepositoryCustom;
import com.user.repository.AdminUserRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final TokenPayloadVo tokenPayloadVo;

    public List<User> getUserListForAdmin(AdminUserListInfo adminUserListInfo) {

        return adminUserRepository.getUserListForAdmin(adminUserListInfo);
    }

    @Transactional
    public void saveUser(UserInfoVo userInfoVo) {

        UserPk userPk = UserPk.builder()
                .userNo(tokenPayloadVo.getUserNo())
                .build();

        User user = User.builder()
                .userPk(userPk)
                .userName(userInfoVo.getUserName())
                .sex(userInfoVo.getSex())
                .authority(userInfoVo.getAuthority())
                .blockedFlag(userInfoVo.getBlockedFlag())
                .phoneNumber(userInfoVo.getPhoneNumber())
                .build();

        user = adminUserRepository.save(user);

        if(user == null) throw new DevShopException(DevShopResponseCode.USER_DOESNT_EXISTS);

    }
}