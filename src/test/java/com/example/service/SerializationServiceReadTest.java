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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
            orDefault.add(foot);

            data.put(dto, orDefault);
        }

    }

    @Test
    @Order(1)
    void readObjectEqualsKey() {
        HashMap<FootwearDTO, List<FootwearAbstract>> footwearDTOListHashMap = saveService.readObject();

        for (FootwearDTO dto : footwearDTOListHashMap.keySet()) {

            assertTrue(data.containsKey(dto));

        }

    }

    @Test
    @Order(2)
    void readObjectEqualValuesBySubType() {

        HashMap<FootwearDTO, List<FootwearAbstract>> footwearDTOListHashMap = saveService.readObject();

        for (FootwearAbstract foot : footObjects) {

            FootwearDTO dto = FootwearDTO.getDTO(foot);

            List<FootwearAbstract> footwearAbstracts = footwearDTOListHashMap.get(dto);

            for (int i = 0; i < footwearAbstracts.size(); i++) {

                FootwearAbstract ft = footwearAbstracts.get(i);

                if (ft instanceof Slippers && foot instanceof Slippers) {

                    assertEquals((Slippers)ft, (Slippers)foot);

                } else if (ft instanceof Boots && foot instanceof Boots) {

                    assertEquals((Boots)ft, (Boots)foot);

                } else if (ft instanceof Sandals && foot instanceof Sandals) {

                    assertEquals((Sandals)ft, (Sandals)foot);

                } else if (ft instanceof Shoes && foot instanceof Shoes) {

                    assertEquals((Shoes)ft, (Shoes)foot);

                }

            }

        }


    }


}
