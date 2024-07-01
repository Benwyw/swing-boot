package com.benwyw.ui.panels;

import com.benwyw.model.PanelEnum;
import com.benwyw.ui.dialogs.CustomNameDialog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@Slf4j
public class InfoPanel extends JPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;

//    @Autowired
//    private MiscService miscService;

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

        // Layout
//        setLayout(new BorderLayout());
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // JTextArea at the top
        JTextArea textArea = new JTextArea("You are now logged in!");
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(scrollPane, constraints);

        // JButton on the left
        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(e -> {
            this.cardLayout.show(this.mainPanel, PanelEnum.LOGIN_PANEL.getName());
        });
        constraints = new GridBagConstraints();
        constraints.gridx = 11;
        constraints.gridy = 1;
        add(leftButton, constraints);

        // JButton on the right
        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                textArea.setText(selectedFile.getName());
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(rightButton, constraints);

        // JButton at the bottom
        JButton bottomButton = new JButton("Bottom");
        bottomButton.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            CustomNameDialog dialog = new CustomNameDialog(parentFrame);
            dialog.setVisible(true);
        });
        constraints.gridx = 1;
        constraints.gridy = 11;
        add(bottomButton, constraints);

        // JCheckBox
        JCheckBox checkBox = new JCheckBox("Check me");
        add(checkBox);

        // JRadioButton
        JRadioButton radioButton1 = new JRadioButton("Option 1");
        JRadioButton radioButton2 = new JRadioButton("Option 2");
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        add(radioButton1);
        add(radioButton2);

        // JComboBox
        String[] items = {"Item 1", "Item 2", "Item 3"};
        JComboBox comboBox = new JComboBox(items);
        comboBox.addActionListener(e -> {
            String selectedItem = !ObjectUtils.isEmpty(comboBox.getSelectedItem()) ? comboBox.getSelectedItem().toString() : null;
            log.info(String.format("User selected: %s", selectedItem));
            if ("Item 2".equals(selectedItem)) {
                log.info("User selected item 2! Proceed to database operation...");
//                long featureCount = miscService.getFeaturesCount();
//                log.info(String.format("Database returned featureCount: %s", featureCount));
//                textArea.setText(String.valueOf(featureCount));
            }
        });
        add(comboBox);

        // JSlider
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        add(slider);

        // JPasswordField
        JPasswordField passwordField = new JPasswordField(10);
        add(passwordField);

        // JLabel
        JLabel label = new JLabel("This is a label");
        add(label);

        // JTextArea, JScrollPane
        JTextArea textAreaLarge = new JTextArea(5, 20);
        JScrollPane scrollPaneLarge = new JScrollPane(textAreaLarge);
        add(scrollPaneLarge);
    }

}
