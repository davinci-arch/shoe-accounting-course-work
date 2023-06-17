package com.example.model;

public enum Seasons {
    SUMMER("Літнє"), SPRING_AUTUMN("Весняно-осіннє"), WINTER("Зимове");

    private final String seasonName;

    Seasons(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }
}
