package dice;
import javax.swing.*;
import java.util.ArrayList;

public class GameEngine {
    public static ArrayList<Player> Players = new ArrayList<Player>();
    public static int playerCount = 0;
    public static Player activePlayer;
    public void startGame() {
        GameTurn gTurn = new GameTurn();
        String playerCountS = "";

        while (playerCount <= 0) {
            playerCountS = (String)JOptionPane.showInputDialog(
                    null,
                    "Enter the number of players",
                    "",
                    JOptionPane.PLAIN_MESSAGE);

            if (playerCountS.equals("")) {
                playerCount = 0;
            } else {
                playerCount = Integer.parseInt(playerCountS);
            }
        }

        playerCount = Integer.parseInt(playerCountS);

        for (int i = 0;i < playerCount ; i++) {
            Players.add(new Player(i));
        }

        GameMain.userInterface.setVisible();
        GameTurn.turn.newTurn();
    }

    public static void theEnd(int winnerIndex) {
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
            String infoMessage = "Player " + (i+1) + " Final score:" + player.getBank() + "\n" +
                    "You played " + (games[0] + games[1]) + " games with a win ratio of " + player.getWinRate() + " (W" + games[0] + "/L" + games[1] + ")\n" +
                    "Your average bet over " + player.getBets().length + " bets was " + player.getAveBet() + "\n" +
                    "You rolled " + max[0] + "'s the most, and " + min[0] + "'s the least with an average sum of " + player.getAveRoll();
            JOptionPane.showMessageDialog(null, infoMessage, "Game Statistics", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static int[] playersAlive() {
        int[] res = {0, -1};
        for (int i = 0; i < Players.size() ; i++) {
            if(Players.get(i).getBank() > 0) {
                res[0]++;
                res[1] = i;
            }
        }
        if(res[0] != 1) res[1] = -1;
        //returns alive player count and index of winner if count==1
        return res;
    }
}
