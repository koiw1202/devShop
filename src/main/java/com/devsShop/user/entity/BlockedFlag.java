package com.devsShop.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

@Getter
@AllArgsConstructor
public enum BlockedFlag {

     NON_BLOCKED("0")
    ,BLOCKED("1")

    ;
    private final String code;

    public static BlockedFlag getBlockedFlag(String code) {
        if(ObjectUtils.isEmpty(code)) return null;

        for(BlockedFlag blockedFlag : BlockedFlag.values()) {
            if(blockedFlag.getCode().equalsIgnoreCase(code)) {
                return blockedFlag;
            }
        }

        return null;
    }

    public static boolean isEnable(String code) {
        if(ObjectUtils.isEmpty(code)) return false;

        BlockedFlag blockedFlag = getBlockedFlag(code);
        if(blockedFlag == null) return false;

        if(blockedFlag == BlockedFlag.NON_BLOCKED) return true;
        else return false;

    }


}
