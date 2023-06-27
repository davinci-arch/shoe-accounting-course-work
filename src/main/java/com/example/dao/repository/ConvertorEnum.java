package com.example.dao.repository;

import com.example.model.*;
import com.example.model.types.SlippersType;
import com.example.model.types.TypeFootwear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertorEnum {

    private final static Logger LOG = LoggerFactory.getLogger(ConvertorEnum.class);

    public static TypeFootwear getType(String type, TypeFootwear[] types) {

        for (TypeFootwear c : types) {
            if (c.getType().equals(type)) {
                return c;
            }
        }
        LOG.warn(type + " type cannot find");
        throw new RuntimeException("Cannot find a type");
    }

    public static Seasons getSeason(String season) {
        for (Seasons c : Seasons.values()) {
            if (c.getSeasonName().equals(season)) {
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
