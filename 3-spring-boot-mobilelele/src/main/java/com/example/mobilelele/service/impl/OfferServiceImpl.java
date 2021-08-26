package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.Offer;
import com.example.mobilelele.model.view.OfferSummaryViewModel;
import com.example.mobilelele.repo.OfferRepo;
import com.example.mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepo offerRepo;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepo offerRepo, ModelMapper modelMapper) {
        this.offerRepo = offerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OfferSummaryViewModel> getAllOffers() {
        //TODO - implement mapping
        List<Offer> allOffersList = offerRepo.findAll();
        List<OfferSummaryViewModel> offerSummaryViewModel = new ArrayList<>();
        allOffersList.forEach(offer -> {
            OfferSummaryViewModel summaryViewModel = new OfferSummaryViewModel();
            modelMapper.map(offer, summaryViewModel);
            offerSummaryViewModel.add(summaryViewModel);
        });

        return offerSummaryViewModel;

//        throw new UnsupportedOperationException("Coming soon...");
    }
}
