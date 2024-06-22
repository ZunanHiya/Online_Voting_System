package online.voting.system;



import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("Online Voting System");
       // setSize(600, 400);
        setBounds(90, 90, 1400, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("Online Voting System");
        title.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 30));
        title.setSize(500, 35);
        title.setLocation(550, 35);
        
        JButton voterButton = new JButton("Voter");
        voterButton.setBounds(300, 250, 300, 100);
        voterButton.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 27));
        voterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VoterLoginPage();
                dispose();
            }
        });
        
        JButton adminButton = new JButton("Admin");
        adminButton.setBounds(800, 250, 300, 100);
        adminButton.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 27));
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLoginPage();
                dispose();
            }
        });
        
        add(voterButton);
        add(adminButton);
        add(title);
        
        setVisible(true);
    }
}

