package dice;
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
            GameMain.userInterface.setMyBet(thrown);

            System.out.println("Player " + (player.getI()+1) + " is playing!");
        }
    }

    public void getBet(int[] turnData) {
        Player player = GameEngine.activePlayer;

        // Calling class Bet and setting parameter which is thrown sum.
        player.changeBank(turnData[5]-turnData[4]);

        System.out.println(player.getBank());
        String winLose, loseMessage = "", fMessage = "";
        if(turnData[5]-turnData[4] > 0) {
            winLose = "You won " + turnData[5];
            fMessage = "You guessed " + turnData[2] + " and " + turnData[3] + " and you were right!";
        }
        else if (turnData[5]-turnData[4] < 0){
            winLose = "You lost " + turnData[4];
            loseMessage = "Better luck next time!";
            fMessage = "You guessed " + turnData[2] + " and " + turnData[3] + " but the right answer is:";
        } else {
            winLose = "You didn't bet anything! ";
        }

        String[] myMessage = new String[5];
        myMessage[0] = fMessage;
        myMessage[1] = winLose + " points and your score is now " + player.getBank() + ".";
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