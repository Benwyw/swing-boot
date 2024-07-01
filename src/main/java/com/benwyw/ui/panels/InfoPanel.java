package com.benwyw.ui.panels;

import com.benwyw.model.PanelEnum;
import com.benwyw.service.MiscService;
import com.benwyw.ui.dialogs.CustomNameDialog;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@Slf4j
@Component
public class InfoPanel extends JPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTextArea textArea;

    @Autowired
    private MiscService miscService;

    /**
     * JMenu, JMenuItem, JMenuBar: These components are used to create menus in your application.
     * JToolBar: It’s used to create toolbars in your application.
     * JList: It’s used to display a list of items for selection.
     * JTable: It’s used to display and edit regular two-dimensional tables of cells.
     * JTree: It’s used to display hierarchical data, like a file system.
     * JSplitPane: It’s used to divide two components (either horizontally or vertically) that the user can resize.
     * JTabbedPane: It’s used to switch between a group of components by clicking on a tab with a given title and/or icon.
     * JProgressBar: It’s used to visualize the progress of some task.
     * JSpinner: It’s used to allow the user to enter a number or an object value from an ordered sequence, with the input value being incremented or decremented on each button click.
     * JColorChooser, JFileChooser: These are complex components to let users choose a Color or a File.
     * @param mainPanel JPanel
     * @param cardLayout CardLayout
     */
    public InfoPanel(JPanel mainPanel, CardLayout cardLayout) {
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
        JButton leftButton = new JButton("Back");
        leftButton.addActionListener(e -> {
            this.cardLayout.show(this.mainPanel, PanelEnum.LOGIN_PANEL.getName());
        });
        constraints.gridx = 0;
        add(leftButton, constraints);

        // Upload files
        JButton rightButton = new JButton("Upload");
        rightButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                appendText(selectedFile.getName());
            }
        });
        constraints.gridx = 1;
        add(rightButton, constraints);

        // Enter additional information
        JButton bottomButton = new JButton("Enter");
        bottomButton.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            CustomNameDialog dialog = new CustomNameDialog(parentFrame, this);
            dialog.setVisible(true);
        });
        constraints.gridx = 2;
        add(bottomButton, constraints);


        /*
        LINE 2
         */
        constraints.gridy = 2;

        // JCheckBox
        JCheckBox checkBox = new JCheckBox("Check me");
        constraints.gridx = 0;
        add(checkBox, constraints);

        // JRadioButton
        JRadioButton radioButton1 = new JRadioButton("Option 1");
        JRadioButton radioButton2 = new JRadioButton("Option 2");
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        constraints.gridx = 1;
        add(radioButton1, constraints);
        constraints.gridx = 2;
        add(radioButton2, constraints);


        /*
        LINE 3
         */
        constraints.gridy = 3;

        // JSlider
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        constraints.gridx = 0;
        add(slider, constraints);

        // JComboBox
        String[] items = {"Item 1", "Item 2", "Item 3"};
        JComboBox comboBox = new JComboBox(items);
        comboBox.addActionListener(e -> {
            String selectedItem = !ObjectUtils.isEmpty(comboBox.getSelectedItem()) ? comboBox.getSelectedItem().toString() : null;
            log.info(String.format("User selected: %s", selectedItem));
            if ("Item 2".equals(selectedItem)) {
                log.info("User selected item 2! Proceed to database operation...");
                long featureCount = miscService.getFeaturesCount();
                log.info(String.format("Database returned featureCount: %s", featureCount));
                appendText(String.valueOf(featureCount));
            }
        });
        constraints.gridx = 2;
        add(comboBox, constraints);


        /*
        LINE 4
         */
        constraints.gridy = 4;

        // JLabel
        JLabel label = new JLabel("Password");
        constraints.gridx = 0;
        add(label, constraints);

        // JPasswordField
        JPasswordField passwordField = new JPasswordField(10);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 1;
        add(passwordField, constraints);


        /*
        LINE 5
         */
        constraints.gridy = 5;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        // JTextArea, JScrollPane
        JTextArea textAreaLarge = new JTextArea(5, 20);
        JScrollPane scrollPaneLarge = new JScrollPane(textAreaLarge);
        constraints.gridx = 0;
        add(scrollPaneLarge, constraints);
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
