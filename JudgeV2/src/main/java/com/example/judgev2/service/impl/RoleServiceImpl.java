package com.example.judgev2.service.impl;

import com.example.judgev2.model.entity.Role;
import com.example.judgev2.model.entity.RoleEnum;
import com.example.judgev2.repo.RoleRepo;
import com.example.judgev2.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo repo) {
        this.roleRepo = repo;
    }

    @Override
    public void initializeRoles() {
        if (roleRepo.count() == 0) {
            final Role admin = new Role(RoleEnum.ADMIN);
            final Role user = new Role(RoleEnum.USER);

            roleRepo.saveAll(List.of(admin, user));
        }
    }

    @Override
    public Role findRole(RoleEnum roleEnum) {
        return roleRepo.findByName(roleEnum).orElse(null);
    }
}
