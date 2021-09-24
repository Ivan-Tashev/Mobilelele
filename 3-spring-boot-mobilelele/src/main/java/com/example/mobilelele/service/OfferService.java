package com.example.mobilelele.service;

import com.example.mobilelele.model.view.AddOfferViewModel;
import com.example.mobilelele.model.view.OfferSummaryViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferService {

    List<OfferSummaryViewModel> getAllOffers();

    void saveOffer(AddOfferViewModel addOfferViewModel);

}
