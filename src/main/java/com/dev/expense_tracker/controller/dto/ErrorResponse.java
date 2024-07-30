package com.dev.expense_tracker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
