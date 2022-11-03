package com.pawbla.pi.uploader.view.panels;

import com.pawbla.pi.uploader.view.elements.PasswordTextArea;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class ConnectionPanel extends JPanel {

    public ConnectionPanel(PasswordTextArea passwordTextArea) {
        add(passwordTextArea);
    }
}
