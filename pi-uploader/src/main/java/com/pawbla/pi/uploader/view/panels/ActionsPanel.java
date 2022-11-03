package com.pawbla.pi.uploader.view.panels;

import com.pawbla.pi.uploader.view.elements.UpdateAppButton;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class ActionsPanel extends JPanel {

    public ActionsPanel(UpdateAppButton updateAppButton) {
        add(updateAppButton);
    }
}
