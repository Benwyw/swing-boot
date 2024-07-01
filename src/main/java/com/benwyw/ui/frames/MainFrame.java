package com.benwyw.ui.frames;

import com.benwyw.model.PanelEnum;
import com.benwyw.ui.panels.BatchPanel;
import com.benwyw.ui.panels.InfoPanel;
import com.benwyw.ui.panels.LoginPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainFrame extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private LoginPanel loginPanel;
    private InfoPanel infoPanel;
    private BatchPanel batchPanel;

    @Autowired
    public MainFrame(JPanel mainPanel, CardLayout cardLayout, LoginPanel loginPanel, InfoPanel infoPanel, BatchPanel batchPanel) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.loginPanel = loginPanel;
        this.infoPanel = infoPanel;
        this.batchPanel = batchPanel;

        setSize(960, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MainFrame title");

        mainPanel.add(loginPanel, PanelEnum.LOGIN_PANEL.getName());
        mainPanel.add(infoPanel, PanelEnum.INFO_PANEL.getName());
        mainPanel.add(batchPanel, PanelEnum.BATCH_PANEL.getName());

        add(mainPanel);
        cardLayout.show(mainPanel, PanelEnum.LOGIN_PANEL.getName());
    }

}
