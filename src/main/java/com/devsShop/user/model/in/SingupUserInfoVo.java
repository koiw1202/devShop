package com.devsShop.user.model.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingupUserInfoVo {

    private String userId;
    private String password;
    private String userName;
    private String nickName;
    private String sex;
    private String phoneNumber;

}
