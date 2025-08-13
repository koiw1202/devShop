package com.user.repository;

import com.user.entity.User;
import com.user.entity.UserPk;

public interface UserRepositoryCustom {

    public User getUserInfo(UserPk userPk);

}
