package com.user.controller;

import com.common.ApiUtils;
import com.config.apiProtocol.ResponseBody;
import com.user.entity.User;
import com.user.model.in.AdminUserListInfo;
import com.user.model.in.UserInfoVo;
import com.user.model.out.UserListOutVo;
import com.user.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.config.apiProtocol.DevShopResponseCode.EDIT_PROFILE_SUCCESS;

@RestController("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    public ResponseEntity<ResponseBody> getUserListForAdmin(@RequestBody AdminUserListInfo adminUserListInfo) {

        List<User> userList = adminUserService.getUserListForAdmin(adminUserListInfo);

        List<UserListOutVo> userListForOutVo = Optional.ofNullable(userList)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(user -> UserListOutVo.builder()
                        .userId(user.getUserPk().getUserId())
                        .userName(user.getUserName())
                        .nickName(user.getNickName())
                        .sex(user.getSex())
                        .phoneNumber(user.getPhoneNumber())
                        .authority(user.getAuthority())
                        .blockedFlag(user.getBlockedFlag())
                        .build())
                .toList();

        return ApiUtils.createSuccessResponseEntity(userListForOutVo);

    }

    @PutMapping
    public ResponseEntity<ResponseBody> editProfile(UserInfoVo userInfoVo) {
        adminUserService.saveUser(userInfoVo);
        return ApiUtils.createSuccessResponseEntity(null, EDIT_PROFILE_SUCCESS);
    }

}