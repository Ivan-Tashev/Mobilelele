package com.example.mobilelele.model.entity;

import com.example.mobilelele.model.entity.enums.Engine;
import com.example.mobilelele.model.entity.enums.Transmission;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private Integer mileage;

    @Column
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column
    private Integer year;

    @ManyToOne
    private Model model;

    // TODO: uncomment when Users are available!
//    @ManyToOne
//    private User seller;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
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

    // TODO: uncomment when Users are available!
//    public User getSeller() {
//        return seller;
//    }
//
//    public void setSeller(User seller) {
//        this.seller = seller;
//    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", created=" + created +
                ", modified=" + modified +
                ", description='" + description + '\'' +
                ", engine=" + engine +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", transmission=" + transmission +
                ", year=" + year +
                ", model=" + model +
//                ", seller=" + seller +
                "} " + super.toString();
    }
}
