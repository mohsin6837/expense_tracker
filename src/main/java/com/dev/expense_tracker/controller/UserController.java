package com.dev.expense_tracker.controller;

import com.dev.expense_tracker.controller.dto.ChangePasswordRequest;
import com.dev.expense_tracker.controller.dto.UserDetails;
import com.dev.expense_tracker.controller.mapper.UserInfoMapper;
import com.dev.expense_tracker.data.repo.AppUserRepo;
import com.dev.expense_tracker.service.user.UserInfo;
import com.dev.expense_tracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dev.expense_tracker.constant.LoggingConstants.END_METHOD_LOGGER;
import static com.dev.expense_tracker.constant.LoggingConstants.START_METHOD_LOGGER;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final AppUserRepo userRepo;
    private final UserService userService;

    // Get User Info
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable String userId) {
        var methodName = "UserController:getUserInfo";
        log.info(START_METHOD_LOGGER, methodName, userId);
        var appUser = userService.getUserInfo(userId);
        log.info(END_METHOD_LOGGER, methodName);
        return ResponseEntity.status(HttpStatus.OK).body(
                UserInfoMapper.INSTANCE.mapToUserInfo(appUser)
        );
    }

    // Change password
    @GetMapping("/{userId}/changePassword")
    public ResponseEntity<Void> changePassword(@PathVariable String userId,
                                               @RequestBody ChangePasswordRequest changePasswordRequest) {
        var methodName = "UserController:getUserInfo";
        log.info(START_METHOD_LOGGER, methodName, userId);
        userService.changePassword(userId, changePasswordRequest.getOldPassword(),
                changePasswordRequest.getNewPassword());
        log.info(END_METHOD_LOGGER, methodName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/updateUser")
    public ResponseEntity<UserInfo> updateUserDetails(@PathVariable String userId,
                                                      @RequestBody UserDetails userDetails) {
        var methodName = "UserController:updateUserDetails";
        log.info(START_METHOD_LOGGER, methodName, userId);
        var appuser = switch (userDetails.getUpdateRequestType()) {
            case UPDATE_NAME -> userService.updateName(userId, userDetails.getName());
            case UPDATE_EMAIL -> userService.updateEmail(userId, userDetails.getEmail());

        };
        log.info(END_METHOD_LOGGER, methodName);
        return ResponseEntity.status(HttpStatus.OK).body(
                UserInfoMapper.INSTANCE.mapToUserInfo(appuser)
        );
    }
}
