package com.example.mobilelele.model.service;

import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.enums.Engine;
import com.example.mobilelele.model.entity.enums.Transmission;

import java.math.BigDecimal;
import java.time.Instant;

public class OfferServiceModel {
    private Long id;
    private Model model;
    private BigDecimal price;
    private Engine engine;
    private Integer mileage;
    private Transmission transmission;
    private Integer year;
    private String description;
    private String imageUrl;
    private User seller;
    protected Instant created;
    protected Instant modified;

    public Long getId() {
        return id;
    }

    public OfferServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public OfferServiceModel setModel(Model model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public OfferServiceModel setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferServiceModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public OfferServiceModel setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferServiceModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public User getSeller() {
        return seller;
    }

    public OfferServiceModel setSeller(User seller) {
        this.seller = seller;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OfferServiceModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OfferServiceModel setModified(Instant modified) {
        this.modified = modified;
        return this;
    }
}
