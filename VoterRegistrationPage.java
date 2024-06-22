package online.voting.system;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class VoterRegistrationPage extends JFrame {
    public VoterRegistrationPage() {
        setTitle("Voter Registration");
        //setSize(400, 500);
        setBounds(90, 90, 1400, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setBounds(50, 20, 100, 30);
        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(150, 20, 200, 30);
        
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 60, 100, 30);
        JTextField dobField = new JTextField();
        dobField.setBounds(150, 60, 200, 30);
        
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 100, 100, 30);

      /*  JTextField genderField = new JTextField();
        genderField.setBounds(150, 100, 200, 30); */

        JRadioButton male = new JRadioButton("Male");
        male.setBounds(150, 100, 75, 30);
        male.setSelected(true);
        JRadioButton female = new JRadioButton("Female");
        female.setBounds(225, 100, 80, 30);
        female.setSelected(false);

        ButtonGroup btngroup = new ButtonGroup();
        btngroup.add(male);
        btngroup.add(female); 
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 140, 100, 30);
        JTextField emailField = new JTextField();
        emailField.setBounds(150, 140, 200, 30);
        
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 180, 100, 30);
        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 180, 200, 30);


        
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 220, 100, 30);
        JTextField addressField = new JTextField();
        addressField.setBounds(150, 220, 200, 30);
        
        JLabel nationalIdLabel = new JLabel("National ID:");
        nationalIdLabel.setBounds(50, 260, 100, 30);
        JTextField nationalIdField = new JTextField();
        nationalIdField.setBounds(150, 260, 200, 30);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 300, 100, 30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 300, 200, 30);
        
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 340, 100, 30);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = fullNameField.getText();
                String dob = dobField.getText();
                String gender;
                if(male.isSelected())
                    gender = "Male";
                else
                    gender = "Female";
                
                
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                String nationalId = nationalIdField.getText();
                String password = new String(passwordField.getPassword());

                if (isAnyFieldEmpty(fullName, dob, gender, email, phone, address, nationalId, password)) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields.");
                } else{  
                if (registerVoter(fullName, dob, gender, email, phone, address, nationalId, password)) {
                    JOptionPane.showMessageDialog(null, "Registration successful. Waiting for admin approval.");
                    new VoterLoginPage();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed. User might already exist.");
                }
              }
            }
        });

        JButton homeButton = new JButton("Home");
        homeButton.setBounds(50, 340, 75, 30);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });

        add(fullNameLabel);
        add(fullNameField);
        add(dobLabel);
        add(dobField);
        add(genderLabel);
       // add(genderField);
        add(emailLabel);
        add(emailField);
        add(phoneLabel);
        add(phoneField);
        add(addressLabel);
        add(addressField);
        add(nationalIdLabel);
        add(nationalIdField);
        add(passwordLabel);
        add(passwordField);
        add(registerButton);
        add(homeButton);
        add(male);
        add(female);
        setVisible(true);
    }

    private boolean registerVoter(String fullName, String dob, String gender, String email, String phone, String address, String nationalId, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("voters.txt", true))) {
            writer.write(fullName + "," + dob + "," + gender + "," + email + "," + phone + "," + address + "," + nationalId + "," + password + ",pending");
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isAnyFieldEmpty(String fullName, String dob, String gender, String email, String phone, String address, String nationalId, String password) {
        return fullName.isEmpty() || dob.isEmpty() || gender.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || nationalId.isEmpty() || password.isEmpty();
    }




}

