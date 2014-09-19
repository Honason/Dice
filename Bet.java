package dice;

import javax.swing.*;
import java.util.*;

public class Bet {
    public static int main(int[] thrown) {
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

        String input = (String) JOptionPane.showInputDialog(null, "The sum of thrown dices is " + thrownSum + ". What values were there?",
                "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, // Use
                // default
                // icon
                choices, // Array of choices
                choices[0]); // Initial choice
        System.out.println(input.equals(answer));

        return 10;
    }
}
