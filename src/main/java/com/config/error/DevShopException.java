package com.config.error;

import lombok.Getter;

@Getter
public class DevShopException extends RuntimeException {

    private final Integer status ;
    private final String errCode;
    private final String externalMessage;
    private final String internalMessage;

    public DevShopException(DevShopErrorCode devShopErrorCode) {
        super(devShopErrorCode.getExternalMessage());
        this.status = devShopErrorCode.getStatus();
        this.errCode = devShopErrorCode.getErrCode();
        this.externalMessage = devShopErrorCode.getExternalMessage();
        this.internalMessage = devShopErrorCode.getInternalMessage();
    }
}