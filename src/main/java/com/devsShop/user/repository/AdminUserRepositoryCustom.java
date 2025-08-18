package com.devsShop.user.repository;

import com.devsShop.user.model.in.AdminUserListInfo;
import com.devsShop.user.entity.User;

import java.util.List;

public interface AdminUserRepositoryCustom {

    List<User> getUserListForAdmin(AdminUserListInfo adminUserListInfo);

}
