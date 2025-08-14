package com.user.controller;

import com.common.ApiUtils;
import com.config.apiProtocol.DevShopResponseCode;
import com.config.apiProtocol.ResponseBody;
import com.config.error.DevShopException;
import com.user.entity.User;
import com.user.entity.UserPk;
import com.user.model.in.LogInVo;
import com.user.model.in.UserInfoVo;
import com.user.model.out.TokenOutVo;
import com.user.model.out.UserInfoOutVo;
import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ResponseBody> logIn(@RequestBody LogInVo logInVo) {

        TokenOutVo tokenOutVo = userService.logIn(logInVo);

        if(tokenOutVo != null) {
            return ApiUtils.createSuccessResponseEntity(tokenOutVo, DevShopResponseCode.LOGIN_SUCCESS);
        } else {
            throw new DevShopException(DevShopResponseCode.LOGIN_FAIL);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseBody> addUser(@RequestBody UserInfoVo userInfoVo) {

        int result = userService.addUser(userInfoVo);
        if(result == 0) return ApiUtils.createSuccessResponseEntity(null, DevShopResponseCode.SIGN_UP_SUCCESS);
        else throw new DevShopException(DevShopResponseCode.SIGN_UP_ERROR);

    }
}




















































