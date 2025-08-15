package com.user.repository;

import com.common.RequestHeaders;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.user.entity.User;
import com.user.entity.UserPk;
import com.user.model.in.AdminUserListInfo;
import com.user.model.in.UserInfoVo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.common.Constants.*;
import static com.user.entity.QUser.user;
import static com.user.repository.UserRepositoryImpl.*;

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














