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
    public void recalculatePrice() {

        BigDecimal result = getPrice();


        switch (getType().getType()) {
            case "Ботфорти" -> result = result.add(BigDecimal.valueOf(220));
            case "Чоботи до колін" -> result = result.add(BigDecimal.valueOf(250));
            case "Мисливські чоботи" -> result = result.add(BigDecimal.valueOf(300));
            case "Ковбойські чоботи" -> result = result.add(BigDecimal.valueOf(150));
            case "Чоботи-гладіатори" -> result = result.add(BigDecimal.valueOf(400));
            case "Угіі" -> result = result.add(BigDecimal.valueOf(100));
        }

        switch (getCategory().getCategory()) {
            case "Жіноче" -> result = result.add(BigDecimal.valueOf(144));
            case "Дитяче" -> result = result.subtract(BigDecimal.valueOf(getPrice().longValue()/2));
            case "Унісекс" -> result = result.add(BigDecimal.valueOf(288));
            case "Ортопедичне" -> result = result.add(BigDecimal.valueOf(500));
        }

        switch (getBrand()) {
            case "Manolo Blahnik" -> result = result.add(BigDecimal.valueOf(25000));
            case "Gucci" -> result = result.add(BigDecimal.valueOf(15000));
            case "Balenciaga" -> result = result.add(BigDecimal.valueOf(5000));
            case "Buscemi" -> result = result.add(BigDecimal.valueOf(7000));
        }

        switch (getFaster().getTypeFastener()) {
            case "Липучка" -> result = result.add(BigDecimal.valueOf(10));
            case "Сліпони" -> result = result.add(BigDecimal.valueOf(15));
            case "Блискавка" -> result = result.add(BigDecimal.valueOf(20));
            case "Пряжка" -> result = result.add(BigDecimal.valueOf(25));
            case "Шнурки" -> result = result.add(BigDecimal.valueOf(30));
        }

        if (weight > 720) {
            result = result.add(BigDecimal.valueOf(150));
        }

       setPrice(result);
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
