package com.dev.expense_tracker.controller.mapper;

import com.dev.expense_tracker.controller.dto.AuthRequest;
import com.dev.expense_tracker.service.auth.model.LoginRequest;
import com.dev.expense_tracker.service.auth.model.SignUpRequest;
import org.mapstruct.factory.Mappers;

public interface AuthRequestMapper {
    AuthRequestMapper INSTANCE= Mappers.getMapper(AuthRequestMapper.class);
    SignUpRequest mapToSignUpRequest(AuthRequest authRequest);
    LoginRequest mapToLoginRequest(AuthRequest authRequest);
}
