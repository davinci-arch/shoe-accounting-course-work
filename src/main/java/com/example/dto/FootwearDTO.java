package com.example.dto;

import com.example.model.Category;
import com.example.model.FootwearAbstract;
import com.example.model.Seasons;
import com.example.model.types.TypeFootwear;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class FootwearDTO implements Serializable {

    private Category category;
    private TypeFootwear type;
    private String model;
    private String brand;
    private BigDecimal price;
    private Seasons season;


    private FootwearDTO(Category category, TypeFootwear type, String model, String brand, BigDecimal price, Seasons season) {
        this.category = category;
        this.type = type;
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.season = season;
    }

    public static FootwearDTO getDTO(FootwearAbstract footwearAbstract) {
        return new FootwearDTO(footwearAbstract.getCategory(), footwearAbstract.getType(),
                footwearAbstract.getModel(), footwearAbstract.getBrand(), footwearAbstract.getPrice(),
                footwearAbstract.getSeason());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TypeFootwear getType() {
        return type;
    }

    public void setType(TypeFootwear type) {
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

    public Seasons getSeason() {
        return season;
    }

    public void setSeason(Seasons season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "FootwearDTO{" +
                "category=" + category +
                ", type=" + type +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", season=" + season +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootwearDTO that = (FootwearDTO) o;
        return category == that.category && Objects.equals(type, that.type) && Objects.equals(model, that.model) && Objects.equals(brand, that.brand) && Objects.equals(price, that.price) && season == that.season;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, type, model, brand, price, season);
    }
}
