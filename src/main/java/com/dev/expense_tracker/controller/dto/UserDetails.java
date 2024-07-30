package com.dev.expense_tracker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetails {
    private String name;
    private String email;
    private UpdateRequestType updateRequestType;
}
