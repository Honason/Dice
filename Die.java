package dice;

import java.util.Random;

public class Die {
    public int[] main() {

        // Returning an array of two random number from 1 to 6.

        Random rand = new Random();
        int number[] = new int[3];
        number[0] = rand.nextInt(6) + 1;
        number[1] = rand.nextInt(6) + 1;
        number[2] = 0;
        return number;
    }
}
