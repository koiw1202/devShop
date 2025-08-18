package com.devsShop.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor            // 기본 생성자 필수
@AllArgsConstructor
@Builder
public class UserPk implements Serializable {

    @Column(name = "user_no", nullable = false)
    private long userNo;

    @Column(name = "user_id", length = 50, nullable = false)
    private String userId;

    @Override
    public boolean equals(Object o) {
        UserPk userPk = (UserPk) o;

        if( ! ObjectUtils.isEmpty(userPk.getUserNo())) {
            return userPk.getUserNo() == userNo;
        }

        if( ! ObjectUtils.isEmpty(userPk.getUserId())) {
            return userPk.getUserId().equals(userId);
        }

        return false;
    }
}