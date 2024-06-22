package online.voting.system;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AdminPage extends JFrame {
    public AdminPage() {
        setTitle("Admin Dashboard");
        //setSize(600, 400);
        setBounds(90, 90, 1400, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JButton viewResultsButton = new JButton("View Results");
        viewResultsButton.setBounds(50, 50, 200, 30);
        viewResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewResults();
            }
        });
        
        JButton addElectionButton = new JButton("Add Election");
        addElectionButton.setBounds(50, 100, 200, 30);
        addElectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addElection();
            }
        });
        
        JButton addCandidateButton = new JButton("Add Candidate");
        addCandidateButton.setBounds(50, 150, 200, 30);
        addCandidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCandidate();
            }
        });
        
        JButton viewVotersButton = new JButton("View Voters");
        viewVotersButton.setBounds(50, 200, 200, 30);
        viewVotersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewVoters();
            }
        });

      /* JButton homeButton = new JButton("Home");
        homeButton.setBounds(50, 300, 75, 30);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });  */

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(450, 300, 100, 30);
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });

        add(viewResultsButton);
        add(addElectionButton);
        add(addCandidateButton);
        add(viewVotersButton);
       // add(homeButton);
        add(signOutButton);
        
        setVisible(true);
    }

    private void viewResults() {
        JFrame resultsFrame = new JFrame("Election Results");
        //resultsFrame.setSize(600, 400);
        resultsFrame.setBounds(90, 90, 1400, 700);
        resultsFrame.setResizable(false);
        resultsFrame.setLocationRelativeTo(null);
        resultsFrame.setLayout(null);
        
        JLabel resultsLabel = new JLabel("Election Results:");
        resultsLabel.setBounds(50, 20, 500, 30);
        resultsFrame.add(resultsLabel);

        Map<String, Integer> resultsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("votes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String bothString = "Election: "+ parts[1] + "   |   Candidate: " + parts[2];
                resultsMap.put(bothString, resultsMap.getOrDefault(bothString, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int y = 60;
        for (Map.Entry<String, Integer> entry : resultsMap.entrySet()) {
            JLabel resultLabel = new JLabel(" " + entry.getKey() + "   |   Votes: " + entry.getValue());
            resultLabel.setBounds(50, y, 500, 30);
            resultsFrame.add(resultLabel);
            y += 40;
        }




     /*   try (BufferedReader reader = new BufferedReader(new FileReader("votes.txt"))) {
            String line;
            int y = 60;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                JLabel voteLabel = new JLabel("Voter: " + parts[0] + " | Election: " + parts[1] + " | Candidate: " + parts[2]);
                voteLabel.setBounds(50, y, 500, 30);
                resultsFrame.add(voteLabel);
                y += 40;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  */

        resultsFrame.setVisible(true);
    }

    private void addElection() {
        JFrame addElectionFrame = new JFrame("Add Election");
        //addElectionFrame.setSize(400, 200);
        addElectionFrame.setBounds(90, 90, 800, 400);
        addElectionFrame.setResizable(false);
        addElectionFrame.setLocationRelativeTo(null);
        addElectionFrame.setLayout(null);
        
        JLabel nameLabel = new JLabel("Election Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(200, 50, 150, 30);
        
        JLabel yearLabel = new JLabel("Election Year:");
        yearLabel.setBounds(50, 100, 100, 30);
        JTextField yearField = new JTextField();
        yearField.setBounds(200, 100, 150, 30);
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(150, 150, 100, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String year = yearField.getText();

                if (name.isEmpty() || year.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out.");
                    return;
                }
                if (addElectionToFile(name, year)) {
                    JOptionPane.showMessageDialog(null, "Election added successfully.");
                    addElectionFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add election.");
                }
            }
        });

        addElectionFrame.add(nameLabel);
        addElectionFrame.add(nameField);
        addElectionFrame.add(yearLabel);
        addElectionFrame.add(yearField);
        addElectionFrame.add(addButton);
        
        addElectionFrame.setVisible(true);
    }

    private boolean addElectionToFile(String name, String year) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("elections.txt", true))) {
            writer.write(name + " (" + year + ")");
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void addCandidate() {
        JFrame addCandidateFrame = new JFrame("Add Candidate");
        //addCandidateFrame.setSize(400, 400);
        addCandidateFrame.setBounds(90, 90, 900, 600);
        addCandidateFrame.setResizable(false);
        addCandidateFrame.setLocationRelativeTo(null);
        addCandidateFrame.setLayout(null);
        
        JLabel electionLabel = new JLabel("Election:");
        electionLabel.setBounds(50, 50, 100, 30);
        JComboBox<String> electionComboBox = new JComboBox<>();
        electionComboBox.setBounds(200, 50, 150, 30);
        loadElections(electionComboBox);

        JLabel nameLabel = new JLabel("Candidate Name:");
        nameLabel.setBounds(50, 100, 100, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(200, 100, 150, 30);

        JLabel partyLabel = new JLabel("Party Name:");
        partyLabel.setBounds(50, 150, 100, 30);
        JTextField partyField = new JTextField();
        partyField.setBounds(200, 150, 150, 30);
        
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 200, 100, 30);
        JTextField addressField = new JTextField();
        addressField.setBounds(200, 200, 150, 30);
        
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 250, 100, 30);
        JTextField phoneField = new JTextField();
        phoneField.setBounds(200, 250, 150, 30);
        
        JLabel nationalIdLabel = new JLabel("National ID:");
        nationalIdLabel.setBounds(50, 300, 100, 30);
        JTextField nationalIdField = new JTextField();
        nationalIdField.setBounds(200, 300, 150, 30);

        JButton addButton = new JButton("Add");
        addButton.setBounds(150, 350, 100, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String electionName = electionComboBox.getSelectedItem().toString();
                String candidateName = nameField.getText();
                String partyName = partyField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String nationalId = nationalIdField.getText();

                if (electionName.isEmpty() || candidateName.isEmpty() || partyName.isEmpty() || address.isEmpty() || phone.isEmpty() || nationalId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out.");
                    return;
                }
                if (addCandidateToFile(electionName, candidateName, partyName, address, phone, nationalId)) {
                    JOptionPane.showMessageDialog(null, "Candidate added successfully.");
                    addCandidateFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add candidate.");
                }
            }
        });

        addCandidateFrame.add(electionLabel);
        addCandidateFrame.add(electionComboBox);
        addCandidateFrame.add(nameLabel);
        addCandidateFrame.add(nameField);
        addCandidateFrame.add(partyLabel);
        addCandidateFrame.add(partyField);
        addCandidateFrame.add(addressLabel);
        addCandidateFrame.add(addressField);
        addCandidateFrame.add(phoneLabel);
        addCandidateFrame.add(phoneField);
        addCandidateFrame.add(nationalIdLabel);
        addCandidateFrame.add(nationalIdField);
        addCandidateFrame.add(addButton);
        
        addCandidateFrame.setVisible(true);
    }

    private boolean addCandidateToFile(String electionName, String candidateName, String partyName, String address, String phone, String nationalId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("candidates.txt", true))) {
            writer.write(electionName + "," + candidateName + "," + partyName + "," + address + "," + phone + "," + nationalId);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void viewVoters() {
        JFrame votersFrame = new JFrame("Registered Voters");
        //votersFrame.setSize(800, 600);
        votersFrame.setBounds(90, 90, 1400, 700);
        votersFrame.setResizable(false);
        votersFrame.setLocationRelativeTo(null);
        votersFrame.setLayout(null);
        
        JLabel votersLabel = new JLabel("Registered Voters:");
        votersLabel.setBounds(50, 20, 700, 30);
        votersFrame.add(votersLabel);

        try (BufferedReader reader = new BufferedReader(new FileReader("voters.txt"))) {
            String line;
            int y = 60;
            int serial = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                JLabel voterLabel = new JLabel(serial + ". " + "Name: " + parts[0] + " | DOB: " + parts[1] + " | Phone: " + parts[4] + " | National ID: " + parts[6] + " | Email: " + parts[3] + " | Status: " + parts[8]);
                voterLabel.setBounds(50, y, 600, 30);
                JButton approveButton = new JButton("Approve");
                approveButton.setBounds(650, y, 100, 30);
                String voterName = parts[0];
                approveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        approveVoter(voterName);
                        votersFrame.dispose();
                        viewVoters();
                    }
                });
                if (parts[8].equals("approved")) {
                    approveButton.setEnabled(false);
                }
                votersFrame.add(voterLabel);
                votersFrame.add(approveButton);
                y += 40;
                serial++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        votersFrame.setVisible(true);
    }

    private void approveVoter(String voterName) {
        try {
            File inputFile = new File("voters.txt");
            File tempFile = new File("voters_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(voterName)) {
                    parts[8] = "approved";
                    line = String.join(",", parts);
                }
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
