package com.example.mobilelele.repo;

import com.example.mobilelele.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepo extends JpaRepository<Model, Long> {
}
