package com.dev.expense_tracker.controller.mapper;

import com.dev.expense_tracker.service.user.UserInfo;
import com.dev.expense_tracker.data.model.AppUser;
import org.mapstruct.factory.Mappers;

public interface UserInfoMapper {
    UserInfoMapper INSTANCE= Mappers.getMapper(UserInfoMapper.class);

    UserInfo mapToUserInfo(AppUser appUser);
}
