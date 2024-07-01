package com.benwyw.ui.frames;

import com.benwyw.model.PanelEnum;
import com.benwyw.ui.panels.InfoPanel;
import com.benwyw.ui.panels.LoginPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainFrame extends JFrame {

    private LoginPanel loginPanel;
    private InfoPanel infoPanel;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    @Autowired
    public MainFrame(LoginPanel loginPanel, InfoPanel infoPanel, JPanel mainPanel, CardLayout cardLayout) {
        this.loginPanel = loginPanel;
        this.infoPanel = infoPanel;
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MainFrame title");

        mainPanel.add(loginPanel, PanelEnum.LOGIN_PANEL.getName());
        mainPanel.add(infoPanel, PanelEnum.INFO_PANEL.getName());

        add(mainPanel);
        cardLayout.show(mainPanel, PanelEnum.LOGIN_PANEL.getName());
    }

}
