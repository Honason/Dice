package dice;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel topPanel;
    private JPanel guessPanel;
    private JLabel dice1;
    private JLabel dice2;

    public GUI() {
        super("Dice Game");
        setContentPane(rootPanel);
        rootPanel.setPreferredSize(new Dimension(400, 280));
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

    // Setting GUI for making bet.
    public void setMyBet(final int[] thrown) {
        sliderPanel.removeAll();
        guessPanel.removeAll();
        buttonPanel.removeAll();
        centerPanel.removeAll();

        JLabel sumLabel = new JLabel();
        sumLabel.setText("" + (thrown[0]+thrown[1]));
        sumLabel.setFont(new Font("Sans Serif", Font.PLAIN, 50));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(sumLabel, c);
        centerPanel.validate();
        centerPanel.repaint();

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
        mySlider.setPreferredSize(new Dimension(310, 40));

        //betButton = new JButton();
        //betButton.setText("Bet");
        //rootPanel.getRootPane().setDefaultButton(betButton);
        //betButton.requestFocus();
        sliderValue = 0;
        guessButton = new JButton();
        guessButton.setText("Bet");

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider theSlider = (JSlider) changeEvent.getSource();
                if (!theSlider.getValueIsAdjusting()) {
                    sliderValue = new Integer(theSlider.getValue());
                    GameTurn.turn.setMyBet(sliderValue);
                    thrown[2] = sliderValue;
                    guessButton.setText("Bet " + sliderValue + " points");
                }
            }
        };

        mySlider.addChangeListener(changeListener);

        Bet bet = new Bet();
        thrown[2] = sliderValue;
        String[] choices = bet.pickValues(thrown);

        final JComboBox list = new JComboBox(choices);
        JLabel messageText = new JLabel();
        messageText.setText("What numbers were thrown?");

        guessPanel.add(messageText);
        guessPanel.add(list);
        sliderPanel.add(mySlider);
        sliderPanel.validate();
        sliderPanel.repaint();

        /*
        betButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Bet bet = new Bet();
                thrown[2] = sliderValue;
                bet.pickValues(thrown);
            }
        });
        */

        //buttonPanel.add(betButton);
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
                Bet bet = new Bet();
                bet.makeGuess(String.valueOf(list.getSelectedItem()));
                GameTurn.turn.turnOnePlayer();
            }
        });
        rootPanel.getRootPane().setDefaultButton(guessButton);
        guessButton.requestFocus();

        buttonPanel.add(guessButton);
        buttonPanel.validate();
        buttonPanel.repaint();

        rootPanel.validate();
        rootPanel.repaint();
    }

    // Setting UI for making guess.
    /*
    public void setGuess(String[] choices) {
        sliderPanel.removeAll();
        buttonPanel.removeAll();

        final JComboBox list = new JComboBox(choices);
        JLabel messageText = new JLabel();
        messageText.setText("What numbers were thrown?");

        sliderPanel.add(messageText);
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
    */

    // Called after each turn. Result and basic stats are shown.
    public void setTurnMessage(String[] message) {
        playerLabel.setText(message[0]);
        sliderPanel.removeAll();
        guessPanel.removeAll();

        JLabel messageText = new JLabel();
        messageText.setText("<html><body>" + message[1] + "<br>" + message[2] + "</body></html>");
        messageText.setPreferredSize(new Dimension(310, 40));

        sliderPanel.add(messageText);
        sliderPanel.validate();
        sliderPanel.repaint();


        buttonPanel.removeAll();

        okButton = new JButton();
        okButton.setText("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameTurn.turn.newTurn();
            }
        });
        rootPanel.getRootPane().setDefaultButton(okButton);
        okButton.requestFocus();

        buttonPanel.add(okButton);
        buttonPanel.validate();
        buttonPanel.repaint();

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(15, 5, 5, 5);

        centerPanel.removeAll();
        JLabel dice1 = new JLabel();
        dice1.setIcon(new ImageIcon("src/dice/assets/" + Integer.parseInt(message[3]) + ".png"));
        centerPanel.add(dice1, c);
        JLabel dice2 = new JLabel();
        dice2.setIcon(new ImageIcon("src/dice/assets/" + Integer.parseInt(message[4]) + ".png"));
        centerPanel.add(dice2, c);

        centerPanel.validate();
        centerPanel.repaint();
    }

    public int getSliderValue() {
        return sliderValue;
    }

}
