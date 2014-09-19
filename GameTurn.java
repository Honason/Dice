package dice;
import javax.swing.*;

public class GameTurn {
    public static void main() {
        // Get random dice values
        int[] thrown = new int[2];
        thrown = Die.main();
        // Calling class Bet and setting parameter which is thrown sum.
        Player.changeBank(Bet.main(thrown));
        System.out.println(Player.getBank());
        String winLose, loseMessage = "";
        if(Bet.turnData[5] > 0)  winLose = "You won " + Bet.turnData[5];
        else if (Bet.turnData[5] < 0) {
        	winLose = "You lost " + Bet.turnData[5];
        	loseMessage = "\nYou guessed " + Bet.turnData[2] + " and " + Bet.turnData[3] + " but the right answer was " + Bet.turnData[0] + " and " + Bet.turnData[1] + "\nBetter luck next time!";
        } else {
            winLose = "“If you never take risks in life,”...\n..you'll never get points! ";
        }
    	String infoMessage = winLose + "\n" + 
    		"Your score is now " + Player.getBank() + loseMessage;
    	JOptionPane.showMessageDialog(null, infoMessage, "Game Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
}