package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JPanel {

    public MenuView(Game game) {
        // Set layout to center components
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(1024, 567));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Spacing

        // Title
        JLabel title = new JLabel("Dominicks Amazing project");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.WHITE);
        gbc.gridy = 0;
        this.add(title, gbc);

        // Start Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.setPreferredSize(new Dimension(200, 60));
        gbc.gridy = 1;
        this.add(startButton, gbc);

        // Action Listener
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.startGame();
            }
        });
    }
}
