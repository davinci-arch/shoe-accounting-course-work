package com.example.model;

import java.io.Serializable;

public enum Category implements Serializable {
    MALE("Чоловічі"), FEMALE("Жіночі"),
    CHILD("Дитячі"), UNISEX("Унісекс"),
    ORTHOPEDIC("Ортопедичне"), HOMELY("Домашнє");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
