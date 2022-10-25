package com.pawbla.pi.uploader.view.elements;

import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class PasswordTextArea extends JPasswordField {

    public PasswordTextArea() {
        setColumns(10);
    }
}
