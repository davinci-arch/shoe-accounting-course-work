package com.example.service;

import com.example.dto.FootwearDTO;
import com.example.model.*;
import com.example.model.types.BootsType;
import com.example.model.types.SandalsType;
import com.example.model.types.SlippersType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SerializationServiceReadTest {

    private static SerializationService saveService;

    private static List<FootwearAbstract> footObjects = new ArrayList<>();

    private static Map<FootwearDTO, List<FootwearAbstract>> data;

    @BeforeAll
    static void initData() {
        saveService = new SerializationService();

        footObjects.add(new Boots(Category.MALE, BootsType.BOOTFORTS, "model1", "Nike", BigDecimal.valueOf(2000),
                Seasons.SUMMER, 41, "Червоні", "Пластелин", "Висока якість",
                22.3, Fastener.MORSE));
        footObjects.add(new Slippers(Category.CHILD, SlippersType.BEDROOM, "model2", "Abibas", BigDecimal.valueOf(1500),
                Seasons.SUMMER, 39, "Сині", "Пластелин", "Висока якість",
                33.4, Fastener.VELCRO));

        footObjects.add(new Sandals(Category.UNISEX, SandalsType.SHALES, "model3", "Nike", BigDecimal.valueOf(799),
                Seasons.SUMMER, 39, "Сині", "Пластелин", "Висока якість",
                55, Fastener.VELCRO));

        data = new HashMap<>();

        for (FootwearAbstract foot : footObjects) {

            FootwearDTO dto = FootwearDTO.getDTO(foot);

            List<FootwearAbstract> orDefault = data.getOrDefault(dto, new ArrayList<>());

            data.put(dto, orDefault);
        }

    }

    @Test
    @Order(1)
    void readObjectEqualsKey() {
        HashMap<FootwearDTO, List<FootwearAbstract>> footwearDTOListHashMap = saveService.readObject();

        boolean res = false;

        for (FootwearDTO dto : footwearDTOListHashMap.keySet()) {

            if (data.containsKey(dto)) {
                res = true;
            } else {
                res = false;
            }
        }

        assertTrue(res);
    }

    @Test
    @Order(2)
    void readObjectEqualValues() {

        HashMap<FootwearDTO, List<FootwearAbstract>> footwearDTOListHashMap = saveService.readObject();

        boolean res = false;

        for (List<FootwearAbstract> lst : footwearDTOListHashMap.values()) {

            for (int i = 0; i < lst.size(); i++) {

                if (footObjects.get(i).equals(lst.get(i))) {
                    res = true;
                } else {
                    res = false;
                }
            }

        }
    }
}
