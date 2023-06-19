package com.example.dao.repository;

import com.example.model.Category;
import com.example.model.Fastener;
import com.example.model.Seasons;
import com.example.model.types.SlippersType;
import com.example.model.types.TypeFootwear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertorEnum {

    private final static Logger LOG = LoggerFactory.getLogger(ConvertorEnum.class);

    public static TypeFootwear getType(String type) {
        for (TypeFootwear c : SlippersType.values()) {
            if (c.getType().equals(type)) {
                return c;
            }
        }
        LOG.warn("type cannot find");
        throw new RuntimeException("Cannot find a type");
    }

    public static Seasons getSeason(String type) {
        for (Seasons c : Seasons.values()) {
            if (c.getSeasonName().equals(type)) {
                return c;
            }
        }
        LOG.warn("type season find");
        throw new RuntimeException("Cannot find a season");
    }

    public static Fastener getFastener(String type) {
        for (Fastener c : Fastener.values()) {
            if (c.getTypeFastener().equals(type)) {
                return c;
            }
        }
        LOG.warn("type fastener find");
        throw new RuntimeException("Cannot find a fastener");
    }

    public static Category getCategory(String category) {
        for (Category c : Category.values()) {
            if (c.getCategory().equals(category)) {
                return c;
            }
        }
        LOG.warn("category cannot find");
        throw new RuntimeException("Cannot find a category");
    }
}
