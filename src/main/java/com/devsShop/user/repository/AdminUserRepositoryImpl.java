package com.devsShop.user.repository;

import com.devsShop.common.RequestHeaders;
import com.devsShop.user.model.in.AdminUserListInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.devsShop.user.entity.User;
import com.devsShop.user.entity.UserPk;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.devsShop.common.Constants.*;
import static com.devsShop.user.entity.QUser.user;
import static com.devsShop.user.repository.UserRepositoryImpl.*;

@RequiredArgsConstructor
public class AdminUserRepositoryImpl implements AdminUserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory ;
    private final RequestHeaders requestHeaders;

    @Override
    public List<User> getUserListForAdmin(AdminUserListInfo adminUserListInfo) {

        UserPk userPk = UserPk.builder()
                .userId(adminUserListInfo.getUserId())
                .build();

        Long pageUnit = Long.valueOf(requestHeaders.get(PAGE_UNIT));
        pageUnit = pageUnit == null ? DEFAULT_PAGE_SIZE : pageUnit;

        List<User> userList = jpaQueryFactory.selectFrom(user)
                .where(
                        userPkEq(userPk),
                        userNameEq(adminUserListInfo.getUserName()),
                        phoneNumberEq(adminUserListInfo.getPhoneNumber()),
                        blockedFlagEq(adminUserListInfo.getBlockedFlag()),
                        userNoGoe(requestHeaders.get(CURRENT_PAGE))
                )
                .limit(pageUnit)
                .fetch();

        return userList;
    }

}














