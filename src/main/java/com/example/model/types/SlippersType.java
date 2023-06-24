package com.example.model.types;

//Тапочки
public enum SlippersType implements TypeFootwear{

    BEDROOM("Кімнатні"), PINTECAS("Пінетки"),
    WARM_SLIPPERS("Теплі тапочки"), CLOSED_SLIPPERS("Закритого типу"),
    MOCCASINS_SLIPPERS("Тапочки-мокасини");

    private String type;

    SlippersType(String type) {
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
