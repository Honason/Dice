package dice;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class Bet {
    public static int liveScore = Player.getBank();
    public static int[] turnData = new int[6];

    public static int main(int[] thrown) {
        turnData[0] = thrown[0];
        turnData[1] = thrown[1];

        int thrownSum = thrown[0]+thrown[1];
        String answer = "";
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= 6 ; i++) {
            for (int j = 1; j <= 6 ; j++) {
                if(i+j == thrownSum) list.add(i + " and " + j);
                if(i == thrown[0] && j == thrown[1]) answer = i + " and " + j;
            }            
        }
        System.out.println(answer);

        String[] choices = list.toArray(new String[list.size()]);

        JOptionPane optionPane = new JOptionPane();
        JSlider slider = getSlider(optionPane);
        optionPane.setMessage(new Object[] { "The sum of dice faces is " + thrownSum + ". What is your bet?", slider });
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(null, "My Slider");
        dialog.setVisible(true);
        int myBet = (Integer) optionPane.getInputValue();
        dialog.dispose();

        String input = (String) JOptionPane.showInputDialog(null, "What values are there?",
                "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, // Use
                // default
                // icon
                choices, // Array of choices
                choices[0]); // Initial choice
        //System.out.println(input.equals(answer));

        String[] bets = input.split(" and ");
        turnData[2] = Integer.parseInt(bets[0]);
        turnData[3] = Integer.parseInt(bets[1]);
        turnData[4] = myBet;

        if (input.equals(answer)) {
            if (thrownSum==2 || thrownSum==3 || thrownSum==11 || thrownSum==12) {
                double temp = (myBet*1.5);
                myBet = (int) temp;
            } else if (thrownSum==4 || thrownSum==5 || thrownSum==9 || thrownSum==10) {
                myBet = myBet*2;
            } else if (thrownSum==6 || thrownSum==7 || thrownSum==8) {
                myBet = myBet*3;
            }
        } else {
            myBet = myBet * (-1);
        }

        turnData[5] = myBet;

        return myBet;
    }

    static JSlider getSlider(final JOptionPane optionPane) {

        JSlider slider = new JSlider();
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setSnapToTicks(true);
        slider.setMinimum(0);
        slider.setMaximum(liveScore);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setValue(0);
        optionPane.setInputValue(0);
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider theSlider = (JSlider) changeEvent.getSource();
                if (!theSlider.getValueIsAdjusting()) {
                    optionPane.setInputValue(new Integer(theSlider.getValue()));
                }
            }
        };
        slider.addChangeListener(changeListener);
        return slider;
    }
}
