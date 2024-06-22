package online.voting.system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class VoterLoginPage extends JFrame {
    public VoterLoginPage() {
        setTitle("Voter Login");
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

             if (fieldsAreNotEmpty(username, password)){
                if (authenticateVoter(username, password)) {
                    if (isVoterApproved(username)) {
                        new VotingPage(username);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Your registration is pending approval by admin.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials or user not registered.");
                } 
              }   else {
                    JOptionPane.showMessageDialog(null, "All fields must be filled.");
                }
             
            }
        });
        
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 200, 100, 30);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VoterRegistrationPage();
                dispose();
            }
        });
        
        JButton homeButton = new JButton("Home");
        homeButton.setBounds(50, 200, 75, 30);
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
        add(registerButton);
        add(homeButton);
        
        setVisible(true);
    }


    private boolean fieldsAreNotEmpty(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }


    private boolean authenticateVoter(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("voters.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[7].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isVoterApproved(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("voters.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return parts[8].equals("approved");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
