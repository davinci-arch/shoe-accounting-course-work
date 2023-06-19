package com.example.dao.repository;

import com.example.dto.FootwearDTO;
import com.example.model.*;
import com.example.model.types.BootsType;
import com.example.model.types.SandalsType;
import com.example.model.types.SlippersType;
import com.example.model.types.TypeFootwear;
import com.example.service.SerializationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SlippersRepositoryTest {

    private static FootwearAbstract footObject;

    private static SlippersRepository slippersRepository;

    @BeforeAll
    static void initData() {

        slippersRepository = new SlippersRepository();

        footObject = new Slippers(Category.CHILD, SlippersType.BEDROOM, "model2", "Abibas", BigDecimal.valueOf(1500),
                Seasons.SUMMER, 39, "Сині", "Пластелин", "Висока якість",
                33.4, Fastener.VELCRO);

    }

    @Test
    void getFootwearById() {

        Optional<FootwearAbstract> footwearById = slippersRepository.getFootwearById(1L);

        footwearById.ifPresent((i) -> {
            if (i instanceof Slippers) {
                assertEquals(i, footObject);
            }
        });
    }

    @Test
    void getFootwearByCategory() {

        FootwearAbstract footwearById = slippersRepository.getFootwearByCategory(Category.CHILD).get(0);

        if (footwearById instanceof Slippers) {
            assertEquals(footwearById, footObject);
        }
    }

    @Test
    void getFootwearByType() {

        FootwearAbstract footwearById = slippersRepository.getFootwearByType(SlippersType.BEDROOM).get(0);

        if (footwearById instanceof Slippers) {
            assertEquals(footwearById, footObject);
        }
    }

    @Test
    @Order(1)
    void save() {

        slippersRepository.save(footObject);

    }
}