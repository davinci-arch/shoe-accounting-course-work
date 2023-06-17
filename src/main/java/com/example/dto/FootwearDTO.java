package com.example.dto;

import com.example.model.FootwearAbstract;

import java.math.BigDecimal;

public class FootwearDTO {

    private String category;
    private String type;
    private String model;
    private String brand;
    private BigDecimal price;
    private String season;


    private FootwearDTO(String category, String type, String model, String brand, BigDecimal price, String season) {
        this.category = category;
        this.type = type;
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.season = season;
    }

    public FootwearDTO getDTO(FootwearAbstract footwearAbstract) {
        return new FootwearDTO(footwearAbstract.getCategory(), footwearAbstract.getType(),
                footwearAbstract.getModel(), footwearAbstract.getBrand(), footwearAbstract.getPrice(),
                footwearAbstract.getSeason());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setSeason(String season) {
        this.season = season;
    }
}
