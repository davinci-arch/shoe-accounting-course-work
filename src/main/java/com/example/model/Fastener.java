package com.example.model;

import java.io.Serializable;

public enum Fastener implements Serializable {
    VELCRO("Липучка"), SLIPON("Сліпони"), LIGHTNING("Блискавка"), MORSE("Пряжка"), LACES("Шнурки");

    private final String typeFastener;

    Fastener(String typeFastener) {
        this.typeFastener = typeFastener;
    }

    public String getTypeFastener() {
        return typeFastener;
    }
}
