package com.example.mobilelele.service;

import com.example.mobilelele.model.entity.Offer;
import com.example.mobilelele.model.view.AddOfferViewModel;
import com.example.mobilelele.model.view.OfferSummaryViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferService {

    List<OfferSummaryViewModel> getAllOffers();

    Long saveOffer(AddOfferViewModel addOfferViewModel, String username);

    Offer findOfferById(String id);

    void delete(Long id);
}
