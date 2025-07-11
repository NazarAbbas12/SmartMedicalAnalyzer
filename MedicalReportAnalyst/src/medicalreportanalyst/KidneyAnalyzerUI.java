package medicalreportanalyst;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KidneyAnalyzerUI extends JFrame {
    private JComboBox<String> testSelector;
    private JTextField creat1Field, bunField, albuminField, egfrField, creat2Field, creat3Field;
    private JLabel creat1Label, bunLabel, albuminLabel, egfrLabel, creat2Label, creat3Label;
    private JLabel severityLabel, adviceLabel;
    private JPanel resultsPanel;
    private JPanel inputPanel;

    public KidneyAnalyzerUI() {
        setTitle("Smart Medical Analyzer - Kidney");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
          setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        createSidebar();
        createMainContent();

        setVisible(true);
    }

    // Sidebar remains unchanged...
  private void createSidebar() {
        JPanel sidebar = new JPanel(new GridLayout(3, 1, 0, 20));
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

        kidneybtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You are already in Kidney Analyzer!");
        });

        heartbtn.addActionListener(e -> {
            new HeartAnalyzerUI();
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
        JLabel title = new JLabel("Kidney Analyzer");
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
        String[] testOptions = {"Creatinine & BUN", "Albumin & eGFR", "Creatinine 3-Stage"};
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

        creat1Label = createInputLabel("Creatinine:");
        creat1Field = createInputField();

        bunLabel = createInputLabel("BUN:");
        bunField = createInputField();

        albuminLabel = createInputLabel("Albumin:");
        albuminField = createInputField();

        egfrLabel = createInputLabel("eGFR:");
        egfrField = createInputField();

        creat2Label = createInputLabel("Creatinine (Repeat):");
        creat2Field = createInputField();

        creat3Label = createInputLabel("Creatinine (Stage 3):");
        creat3Field = createInputField();

         // Add fields to input panel
        int row = 0;
        addInputRow(inputPanel,gbc, creat1Label, creat1Field, row++);
        addInputRow(inputPanel,gbc, bunLabel, bunField, row++);
        addInputRow(inputPanel,gbc, albuminLabel, albuminField, row++);
        addInputRow(inputPanel,gbc, egfrLabel, egfrField, row++);
        addInputRow(inputPanel,gbc, creat2Label, creat2Field,  row++);
        addInputRow(inputPanel,gbc, creat3Label, creat3Field,  row++);

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
        boolean isCreatinineBUN = selected.equals("Creatinine & BUN");
        boolean isAlbuminEgfr = selected.equals("Albumin & eGFR");
        boolean isCreatinineStage = selected.equals("Creatinine 3-Stage");

        creat1Label.setVisible(isCreatinineBUN);
        creat1Field.setVisible(isCreatinineBUN);
        bunLabel.setVisible(isCreatinineBUN);
        bunField.setVisible(isCreatinineBUN);

        albuminLabel.setVisible(isAlbuminEgfr);
        albuminField.setVisible(isAlbuminEgfr);
        egfrLabel.setVisible(isAlbuminEgfr);
        egfrField.setVisible(isAlbuminEgfr);

        creat2Label.setVisible(isCreatinineStage);
        creat2Field.setVisible(isCreatinineStage);
        creat3Label.setVisible(isCreatinineStage);
        creat3Field.setVisible(isCreatinineStage);

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void handleSubmit() {
        try {
            String selectedTest = (String) testSelector.getSelectedItem();
            String severity = "";
            String advice = "";

            if (selectedTest.equals("Creatinine & BUN")) {
                double creat = Double.parseDouble(creat1Field.getText());
                double bun = Double.parseDouble(bunField.getText());
                CreatinineandBUNTestKIDNEY1 analyzer = new CreatinineandBUNTestKIDNEY1(creat, bun);
                analyzer.analyze();
                 severityLabel.setText("Severity: " + analyzer.severity());
                adviceLabel.setText("Advice: " + analyzer.advice());

            } else if (selectedTest.equals("Albumin & eGFR")) {
                double albumin = Double.parseDouble(albuminField.getText());
                double egfr = Double.parseDouble(egfrField.getText());
                UrineAlbuminandCreatinineTestKIDNEY2 analyzer = new UrineAlbuminandCreatinineTestKIDNEY2(albumin, egfr);
                analyzer.analyze();
                severityLabel.setText("Severity: " + analyzer.severity());
                adviceLabel.setText("Advice: " + analyzer.advice());

            } else if (selectedTest.equals("Creatinine 3-Stage")) {
                double c2 = Double.parseDouble(creat2Field.getText());
                double c3 = Double.parseDouble(creat3Field.getText());
                EGFRandCreatinineTestKIDNEY3 analyzer = new EGFRandCreatinineTestKIDNEY3(c2, c3);
                analyzer.analyze();
                 severityLabel.setText("Severity: " + analyzer.severity());
                adviceLabel.setText("Advice: " + analyzer.advice());
            }

          
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
        }
    }
    
}
