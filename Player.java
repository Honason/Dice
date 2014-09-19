package dice;
import java.util.*;
import java.text.*;

public class Player {
	public static int bank = 100;
	public static int averageRoll = 0;
	public static int averageBet = 0;
	public static int[][] distrRoll = {
		{1,0},
		{2,0},
		{3,0},
		{4,0},
		{5,0},
		{6,0}
	};
	public static int[] winLoses = {0,0};  // Win Count, Lose Count
	public static ArrayList<Integer> diceRolls = new ArrayList<Integer>();
	public static ArrayList<Integer> bets = new ArrayList<Integer>();

	public static void main() {
		
	}
	public static int getBank() {
		return bank;
	}
	public static void changeBank(int change) {
		bank += change;
	}
	public static void addRolls(int[] rolls) {
		for (int i = 0; i < rolls.length ; i++) {
			diceRolls.add(rolls[i]);
			for (int j = 0; j < distrRoll.length ; j++) {
				if(distrRoll[j][0] == rolls[i]) distrRoll[j][1]++;
			}
		}
		int sum = 0;
		for (int d : diceRolls) sum += d;
		averageRoll = sum / diceRolls.size();
	}

	public static int[] getRolls() {
		return listToArray(diceRolls);
	}
	public static int getAveRoll() {
		return averageRoll;
	}
	public static int[][] getDistrRolls() {
		return distrRoll;
	}
	public static void addBet(int bet) {
		bets.add(bet);
		int sum = 0;
		for (int d : bets) sum += d;
		averageBet = sum / bets.size();
	}
	public static int[] getBets() {
		return listToArray(bets);
	}
	public static int getAveBet() {
		return averageBet;
	}
	public static void addGameWin(boolean tsmwonnered) {
		if(tsmwonnered) winLoses[0]++;
		else winLoses[1]++;
	}
	public static int[] getWinLoses() {
		return winLoses;
	}
	public static String getWinRate() {
		DecimalFormat df = new DecimalFormat("#.##"); 
		return df.format(((double)winLoses[0] / (winLoses[0] + winLoses[1]))*100);
	}


	public static int[] listToArray(ArrayList<Integer> list) {
		int[] res = new int[list.size()];
		Iterator<Integer> iterator = list.iterator();
		for (int i = 0; i < res.length; i++)
		{
			res[i] = iterator.next().intValue();
		}
		return res;
	}
}
