package com.example.model.types;

public enum SportsType implements TypeFootwear{

    CROSS("Кросівки"), SNEAKERS("Снікери"),
    KEDU("Кеди"), FOOTBALL_BOOTS("Бутси"),
    BOXERS("Боксерки");

    private String type;

    SportsType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getType();
    }
}
