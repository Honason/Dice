package dice;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class GUI extends JFrame {
    public int sliderValue = 0;

    public JPanel rootPanel;
    public JLabel playerLabel;
    private JSlider mySlider;
    private JPanel sliderPanel;
    private JLabel sumLabel;
    private JPanel centerPanel;
    private JPanel buttonPanel;
    private JButton betButton;
    private JButton guessButton;
    private JLabel questionLabel;

    public GUI() {
        super("Dice Game");
        setContentPane(rootPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSum(5);

        pack();
        setVisible(true);
    }

    public void returnPlayer() {
        Player player = GameEngine.activePlayer;
        playerLabel.setText("Player " + player.getI() + ", the sum of dice faces is:");
    }

    public void setSum(int sum) {
        sumLabel.setText("" + sum);
        sumLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setMySlider(final int[] thrown) {
        sliderPanel.removeAll();
        mySlider = new JSlider();

        int bank = GameEngine.activePlayer.getBank();
        mySlider.setMinorTickSpacing(bank/20);
        mySlider.setMajorTickSpacing(bank/20*2);
        mySlider.setSnapToTicks(true);
        mySlider.setMinimum(0);
        mySlider.setMaximum(bank);
        mySlider.setPaintTicks(true);
        mySlider.setPaintLabels(true);
        mySlider.setValue(0);
        mySlider.setPreferredSize(new Dimension(314, 40));

        sliderPanel.add(mySlider);
        sliderPanel.validate();
        sliderPanel.repaint();

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider theSlider = (JSlider) changeEvent.getSource();
                if (!theSlider.getValueIsAdjusting()) {
                    sliderValue = new Integer(theSlider.getValue());
                    GameTurn.turn.setMyBet(sliderValue);
                    thrown[2] = sliderValue;
                }
            }
        };
        mySlider.addChangeListener(changeListener);

        betButton = new JButton();
        betButton.setText("Bet");

        betButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {

            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Bet bet = new Bet();
                betButton.setBackground(new Color(246, 27, 27));
                System.out.println("I am sending " + thrown[0] + " and " + thrown[1] + " to PickValues");
                bet.pickValues(thrown);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {

            }
        });
        buttonPanel.add(betButton);
        buttonPanel.validate();
        buttonPanel.repaint();

        setSum(sliderValue);
    }

    public void setGuess(String[] choices) {
        questionLabel.setText("What values are there?");
        sliderPanel.removeAll();

        final JComboBox list = new JComboBox(choices);
        sliderPanel.add(list);
        sliderPanel.validate();
        sliderPanel.repaint();

        buttonPanel.removeAll();

        guessButton = new JButton();
        guessButton.setText("Guess");
        guessButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Bet bet = new Bet();
                bet.makeGuess(String.valueOf(list.getSelectedItem()));
            }
        });

        buttonPanel.add(guessButton);
        buttonPanel.validate();
        buttonPanel.repaint();
    }

    public int getSliderValue() {
        return sliderValue;
    }

}
