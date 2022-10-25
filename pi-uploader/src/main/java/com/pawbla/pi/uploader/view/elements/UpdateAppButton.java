package com.pawbla.pi.uploader.view.elements;

import com.pawbla.pi.uploader.service.UpdateAppService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class UpdateAppButton extends JButton {
    private static final String LABEL = "Upload";

    private final UpdateAppService updateAppService;

    public UpdateAppButton(UpdateAppService updateAppService) {
        setText(LABEL);
        addActionListener(getButtonAction());
        this.updateAppService = updateAppService;
    }

    private ActionListener getButtonAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("UPLOAD APPLICATION BUTTON");
                updateAppService.uploadApps();
            }
        };
    }
}
