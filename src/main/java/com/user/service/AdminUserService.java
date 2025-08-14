package com.user.service;

import com.user.entity.User;
import com.user.model.in.AdminUserListInfo;
import com.user.repository.AdminUserRepository;
import com.user.repository.AdminUserRepositoryCustom;
import com.user.repository.AdminUserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    public List<User> getUserListForAdmin(AdminUserListInfo adminUserListInfo) {

        return adminUserRepository.getUserListForAdmin(adminUserListInfo);
    }

}
