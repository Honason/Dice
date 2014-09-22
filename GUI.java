package dice;

import javax.swing.*;

public class GUI extends JFrame {
    private JPanel rootPanel;
    private JLabel playerLabel;

    public GUI() {
        super("Dice Game");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //playerLabel.setText("Player 1");

        setVisible(true);

        returnPlayer();
        //playerLabel.setText(returnPlayer());
    }

    public void returnPlayer() {
        //return "Player 1";
        playerLabel.setText("Player 2");
    }
}
