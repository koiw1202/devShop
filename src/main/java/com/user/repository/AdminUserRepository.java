package com.user.repository;

import com.user.entity.User;
import com.user.entity.UserPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<User, UserPk>, AdminUserRepositoryCustom {

}
