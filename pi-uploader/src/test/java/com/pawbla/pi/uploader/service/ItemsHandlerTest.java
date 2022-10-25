package com.pawbla.pi.uploader.service;

import com.pawbla.pi.uploader.model.ItemsList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ItemsHandlerTest {

    @Mock
    private FileReaderService readerService;

    private ItemsHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(readerService.readFile(anyString())).thenReturn(getReadString());
        handler = new ItemsHandler("", readerService);
    }

    @Test
    void shouldProvideConfiguredFolders() {
        handler.loadConfiguration();
        ItemsList actual = handler.getConfiguration();

        assertEquals(3, actual.getItems().keySet().size());

        assertEquals(3, actual.getItems().size());
        assertTrue(actual.getItems().containsKey("display"));
        assertEquals("E:\\Projects\\HomeActiveManager\\home-active-manager-parent\\release", actual.getItems().get("display").getSource());
        assertEquals("/usr/local/bin/displayApp/", actual.getItems().get("display").getDestination());
        assertNull(actual.getItems().get("display").getFile());
        assertTrue(actual.getItems().containsKey("backend_all"));
        assertEquals("E:\\Projects\\HomeActiveManager\\home-active-manager-parent\\release", actual.getItems().get("backend_all").getSource());
        assertEquals("/usr/local/bin/homeActiveManager/", actual.getItems().get("backend_all").getDestination());
        assertNull(actual.getItems().get("backend_all").getFile());
        assertTrue(actual.getItems().containsKey("weather_app_jar"));
        assertEquals("E:\\Projects\\HomeActiveManager\\home-active-manager-parent\\release\\weather-service-module", actual.getItems().get("weather_app_jar").getSource());
        assertEquals("/usr/local/bin/homeActiveManager/weather-service-module/", actual.getItems().get("weather_app_jar").getDestination());
        assertEquals("weather-service-module", actual.getItems().get("weather_app_jar").getFile());
    }

    private String getReadString() {
        return "{\n" +
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
                "}";
    }
}