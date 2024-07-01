package com.benwyw.ui.dialogs;

import com.benwyw.ui.panels.InfoPanel;

import javax.swing.*;

public class CustomNameDialog extends JDialog {
    private JTextField nameField;
    private InfoPanel infoPanel;

    public CustomNameDialog(JFrame parentFrame, InfoPanel infoPanel) {
        super(parentFrame, "Enter Your Name", true); // Modal dialog
        this.infoPanel = infoPanel;

        nameField = new JTextField(20);
        JButton okButton = new JButton("OK");

        okButton.addActionListener(e -> {
            String enteredName = nameField.getText();
            JOptionPane.showMessageDialog(this, "Hello, " + enteredName + "!");
            this.infoPanel.appendText(String.format("Welcome %s", enteredName));
            dispose(); // Close the dialog
        });

        JPanel contentPane = new JPanel();
        contentPane.add(new JLabel("Enter your name:"));
        contentPane.add(nameField);
        contentPane.add(okButton);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(parentFrame);
    }
}