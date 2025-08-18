package com.devsShop.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Authority {

     USER("USER")
    ,ADMIN("ADMIN")
    ,MANAGER("MANAGER")

    ;
    private final String role;



}
