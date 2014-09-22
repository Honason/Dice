package dice;
import javax.swing.*;

public class GameTurn {
    public void main() {
        // Get random dice values
        Player player = GameEngine.activePlayer;
        
        int[] thrown = new int[2];
        Die dice = new Die();
        thrown = dice.main();
        // Calling class Bet and setting parameter which is thrown sum.
        System.out.println("Player " + player.getI() + " is playing!");
        Bet bet = new Bet();
        player.changeBank(bet.main(thrown));
        System.out.println(player.getBank());
        String winLose, loseMessage = "";
        if(bet.turnData[5] > 0)  winLose = "You won " + bet.turnData[5];
        else if (bet.turnData[5] < 0) {
        	winLose = "You lost " + bet.turnData[5];
        	loseMessage = "\nYou guessed " + bet.turnData[2] + " and " + bet.turnData[3] + " but the right answer was " + bet.turnData[0] + " and " + bet.turnData[1] + "\nBetter luck next time!";
        } else {
            winLose = "“If you never take risks in life,”...\n..you'll never get points! ";
        }
    	String infoMessage = winLose + "\n" + 
    		"Your score is now " + player.getBank() + loseMessage;
    	JOptionPane.showMessageDialog(null, infoMessage, "Game Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
}