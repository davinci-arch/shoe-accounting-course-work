package com.example.model;

public enum Fastener {
    VELCRO("Липучка"), SLIPON("Сліпони"), LIGHTNING("Блискавка"), MORSE("Пряжка"), LACES("Шнурки");

    private final String typeFastener;

    Fastener(String typeFastener) {
        this.typeFastener = typeFastener;
    }

    public String getTypeFastener() {
        return typeFastener;
    }
}
