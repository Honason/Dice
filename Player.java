package dice;
import java.util.*;
import java.text.*;

public class Player {
	public int playerIndex = 0;
	public int bank = 100;
	public int averageRoll = 0;
	public int averageBet = 0;
	public int averageReward = 0;
	public int[][] distrRoll = {
		{1,0},
		{2,0},
		{3,0},
		{4,0},
		{5,0},
		{6,0}
	};
	public int[] winLoses = {0,0};  // Win Count, Lose Count
	public ArrayList<Integer> diceRolls = new ArrayList<Integer>();
	public ArrayList<Integer> bets = new ArrayList<Integer>();
	public ArrayList<Integer> rewards = new ArrayList<Integer>();

	public Player(int i) {
		playerIndex = i;
	}
	public int getI() {
		return playerIndex;
	}
	public int getBank() {
		return bank;
	}
	public void changeBank(int change) {
		bank += change;
	}
	public void addRolls(int[] rolls) {
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

	public int[] getRolls() {
		return listToArray(diceRolls);
	}
	public int getAveRoll() {
		return averageRoll;
	}
	public int[][] getDistrRolls() {
		return distrRoll;
	}
	public void addBet(int bet) {
		bets.add(bet);
		int sum = 0;
		for (int d : bets) sum += d;
		averageBet = sum / bets.size();
	}
	public int[] getBets() {
		return listToArray(bets);
	}
	public int getAveBet() {
		return averageBet;
	}
	public void addReward(int reward) {
		rewards.add(reward);
		int sum = 0;
		for (int d : rewards) sum += d;
			averageReward = sum / rewards.size();
	}
	public int[] getRewards() {
		return listToArray(rewards);
	}
	public int getAveReward() {
		return averageReward;
	}
	public void addGameWin(boolean tsmwonnered) {
		if(tsmwonnered) winLoses[0]++;
		else winLoses[1]++;
	}
	public int[] getWinLoses() {
		return winLoses;
	}
	public String getWinRate() {
		DecimalFormat df = new DecimalFormat("#.##"); 
		return df.format(((double)winLoses[0] / (winLoses[0] + winLoses[1]))*100);
	}


	public int[] listToArray(ArrayList<Integer> list) {
		int[] res = new int[list.size()];
		Iterator<Integer> iterator = list.iterator();
		for (int i = 0; i < res.length; i++)
		{
			res[i] = iterator.next().intValue();
		}
		return res;
	}
}
