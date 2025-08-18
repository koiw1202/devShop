package com.devsShop.user.model.out;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TokenOutVo {

    private String accessToken;
    private String refreshToken;

}
