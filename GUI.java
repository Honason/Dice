package dice;

import javax.swing.*;

public class GUI extends JFrame {
    public JPanel rootPanel;
    public JLabel playerLabel;
    private JSlider mySlider;

    public GUI() {
        super("Dice Game");
        setContentPane(rootPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        //playerLabel.setText("Player 1");

        setVisible(true);
    }

    public void returnPlayer() {
        Player player = GameEngine.activePlayer;
        playerLabel.setText("Player " + player.getI());
    }

    public void setMySlider() {
        int bank = GameEngine.activePlayer.getBank();
        System.out.println("Bank of active player is " + GameEngine.activePlayer.getBank());
        int minorSpacing = bank/20;
        mySlider.setMinorTickSpacing(minorSpacing);
        int majorSpacing = bank/10;
        mySlider.setMajorTickSpacing(majorSpacing);
        mySlider.setSnapToTicks(true);
        mySlider.setMinimum(0);
        mySlider.setMaximum(bank);
        mySlider.setPaintTicks(true);
        mySlider.setPaintLabels(true);
        mySlider.setValue(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        mySlider = new JSlider();
    }
}
