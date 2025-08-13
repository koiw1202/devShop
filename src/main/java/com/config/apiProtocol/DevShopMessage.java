package com.config.apiProtocol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class DevShopMessage {

    private String message;
    private String code;

}
