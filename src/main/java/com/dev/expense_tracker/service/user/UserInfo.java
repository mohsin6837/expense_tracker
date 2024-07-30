package com.dev.expense_tracker.service.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserInfo {
    private String name;
    private String email;
    private String createdAt;
}
