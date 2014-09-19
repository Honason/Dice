package dice;
import java.util.Arrays;

public class GameTurn {
    public static void main() {
        // Get random dice values
        int[] thrown = new int[2];
        thrown = Die.main();
        Player.addRolls(thrown);
        Player.addBet(100);
        Player.addBet(150);
        System.out.println( Arrays.toString(Player.getBets()));
        System.out.println( Player.getAveBets());
        Player.addGameWin(true);
        Player.addGameWin(true);
        Player.addGameWin(true);
        Player.addGameWin(true);
        Player.addGameWin(true);
        Player.addGameWin(true);
        Player.addGameWin(true);
        Player.addGameWin(false);
        Player.addGameWin(false);
        Player.addGameWin(false);
        Player.addGameWin(false);
        System.out.println(Player.getWinRate());
        System.out.println(Arrays.toString(Player.getWinLoses()));


        // Calling class Bet and setting parameter which is thrown sum.
        Player.changeBank(Bet.main(thrown));
        System.out.println(Player.getBank());
    }
}