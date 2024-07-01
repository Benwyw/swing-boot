package com.benwyw.ui.panels;

import com.benwyw.model.PanelEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

import static com.benwyw.model.StatusConstant.LOGIN_SUCCESS_MESSAGE;

@Slf4j
@Component
public class LoginPanel extends JPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    @Autowired
    private InfoPanel infoPanel;

    public LoginPanel(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            this.cardLayout.show(this.mainPanel, PanelEnum.INFO_PANEL.getName());
            infoPanel.appendText(LOGIN_SUCCESS_MESSAGE);
        });

        add(loginButton);
    }

}
