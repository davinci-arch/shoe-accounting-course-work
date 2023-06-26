package com.example.model.types;

//Туфлі
public enum ShoesType implements TypeFootwear{
    STILETTOS("Стилети"), OXFORDS("Оксфорди"),
    DERBY("Дерби"), LOAFERS("Лофери"), MONKS("Монки"),
    MOCCASINS("Мокасіни"), SHUTTLES("Човники");


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
