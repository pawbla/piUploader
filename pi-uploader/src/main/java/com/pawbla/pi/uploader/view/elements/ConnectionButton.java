package com.pawbla.pi.uploader.view.elements;

import com.pawbla.pi.uploader.service.SSHService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ConnectionButton extends JButton {
    private static final String LABEL = "Connect to RPi";

    private SSHService sshService;

    public ConnectionButton(SSHService sshService) {
        setText(LABEL);
        addActionListener(getButtonAction());
        this.sshService = sshService;
    }

    private ActionListener getButtonAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BUTTON");
                sshService.connect();
            }
        };
    }
}
