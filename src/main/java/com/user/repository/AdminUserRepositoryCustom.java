package com.user.repository;

import com.user.entity.User;
import com.user.entity.UserPk;
import com.user.model.in.AdminUserListInfo;

import java.util.List;

public interface AdminUserRepositoryCustom {

    List<User> getUserListForAdmin(AdminUserListInfo adminUserListInfo);



}
