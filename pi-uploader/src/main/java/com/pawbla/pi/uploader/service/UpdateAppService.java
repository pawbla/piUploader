package com.pawbla.pi.uploader.service;

import com.pawbla.pi.uploader.model.Item;
import com.pawbla.pi.uploader.view.elements.AppListCheckboxes;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UpdateAppService {

    private final ItemsHandler itemsHandler;
    private final AppListCheckboxes appListCheckboxes;

    public UpdateAppService(ItemsHandler itemsHandler, AppListCheckboxes appListCheckboxes) {
        this.itemsHandler = itemsHandler;
        this.appListCheckboxes = appListCheckboxes;
    }

    public void uploadApps() {
        System.out.println("START UPLOAD");
        List<Item> uploadApps = getAppToUpload();
        uploadApps.forEach(app -> System.out.println(app.getSource()));
        System.out.println("STOP UPLOAD");
    }

    private List<Item> getAppToUpload() {
        Map<String, Item> items = itemsHandler.getConfiguration().getItems();

        return appListCheckboxes.getCheckboxList().stream()
                .filter(AbstractButton::isSelected)
                .map(jCheckBox -> items.get(jCheckBox.getText()))
                .collect(Collectors.toList());
    }
}
