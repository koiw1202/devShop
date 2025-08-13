package com.user.model.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class LogInVo {

    private String userId;
    private String password;

}
