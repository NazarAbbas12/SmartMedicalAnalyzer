package medicalreportanalyst;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.LineBorder;
import org.mindrot.jbcrypt.BCrypt;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signupButton;

    public Login() {
        setTitle("Smart Medical Analyzer - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        setMinimumSize(new Dimension(600, 500));
      // setResizable(false);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(240, 248, 255));
        GridBagConstraints rootGbc = new GridBagConstraints();
        rootGbc.gridx = 0;
        rootGbc.gridy = 0;
        rootGbc.anchor = GridBagConstraints.CENTER;

        JPanel content = new JPanel();
         // 🔥 NEW DYNAMIC SIZE BASED ON SCREEN
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int)(screenSize.width * 0.65);  // 65% of screen width
    int height = (int)(screenSize.height * 0.65); // 65% of screen height
    content.setPreferredSize(new Dimension(width, height));
    content.setMaximumSize(new Dimension(width, height));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createCompoundBorder(
                new RoundBorder(new Color(70, 130, 180), 15),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel iconLabel = new JLabel("\uD83C\uDFE5");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel titleLabel = new JLabel("Medical Analyst Login", SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 102, 153));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));

        JPanel userPanel = new JPanel();
        userPanel.setBackground(Color.WHITE);
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new JTextField(20);
        int inputWidth = (int)(screenSize.width * 0.4); // 40% of screen width
int inputHeight = 45;
usernameField.setMaximumSize(new Dimension(inputWidth, inputHeight));
        usernameField.setBorder(new RoundBorder(new Color(200, 200, 200), 8));
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        usernameField.setMargin(new Insets(5, 10, 5, 10));

        userPanel.add(userLabel);
        userPanel.add(Box.createVerticalStrut(5));
        userPanel.add(usernameField);

        JPanel passPanel = new JPanel();
        passPanel.setBackground(Color.WHITE);
        passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.Y_AXIS));
        passPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new JPasswordField(20);
        inputWidth = (int)(screenSize.width * 0.4); // 40% of screen width
  inputHeight = 45;
passwordField.setMaximumSize(new Dimension(inputWidth, inputHeight));

        passwordField.setBorder(new RoundBorder(new Color(200, 200, 200), 8));
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordField.setMargin(new Insets(5, 10, 5, 10));

        passPanel.add(passLabel);
        passPanel.add(Box.createVerticalStrut(5));
        passPanel.add(passwordField);

        loginButton = new JButton("Login") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                if (getModel().isPressed()) {
                    g2.setPaint(new GradientPaint(0, 0, new Color(0, 102, 153), 0, getHeight(), new Color(0, 76, 115)));
                } else if (getModel().isRollover()) {
                    g2.setPaint(new GradientPaint(0, 0, new Color(0, 122, 204), 0, getHeight(), new Color(0, 102, 153)));
                } else {
                    g2.setPaint(new GradientPaint(0, 0, new Color(0, 102, 153), 0, getHeight(), new Color(0, 122, 204)));
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {}
        };
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginButton.setForeground(Color.WHITE);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> handleLogin());

        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(Color.WHITE);
        signUpPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel noAccount = new JLabel("Don't have an account?");
        noAccount.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        signupButton.setForeground(new Color(0, 102, 153));
        signupButton.setBackground(Color.WHITE);
        signupButton.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        signupButton.setContentAreaFilled(false);
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupButton.addActionListener(e -> handleSignUp());

        signUpPanel.add(noAccount);
        signUpPanel.add(signupButton);

        content.add(iconLabel);
        content.add(titleLabel);
        content.add(Box.createVerticalStrut(15));
        content.add(userPanel);
        content.add(Box.createVerticalStrut(15));
        content.add(passPanel);
        content.add(Box.createVerticalStrut(25));
        content.add(loginButton);
        content.add(Box.createVerticalStrut(20));
        content.add(signUpPanel);

        root.add(content, rootGbc);
        setContentPane(root);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

  private void handleLogin() {
    String username = usernameField.getText().trim();
    String password = new String(passwordField.getPassword());

    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter both username and password.", 
            "Login Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Connection string to MS Access
        String dbPath = "C:/Users/HAROON TRADERS/OneDrive/Documents/MedicalDatabase.accdb";
        String url = "jdbc:ucanaccess://" + dbPath;

        Connection conn = DriverManager.getConnection(url);
        String sql = "SELECT Password FROM Table1 WHERE UserName = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String hashedPassword = rs.getString("Password");

            if (BCrypt.checkpw(password, hashedPassword)) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new KidneyAnalyzerUI().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username not found or Invalid password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Username not found or Invalid password.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error connecting to database.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private void handleSignUp() {
        new SignUp().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }

    class RoundBorder extends LineBorder {
        private int radius;

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