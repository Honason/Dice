package dice;
import javax.swing.*;

public class GameEngine {
    public static void main() {
    	while(Player.getBank() > 0) {
    		GameTurn.main();
    	}
    	int[] games = Player.getWinLoses();
    	int[][] rolls = Player.getDistrRolls();
    	int[] max = {0,0}; //Face, Count
    	int[] min = {0,1000}; //Face, Count
    	for (int i = 0; i < rolls.length ; i++) {
    			if(rolls[i][1] > max[1]) max[0] = rolls[i][0];
    			if(rolls[i][1] < min[1] && rolls[i][1] > 0) min[0] = rolls[i][0];
    	}
    	String infoMessage = "Final score:" + Player.getBank() + "\n" +
    	"You played " + (games[0] + games[1]) + " games with a win ratio of " + Player.getWinRate() + " (W" + games[0] + "/L" + games[1] + ")\n" + 
    	"Your average bet over " + Player.getBets().length + " bets was " + Player.getAveBet() + "\n" + 
    	"You rolled " + max[0] + "'s the most, and " + min[0] + "'s the least with an average sum of " + Player.getAveRoll();
    	JOptionPane.showMessageDialog(null, infoMessage, "Game Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
}
