package com.devsShop.user.model.out;

import com.devsShop.user.entity.Authority;
import com.devsShop.user.entity.BlockedFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserListOutVo {

    private String userId;
    private String userName;
    private String nickName;
    private String sex;
    private String phoneNumber;
    private Authority authority;
    private BlockedFlag blockedFlag;

}
