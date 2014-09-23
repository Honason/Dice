package dice;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
    private JButton okButton;
    private JButton guessButton;
    private JLabel questionLabel;
    private JLabel dice1;
    private JLabel dice2;

    public GUI() {
        super("Dice Game");
        setContentPane(rootPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    public void setVisible() {
        setVisible(true);
    }

    public void returnPlayer() {
        Player player = GameEngine.activePlayer;
        playerLabel.setText("Player " + (player.getI()+1) + ", the sum of dice faces is:");
    }



    public void setMyGuess(final int[] thrown) {
        sliderPanel.removeAll();
        buttonPanel.removeAll();
        centerPanel.removeAll();

        JLabel sumLabel = new JLabel();
        sumLabel.setText("" + (thrown[0]+thrown[1]));
        centerPanel.add(sumLabel);
        centerPanel.validate();
        centerPanel.repaint();

        mySlider = new JSlider();
        questionLabel.setText("What is your bet?");

        int bank = GameEngine.activePlayer.getBank();
        mySlider.setMinorTickSpacing(bank/20);
        mySlider.setMajorTickSpacing(bank/20*2);
        mySlider.setSnapToTicks(true);
        mySlider.setMinimum(0);
        mySlider.setMaximum(bank);
        mySlider.setPaintTicks(true);
        mySlider.setPaintLabels(true);
        mySlider.setValue(0);
        mySlider.setPreferredSize(new Dimension(310, 40));

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

        rootPanel.getRootPane().setDefaultButton(betButton);
        betButton.requestFocus();

        betButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                Bet bet = new Bet();
                bet.pickValues(thrown);
            }
        });
        betButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {

            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                betButton.setBackground(new Color(246, 27, 27));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {

            }
        });
        buttonPanel.add(betButton);
        buttonPanel.validate();
        buttonPanel.repaint();
    }

    public void setGuess(String[] choices) {
        questionLabel.setText("What values are there?");
        sliderPanel.removeAll();
        buttonPanel.removeAll();

        final JComboBox list = new JComboBox(choices);
        sliderPanel.add(list);
        sliderPanel.validate();
        sliderPanel.repaint();

        buttonPanel.removeAll();

        guessButton = new JButton();
        guessButton.setText("Guess");
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
                Bet bet = new Bet();
                bet.makeGuess(String.valueOf(list.getSelectedItem()));
            }
        });
        rootPanel.getRootPane().setDefaultButton(guessButton);
        guessButton.requestFocus();

        buttonPanel.add(guessButton);
        buttonPanel.validate();
        buttonPanel.repaint();
    }

    public void setTurnMessage(String[] message) {
        playerLabel.setText(message[0]);
        sliderPanel.removeAll();
        sliderPanel.setPreferredSize(new Dimension(314, 80));
        questionLabel.setText(message[1]);
        JLabel messageText = new JLabel();
        messageText.setText("<html><body>" + message[2] + "</body></html>");
        messageText.setPreferredSize(new Dimension(314, 80));

        sliderPanel.add(messageText);
        sliderPanel.validate();
        sliderPanel.repaint();


        buttonPanel.removeAll();

        okButton = new JButton();
        okButton.setText("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameTurn.turn.main();
            }
        });
        rootPanel.getRootPane().setDefaultButton(okButton);
        okButton.requestFocus();

        buttonPanel.add(okButton);
        buttonPanel.validate();
        buttonPanel.repaint();

        centerPanel.removeAll();
        JLabel dice1 = new JLabel();
        dice1.setIcon(new ImageIcon("src/dice/assets/" + Integer.parseInt(message[3]) + ".png"));
        System.out.println("First dice is " + message[3] + " and second is " + message[4]);
        centerPanel.add(dice1);
        JLabel dice2 = new JLabel();
        dice2.setIcon(new ImageIcon("src/dice/assets/" + Integer.parseInt(message[4]) + ".png"));
        centerPanel.add(dice2);

        centerPanel.validate();
        centerPanel.repaint();
    }

    public int getSliderValue() {
        return sliderValue;
    }

}
