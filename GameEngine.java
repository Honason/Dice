package dice;
import javax.swing.*;
import java.util.ArrayList;

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
        GUI userInterface = new GUI();
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
        for (int i = 0; i < playerCount ; i++) {
            Player player = Players.get(i);
            int[] games = player.getWinLoses();
            int[][] rolls = player.getDistrRolls();
            int[] max = {0,0}; //Face, Count
            int[] min = {0,1000}; //Face, Count
            for (int j = 0; j < rolls.length ; j++) {
                if(rolls[j][1] > max[1]) max[0] = rolls[j][0];
                if(rolls[j][1] < min[1] && rolls[j][1] > 0) min[0] = rolls[j][0];
            }
            String infoMessage = "Final score:" + player.getBank() + "\n" +
            "You played " + (games[0] + games[1]) + " games with a win ratio of " + player.getWinRate() + " (W" + games[0] + "/L" + games[1] + ")\n" + 
            "Your average bet over " + player.getBets().length + " bets was " + player.getAveBet() + "\n" + 
            "You rolled " + max[0] + "'s the most, and " + min[0] + "'s the least with an average sum of " + player.getAveRoll();
            JOptionPane.showMessageDialog(null, infoMessage, "Game Statistics", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public boolean playersAlive() {
        int count = 0;
        for (int i = 0; i < Players.size() ; i++) {
            if(Players.get(i).getBank() > 0) count++;
        }
        return (count > 1);
    }
}
