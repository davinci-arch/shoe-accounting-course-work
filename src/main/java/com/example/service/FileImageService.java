package com.example.service;

import javafx.stage.FileChooser;
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
        LOG.info("File path img: " + filePath);

        if (!filePath.equals("file:/D:/repositories/shoe-accounting-course-work/target/classes/images/not-found.png")) {
            Path sourcePath = Path.of(filePath);
            Path newFileLocation = Path.of(URI);
            Path newFileName = newFileLocation.resolve(objectAttributesForName + ".png");

            try {
                Files.copy(sourcePath, newFileName, StandardCopyOption.REPLACE_EXISTING);
                LOG.info("Path to save: " + newFileName.toAbsolutePath());
            } catch (IOException e) {
                LOG.warn("Не вдалося перемістити файл");
            }
        }

    }

    public void renameOldImg(String oldName, String newName) {
        LOG.info("Rename old image - oldName: " + oldName + ", new name: " + newName);

        String filePath = URI + "\\" + "%s.png";

        if (!oldName.equals("not-found.png")) {

            Path source = Path.of(String.format(filePath, oldName));

            try {
                Files.move(source, source.resolveSibling(newName+".png"));
            } catch (IOException e) {
                LOG.warn("cannot change filename from sources: " + filePath + " to: " + newName + ".png");
                throw new RuntimeException(e);
            }

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

    public boolean removeFile(String name) {
        LOG.info("Remove file by name: " + name);
        File file = new File(URI + "\\" + name + ".png");
        LOG.info("path: " + URI + name + ".png");
        return file.delete();
    }

    public FileChooser.ExtensionFilter getAllTypesOfExtensionsImage() {

        return new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png", "*.jpeg",
                        "*.jfif", "*.pjp", "*.pjpeg", "*.svg");
    }

}
