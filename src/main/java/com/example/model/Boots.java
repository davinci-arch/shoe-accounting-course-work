package com.example.model;

import com.example.model.types.TypeFootwear;

import java.math.BigDecimal;
import java.util.Objects;

public class Boots extends FootwearAbstract{

    private Fastener faster;

    private String color;

    private String material;

    private double weight;

    private int size;

    public Boots(int id, Category category, TypeFootwear type, String model,
                 String brand, BigDecimal price, Seasons season,
                 Fastener faster, String color, String material, double weight, int size) {
        super(id, category, type, model, brand, price, season);
        this.faster = faster;
        this.color = color;
        this.material = material;
        this.weight = weight;
        this.size = size;
    }

    public Boots(Category category, TypeFootwear type, String model,
                 String brand, BigDecimal price, Seasons season,
                 Fastener faster, String color, String material,
                 double weight, int size) {
        super(category, type, model, brand, price, season);
        this.faster = faster;
        this.color = color;
        this.material = material;
        this.weight = weight;
        this.size = size;
    }

    public Fastener getFaster() {
        return faster;
    }

    public void setFaster(Fastener faster) {
        this.faster = faster;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return "Boots{" +
                "faster=" + faster +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", weight=" + weight +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Boots boots = (Boots) o;
        return Double.compare(boots.weight, weight) == 0 &&
                size == boots.size &&
                faster == boots.faster &&
                Objects.equals(color, boots.color) &&
                Objects.equals(material, boots.material) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faster, color, material, weight, size);
    }
}
