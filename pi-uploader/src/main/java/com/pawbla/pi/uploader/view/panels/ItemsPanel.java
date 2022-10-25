package com.pawbla.pi.uploader.view.panels;

import com.pawbla.pi.uploader.view.elements.AppListCheckboxes;
import com.pawbla.pi.uploader.service.ItemsHandler;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class ItemsPanel extends JPanel {
    private ItemsHandler itemsHandler;

    public ItemsPanel(ItemsHandler itemsHandler, AppListCheckboxes appListCheckboxes) {
        this.itemsHandler = itemsHandler;
        this.itemsHandler.loadConfiguration();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        appListCheckboxes.prepareCheckboxList(this.itemsHandler.getConfiguration());
        appListCheckboxes.getCheckboxList().forEach(this::add);
    }
}
