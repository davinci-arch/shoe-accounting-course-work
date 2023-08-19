package com.example.model;

import com.example.model.types.TypeFootwear;

import java.math.BigDecimal;
import java.util.Objects;

public class Sandals extends FootwearAbstract{

    private Fastener fastener;

    private String color;

    private String appointment;

    private int size;


    public Sandals(int id, Category category, TypeFootwear type, String model,
                   String brand, BigDecimal price, Seasons season,
                   Fastener fastener, String color, String appointment, int size) {
        super(id, category, type, model, brand, price, season);
        this.fastener = fastener;
        this.color = color;
        this.appointment = appointment;
        this.size = size;
    }

    public Sandals(Category category, TypeFootwear type, String model,
                   String brand, BigDecimal price, Seasons season,
                   Fastener fastener, String color, String appointment,
                   int size) {
        super(category, type, model, brand, price, season);
        this.fastener = fastener;
        this.color = color;
        this.appointment = appointment;
        this.size = size;
    }

    public Fastener getFastener() {
        return fastener;
    }

    public void setFastener(Fastener fastener) {
        this.fastener = fastener;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
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
            case "М'юлі" -> result = result.add(BigDecimal.valueOf(44));
            case "Сандалі" -> result = result.add(BigDecimal.valueOf(33));
            case "В'єтнамки" -> result = result.add(BigDecimal.valueOf(15));
            case "Сланці" -> result = result.add(BigDecimal.valueOf(50));
            case "Біркеншток" -> result = result.add(BigDecimal.valueOf(99));
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

        switch (getFastener().getTypeFastener()) {
            case "Липучка" -> result = result.add(BigDecimal.valueOf(10));
            case "Сліпони" -> result = result.add(BigDecimal.valueOf(15));
            case "Блискавка" -> result = result.add(BigDecimal.valueOf(20));
            case "Пряжка" -> result = result.add(BigDecimal.valueOf(25));
            case "Шнурки" -> result = result.add(BigDecimal.valueOf(30));
        }

        switch (getAppointment()) {
            case "Повсякденне" -> result = result.add(BigDecimal.valueOf(30));
            case "Спортивне" -> result = result.add(BigDecimal.valueOf(100));
            case "Вечірнє" -> result = result.add(BigDecimal.valueOf(200));
            case "Робоче" -> result = result.add(BigDecimal.valueOf(15));
        }

        setPrice(result);
    }

    @Override
    public String toString() {
        return "Sandals{" +
                "fastener=" + fastener +
                ", color='" + color + '\'' +
                ", appointment='" + appointment + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sandals sandals = (Sandals) o;
        return size == sandals.size &&
                fastener == sandals.fastener &&
                Objects.equals(color, sandals.color) &&
                Objects.equals(appointment, sandals.appointment) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fastener, color, appointment, size);
    }
}
