package dice;
import java.util.Arrays;

public class GameTurn {
    public static void main() {
        // Get random dice values
        int[] thrown = new int[2];
        thrown = Die.main();
        // Calling class Bet and setting parameter which is thrown sum.
        Player.changeBank(Bet.main(thrown));
        System.out.println(Player.getBank());

        System.out.println(Bet.turnData[0] + " " + Bet.turnData[1] + " " + Bet.turnData[2] + " " + Bet.turnData[3] + " " + Bet.turnData[4] + " " + Bet.turnData[5] + " ");
    }
}