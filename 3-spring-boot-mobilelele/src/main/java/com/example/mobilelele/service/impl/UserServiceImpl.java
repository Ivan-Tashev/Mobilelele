package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.entity.enums.Role;
import com.example.mobilelele.repo.UserRepo;
import com.example.mobilelele.security.CurrentUser;
import com.example.mobilelele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override  // return true, if user authenticated successfully
    public boolean authenticate(String username, String password) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        if (userOpt.isEmpty()){
            return false;
        }
        return passwordEncoder.matches(password, userOpt.get().getPassword());
    }

    @Override  // set CurrentUser to the Logged-In user
    public void loginUser(String username) {
        final User user = userRepo.findByUsername(username).orElseThrow();
        final List<Role> userRoles = user.getRoles().stream()
                .map(UserRole::getName)
                .collect(Collectors.toList());

        currentUser.setName(username);
        currentUser.setUserRoleList(userRoles);
        currentUser.setAnonymous(false);
    }

    @Override
    public void logoutUser() {
        currentUser.setAnonymous(true);
    }
}
