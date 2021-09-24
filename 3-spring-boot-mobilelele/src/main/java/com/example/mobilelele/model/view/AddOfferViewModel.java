package com.example.mobilelele.model.view;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class AddOfferViewModel {
    @NotEmpty
    private String model;
    @NotNull @DecimalMin("100")
    private BigDecimal price;
    @NotEmpty
    private String engine;
    @NotEmpty
    private String transmission;
    @NotNull @Min(1930) @Max(2021)
    private Integer year;
    @NotNull @PositiveOrZero
    private Integer mileage;
    @NotEmpty
    private String description;
    @NotEmpty
    private String imageUrl;

    public String getModel() {
        return model;
    }

    public AddOfferViewModel setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOfferViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getEngine() {
        return engine;
    }

    public AddOfferViewModel setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public String getTransmission() {
        return transmission;
    }

    public AddOfferViewModel setTransmission(String transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public AddOfferViewModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public AddOfferViewModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOfferViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddOfferViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
