package com.common;


import com.config.apiProtocol.DevShopMessage;
import com.config.apiProtocol.DevShopResponseCode;
import com.config.apiProtocol.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiUtils {

    private ApiUtils(){}

    public static ResponseEntity<ResponseBody> createSuccessResponseEntity(Object data) {

        DevShopMessage devShopMessage = DevShopMessage.builder()
                .message(DevShopResponseCode.SUCCESS.getExternalMessage())
                .code(DevShopResponseCode.SUCCESS.getCode())
                .build();

        ResponseBody responseBody = ResponseBody.builder()
                .data(data)
                .devShopMessage(devShopMessage)
                .build();

        return new ResponseEntity(responseBody, HttpStatus.OK);
    }

}
