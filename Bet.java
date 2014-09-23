package dice;

import java.util.ArrayList;

public class Bet {
    public int[] turnData = new int[6];
    public int myBet = 0;
    public int thrownSum;
    public String[] choices;
    public String answer = "";

    public void pickValues(int[] thrown) {
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
        System.out.println(answer);

        choices = list.toArray(new String[list.size()]);

        GameTurn.turn.setThrown(thrown);
        GameMain.userInterface.setGuess(choices);

    }

    public void makeGuess(String guess) {
        int[] thrown = GameTurn.turn.getThrown();
        thrownSum = thrown[0]+thrown[1];
        //myBet = GameTurn.turn.getMyBet();
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
            if (thrownSum==2 || thrownSum==3 || thrownSum==11 || thrownSum==12) {
                double temp = (myBet*1.5);
                myBet = (int) temp;
            } else if (thrownSum==4 || thrownSum==5 || thrownSum==9 || thrownSum==10) {
                myBet = myBet*2;
            } else if (thrownSum==6 || thrownSum==7 || thrownSum==8) {
                myBet = myBet*3;
            }
        } else {
            myBet = myBet * 0;
        }

        turnData[5] = myBet;

        player.addReward(myBet);

        GameTurn turn = new GameTurn();
        turn.getBet(turnData);
    }
    
    // 1. and 2.: Dice face values, 3.and 4. Guesses, 5. Amount we have bet, 6. is reward.
}
