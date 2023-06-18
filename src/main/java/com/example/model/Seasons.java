package com.example.model;

import java.io.Serializable;

public enum Seasons implements Serializable {
    SUMMER("Літнє"), SPRING_AUTUMN("Весняно-осіннє"), WINTER("Зимове");

    private final String seasonName;

    Seasons(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }
}
