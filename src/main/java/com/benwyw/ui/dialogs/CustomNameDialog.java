package com.benwyw.ui.dialogs;

import javax.swing.*;

public class CustomNameDialog extends JDialog {
    private JTextField nameField;

    public CustomNameDialog(JFrame parentFrame) {
        super(parentFrame, "Enter Your Name", true); // Modal dialog

        nameField = new JTextField(20);
        JButton okButton = new JButton("OK");

        okButton.addActionListener(e -> {
            String enteredName = nameField.getText();
            JOptionPane.showMessageDialog(this, "Hello, " + enteredName + "!");
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