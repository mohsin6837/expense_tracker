package com.dev.expense_tracker.service.user;

import com.dev.expense_tracker.data.model.AppUser;
import com.dev.expense_tracker.data.repo.AppUserRepo;
import com.dev.expense_tracker.exception.BadCredentialsException;
import com.dev.expense_tracker.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.dev.expense_tracker.constant.ErrorMessages.PASSWORD_NOT_MATCHED;
import static com.dev.expense_tracker.constant.ErrorMessages.USER_NOT_FOUND;
import static com.dev.expense_tracker.constant.LoggingConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser getUserInfo(String userId) {
        var methodName = "UserServiceImpl:getUserInfo";
        log.info(START_METHOD_LOGGER, methodName, userId);

        var user = userRepo.findById(userId).orElseThrow(() -> {
            log.error(ERROR_METHOD_LOGGER, methodName, userId + " not found");
            return new UserNotFoundException(USER_NOT_FOUND.getErrorMessage(), USER_NOT_FOUND.getErrorCode());
        });
        return user;
    }

    @Override
    public void changePassword(String userId, String oldPassword, String newPassword) {
        var methodName = "UserServiceImpl:changePassword";
        log.info(START_METHOD_LOGGER, methodName, userId);

        var user = getUserInfo(userId);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            log.error(ERROR_METHOD_LOGGER, methodName, "Incorrect password");
            throw new BadCredentialsException(PASSWORD_NOT_MATCHED.getErrorMessage(),
                    PASSWORD_NOT_MATCHED.getErrorCode());
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        log.info(END_METHOD_LOGGER, methodName);
        userRepo.save(user);
    }

    @Override
    public AppUser updateName(String userId, String name) {
        var methodName = "UserServiceImpl:updateName";
        log.info(START_METHOD_LOGGER, methodName, userId);

        var user = getUserInfo(userId);
        user.setName(name);
        var updatedUser = userRepo.save(user);
        log.info(END_METHOD_LOGGER, methodName);
        return updatedUser;
    }

    @Override
    public AppUser updateEmail(String userId, String email) {
        var methodName = "UserServiceImpl:updateEmail";
        log.info(START_METHOD_LOGGER, methodName, userId);

        var user = getUserInfo(userId);
        user.setEmail(email);
        var updatedUser = userRepo.save(user);
        log.info(END_METHOD_LOGGER, methodName);
        return updatedUser;
    }
}
