package online.voting.system;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class VotingPage extends JFrame {
    private String username;

    public VotingPage(String username) {
        this.username = username;
        setTitle("Vote");
        //setSize(600, 400);
        setBounds(90, 90, 1400, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel electionLabel = new JLabel("Select Election:");
        electionLabel.setBounds(50, 50, 200, 30);
        JComboBox<String> electionComboBox = new JComboBox<>();
        electionComboBox.setBounds(200, 50, 300, 30);
        loadElections(electionComboBox);

     /*    JButton homeButton = new JButton("Home");
        homeButton.setBounds(50, 300, 75, 30);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });   */

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(450, 300, 100, 30);
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });

        JButton showCandidatesButton = new JButton("Show Candidates");
        showCandidatesButton.setBounds(200, 100, 200, 30);
        showCandidatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCandidates(electionComboBox.getSelectedItem().toString());
            }
        });

        add(electionLabel);
        add(electionComboBox);
        add(showCandidatesButton);
        //add(homeButton);
        add(signOutButton);
        
        setVisible(true);
    }

    private void loadElections(JComboBox<String> electionComboBox) {
        try (BufferedReader reader = new BufferedReader(new FileReader("elections.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                electionComboBox.addItem(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCandidates(String electionName) {
        JFrame candidatesFrame = new JFrame("Candidates");
        //candidatesFrame.setSize(600, 400);
        candidatesFrame.setBounds(90, 90, 1400, 700);
        candidatesFrame.setResizable(false);
        candidatesFrame.setLocationRelativeTo(null);
        candidatesFrame.setLayout(null);
        
        JLabel candidatesLabel = new JLabel("Candidates for " + electionName + ":");
        candidatesLabel.setBounds(50, 20, 500, 30);
        candidatesFrame.add(candidatesLabel);

        try (BufferedReader reader = new BufferedReader(new FileReader("candidates.txt"))) {
            String line;
            int y = 60;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(electionName)) {
                    JLabel candidateLabel = new JLabel("Name: " + parts[1] + " | Party: " + parts[2]);
                    candidateLabel.setBounds(50, y, 400, 30);
                    JButton voteButton = new JButton("Vote");
                    voteButton.setBounds(450, y, 100, 30);
                    String candidateName = parts[1];
                    voteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (voteForCandidate(username, electionName, candidateName)) {
                                JOptionPane.showMessageDialog(null, "Your vote has been cast.");
                                candidatesFrame.dispose();
                                dispose();
                                new LoginPage();
                            } else {
                                JOptionPane.showMessageDialog(null, "You have already voted.");
                            }
                        }
                    });
                    candidatesFrame.add(candidateLabel);
                    candidatesFrame.add(voteButton);
                    y += 40;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        candidatesFrame.setVisible(true);
    }

    private boolean voteForCandidate(String username, String electionName, String candidateName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("votes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(electionName)) {
                    return false; // User has already voted
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("votes.txt", true))) {
            writer.write(username + "," + electionName + "," + candidateName);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}


