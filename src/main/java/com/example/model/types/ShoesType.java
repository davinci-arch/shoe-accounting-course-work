package com.example.model.types;

//Туфлі
public enum ShoesType implements TypeFootwear{
    STILETTOS("Стилети"), OXFORDS("Оксфорди"),
    DERBY("Дерби"), LOAFERS("Лофери"),
    BROGUES("Броги"), MONKS("Монки"),
    MOCCASINS("Мокасіни"), SHUTTLES("Човники"),
    SCARPIN("Скарпин"), TEAR("Крапля"),
    CHUNKY("Чанки");

    private String type;

    ShoesType(String type) {
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
