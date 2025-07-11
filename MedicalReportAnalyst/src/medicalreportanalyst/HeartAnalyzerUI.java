package medicalreportanalyst;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HeartAnalyzerUI extends JFrame {
    private JComboBox<String> testSelector;
    private JTextField ldlField, hdlField, heartRateField, qtIntervalField, systolicField, diastolicField;
    private JLabel ldlLabel, hdlLabel, heartRateLabel, qtIntervalLabel, systolicLabel, diastolicLabel;
    private JLabel severityLabel, adviceLabel;
    private JPanel resultsPanel, inputPanel;

    public HeartAnalyzerUI() {
        setTitle("Smart Medical Analyzer - Heart");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
          setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        createSidebar();
        createMainContent();

        setVisible(true);
    }

    private void createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(3, 1, 0, 20));
        sidebar.setPreferredSize(new Dimension(180, 0));
        sidebar.setBackground(new Color(30, 30, 60));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton liverbtn = createSidebarButton("Liver");
        JButton heartbtn = createSidebarButton("Heart");
        JButton kidneybtn = createSidebarButton("Kidney");

        liverbtn.addActionListener(e -> {
            new LiverAnalyzerUI();
            this.dispose();
        });

        heartbtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You are already in Heart Analyzer!");
        });

        kidneybtn.addActionListener(e -> {
            new KidneyAnalyzerUI();
            this.dispose();
        });

        sidebar.add(liverbtn);
        sidebar.add(heartbtn);
        sidebar.add(kidneybtn);

        add(sidebar, BorderLayout.WEST);
    }

    private JButton createSidebarButton(String text) {
          JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(60, 60, 120));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btn;
    }

    private void createMainContent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
          mainPanel.setBackground(Color.WHITE);
          
        // Header
       JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        JLabel title = new JLabel("Heart Analyzer");
        title.setFont(new Font("Segoe UI", Font.BOLD, 64));
        title.setForeground(new Color(0, 123, 255));
        headerPanel.add(title);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        // Test selection
        JPanel testSelectionPanel = new JPanel();
        testSelectionPanel.setBackground(Color.WHITE);
        String[] testOptions = {"LDL and HDL Test", "Heart Rate and QT Test", "Blood Pressure Test"};
        testSelector = new JComboBox<>(testOptions);
        testSelector.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        testSelector.setPreferredSize(new Dimension(280, 40));
        testSelectionPanel.add(testSelector);
        centerPanel.add(testSelectionPanel);

        // Input Fields
         inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        inputPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create Labels and Fields
        ldlLabel = createInputLabel("LDL:");
        ldlField = createInputField();

        hdlLabel = createInputLabel("HDL:");
        hdlField = createInputField();

        heartRateLabel = createInputLabel("Heart Rate:");
        heartRateField = createInputField();

        qtIntervalLabel = createInputLabel("QT Interval:");
        qtIntervalField = createInputField();

        systolicLabel = createInputLabel("Systolic:");
        systolicField = createInputField();

        diastolicLabel = createInputLabel("Diastolic:");
        diastolicField = createInputField();

        // Add fields to input panel
        int row = 0;
        addInputRow(inputPanel,gbc, ldlLabel, ldlField, row++);
        addInputRow(inputPanel,gbc, hdlLabel, hdlField, row++);
        addInputRow(inputPanel,gbc, heartRateLabel, heartRateField, row++);
        addInputRow(inputPanel,gbc, qtIntervalLabel, qtIntervalField, row++);
        addInputRow(inputPanel,gbc, systolicLabel, systolicField,  row++);
        addInputRow(inputPanel,gbc, diastolicLabel, diastolicField,  row++);

        centerPanel.add(inputPanel);

        // Analyze Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton submitButton = new JButton("Analyze");
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 28));
        submitButton.setBackground(new Color(0, 123, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(180, 50));
        buttonPanel.add(submitButton);
        centerPanel.add(buttonPanel);

        // Results Panel
         resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(new Color(245, 250, 255));
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        resultsPanel.setMaximumSize(new Dimension(800, 200));
        resultsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        severityLabel = new JLabel("Severity: ");
        severityLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        severityLabel.setForeground(new Color(220, 53, 69));

        adviceLabel = new JLabel("Advice: ");
        adviceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        adviceLabel.setForeground(new Color(33, 37, 41));

        resultsPanel.add(severityLabel);
        resultsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        resultsPanel.add(adviceLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(resultsPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        testSelector.addActionListener(e -> updateInputVisibility());
        updateInputVisibility();

        submitButton.addActionListener(e -> handleSubmit());

        add(mainPanel, BorderLayout.CENTER);
    }

   private void addInputRow(JPanel panel, GridBagConstraints gbc, JLabel label, JTextField field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private JLabel createInputLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        return label;
    }

    private JTextField createInputField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        field.setPreferredSize(new Dimension(200, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private void updateInputVisibility() {
        String selected = (String) testSelector.getSelectedItem();

        boolean isLdlHdl = selected.equals("LDL and HDL Test");
        boolean isHQT = selected.equals("Heart Rate and QT Test");
        boolean isBPT = selected.equals("Blood Pressure Test");

        ldlLabel.setVisible(isLdlHdl);
        ldlField.setVisible(isLdlHdl);
        hdlLabel.setVisible(isLdlHdl);
        hdlField.setVisible(isLdlHdl);

        heartRateLabel.setVisible(isHQT);
        heartRateField.setVisible(isHQT);
        qtIntervalLabel.setVisible(isHQT);
        qtIntervalField.setVisible(isHQT);

        systolicLabel.setVisible(isBPT);
        systolicField.setVisible(isBPT);
        diastolicLabel.setVisible(isBPT);
        diastolicField.setVisible(isBPT);

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void handleSubmit() {
        try {
            String selectedTest = (String) testSelector.getSelectedItem();

            if (selectedTest.equals("LDL and HDL Test")) {
                double ldl = Double.parseDouble(ldlField.getText());
                double hdl = Double.parseDouble(hdlField.getText());
                LDLandHDLTestHEART1 analyzer = new LDLandHDLTestHEART1(ldl, hdl);
                analyzer.analyze();
                severityLabel.setText("Severity: " + analyzer.severity());
                adviceLabel.setText("Advice: " + analyzer.advice());

            } else if (selectedTest.equals("Heart Rate and QT Test")) {
                double hr = Double.parseDouble(heartRateField.getText());
                double qt = Double.parseDouble(qtIntervalField.getText());
                HeartRateandQTTestHEART2 analyzer = new HeartRateandQTTestHEART2(hr, qt);
                analyzer.analyze();
                severityLabel.setText("Severity: " + analyzer.severity());
                adviceLabel.setText("Advice: " + analyzer.advice());

            } else if (selectedTest.equals("Blood Pressure Test")) {
                double sys = Double.parseDouble(systolicField.getText());
                double dia = Double.parseDouble(diastolicField.getText());
                BloodPressureTestHEART3 analyzer = new BloodPressureTestHEART3(sys, dia);
                analyzer.analyze();
                severityLabel.setText("Severity: " + analyzer.severity());
                adviceLabel.setText("Advice: " + analyzer.advice());
            }

            resultsPanel.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
        }
    }
}
