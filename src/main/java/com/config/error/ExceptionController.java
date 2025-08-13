package com.config.error;

import com.config.apiProtocol.DevShopMessage;
import com.config.apiProtocol.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({DevShopException.class})
    public ResponseEntity handleException(DevShopException devShopException) {

        DevShopMessage devShopMessage = DevShopMessage.builder()
                .message(devShopException.getMessage())
                .code(devShopException.getErrCode())
                .build();

        ResponseBody responseBody = ResponseBody.builder()
                .data(null)
                .message(devShopMessage)
                .build();

        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
