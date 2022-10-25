package com.pawbla.pi.uploader.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.Map;

public class ItemsList implements Serializable {

    private Map<String, Item> items;

    public ItemsList(Map<String, Item> items) {
        this.items = items;
    }

    @JsonCreator
    public static ItemsList create(Map<String, Item> items) {
        return new ItemsList(items);
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }
}
