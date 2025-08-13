package com.config.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DevShopErrorCode {

    USER_ALREADY_EXSITS(407, "E0001", "ID가 이미 존재합니다.", "")

    ;

    private Integer status ;
    private final String errCode;
    private final String externalMessage;
    private final String internalMessage;


}
