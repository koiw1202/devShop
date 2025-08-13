package com.user.controller;

import com.common.ApiUtils;
import com.config.apiProtocol.DevShopResponseCode;
import com.config.apiProtocol.ResponseBody;
import com.user.entity.User;
import com.user.entity.UserPk;
import com.user.model.in.LogInVo;
import com.user.model.in.UserInfoVo;
import com.user.model.out.UserInfoOutVo;
import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-08-12        koiw1       최초 생성
 */
@RestController(value = "/public")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<ResponseBody> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {

        UserPk userPk = UserPk.builder()
                .userId(userDetails.getUsername())
                .build();

        User user = userService.getUserInfo(userPk);

        UserInfoOutVo userInfoOutVo = UserInfoOutVo.builder()
                .userNo(user.getUserPk().getUserNo())
                .nickName(user.getNickName())
                .build();

        return ApiUtils.createSuccessResponseEntity(userInfoOutVo);
    }

    @PostMapping("/login")
    public void logIn(@RequestBody LogInVo logInVo) {

        boolean loginSuccessFlag = userService.logIn(logInVo);

        //TODO: 메시지 문구 설정 필요
        if(loginSuccessFlag) {

        } else {

        }
    }

    @PostMapping("/user")
    public void addUser(@RequestBody UserInfoVo userInfoVo) {

    }
}