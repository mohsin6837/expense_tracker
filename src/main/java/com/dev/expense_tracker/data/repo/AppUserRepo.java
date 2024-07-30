package com.dev.expense_tracker.data.repo;

import com.dev.expense_tracker.data.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppUserRepo extends MongoRepository<AppUser,String> {

    Optional<AppUser> findByEmail(String email);
    Boolean existsByEmail(String email);
}
