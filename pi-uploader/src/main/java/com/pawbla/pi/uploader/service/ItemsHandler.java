package com.pawbla.pi.uploader.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawbla.pi.uploader.model.ItemsList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ItemsHandler {
    private static final Logger LOG = LogManager.getLogger(ItemsHandler.class);

    private ItemsList itemsList;
    private final FileReaderService readerService;
    private final String appConfigPath;
    private final ObjectMapper mapper;

    public ItemsHandler(@Value("${items.config.path}") String appConfigPath, FileReaderService readerService) {
        this.appConfigPath = appConfigPath;
        this.readerService = readerService;
        this.mapper = new ObjectMapper();
    }

    public void loadConfiguration() {
        String configStr = readerService.readFile(appConfigPath);
        try {
            itemsList = mapper.readValue(configStr, ItemsList.class);
        } catch (JsonProcessingException e) {
            LOG.warn("JSON cannot be processed {}", e.getMessage());
            itemsList = null;
        }
    }

    public ItemsList getConfiguration() {
        return itemsList;
    }
}
