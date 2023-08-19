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
    public void recalculatePrice() {
        BigDecimal result = getPrice();

        switch (getType().getType()) {
            case "Стилети" -> result = result.add(BigDecimal.valueOf(50));
            case "Оксфорди" -> result = result.add(BigDecimal.valueOf(300));
            case "Дерби" -> result = result.add(BigDecimal.valueOf(120));
            case "Лофери" -> result = result.add(BigDecimal.valueOf(100));
            case "Монки" -> result = result.add(BigDecimal.valueOf(55));
            case "Мокасіни" -> result = result.add(BigDecimal.valueOf(31));
            case "Човники" -> result = result.add(BigDecimal.valueOf(78));
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

        setPrice(result);
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
