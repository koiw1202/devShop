package com.user.model.in;

import com.user.entity.BlockedFlag;
import jakarta.persistence.Column;
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
