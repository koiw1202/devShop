package com.devsShop.user.controller;

import com.devsShop.common.ApiUtils;
import com.devsShop.config.apiProtocol.DevShopResponseCode;
import com.devsShop.config.apiProtocol.ResponseBody;
import com.devsShop.config.error.DevShopException;
import com.devsShop.user.entity.User;
import com.devsShop.user.entity.UserPk;
import com.devsShop.user.model.in.LogInVo;
import com.devsShop.user.model.in.SingupUserInfoVo;
import com.devsShop.user.model.out.TokenOutVo;
import com.devsShop.user.model.out.UserInfoOutVo;
import com.devsShop.user.service.UserAccountService;
import com.devsShop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserAccountService userAccountService;

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseBody> getUserInfo() {



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

        TokenOutVo tokenOutVo = userAccountService.logIn(logInVo);

        if(tokenOutVo != null) {
            return ApiUtils.createSuccessResponseEntity(tokenOutVo, DevShopResponseCode.LOGIN_SUCCESS);
        } else {
            throw new DevShopException(DevShopResponseCode.LOGIN_FAIL);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseBody> addUser(@RequestBody SingupUserInfoVo singupUserInfoVo) {

        int result = userService.addUser(singupUserInfoVo);
        if(result == 0) return ApiUtils.createSuccessResponseEntity(null, DevShopResponseCode.SIGN_UP_SUCCESS);
        else throw new DevShopException(DevShopResponseCode.SIGN_UP_ERROR);

    }
}




















































