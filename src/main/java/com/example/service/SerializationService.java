package com.example.service;

import com.example.dto.FootwearDTO;
import com.example.model.FootwearAbstract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializationService {

    private static final Logger LOG = LoggerFactory.getLogger(FootwearService.class);

    private final String PATH = "save.out";

    public void saveFootwear(Map<FootwearDTO, List<FootwearAbstract>> data) {

        LOG.debug("Enter into FootwearService.saveFootwear method");

        writeIntoFile(data);
    }

    private void writeIntoFile(Map<FootwearDTO, List<FootwearAbstract>> data) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(PATH)));
            out.writeObject(data);
            out.close();
        } catch (IOException e) {
            LOG.error("cannot write object in a file");
            throw new RuntimeException(e);
        }
    }


    public HashMap<FootwearDTO, List<FootwearAbstract>> readObject() {

        return getFootwearDTOListHashMap();

    }

    private HashMap<FootwearDTO, List<FootwearAbstract>> getFootwearDTOListHashMap() {

        HashMap<FootwearDTO, List<FootwearAbstract>> o;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(PATH)));
            o = (HashMap<FootwearDTO, List<FootwearAbstract>>) in.readObject();
            in.close();
        } catch (IOException e) {
            LOG.error("cannot read object into a file");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            LOG.warn("class not found");
            throw new RuntimeException(e);
        }
        return o;
    }
}
