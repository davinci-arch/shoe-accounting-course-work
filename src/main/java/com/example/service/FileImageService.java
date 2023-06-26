package com.example.service;

import com.mysql.cj.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileImageService {

    private final static Logger LOG = LoggerFactory.getLogger(FileImageService.class);
    private final String URI = "D:\\repositories\\shoe-accounting-course-work\\src\\main\\resources\\images";


    public void save(String filePath, String objectAttributesForName) {
        LOG.info("New file name " + objectAttributesForName);

        Path sourcePath = Path.of(filePath);

        Path newFileLocation = Path.of(URI);
        Path newFileName = newFileLocation.resolve(objectAttributesForName+".png");

        try {
            Files.move(sourcePath, newFileName, StandardCopyOption.REPLACE_EXISTING);
            LOG.info("Path to save: " + newFileName.toAbsolutePath());
        } catch (IOException e) {
            LOG.warn("Не вдалося перемістити файл");
        }

    }

    public File getImageByName(String fileName) {
        LOG.info("get file by name: " + fileName);

        File file = new File(URI + "\\" + fileName + ".png");

        if (!file.exists()) {
            LOG.info("file does not exist");
            LOG.info(file.getAbsolutePath());

            file = getDefaultImage();
        }

        return file;
    }

    public File getDefaultImage() {

        return new File(URI + "\\" + "not-found.png");
    }

}
