package com.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.user.entity.QUser;
import com.user.entity.User;
import com.user.entity.UserPk;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import static com.user.entity.QUser.user;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory ;

    /*
    * userPK (userId or userNo)를 기준으로 데이터를 가져온다.
    * */
    public static BooleanExpression userPkEq(UserPk userPk) {
        if(StringUtils.hasText(userPk.getUserId())) {
            return user.id.eq(userPk);
        }
        return user.id.eq(userPk);
    }

    @Override
    public User getUserInfo(UserPk userPk) {

        return jpaQueryFactory
                .selectFrom(user)
                .where(userPkEq(userPk))
                .fetchOne();

    }
}
