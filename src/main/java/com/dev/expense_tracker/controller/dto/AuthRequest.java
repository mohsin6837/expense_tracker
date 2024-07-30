package com.dev.expense_tracker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    private String name;
    private String email;
    private String password;

}
