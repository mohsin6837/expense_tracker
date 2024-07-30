package com.dev.expense_tracker.exception;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    private String errorCode;

    public UserAlreadyExistsException(String message, String errorCode) {
        super("User Already Exists with Email: " + message);
        this.errorCode = errorCode;
    }
}
