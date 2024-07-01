package com.benwyw.ui.frames;

import com.benwyw.model.PanelEnum;
import com.benwyw.ui.panels.InfoPanel;
import com.benwyw.ui.panels.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MainFrame title");

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel(mainPanel, cardLayout);
        InfoPanel infoPanel = new InfoPanel(mainPanel, cardLayout);

        mainPanel.add(loginPanel, PanelEnum.LOGIN_PANEL.getName());
        mainPanel.add(infoPanel, PanelEnum.INFO_PANEL.getName());

        add(mainPanel);
        cardLayout.show(mainPanel, PanelEnum.LOGIN_PANEL.getName());
    }

}
