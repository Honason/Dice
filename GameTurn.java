package dice;
import javax.swing.*;

public class GameTurn {
    public static GameTurn turn = new GameTurn();
    public static int activeP = 0;
    public static int thrown[] = new int[3];
    public static int myBet;

    public void main() {
        if (0<GameEngine.playerCount) {
            GameEngine gEngine = new GameEngine();
            gEngine.activePlayer = gEngine.Players.get(activeP);
            Player player = gEngine.activePlayer;

            activeP += 1;
            if (activeP >= GameEngine.playerCount) {
                activeP = 0;
            }

            Die dice = new Die();
            thrown = dice.main();

            GameMain.userInterface.returnPlayer();
            GameMain.userInterface.setMySlider(thrown);
            GameMain.userInterface.setSum(thrown[0]+thrown[1]);

            System.out.println("Player " + player.getI() + " is playing!");
        }
    }

    public void getBet(int[] turnData) {
        Player player = GameEngine.activePlayer;

        // Calling class Bet and setting parameter which is thrown sum.
        System.out.println("Player " + player.getI() + " is playing!");

        player.changeBank(turnData[5]-turnData[4]);

        System.out.println(player.getBank());
        String winLose, loseMessage = "";
        if(turnData[5] > 0)  winLose = "You won " + turnData[5];
        else {
            winLose = "You lost " + turnData[4];
            loseMessage = "\nYou guessed " + turnData[2] + " and " + turnData[3] + " but the right answer was " + turnData[0] + " and " + turnData[1] + "\nBetter luck next time!";
        }
        String infoMessage = winLose + "\n" +
                "Your score is now " + player.getBank() + loseMessage;
        JOptionPane.showMessageDialog(null, infoMessage, "Game Statistics", JOptionPane.INFORMATION_MESSAGE);

        GameTurn.turn.main();
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