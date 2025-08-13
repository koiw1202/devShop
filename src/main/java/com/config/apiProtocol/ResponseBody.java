package com.config.apiProtocol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpEntity;

import static com.common.Constants.SUCCESS_MESSAGE;

@AllArgsConstructor
@Builder
public class ResponseBody extends HttpEntity {

    private final Object data;
    private final DevShopMessage devShopMessage;

    public ResponseBody(Object data) {
        this.data = data;
        this.devShopMessage = DevShopMessage.builder()
                .message(SUCCESS_MESSAGE)
                .code(DevShopResponseCode.SUCCESS.getCode())
                .build();

    }

}
