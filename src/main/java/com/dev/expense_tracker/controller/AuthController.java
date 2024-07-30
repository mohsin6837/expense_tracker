package com.dev.expense_tracker.controller;

import com.dev.expense_tracker.controller.dto.AuthRequest;
import com.dev.expense_tracker.controller.dto.AuthResponse;
import com.dev.expense_tracker.controller.dto.VerifyTokenRequest;
import com.dev.expense_tracker.controller.dto.VerifyTokenResponse;
import com.dev.expense_tracker.controller.mapper.AuthRequestMapper;
import com.dev.expense_tracker.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dev.expense_tracker.constant.LoggingConstants.END_METHOD_LOGGER;
import static com.dev.expense_tracker.constant.LoggingConstants.START_METHOD_LOGGER;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> signUp(@RequestBody AuthRequest authRequest) {
        var methodName = "AuthController:signUp";
        log.info(START_METHOD_LOGGER, methodName, authRequest);

        log.info(END_METHOD_LOGGER, methodName);
        String accessToken = authService.signUp(AuthRequestMapper.INSTANCE.mapToSignUpRequest(authRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(AuthResponse.builder().accessToken(accessToken).build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        var methodName = "AuthController:login";
        log.info(START_METHOD_LOGGER, methodName, authRequest);

        String accessToken = authService.login(AuthRequestMapper.INSTANCE.mapToLoginRequest(authRequest));
        log.info(END_METHOD_LOGGER, methodName);
        return ResponseEntity.status(HttpStatus.CREATED).body(AuthResponse.builder().accessToken(accessToken).build());
    }

    // Verify Token
    @PostMapping("/verifyToken")
    public ResponseEntity<VerifyTokenResponse> verifyToken(@RequestBody VerifyTokenRequest verifyTokenRequest) {
        var methodName = "AuthController:login";
        log.info(START_METHOD_LOGGER, methodName, verifyTokenRequest);
        String userId = authService.verifyToken(verifyTokenRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                VerifyTokenResponse.builder().userId(userId).build()
        );
    }
}
