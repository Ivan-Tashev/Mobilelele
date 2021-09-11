package com.example.judgev2.repo;

import com.example.judgev2.model.entity.Role;
import com.example.judgev2.model.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepo extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(RoleEnum roleEnum);
}
