package medicalreportanalyst;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class SignUp extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton signupButton, loginButton;

    public SignUp() {
        setTitle("Smart Medical Analyzer - Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);

        // Main panel with light blue background
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(240, 248, 255)); // Light blue background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(20, 20, 20, 20);

        // Content panel with white background and blue rounded border
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createCompoundBorder(
                new RoundBorder(new Color(70, 130, 180), 15), // Blue rounded border
                BorderFactory.createEmptyBorder(40, 60, 40, 60) // Increased internal padding
        ));
        content.setPreferredSize(new Dimension(600, 700)); // Larger size for main content

        // Title with medical icon
        JLabel iconLabel = new JLabel("⚕"); // Medical symbol
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60)); // Larger icon
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel titleLabel = new JLabel("Create Your Account", SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28)); // Larger font
        titleLabel.setForeground(new Color(0, 102, 153));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));

        // Input fields - larger and centered
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);

        JPanel userPanel = createFieldPanel("Username:", usernameField);
        JPanel passPanel = createFieldPanel("Password:", passwordField);
        JPanel confirmPanel = createFieldPanel("Confirm Password:", confirmPasswordField);

        // Sign Up button - larger
        signupButton = new JButton("Sign Up");
        styleButton(signupButton);
        signupButton.setPreferredSize(new Dimension(200, 50)); // Larger button
        signupButton.addActionListener(e -> handleSignup());

        // Login link
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel alreadyLabel = new JLabel("Already have an account?");
        alreadyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Larger font

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Larger font
        loginButton.setForeground(new Color(0, 102, 153));
        loginButton.setBackground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        loginButton.setContentAreaFilled(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> handleLogin());

        loginPanel.add(alreadyLabel);
        loginPanel.add(loginButton);

        // Add components to content panel with proper spacing
        content.add(iconLabel);
        content.add(titleLabel);
        content.add(Box.createVerticalStrut(20));
        content.add(userPanel);
        content.add(Box.createVerticalStrut(25));
        content.add(passPanel);
        content.add(Box.createVerticalStrut(25));
        content.add(confirmPanel);
        content.add(Box.createVerticalStrut(35));
        content.add(signupButton);
        content.add(Box.createVerticalStrut(25));
        content.add(loginPanel);

        // Center the content panel
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(240, 248, 255));
        centerWrapper.add(content);

        root.add(centerWrapper, gbc);
        setContentPane(root);
        
        setVisible(true);
    }

    private JPanel createFieldPanel(String labelText, JComponent inputField) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Larger font
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputField.setMaximumSize(new Dimension(400, 45)); // Larger input fields
        inputField.setPreferredSize(new Dimension(400, 45));
        inputField.setBorder(new RoundBorder(new Color(200, 200, 200), 8));
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Larger font
        if (inputField instanceof JTextField)
            ((JTextField)inputField).setMargin(new Insets(8, 15, 8, 15)); // Larger padding

        panel.add(label);
        panel.add(Box.createVerticalStrut(8));
        panel.add(inputField);

        return panel;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Larger font
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 153));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30)); // Larger padding
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
    }

    private void handleSignup() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String dbPath = "C:/Users/HAROON TRADERS/OneDrive/Documents/MedicalDatabase.accdb";
            String url = "jdbc:ucanaccess://" + dbPath;
            Connection conn = DriverManager.getConnection(url);

            String checkSql = "SELECT * FROM Table1 WHERE UserName = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                rs.close(); checkStmt.close(); conn.close();
                return;
            }

            rs.close(); checkStmt.close();
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            String insertSql = "INSERT INTO Table1 (UserName, Password) VALUES (?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, username);
            insertStmt.setString(2, hashedPassword);

            int rowsInserted = insertStmt.executeUpdate();
            insertStmt.close(); conn.close();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new Login().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create account.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLogin() {
        new Login().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignUp());
    }

    class RoundBorder extends LineBorder {
        private final int radius;
        public RoundBorder(Color color, int radius) {
            super(color, 1, true);
            this.radius = radius;
        }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getLineColor());
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }
}