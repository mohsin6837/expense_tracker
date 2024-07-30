package com.dev.expense_tracker.service.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
}
