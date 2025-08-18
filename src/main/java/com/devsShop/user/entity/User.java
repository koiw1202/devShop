package com.devsShop.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @EmbeddedId
    private UserPk userPk;

    @Column(name ="password", nullable = false)
    private String password;

    @Column(name = "user_name", length = 20, nullable = false)
    private String userName;

    @Column(name = "nick_name", length = 12, nullable = false)
    private String nickName;

    @Column(name = "sex", length = 1, nullable = false)
    private String sex;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "authority", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "blocked_flag", length = 1, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BlockedFlag blockedFlag;

}