package com.dev.expense_tracker.controller.mapper;

import com.dev.expense_tracker.controller.dto.AuthRequest;
import com.dev.expense_tracker.service.auth.model.LoginRequest;
import com.dev.expense_tracker.service.auth.model.SignUpRequest;

public class AuthRequestMapperImpl implements AuthRequestMapper {

    @Override
    public LoginRequest mapToLoginRequest(AuthRequest authRequest) {
        if (authRequest == null) {
            return null;
        }
        LoginRequest.LoginRequestBuilder builder = LoginRequest.builder();
        builder.email(authRequest.getEmail());
        builder.password(authRequest.getPassword());
        return builder.build();
    }

    @Override
    public SignUpRequest mapToSignUpRequest(AuthRequest authRequest) {
        if (authRequest == null) {
            return null;
        }
        SignUpRequest.SignUpRequestBuilder builder = SignUpRequest.builder();
        builder.name(authRequest.getName());
        builder.email(authRequest.getEmail());
        builder.password(authRequest.getPassword());
        return builder.build();
    }
}
