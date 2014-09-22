package dice;
import java.util.ArrayList;
import javax.swing.*;

public class GameEngine {
    public static ArrayList<Player> Players = new ArrayList<Player>();
    public static Player activePlayer;
    public void main() {
        GameTurn gTurn = new GameTurn();
        String playerCountS = (String)JOptionPane.showInputDialog(
            null,
            "Enter the number of players",
            JOptionPane.PLAIN_MESSAGE);
        int playerCount = Integer.parseInt(playerCountS);
        for (int i = 0;i < playerCount ; i++) {
            Players.add(new Player(i));
            System.out.println(Players.get(i).getBank());
        }
        System.out.println("PlayerCount " + playerCount);

    	while(playersAlive()) {
            for (int i = 0; i < playerCount; i++) {
                activePlayer = Players.get(i);
                gTurn.main();
            }
    	}
    	// int[] games = Player.getWinLoses();
    	// int[][] rolls = Player.getDistrRolls();
    	// int[] max = {0,0}; //Face, Count
    	// int[] min = {0,1000}; //Face, Count
    	// for (int i = 0; i < rolls.length ; i++) {
    	// 		if(rolls[i][1] > max[1]) max[0] = rolls[i][0];
    	// 		if(rolls[i][1] < min[1] && rolls[i][1] > 0) min[0] = rolls[i][0];
    	// }
    	// String infoMessage = "Final score:" + Player.getBank() + "\n" +
    	// "You played " + (games[0] + games[1]) + " games with a win ratio of " + Player.getWinRate() + " (W" + games[0] + "/L" + games[1] + ")\n" + 
    	// "Your average bet over " + Player.getBets().length + " bets was " + Player.getAveBet() + "\n" + 
    	// "You rolled " + max[0] + "'s the most, and " + min[0] + "'s the least with an average sum of " + Player.getAveRoll();
    	// JOptionPane.showMessageDialog(null, infoMessage, "Game Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
    public boolean playersAlive() {
        int count = 0;
        for (int i = 0; i < Players.size() ; i++) {
            if(Players.get(i).getBank() > 0) count++;
        }
        return (count > 1);
    }
}
