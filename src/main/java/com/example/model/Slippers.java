package com.example.model;

import com.example.model.types.TypeFootwear;

import java.math.BigDecimal;
import java.util.Objects;

public class Slippers extends FootwearAbstract{

    private int size;
    private String color;
    private String material;
    private String sole;
    private double weight;
    private Fastener typeOfFastener;
    public Slippers(Category category, TypeFootwear type, String model,
                    String brand, BigDecimal price, Seasons season,
                    int size, String color, String material, String sole,
                    double weight, Fastener typeOfFastener) {
        super(category, type, model, brand, price, season);
        this.size = size;
        this.color = color;
        this.material = material;
        this.sole = sole;
        this.weight = weight;
        this.typeOfFastener = typeOfFastener;
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

    public Fastener getTypeOfFastener() {
        return typeOfFastener;
    }

    public void setTypeOfFastener(Fastener typeOfFastener) {
        this.typeOfFastener = typeOfFastener;
    }

    @Override
    public String toString() {
        return "Slippers{" +
                "size=" + size +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", sole='" + sole + '\'' +
                ", weight=" + weight +
                ", typeOfFastener='" + typeOfFastener + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slippers slippers = (Slippers) o;
        return size == slippers.size && Double.compare(slippers.weight, weight) == 0 && Objects.equals(color, slippers.color) && Objects.equals(material, slippers.material) && Objects.equals(sole, slippers.sole) && typeOfFastener == slippers.typeOfFastener;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, color, material, sole, weight, typeOfFastener);
    }
}
