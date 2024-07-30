package com.dev.expense_tracker.service.auth;

import com.dev.expense_tracker.constant.LoggingConstants;
import com.dev.expense_tracker.controller.dto.VerifyTokenRequest;
import com.dev.expense_tracker.data.model.AppUser;
import com.dev.expense_tracker.data.repo.AppUserRepo;
import com.dev.expense_tracker.exception.BadCredentialsException;
import com.dev.expense_tracker.exception.InvalidTokenException;
import com.dev.expense_tracker.exception.UserAlreadyExistsException;
import com.dev.expense_tracker.exception.UserNotFoundException;
import com.dev.expense_tracker.service.auth.model.LoginRequest;
import com.dev.expense_tracker.service.auth.model.SignUpRequest;
import com.dev.expense_tracker.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.dev.expense_tracker.constant.ErrorMessages.*;
import static com.dev.expense_tracker.constant.LoggingConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AppUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    /**
     * @param signUpRequest
     * @return accessToken
     */

    @Override
    public String signUp(SignUpRequest signUpRequest) {
        var methodName = "AuthServiceImpl:signUp";
        log.info(START_METHOD_LOGGER, methodName, signUpRequest);
        var appUser = userRepo.findByEmail(signUpRequest.getEmail());
        if (appUser.isPresent()) {
            log.error(LoggingConstants.ERROR_METHOD_LOGGER, methodName, signUpRequest.getEmail() + " already present");
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS.getErrorMessage(),
                    USER_ALREADY_EXISTS.getErrorCode());
        }

        // Create App User model
        var user =
                AppUser.builder().name(signUpRequest.getName()).email(signUpRequest.getEmail()).password(passwordEncoder.encode(signUpRequest.getPassword())).createdAt(LocalDateTime.now()).modifiedAt(LocalDateTime.now()).build();
        userRepo.save(user);
        var accessToken = JwtUtils.generateAccessToken(signUpRequest.getEmail());
        log.info(END_METHOD_LOGGER, methodName);
        return accessToken;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        var methodName = "AuthServiceImpl:login";
        log.info(START_METHOD_LOGGER, methodName, loginRequest);

        // Find user by email
        var user =
                userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                {
                    log.error(ERROR_METHOD_LOGGER, methodName, loginRequest.getEmail() + " not found");

                    return new UserNotFoundException(USER_NOT_FOUND.getErrorMessage(), USER_NOT_FOUND.getErrorCode());
                });
        // Check password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.error(ERROR_METHOD_LOGGER, methodName, loginRequest.getEmail() + "Incorrect password");
            throw new BadCredentialsException(PASSWORD_NOT_MATCHED.getErrorMessage(),
                    PASSWORD_NOT_MATCHED.getErrorCode());
        }
        // Generate access token
        var accessToken = JwtUtils.generateAccessToken(loginRequest.getEmail());
        log.info(END_METHOD_LOGGER, methodName);
        return accessToken;
    }

    @Override
    public String verifyToken(VerifyTokenRequest verifyTokenRequest) {
        var methodName = "AuthServiceImpl:login";
        log.info(START_METHOD_LOGGER, methodName, verifyTokenRequest);
        // Extract username
        var username = JwtUtils.getUsernameFromToken(verifyTokenRequest.getAccessToken())
                .orElseThrow(() -> {
                    log.error(ERROR_METHOD_LOGGER, methodName, "invalid token");
                    return new InvalidTokenException(INVALID_TOKEN_EXCEPTION.getErrorMessage(),
                            INVALID_TOKEN_EXCEPTION.getErrorCode());
                });
        // Find user by email
        var user =
                userRepo.findByEmail(username).orElseThrow(() ->
                {
                    log.error(ERROR_METHOD_LOGGER, methodName, username + " not found");

                    return new UserNotFoundException(USER_NOT_FOUND.getErrorMessage(), USER_NOT_FOUND.getErrorCode());
                });
        var userId = user.getUserId();
        log.info(END_METHOD_LOGGER, methodName);
        return userId;
    }
}
