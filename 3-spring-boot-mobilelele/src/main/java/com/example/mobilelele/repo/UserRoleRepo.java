package com.example.mobilelele.repo;

import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

    UserRole findByName(Role name);
}
