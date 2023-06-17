package com.example.model.types;

public enum BootsType implements TypeFootwear{

    BOOTFORTS("Ботфорти"), KNEE_HIGH_BOOTS("Чоботи до колін"),
    HUNTING_BOOTS("Мисливські чоботи"), COWBOY_BOOTS("Ковбойські чоботи"),
    UGGY("Угіі"), GLADIATOR_BOOTS("Чоботи-гладіатори");

    private String type;

    BootsType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}
