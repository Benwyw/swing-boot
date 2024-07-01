package com.benwyw.ui.panels;

import com.benwyw.model.PanelEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Slf4j
@Component
public class LoginPanel extends JPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public LoginPanel(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
//            mainPanel = (JPanel) getParent();
//            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();

            // TODO global String or ENUM
            this.cardLayout.show(this.mainPanel, PanelEnum.INFO_PANEL.getName());
        });

        add(loginButton);
    }

}
