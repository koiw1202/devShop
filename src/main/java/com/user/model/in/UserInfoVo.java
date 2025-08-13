package com.user.model.in;

import com.user.entity.Authority;
import com.user.entity.BlockedFlag;
import com.user.entity.UserPk;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {

    private String userId;
    private String password;
    private String userName;
    private String nickName;
    private String sex;
    private String phoneNumber;

}
