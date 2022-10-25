package com.pawbla.pi.uploader.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderServiceTest {

    private final FileReaderService readerService = new FileReaderService();

    @Test
    void shouldReadFile() {
        String expected = "{\n" +
                "  \"display\": {\n" +
                "    \"source\": \"E:\\\\Projects\\\\HomeActiveManager\\\\home-active-manager-parent\\\\release\",\n" +
                "    \"destination\": \"/usr/local/bin/displayApp/\"\n" +
                "  },\n" +
                "  \"backend_all\": {\n" +
                "    \"source\": \"E:\\\\Projects\\\\HomeActiveManager\\\\home-active-manager-parent\\\\release\",\n" +
                "    \"destination\": \"/usr/local/bin/homeActiveManager/\"\n" +
                "  },\n" +
                "  \"weather_app_jar\": {\n" +
                "    \"source\": \"E:\\\\Projects\\\\HomeActiveManager\\\\home-active-manager-parent\\\\release\\\\weather-service-module\",\n" +
                "    \"file\": \"weather-service-module\",\n" +
                "    \"destination\": \"/usr/local/bin/homeActiveManager/weather-service-module/\"\n" +
                "  }\n" +
                "}\n";

        String actual = readerService.readFile("src/test/resources/config.json");
        assertEquals(expected, actual);
    }

}