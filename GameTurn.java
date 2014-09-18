package dice;

public class GameTurn {
    public static void main() {
        // Get random dice values
        int[] thrown = new int[2];
        thrown = Die.main();

        // Calling class Bet and setting parameter which is thrown sum.
        Bet.main(thrown[0]+thrown[1]);
    }
}