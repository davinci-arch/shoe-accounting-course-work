package com.example.global;

import com.example.model.FootwearAbstract;
import com.example.model.types.TypeFootwear;

public class SelectedItemSingleton {

    private FootwearAbstract choseFootwear;

    private static SelectedItemSingleton instance;

    private SelectedItemSingleton() {
    }

    public static SelectedItemSingleton getInstance() {

        if (instance == null) {

            instance = new SelectedItemSingleton();
        }
        return instance;
    }

    public FootwearAbstract getChoseFootwear() {
        return choseFootwear;
    }

    public void setChoseFootwear(FootwearAbstract choseFootwear) {
        this.choseFootwear = choseFootwear;
    }
}
