package com.example.model;

import java.io.Serializable;

public enum Category implements Serializable {
    MALE("Чоловіче"), FEMALE("Жіноче"),
    CHILD("Дитяче"), UNISEX("Унісекс"),
    ORTHOPEDIC("Ортопедичне"), HOMELY("Домашнє");

    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return getCategory();
    }
}

