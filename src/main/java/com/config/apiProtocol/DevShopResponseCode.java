package com.config.apiProtocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DevShopResponseCode {

     SUCCESS(200,"S0001", "성공적으로 조회되었습니다.", "성공적으로 조회되었습니다.")
    ,SIGN_UP_SUCCESS(200,"S0002", "성공적으로 등록 되었습니다.", "성공적으로 등록 되었습니다.")
    ,LOGIN_SUCCESS(200,"S0003", "성공적으로 로그인 되었습니다.", "성공적으로 로그인 되었습니다.")

    ,USER_ALREADY_EXSITS(407, "E0001", "ID가 이미 존재합니다.", "ID가 이미 존재합니다.")
    ,LOGIN_FAIL(400,"E0002", "아이디 또는 비밀번호가 잘못되었습니다.", "아이디 또는 비밀번호가 잘못되었습니다.")
    ,SIGN_UP_ERROR(500,"E0003", "회원가입 시 문제가 발생하였습니다.", "회원가입 시 문제가 발생하였습니다.")

    ;

     private final int httpStatus;
    private final String code;
    private final String externalMessage;
    private final String internalMessage;


}
