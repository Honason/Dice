package dice;

import javax.swing.*;

public class Bet {
    public static int main(int[] thrown) {
        String[] choices;
        int thrownSum = thrown[0]+thrown[1];

        switch (thrownSum) {
                case 2 :
                    choices = new String[]{"1, 1, easy."};
                    break;
                case 3 :
                    choices = new String[]{"1, 2","2, 1"};
                    break;
                case 4 :
                    choices = new String[]{"1, 3","2, 2","3, 1"};
                    break;
                case 5 :
                    choices = new String[]{"1, 4","2, 3","4, 1","3, 2"};
                    break;
                case 6 :
                    choices = new String[]{"1, 5","2, 4","3, 3","5, 1","4, 2"};
                    break;
                case 7 :
                    choices = new String[]{"1, 6","2, 5","3, 4","6, 1","5, 2","4, 3"};
                    break;
                case 8 :
                    choices = new String[]{"2, 6","3, 5","4, 4","6, 2","5, 3"};
                    break;
            case 9 :
                choices = new String[]{"3, 6","4, 5","6, 3","5, 4"};
                break;
            case 10 :
                choices = new String[]{"4, 6","5, 5","6, 4"};
                break;
            case 11 :
                choices = new String[]{"5, 6","6, 5"};
                break;
            case 12 :
                choices = new String[]{"6, 6, easy."};
                break;
            default :
                choices = new String[]{"Ups."};
                System.out.println("Error.");
                break;
        }

        String input = (String) JOptionPane.showInputDialog(null, "The sum of thrown dices is " + thrownSum + ". What values were there?",
                "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, // Use
                // default
                // icon
                choices, // Array of choices
                choices[0]); // Initial choice
        System.out.println(input);

        return 10;
    }
}
