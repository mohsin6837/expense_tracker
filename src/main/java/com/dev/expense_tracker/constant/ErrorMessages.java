package com.dev.expense_tracker.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {

    USER_ALREADY_EXISTS("E409", "User Already Exists!"),
    USER_NOT_FOUND("E404", "User not found!"), PASSWORD_NOT_MATCHED("E401", "Password not matched!"),
    INVALID_TOKEN_EXCEPTION("T401", "Invalid access token!");
    String errorCode;
    String errorMessage;
}
