package com.example.model.types;

//Босоніжки
public enum SandalsType implements TypeFootwear{
    MUL("М'юлі"), SANDALS("Сандалі"),
    THONGS("В'єтнамки"), SHALES("Сланці"),
    BIRKENSTOCK("Біркеншток");

    private String type;

    SandalsType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}
