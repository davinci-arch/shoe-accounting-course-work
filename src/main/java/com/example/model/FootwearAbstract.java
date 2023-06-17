package com.example.model;

import com.example.model.types.TypeFootwear;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class FootwearAbstract implements Serializable {
    private String category;
    private String type;
    private String model;
    private String brand;
    private BigDecimal price;
    private String season;

    public FootwearAbstract(Category category, TypeFootwear type, String model, String brand, BigDecimal price, Seasons season) {
        this.category = category.getCategory();
        this.type = type.getType();
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.season = season.getSeasonName();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category.getCategory();
    }

    public String getType() {
        return type;
    }

    public void setType(TypeFootwear type) {
        this.type = type.getType();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(Seasons season) {
        this.season = season.getSeasonName();
    }

    @Override
    public String toString() {
        return "FootwearAbstract{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", season='" + season + '\'' +
                '}';
    }
}
