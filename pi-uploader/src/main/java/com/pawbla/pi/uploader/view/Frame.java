package com.pawbla.pi.uploader.view;

import com.pawbla.pi.uploader.view.panels.ActionsPanel;
import com.pawbla.pi.uploader.view.panels.ConnectionPanel;
import com.pawbla.pi.uploader.view.panels.ItemsPanel;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class Frame extends JFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;
    private static final String TITLE = "PI Uploader";

    public Frame(ConnectionPanel connectionPanel, ItemsPanel itemsPanel, ActionsPanel actionsPanel) {
        setSize(WIDTH,HEIGHT);
        setLocationByPlatform(true);
        setTitle(TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(connectionPanel, BorderLayout.NORTH);
        add(itemsPanel, BorderLayout.CENTER);
        add(actionsPanel, BorderLayout.SOUTH);
    }
}
