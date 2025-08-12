package com.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-08-12        koiw1       최초 생성
 */
@RestController(value = "/public/user")
public class UserController {

    @GetMapping
    public String getUser() {
        return "123";
    }
}
