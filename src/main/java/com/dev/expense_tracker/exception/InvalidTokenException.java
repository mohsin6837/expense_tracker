package com.dev.expense_tracker.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {

    private String errorCode;

    public InvalidTokenException(String message, String errorCode) {
        super("User not found with Email: " + message);
        this.errorCode = errorCode;
    }
}
