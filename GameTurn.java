package dice;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameTurn {
    public static GameTurn turn = new GameTurn();
    public static int activeP = 0;
    public static int thrown[] = new int[3];
    public static int myBet;
    public static ArrayList<Integer> winners = new ArrayList<Integer>();
    public static ArrayList<Integer> losers = new ArrayList<Integer>();
    public static ArrayList<int[]> turnData = new ArrayList<int[]>();

    // Instance of one game turn
    public void newTurn() {
        int[] playersAlive = GameEngine.playersAlive();
        if (playersAlive[0] > 0) {
            turnData.clear();
            Die dice = new Die();
            thrown = dice.main();
            turnOnePlayer();

        } else {
             GameEngine.theEnd(playersAlive[1]);
        }
    }

    public void turnOnePlayer() {
        GameEngine gEngine = new GameEngine();
        winners.clear();
        losers.clear();
        if (activeP >= GameEngine.playerCount) {
            int[] results = new int[GameEngine.playerCount];
            int winSum = 0;
            for (int i = 0; i < winners.size(); i++) {
                winSum = winSum + GameEngine.Players.get(winners.get(i)).getLastReward();
            }
            int losersLose;
            if(losers.size() > 0) {losersLose = winSum/losers.size();}
            else {losersLose = 0;};
            for (int i = 0; i < losers.size(); i++) {
                GameEngine.Players.get(losers.get(i)).changeBank(-1 * losersLose);
            }

            String message = "Players " + Arrays.toString(gEngine.Players.get(0).listToArray(winners)) + " took " + (losersLose) + " each from " + Arrays.toString(gEngine.Players.get(0).listToArray(losers));
            message = message + "\n";
            // 1. and 2.: Dice face values, 3.and 4. Guesses, 5. Amount we have bet, 6. is reward.
            message = message + "The right values is " + turnData.get(0)[0] + " and " + turnData.get(0)[1] + "\n";
            for (int i = 0; i < turnData.size(); i++) {
                message = message + "Player " + (i+1) + " guessed " + turnData.get(i)[2] + " and " + turnData.get(i)[3] + " and bet " + turnData.get(i)[4] + " and won/lost " + (turnData.get(i)[5]-turnData.get(i)[4]) + "\n\n";
            }
            JOptionPane.showMessageDialog(null,  message, "End of turn results", JOptionPane.INFORMATION_MESSAGE);
            int[] playersAlive = GameEngine.playersAlive();
            if (playersAlive[0] > 1) {
                activeP = 0;
                newTurn();
            } else if(playersAlive[0] == 1) {
                GameEngine.theEnd(playersAlive[1]);
            }
        }


        System.out.println("settings player to " + activeP);
        gEngine.activePlayer = gEngine.Players.get(activeP);
        Player player = gEngine.activePlayer;


        GameMain.userInterface.returnPlayer();
        GameMain.userInterface.setMyBet(thrown);
        System.out.println("Player " + (player.getI()+1) + " is playing!");
    }

    // Setting final messages, that are called every turn.
    public void getBet(int[] turnData) {
        Player player = GameEngine.activePlayer;

        player.changeBank(turnData[5]-turnData[4]);
        String winLose, loseMessage = "", fMessage = "";
        if(turnData[5]-turnData[4] > 0) {
            winLose = "You won " + turnData[5] + " points and your score is now ";
            fMessage = "You guessed " + turnData[2] + " and " + turnData[3] + " and you were right!";
        }
        else if (turnData[5]-turnData[4] < 0){
            winLose = "You lost " + turnData[4] + " points and your score is now ";
            loseMessage = "Better luck next time!";
            fMessage = "You guessed " + turnData[2] + " and " + turnData[3] + " and the right answer is:";
        } else {
            winLose = "You didn't bet anything! Your score is now ";
            fMessage = "You guessed " + turnData[2] + " and " + turnData[3] + " and the right answer is:";
        }

        String[] myMessage = new String[5];
        myMessage[0] = fMessage;
        myMessage[1] = winLose + player.getBank() + ".";
        myMessage[2] = loseMessage;
        myMessage[3] = "" + turnData[0];
        myMessage[4] = "" + turnData[1];

        GameMain.userInterface.setTurnMessage(myMessage);

    }

    public int[] getThrown() {
        return thrown;
    }
    public void setThrown(int[] newThrown) {
        thrown = newThrown;
    }

    public int getMyBet() {
        return myBet;
    }
    public void setMyBet(int myNewBet) {
        myBet = myNewBet;
    }
}