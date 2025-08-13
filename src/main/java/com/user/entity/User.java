package com.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @EmbeddedId
    private UserPk userPk;

    @Column(name ="password", length = 255, nullable = false)
    private String password;

    @Column(name = "user_name", length = 20, nullable = false)
    private String userName;

    @Column(name = "sex", length = 1, nullable = false)
    private String sex;

    @Column(name = "phone_number", length = 12, nullable = false)
    private String phoneNumber;

    @Column(name = "authority", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "blocked_flag", length = 1, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BlockedFlag blockedFlag;





}
