package com.example.mobilelele.model.validation;

import com.example.mobilelele.repo.UserRepo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserRepo userRepo;

    public UniqueUsernameValidator(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) { return true; }
        return userRepo.findByUsername(username).isPresent();
    }
}
