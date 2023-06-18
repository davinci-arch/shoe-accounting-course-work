package com.example.server;

import com.example.dto.FootwearDTO;
import com.example.model.*;
import com.example.service.FootwearService;
import com.example.service.SerializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerSingleton {

    private static ServerSingleton serverSingleton;

    private static final Logger LOG = LoggerFactory.getLogger(ServerSingleton.class);

    private static FootwearService footwearService;

    private static Map<FootwearDTO, List<FootwearAbstract>> data;

    private ServerSingleton() {

        footwearService = new FootwearService(new SerializationService());
        LOG.debug("init footwear service");

    }

    public static ServerSingleton getServerSingleton() {

        if (serverSingleton == null) {

            LOG.debug("init ServerSingleton");

            serverSingleton = new ServerSingleton();

            data = initData();
            LOG.debug("init data segment, address: " + data);

        }

        return serverSingleton;
    }

    private static Map<FootwearDTO, List<FootwearAbstract>> initData() {

        return footwearService.readData();
    }

    public void addNewItem(FootwearAbstract footwearAbstract) {

        FootwearDTO dto = footwearService.getDTO(footwearAbstract);

        List<FootwearAbstract> items = data.getOrDefault(dto, new ArrayList<>());

        addItemByType(footwearAbstract, items);

        data.put(dto, items);
    }

    private void addItemByType(FootwearAbstract itemDto, List<FootwearAbstract> items) {

        if (itemDto instanceof Slippers) {

            LOG.debug("item: " + itemDto + " is instance of Slippers");

            items.add((Slippers) itemDto);


        } else if (itemDto instanceof Shoes) {

            LOG.debug("item: " + itemDto + " is instance of Shoes");

            items.add((Shoes) itemDto);

        } else if (itemDto instanceof Sandals) {

            LOG.debug("item: " + itemDto + " is instance of Sandals");

            items.add((Sandals) itemDto);

        } else if (itemDto instanceof Boots) {

            LOG.debug("item: " + itemDto + " is instance of Boots");

            items.add((Boots) itemDto);

        }
    }

    public void removeItem(FootwearAbstract item) {

        LOG.debug("remove item: " + item);

        data.remove(footwearService.getDTO(item));

    }
}
