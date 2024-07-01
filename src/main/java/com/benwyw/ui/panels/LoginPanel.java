package com.benwyw.ui.panels;

import com.benwyw.model.PanelEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

import static com.benwyw.model.StatusConstant.*;

@Slf4j
@Component
public class LoginPanel extends JPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    @Autowired
    private InfoPanel infoPanel;

    @Autowired
    private BatchPanel batchPanel;

    public LoginPanel(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            this.cardLayout.show(this.mainPanel, PanelEnum.INFO_PANEL.getName());
            infoPanel.appendText(LOGIN_SUCCESS_MESSAGE);
        });

        JButton batchButton = new JButton("Batch");
        batchButton.addActionListener(e -> {
            this.cardLayout.show(this.mainPanel, PanelEnum.BATCH_PANEL.getName());
            batchPanel.appendText(BATCH_INIT_SUCCESS_MESSAGE);
        });

        constraints.gridx = 0;

        constraints.gridy = 0;
        add(loginButton, constraints);

        constraints.gridy = 1;
        add(batchButton, constraints);
    }

}
