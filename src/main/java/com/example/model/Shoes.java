package com.example.model;

import com.example.model.types.TypeFootwear;

import java.math.BigDecimal;
import java.util.Objects;

public class Shoes extends FootwearAbstract{

   private String color;

   private int size;

    public Shoes(int id, Category category,
                 TypeFootwear type, String model, String brand,
                 BigDecimal price, Seasons season, String color, int size) {
        super(id, category, type, model, brand, price, season);
        this.color = color;
        this.size = size;
    }

    public Shoes(Category category, TypeFootwear type, String model,
                 String brand, BigDecimal price, Seasons season,
                 String color, int size) {
        super(category, type, model, brand, price, season);
        this.color = color;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return "Shoes{" +
                "color='" + color + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shoes shoes = (Shoes) o;
        return size == shoes.size &&
                Objects.equals(color, shoes.color) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color, size);
    }
}
