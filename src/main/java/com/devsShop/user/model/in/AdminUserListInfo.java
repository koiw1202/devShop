package com.devsShop.user.model.in;

import com.devsShop.user.entity.BlockedFlag;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminUserListInfo {

    private String userId;
    private String userName;
    private String phoneNumber;
    private BlockedFlag blockedFlag;

}
