package online.voting.system;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginPage extends JFrame {
    public AdminLoginPage() {
        setTitle("Admin Login");
        //setSize(400, 300);
        setBounds(90, 90, 1400, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        
        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(150, 150, 100, 30);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (authenticateAdmin(username, password)) {
                    new AdminPage();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                }
            }
        });

        JButton homeButton = new JButton("Home");
        homeButton.setBounds(50, 150, 75, 30);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signInButton);
        add(homeButton);
        
        setVisible(true);
    }

    private boolean authenticateAdmin(String username, String password) {
        // For simplicity, using hardcoded admin credentials
        return "admin".equals(username) && "admin".equals(password);
    }
}






