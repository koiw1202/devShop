package com.config.apiProtocol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpEntity;

@AllArgsConstructor
@Builder
public class ResponseBody extends HttpEntity {

    private final Object data;
    private final DevShopMessage message;

}
