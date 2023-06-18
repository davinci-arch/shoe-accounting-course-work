package com.example.server;

import com.example.dto.FootwearDTO;
import com.example.model.*;
import com.example.model.types.BootsType;
import com.example.model.types.SandalsType;
import com.example.model.types.SlippersType;
import com.example.service.SerializationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerSingletonWriteTest {

    private static SerializationService saveService;

    private static List<FootwearAbstract> footObjects = new ArrayList<>();

    private static Map<FootwearDTO, List<FootwearAbstract>> data;

    private static ServerSingleton server;

    @BeforeAll
    static void initData() {
        saveService = new SerializationService();

        server = ServerSingleton.getServerSingleton();

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
    void addNewItem() {
    }
}
