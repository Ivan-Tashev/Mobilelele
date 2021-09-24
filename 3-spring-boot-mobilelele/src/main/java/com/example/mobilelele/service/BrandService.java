package com.example.mobilelele.service;

import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.view.BrandViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    List<BrandViewModel> getAllBrands();

    Model getModelByName(String model);
}
