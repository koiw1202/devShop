package com.devsShop.config.error;

import com.devsShop.config.apiProtocol.DevShopResponseCode;
import lombok.Getter;

@Getter
public class DevShopException extends RuntimeException {

    private final int httpStats;
    private final String errCode;
    private final String externalMessage;
    private final String internalMessage;

    public DevShopException(DevShopResponseCode devShopResponseCode) {
        super(devShopResponseCode.getExternalMessage());

        this.httpStats = devShopResponseCode.getHttpStatus();
        this.errCode = devShopResponseCode.getCode();
        this.externalMessage = devShopResponseCode.getExternalMessage();
        this.internalMessage = devShopResponseCode.getInternalMessage();
    }
}