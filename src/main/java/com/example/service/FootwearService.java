package com.example.service;

import com.example.dto.FootwearDTO;
import com.example.model.FootwearAbstract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FootwearService {

    private static final Logger LOG = LoggerFactory.getLogger(FootwearService.class);
    private SerializationService saveService;

    public FootwearService(SerializationService saveService) {
        this.saveService = saveService;
    }

    public void saveData(Map<FootwearDTO, List<FootwearAbstract>> data) {
        LOG.debug("enter into writeObject method: data address " + data);

        saveService.saveFootwear(data);

    }

    public Map<FootwearDTO, List<FootwearAbstract>> readData() {
        LOG.debug("enter into readData method");

        return saveService.readObject();
    }

    public FootwearDTO getDTO(FootwearAbstract obj) {

        return FootwearDTO.getDTO(obj);
    }
}
