package com.dev.expense_tracker.controller.mapper;

import com.dev.expense_tracker.data.model.AppUser;
import com.dev.expense_tracker.service.user.UserInfo;

public class UserInfoMapperImpl implements UserInfoMapper {
    @Override
    public UserInfo mapToUserInfo(AppUser appUser) {
        if (appUser == null) {
            return null;
        }
        UserInfo.UserInfoBuilder builder = UserInfo.builder();
        builder.email(appUser.getEmail());
        builder.name(appUser.getName());
        builder.createdAt(appUser.getCreatedAt().toString());
        return builder.build();
    }
}
