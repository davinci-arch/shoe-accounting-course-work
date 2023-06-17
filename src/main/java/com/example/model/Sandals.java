package com.example.model;

import com.example.model.types.TypeFootwear;

import java.math.BigDecimal;

public class Sandals extends FootwearAbstract{

    private int size;
    private String color;
    private String material;
    private String sole;
    private double weight;
    private String typeOfFastener;

    public Sandals(Category category, TypeFootwear type, String model,
                   String brand, BigDecimal price, Seasons season,
                   int size, String color, String material, String sole,
                   double weight, Fastener typeOfFastener) {
        super(category, type, model, brand, price, season);
        this.size = size;
        this.color = color;
        this.material = material;
        this.sole = sole;
        this.weight = weight;
        this.typeOfFastener = typeOfFastener.getTypeFastener();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSole() {
        return sole;
    }

    public void setSole(String sole) {
        this.sole = sole;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getTypeOfFastener() {
        return typeOfFastener;
    }

    public void setTypeOfFastener(Fastener typeOfFastener) {
        this.typeOfFastener = typeOfFastener.getTypeFastener();
    }

    @Override
    public String toString() {
        return "Sandals{" +
                "size=" + size +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", sole='" + sole + '\'' +
                ", weight=" + weight +
                ", typeOfFastener='" + typeOfFastener + '\'' +
                '}';
    }
}
