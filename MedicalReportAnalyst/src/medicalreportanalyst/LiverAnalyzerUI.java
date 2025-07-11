package medicalreportanalyst;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LiverAnalyzerUI extends JFrame {
    private JComboBox<String> testSelector;
    private JTextField altField, astField;
    private JTextField alpField, ggtField;
    private JTextField bilirubinField, indirectBilirubinField;
    private JLabel altLabel, astLabel, alpLabel, ggtLabel;
    private JLabel directLabel, indirectLabel;
    private JLabel severityLabel, adviceLabel;
    private JPanel resultsPanel;
    private JPanel inputPanel;

    public LiverAnalyzerUI() {
       
        setTitle("Smart Medical Analyzer - Liver");
        setSize(1000, 650);
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
            JOptionPane.showMessageDialog(this, "You are already in Liver Analyzer!");
        });
        heartbtn.addActionListener(e -> {
            new HeartAnalyzerUI();
            this.dispose();
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
        JLabel title = new JLabel("Liver Analyzer");
        title.setFont(new Font("Segoe UI", Font.BOLD, 64));
        title.setForeground(new Color(0, 123, 255));
        headerPanel.add(title);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        // Test Selection
        JPanel testSelectionPanel = new JPanel();
        testSelectionPanel.setBackground(Color.WHITE);
        String[] testOptions = {"ALT and AST Test", "ALP and GGT Test", "Bilirubin Test"};
        testSelector = new JComboBox<>(testOptions);
        testSelector.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        testSelector.setPreferredSize(new Dimension(280, 40));
        testSelectionPanel.add(testSelector);
        centerPanel.add(testSelectionPanel);

        // Input Panel
        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        inputPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        altLabel = createInputLabel("ALT:");
        altField = createInputField();
        astLabel = createInputLabel("AST:");
        astField = createInputField();
        alpLabel = createInputLabel("ALP:");
        alpField = createInputField();
        ggtLabel = createInputLabel("GGT:");
        ggtField = createInputField();
        directLabel = createInputLabel("Direct Bilirubin:");
        bilirubinField = createInputField();
        indirectLabel = createInputLabel("Indirect Bilirubin:");
        indirectBilirubinField = createInputField();

        int row = 0;
        addInputRow(inputPanel, gbc, altLabel, altField, row++);
        addInputRow(inputPanel, gbc, astLabel, astField, row++);
        addInputRow(inputPanel, gbc, alpLabel, alpField, row++);
        addInputRow(inputPanel, gbc, ggtLabel, ggtField, row++);
        addInputRow(inputPanel, gbc, directLabel, bilirubinField, row++);
        addInputRow(inputPanel, gbc, indirectLabel, indirectBilirubinField, row++);

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

        boolean isAltAst = selected.equals("ALT and AST Test");
        boolean isAlpGgt = selected.equals("ALP and GGT Test");
        boolean isBilirubin = selected.equals("Bilirubin Test");

        altLabel.setVisible(isAltAst);
        altField.setVisible(isAltAst);
        astLabel.setVisible(isAltAst);
        astField.setVisible(isAltAst);

        alpLabel.setVisible(isAlpGgt);
        alpField.setVisible(isAlpGgt);
        ggtLabel.setVisible(isAlpGgt);
        ggtField.setVisible(isAlpGgt);

        directLabel.setVisible(isBilirubin);
        bilirubinField.setVisible(isBilirubin);
        indirectLabel.setVisible(isBilirubin);
        indirectBilirubinField.setVisible(isBilirubin);

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void handleSubmit() {
        try {
            String selectedTest = (String) testSelector.getSelectedItem();
            if (selectedTest.equals("ALT and AST Test")) {
                double alt = Double.parseDouble(altField.getText());
                double ast = Double.parseDouble(astField.getText());

                ASTandALTtestLIVER1 liver = new ASTandALTtestLIVER1(alt, ast);
                liver.analyze();
                severityLabel.setText("Severity: " + liver.severity());
                adviceLabel.setText("Advice: " + liver.advice());

            } else if (selectedTest.equals("ALP and GGT Test")) {
                double alp = Double.parseDouble(alpField.getText());
                double ggt = Double.parseDouble(ggtField.getText());

                ALPandGGTtestLIVER2 liver = new ALPandGGTtestLIVER2(alp, ggt);
                liver.analyze();
                severityLabel.setText("Severity: " + liver.severity());
                adviceLabel.setText("Advice: " + liver.advice());

            } else if (selectedTest.equals("Bilirubin Test")) {
                double bilirubin = Double.parseDouble(bilirubinField.getText());
                double indirectBilirubin = Double.parseDouble(indirectBilirubinField.getText());

                BilirubinTestLIVER3 liver = new BilirubinTestLIVER3(bilirubin, indirectBilirubin);
                liver.analyze();
                severityLabel.setText("Severity: " + liver.severity());
                adviceLabel.setText("Advice: " + liver.advice());
            }

            resultsPanel.setVisible(true);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values.");
        }
    }
}
