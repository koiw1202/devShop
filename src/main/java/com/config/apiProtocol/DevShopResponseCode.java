package com.config.apiProtocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DevShopResponseCode {

     USER_ALREADY_EXSITS("E0001", "ID가 이미 존재합니다.", "ID가 이미 존재합니다.")
    ,SUCCESS("S0001", "성공적으로 조회되었습니다.", "성공적으로 조회되었습니다.")
    ;

    private final String code;
    private final String externalMessage;
    private final String internalMessage;


}
