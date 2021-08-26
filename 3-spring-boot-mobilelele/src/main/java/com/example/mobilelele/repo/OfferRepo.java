package com.example.mobilelele.repo;

import com.example.mobilelele.model.entity.Brand;
import com.example.mobilelele.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {
}
