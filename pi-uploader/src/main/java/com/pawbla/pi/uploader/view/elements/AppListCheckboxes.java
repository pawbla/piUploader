package com.pawbla.pi.uploader.view.elements;

import com.pawbla.pi.uploader.model.ItemsList;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppListCheckboxes {
    private List<JCheckBox> checkBoxList;

    public void prepareCheckboxList(ItemsList itemsList) {
        checkBoxList = itemsList.getItems().keySet().stream()
                .map(JCheckBox::new)
                .collect(Collectors.toList());
    }

    public List<JCheckBox> getCheckboxList() {
        return checkBoxList;
    }
}
