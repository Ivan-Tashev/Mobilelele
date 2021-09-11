package com.example.judgev2.service;

import com.example.judgev2.model.entity.Role;
import com.example.judgev2.model.entity.RoleEnum;

public interface RoleService {

    void initializeRoles();

    Role findRole(RoleEnum roleEnum);
}
