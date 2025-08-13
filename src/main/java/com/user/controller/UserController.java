package com.user.controller;

import com.user.entity.UserPk;
import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String getUserInfo(@AuthenticationPrincipal UserDetails user) {

        UserPk userPk = UserPk.builder()
                .userId(user.getUsername())
                .build();

        userService.getUserInfo(userPk);

        return "123";

    }

}