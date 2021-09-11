package com.example.judgev2.service.impl;

import com.example.judgev2.model.entity.RoleEnum;
import com.example.judgev2.model.entity.User;
import com.example.judgev2.model.service.UserServiceModel;
import com.example.judgev2.repo.UserRepo;
import com.example.judgev2.service.RoleService;
import com.example.judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, RoleService roleService) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setRole(roleService.findRole(RoleEnum.USER));
        userRepo.save(user);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }
}
