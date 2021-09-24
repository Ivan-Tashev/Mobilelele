package com.example.mobilelele.model.view;

import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.entity.enums.Engine;
import com.example.mobilelele.model.entity.enums.Transmission;

import java.math.BigDecimal;

public class OfferSummaryViewModel {
    private Long id;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private Engine engine;
    private Transmission transmission;
    private Integer year;
    private Model model;

    public Long getId() {
        return id;
    }

    public OfferSummaryViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
