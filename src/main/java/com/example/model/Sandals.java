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
