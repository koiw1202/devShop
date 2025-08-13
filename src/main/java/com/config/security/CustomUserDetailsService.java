package com.config.security;

import com.user.entity.BlockedFlag;
import com.user.entity.User;
import com.user.entity.UserPk;
import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserPk userPk = UserPk.builder()
                .userId(userId)
                .build();

        User user = userService.getUserInfo(userPk);
        List<GrantedAuthority> list = List.of(new SimpleGrantedAuthority(user.getAuthority().getRole()));

        return CustomUserDetails.builder()
                .username(user.getUserName())
                .userId(user.getUserPk().getUserNo())
                .authorities(list)
                .enabled(BlockedFlag.isEnable(user.getBlockedFlag().getCode()))
                .build();
    }
}
