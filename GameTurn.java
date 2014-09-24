package dice;
public class GameTurn {
    public static GameTurn turn = new GameTurn();
    public static int activeP = 0;
    public static int thrown[] = new int[3];
    public static int myBet;

    // Instance of one game turn
    public void newTurn() {
        int[] playersAlive = GameEngine.playersAlive();
        if (playersAlive[0] > 0) {

            Die dice = new Die();
            thrown = dice.main();
            turnOnePlayer();

        } else {
             GameEngine.theEnd(playersAlive[1]);
        }
    }

    public void turnOnePlayer() {
        if (activeP >= GameEngine.playerCount) {
            activeP = 0;
            newTurn();
        }

        GameEngine gEngine = new GameEngine();
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