package com.example.service;

import com.example.dto.FootwearDTO;
import com.example.model.FootwearAbstract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class SerializationService {

    private static final Logger LOG = LoggerFactory.getLogger(FootwearService.class);

    private final String PATH = "save.out";

    public void saveFootwear(HashMap<FootwearDTO, List<FootwearAbstract>> data) {

        LOG.debug("Enter into FootwearService.saveFootwear method");

        writeIntoFile(data);
    }

    private void writeIntoFile(HashMap<FootwearDTO, List<FootwearAbstract>> data) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(PATH)));
            out.writeObject(data);
        } catch (IOException e) {
            LOG.error("cannot write object in a file");
            throw new RuntimeException(e);
        }
    }


    private HashMap<FootwearDTO, List<FootwearAbstract>> readObject() {

        HashMap<FootwearDTO, List<FootwearAbstract>> o;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(PATH)));
            o = (HashMap<FootwearDTO, List<FootwearAbstract>>) in.readObject();
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
