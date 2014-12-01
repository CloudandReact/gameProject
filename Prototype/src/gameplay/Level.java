package gameplay;

import java.io.Serializable;

public class Level implements Serializable{



	private int numberOfBallooms;
	private int numberOfOneals;
	private int numberOfDolls;
	private int numberOfMinvos;
	private int numberOfKondorias;
	private int numberOfOvapis;
	private int numberOfPasses;
	private int numberOfPontans;

	private static int currentLevel = 1;

	/**
	 * Level Constructor 
	 * @param currentLevel is the level that the player is currently in
	 */
	public Level(int currentLevel) {
		this.currentLevel = currentLevel;
		
		setNumberOfEnemies();
	}

	/**
	 * Gives the number of each type of enemies for each level
	 */
	private void setNumberOfEnemies() {

		if (currentLevel == 1) {
			numberOfBallooms = 6;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 0;
			numberOfOvapis = 0;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 2) {
			numberOfBallooms = 3;
			numberOfOneals = 3;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 0;
			numberOfOvapis = 0;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 3) {
			numberOfBallooms = 2;
			numberOfOneals = 2;
			numberOfDolls = 2;
			numberOfMinvos = 0;
			numberOfKondorias = 0;
			numberOfOvapis = 0;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 4) {
			numberOfBallooms = 1;
			numberOfOneals = 1;
			numberOfDolls = 2;
			numberOfMinvos = 2;
			numberOfKondorias = 0;
			numberOfOvapis = 0;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 5) {
			numberOfBallooms = 0;
			numberOfOneals = 4;
			numberOfDolls = 3;
			numberOfMinvos = 0;
			numberOfKondorias = 0;
			numberOfOvapis = 0;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 6) {
			numberOfBallooms = 0;
			numberOfOneals = 2;
			numberOfDolls = 3;
			numberOfMinvos = 2;
			numberOfKondorias = 0;
			numberOfOvapis = 0;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 7) {
			numberOfBallooms = 0;
			numberOfOneals = 2;
			numberOfDolls = 3;
			numberOfMinvos = 0;
			numberOfKondorias = 2;
			numberOfOvapis = 0;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 8) {
			numberOfBallooms = 0;
			numberOfOneals = 1;
			numberOfDolls = 2;
			numberOfMinvos = 4;
			numberOfKondorias = 0;
			numberOfOvapis = 0;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 9) {
			numberOfBallooms = 0;
			numberOfOneals = 1;
			numberOfDolls = 1;
			numberOfMinvos = 4;
			numberOfKondorias = 1;
			numberOfOvapis = 0;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 10) {
			numberOfBallooms = 0;
			numberOfOneals = 1;
			numberOfDolls = 1;
			numberOfMinvos = 1;
			numberOfKondorias = 3;
			numberOfOvapis = 1;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 11) {
			numberOfBallooms = 0;
			numberOfOneals = 1;
			numberOfDolls = 2;
			numberOfMinvos = 3;
			numberOfKondorias = 1;
			numberOfOvapis = 1;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 12) {
			numberOfBallooms = 0;
			numberOfOneals = 1;
			numberOfDolls = 1;
			numberOfMinvos = 1;
			numberOfKondorias = 4;
			numberOfOvapis = 1;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 13) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 3;
			numberOfMinvos = 3;
			numberOfKondorias = 3;
			numberOfOvapis = 0;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 14) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 0;
			numberOfOvapis = 7;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 15) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 1;
			numberOfMinvos = 3;
			numberOfKondorias = 3;
			numberOfOvapis = 0;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 16) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 3;
			numberOfKondorias = 4;
			numberOfOvapis = 0;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 17) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 5;
			numberOfMinvos = 0;
			numberOfKondorias = 2;
			numberOfOvapis = 0;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 18) {
			numberOfBallooms = 3;
			numberOfOneals = 3;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 0;
			numberOfOvapis = 0;
			numberOfPasses = 2;
			numberOfPontans = 0;
		} else if (currentLevel == 19) {
			numberOfBallooms = 1;
			numberOfOneals = 1;
			numberOfDolls = 3;
			numberOfMinvos = 0;
			numberOfKondorias = 0;
			numberOfOvapis = 1;
			numberOfPasses = 2;
			numberOfPontans = 0;
		} else if (currentLevel == 20) {
			numberOfBallooms = 0;
			numberOfOneals = 1;
			numberOfDolls = 1;
			numberOfMinvos = 1;
			numberOfKondorias = 2;
			numberOfOvapis = 1;
			numberOfPasses = 2;
			numberOfPontans = 0;
		} else if (currentLevel == 21) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 4;
			numberOfOvapis = 3;
			numberOfPasses = 2;
			numberOfPontans = 0;
		} else if (currentLevel == 22) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 4;
			numberOfMinvos = 3;
			numberOfKondorias = 1;
			numberOfOvapis = 0;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 23) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 2;
			numberOfMinvos = 2;
			numberOfKondorias = 2;
			numberOfOvapis = 2;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 24) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 1;
			numberOfMinvos = 1;
			numberOfKondorias = 4;
			numberOfOvapis = 2;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 25) {
			numberOfBallooms = 0;
			numberOfOneals = 2;
			numberOfDolls = 1;
			numberOfMinvos = 1;
			numberOfKondorias = 2;
			numberOfOvapis = 2;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 26) {
			numberOfBallooms = 1;
			numberOfOneals = 1;
			numberOfDolls = 1;
			numberOfMinvos = 1;
			numberOfKondorias = 2;
			numberOfOvapis = 1;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 27) {
			numberOfBallooms = 1;
			numberOfOneals = 1;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 5;
			numberOfOvapis = 1;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 28) {
			numberOfBallooms = 0;
			numberOfOneals = 1;
			numberOfDolls = 3;
			numberOfMinvos = 3;
			numberOfKondorias = 1;
			numberOfOvapis = 0;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 29) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 2;
			numberOfOvapis = 5;
			numberOfPasses = 2;
			numberOfPontans = 0;
		} else if (currentLevel == 30) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 3;
			numberOfMinvos = 2;
			numberOfKondorias = 1;
			numberOfOvapis = 2;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 31) {
			numberOfBallooms = 0;
			numberOfOneals = 2;
			numberOfDolls = 2;
			numberOfMinvos = 2;
			numberOfKondorias = 2;
			numberOfOvapis = 2;
			numberOfPasses = 0;
			numberOfPontans = 0;
		} else if (currentLevel == 32) {
			numberOfBallooms = 0;
			numberOfOneals = 1;
			numberOfDolls = 1;
			numberOfMinvos = 3;
			numberOfKondorias = 4;
			numberOfOvapis = 0;
			numberOfPasses = 1;
			numberOfPontans = 0;
		} else if (currentLevel == 33) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 2;
			numberOfMinvos = 2;
			numberOfKondorias = 3;
			numberOfOvapis = 1;
			numberOfPasses = 2;
			numberOfPontans = 0;
		} else if (currentLevel == 34) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 2;
			numberOfMinvos = 3;
			numberOfKondorias = 3;
			numberOfOvapis = 0;
			numberOfPasses = 2;
			numberOfPontans = 0;
		} else if (currentLevel == 35) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 2;
			numberOfMinvos = 1;
			numberOfKondorias = 3;
			numberOfOvapis = 1;
			numberOfPasses = 2;
			numberOfPontans = 0;
		} else if (currentLevel == 36) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 2;
			numberOfMinvos = 2;
			numberOfKondorias = 3;
			numberOfOvapis = 0;
			numberOfPasses = 3;
			numberOfPontans = 0;
		} else if (currentLevel == 37) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 2;
			numberOfMinvos = 1;
			numberOfKondorias = 3;
			numberOfOvapis = 1;
			numberOfPasses = 3;
			numberOfPontans = 0;
		} else if (currentLevel == 38) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 2;
			numberOfMinvos = 2;
			numberOfKondorias = 3;
			numberOfOvapis = 0;
			numberOfPasses = 3;
			numberOfPontans = 0;
		} else if (currentLevel == 39) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 1;
			numberOfMinvos = 1;
			numberOfKondorias = 2;
			numberOfOvapis = 2;
			numberOfPasses = 4;
			numberOfPontans = 0;
		} else if (currentLevel == 40) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 1;
			numberOfMinvos = 2;
			numberOfKondorias = 3;
			numberOfOvapis = 0;
			numberOfPasses = 4;
			numberOfPontans = 0;
		} else if (currentLevel == 41) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 1;
			numberOfMinvos = 1;
			numberOfKondorias = 3;
			numberOfOvapis = 1;
			numberOfPasses = 4;
			numberOfPontans = 0;
		} else if (currentLevel == 42) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 1;
			numberOfKondorias = 3;
			numberOfOvapis = 1;
			numberOfPasses = 5;
			numberOfPontans = 0;
		} else if (currentLevel == 43) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 1;
			numberOfKondorias = 2;
			numberOfOvapis = 1;
			numberOfPasses = 6;
			numberOfPontans = 0;
		} else if (currentLevel == 44) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 1;
			numberOfKondorias = 2;
			numberOfOvapis = 1;
			numberOfPasses = 6;
			numberOfPontans = 0;
		} else if (currentLevel == 45) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 2;
			numberOfOvapis = 2;
			numberOfPasses = 6;
			numberOfPontans = 0;
		} else if (currentLevel == 46) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 2;
			numberOfOvapis = 2;
			numberOfPasses = 6;
			numberOfPontans = 0;
		} else if (currentLevel == 47) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 2;
			numberOfOvapis = 2;
			numberOfPasses = 6;
			numberOfPontans = 0;
		} else if (currentLevel == 48) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 2;
			numberOfOvapis = 1;
			numberOfPasses = 6;
			numberOfPontans = 1;
		} else if (currentLevel == 49) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 1;
			numberOfOvapis = 2;
			numberOfPasses = 6;
			numberOfPontans = 1;
		} else if (currentLevel == 50) {
			numberOfBallooms = 0;
			numberOfOneals = 0;
			numberOfDolls = 0;
			numberOfMinvos = 0;
			numberOfKondorias = 1;
			numberOfOvapis = 2;
			numberOfPasses = 5;
			numberOfPontans = 2;
		}
	}

	public void setLevel(int level) {
		currentLevel = level;
	}

	public int getLevel() {
		return currentLevel;
	}

	public int getnumberOfBallooms() {
		return numberOfBallooms;
	}

	public int getnumberOfOneals() {
		return numberOfOneals;
	}

	public int getnumberOfDolls() {
		return numberOfDolls;
	}

	public int getnumberOfMinvos() {
		return numberOfMinvos;
	}

	public int getnumberOfKondorias() {
		return numberOfKondorias;
	}

	public int getnumberOfOvapis() {
		return numberOfOvapis;
	}

	public int getnumberOfPasses() {
		return numberOfPasses;
	}

	public int getnumberOfPontans() {
		return numberOfPontans;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel( int level) {
		Level.currentLevel = level;
	}
}