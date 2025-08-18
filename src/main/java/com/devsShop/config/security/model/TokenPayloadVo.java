package com.devsShop.config.security.model;

import com.devsShop.user.entity.Authority;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import static org.apache.logging.log4j.util.Strings.EMPTY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Scope(value= WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TokenPayloadVo {

    private long userNo = 0;
    private Authority authority = null;
    private String nickName = EMPTY;

}