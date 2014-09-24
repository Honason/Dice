package dice;

import java.util.ArrayList;

public class Bet {
    public int[] turnData = new int[6];
    public int myBet = 0;
    public int thrownSum;
    public String[] choices;
    public String answer = "";

    // Getting all possible choices, and sending them to interface.
    public String[] pickValues(int[] thrown) {
        Player player = GameEngine.activePlayer;
        myBet = thrown[2];
        player.addRolls(thrown);
        thrownSum = thrown[0]+thrown[1];

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= 6 ; i++) {
            for (int j = 1; j <= 6 ; j++) {
                if(i+j == thrownSum) list.add(i + " and " + j);
                if(i == thrown[0] && j == thrown[1]) answer = i + " and " + j;
            }
        }
        System.out.println("Right answer is " + answer + ". For testing purpose.    ");

        choices = list.toArray(new String[list.size()]);

        GameTurn.turn.setThrown(thrown);
        //GameMain.userInterface.setGuess(choices);

        return choices;
    }

    // Evaluating the choice, calculating reward / loss
    public void makeGuess(String guess) {
        int[] thrown = GameTurn.turn.getThrown();
        thrownSum = thrown[0]+thrown[1];
        myBet = thrown[2];

        turnData[0] = thrown[0];
        turnData[1] = thrown[1];

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= 6 ; i++) {
            for (int j = 1; j <= 6 ; j++) {
                if(i+j == thrownSum) list.add(i + " and " + j);
                if(i == thrown[0] && j == thrown[1]) answer = i + " and " + j;
            }
        }

        Player player = GameEngine.activePlayer;
        player.addGameWin(guess.equals(answer));

        String[] bets = guess.split(" and ");
        turnData[2] = Integer.parseInt(bets[0]);
        turnData[3] = Integer.parseInt(bets[1]);

        player.addBet(myBet);

        turnData[4] = myBet;

        if (guess.equals(answer)) {
            GameTurn.winners.add(player.getI());
            if (thrownSum==2 || thrownSum==3 || thrownSum==11 || thrownSum==12) {
                double temp = (myBet*1.5);
                myBet = (int) temp;
            } else if (thrownSum==4 || thrownSum==5 || thrownSum==9 || thrownSum==10) {
                myBet = myBet*2;
            } else if (thrownSum==6 || thrownSum==7 || thrownSum==8) {
                myBet = myBet*3;
            }
        } else {
            GameTurn.losers.add(player.getI());
            myBet = myBet * 0;
        }

        turnData[5] = myBet;
        GameTurn.turnData.add(turnData);
        player.addReward(myBet);
        player.changeBank(turnData[5] - turnData[4]);

        /*
        GameTurn turn = new GameTurn();
        turn.getBet(turnData);
        */
    }
    
    // 1. and 2.: Dice face values, 3.and 4. Guesses, 5. Amount we have bet, 6. is reward.
}
