package com.example.model;

import com.example.model.types.SlippersType;
import com.example.model.types.TypeFootwear;

import java.math.BigDecimal;
import java.util.Objects;

public class Slippers extends FootwearAbstract {

    private String color;

    private String appointment;

    private int size;

    public Slippers(int id, Category category, TypeFootwear type, String model,
                    String brand, BigDecimal price, Seasons season,
                    String color, String appointment, int size) {
        super(id, category, type, model, brand, price, season);
        this.color = color;
        this.appointment = appointment;
        this.size = size;
    }

    public Slippers(Category category, TypeFootwear type, String model,
                    String brand, BigDecimal price, Seasons season,
                    String color, String appointment, int size) {
        super(category, type, model, brand, price, season);
        this.color = color;
        this.appointment = appointment;
        this.size = size;
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
    public String toString() {
        return "Slippers{" +
                "color='" + color + '\'' +
                ", appointment='" + appointment + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public void recalculatePrice() {

        BigDecimal result = getPrice();

        switch (getType().getType()) {
            case "Кімнатні" -> result = result.add(BigDecimal.valueOf(100));
            case "Пінетки" -> result = result.add(BigDecimal.valueOf(250));
            case "Теплі тапочки" -> result = result.add(BigDecimal.valueOf(340));
            case "Закритого типу" -> result = result.add(BigDecimal.valueOf(50));
            case "Тапочки-мокасини" -> result = result.add(BigDecimal.valueOf(99));
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

        switch (getAppointment()) {
            case "Повсякденне" -> result = result.add(BigDecimal.valueOf(30));
            case "Спортивне" -> result = result.add(BigDecimal.valueOf(100));
            case "Вечірнє" -> result = result.add(BigDecimal.valueOf(200));
            case "Робоче" -> result = result.add(BigDecimal.valueOf(15));
        }

        setPrice(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Slippers slippers = (Slippers) o;
        return size == slippers.size &&
                Objects.equals(color, slippers.color) &&
                Objects.equals(appointment, slippers.appointment) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color, appointment, size);
    }
}
