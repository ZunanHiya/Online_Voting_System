package online.voting.system;

import javax.swing.*;

public class OnlineVotingSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginPage();
        });
    }
}


