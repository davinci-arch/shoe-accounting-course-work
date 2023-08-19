package com.example.service;

import com.example.dto.FootwearDTO;
import com.example.model.FootwearAbstract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class FootwearService {

    private static final Logger LOG = LoggerFactory.getLogger(FootwearService.class);


    public FootwearDTO getDTO(FootwearAbstract obj) {

        return FootwearDTO.getDTO(obj);
    }
}
