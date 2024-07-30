package com.dev.expense_tracker.service.auth;

import com.dev.expense_tracker.controller.dto.VerifyTokenRequest;
import com.dev.expense_tracker.service.auth.model.LoginRequest;
import com.dev.expense_tracker.service.auth.model.SignUpRequest;

public interface AuthService {

    String signUp(SignUpRequest signUpRequest);
    String login(LoginRequest loginRequest);
    String verifyToken(VerifyTokenRequest verifyTokenRequest);
}
