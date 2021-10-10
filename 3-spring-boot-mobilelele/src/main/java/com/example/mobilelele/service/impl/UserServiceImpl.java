package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.service.UserRegisterServiceModel;
import com.example.mobilelele.repo.UserRepo;
import com.example.mobilelele.repo.UserRoleRepo;
import com.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

//    @Override  // return true, if user authenticated successfully
//    public boolean authenticate(String username, String password) {
//        Optional<UserEntity> userOpt = userRepo.findByUsername(username);
//        if (userOpt.isEmpty()){
//            return false;
//        }
//        return passwordEncoder.matches(password, userOpt.get().getPassword());
//    }
//
//    @Override  // set CurrentUser to the Logged-In user
//    public void loginUser(String username) {
//        final UserEntity user = userRepo.findByUsername(username).orElseThrow();
//        final List<Role> userRoles = user.getRoles().stream()
//                .map(UserRole::getName)
//                .collect(Collectors.toList());
//
//        currentUser.setName(username);
//        currentUser.setUserRoleList(userRoles);
//        currentUser.setAnonymous(false);
//    }
//
//    @Override
//    public void logoutUser() {
//        currentUser.setAnonymous(true);
//    }

    @Override
    public boolean checkUsername(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    @Override
    public void saveUser(UserRegisterServiceModel userRegisterModel) {
        User user = modelMapper.map(userRegisterModel, User.class);
        UserRole userRole = userRoleRepo.findByName(userRegisterModel.getRoles());
        user.setPassword(passwordEncoder.encode(userRegisterModel.getPassword()));
        user.setRoles(List.of(userRole));
        user.setCreated(Instant.now());
        user.setModified(Instant.now());
        userRepo.save(user);
    }

    public List<UserRole> getUserRoles() {
        return userRoleRepo.findAll();
    }

    @Override
    public User getUserByName(String name) {
        return userRepo.findByUsername(name).orElse(null);
    }
}
