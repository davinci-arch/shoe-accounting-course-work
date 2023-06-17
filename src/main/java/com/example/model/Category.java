package com.example.model;

public enum Category {
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
