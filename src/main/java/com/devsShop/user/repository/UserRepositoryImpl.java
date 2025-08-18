package com.devsShop.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.devsShop.user.entity.BlockedFlag;
import com.devsShop.user.entity.User;
import com.devsShop.user.entity.UserPk;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import static com.devsShop.user.entity.QUser.user;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory ;

    /*
     * userPK (userId or userNo)를 기준으로 데이터를 가져온다.
     * */
    public static BooleanExpression userNoGoe(String userNo) {
        if(ObjectUtils.isEmpty(userNo)) return null;
        return user.userPk.userNo.goe(Long.valueOf(userNo));
    }

    /*
     * userPK (userId or userNo)를 기준으로 데이터를 가져온다.
     * */
    public static BooleanExpression userPkEq(UserPk userPk) {
        if(StringUtils.hasText(userPk.getUserId())) {
            return user.userPk.eq(userPk);
        }
        return null;
    }

    public static BooleanExpression userNameEq(String userName) {
        if(StringUtils.hasText(userName)) {
            return user.userName.eq(userName);
        }
        return null;
    }

    public static BooleanExpression nickNameEq(String nickName) {
        if(StringUtils.hasText(nickName)) {
            return user.nickName.eq(nickName);
        }
        return null;
    }

    public static BooleanExpression phoneNumberEq(String phoneNumber) {
        if(StringUtils.hasText(phoneNumber)) {
            return user.phoneNumber.eq(phoneNumber);
        }
        return null;
    }

    public static BooleanExpression blockedFlagEq(BlockedFlag blockedFlag) {
        if(blockedFlag != null) {
            return user.blockedFlag.eq(blockedFlag);
        }
        return null;
    }

    @Override
    public User getUserInfo(UserPk userPk) {

        return jpaQueryFactory
                .selectFrom(user)
                .where(
                        userPkEq(userPk)
                )
                .fetchFirst();
    }
}