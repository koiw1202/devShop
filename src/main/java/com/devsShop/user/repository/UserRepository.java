package com.devsShop.user.repository;

import com.devsShop.user.entity.User;
import com.devsShop.user.entity.UserPk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserPk>, UserRepositoryCustom {

}
