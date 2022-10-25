package com.pawbla.pi.uploader.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
public class FileReaderService {
    private static final Logger LOG = LogManager.getLogger(FileReaderService.class);

    String str = null;
    StringBuilder stringBuilder = new StringBuilder();

    public String readFile(String pathToFile)  {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            while ((str = reader.readLine()) != null) {
                stringBuilder.append(str).append("\n");
            }
        } catch (FileNotFoundException e) {
            LOG.warn("Path {} does not exists. Exception: {}", pathToFile, e.getMessage());
        } catch (IOException e) {
            LOG.warn("An exception has occurred: {}", e.getMessage());
        }
        return stringBuilder.toString();
    }
}
