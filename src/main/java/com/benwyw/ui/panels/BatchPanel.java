package com.benwyw.ui.panels;

import com.benwyw.model.PanelEnum;
import com.benwyw.service.MiscService;
import com.benwyw.ui.dialogs.CustomNameDialog;
import com.benwyw.ui.listener.ScriptButtonListener;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Locale;

@Slf4j
@Component
public class BatchPanel extends JPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTextArea textArea;

    public BatchPanel(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();


        /*
        LINE 0
         */
        constraints.gridy = 0;
        constraints.gridx = 0;

        // JTextArea at the top
        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setLocale(Locale.ENGLISH);
        JScrollPane scrollPane = new JScrollPane(textArea);
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        add(scrollPane, constraints);

        /*
        LINE 0+
         */
        constraints = new GridBagConstraints();
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        /*
        LINE 1
         */
        constraints.gridy = 1;

        // Back to LoginPanel
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.cardLayout.show(this.mainPanel, PanelEnum.LOGIN_PANEL.getName());
        });
        constraints.gridx = 0;
        add(backButton, constraints);

        // Batch 1
        JButton batch1Button = new JButton("Batch 1");
        batch1Button.addActionListener(new ScriptButtonListener("copy1.bat", this));
        constraints.gridx = 1;
        add(batch1Button, constraints);


        /*
        LINE 2
         */
        constraints.gridy = 2;

        // Back to LoginPanel
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            this.textArea.setText(null);
        });
        constraints.gridx = 0;
        add(clearButton, constraints);

        // Batch 2
        JButton batch2Button = new JButton("Batch 2");
        batch2Button.addActionListener(new ScriptButtonListener("copy1.bat", this));
        constraints.gridx = 1;
        add(batch2Button, constraints);


        /*
        LINE 3
         */
        constraints.gridy = 3;

        // JComboBox
        String[] items = {"Item 1", "Item 2", "Item 3"};
        JComboBox comboBox = new JComboBox(items);
        comboBox.addActionListener(e -> {
            String selectedItem = !ObjectUtils.isEmpty(comboBox.getSelectedItem()) ? comboBox.getSelectedItem().toString() : null;
            log.info(String.format("User selected: %s", selectedItem));
            if ("Item 2".equals(selectedItem)) {
                log.info("User selected item 2! Proceed to database operation...");
                appendText(String.valueOf(selectedItem));
            }
        });
        constraints.gridx = 0;
        add(comboBox, constraints);

        // Batch 3
        JButton batch3Button = new JButton("Batch 3");
        batch3Button.addActionListener(new ScriptButtonListener("copy1.bat", this));
        constraints.gridx = 1;
        add(batch3Button, constraints);
    }

    /**
     * Append text to console
     * @param text String
     */
    public void appendText(String text) {
        if (StringUtils.isNotBlank(textArea.getText())) {
            textArea.append("\n");
        }
        textArea.append(text);
    }
}
