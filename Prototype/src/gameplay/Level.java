package gameplay;

public class Level {

	private int nEnemy1;
	private int nEnemy2;
	private int nEnemy3;
	private int nEnemy4;
	private int nEnemy5;
	private int nEnemy6;
	private int nEnemy7;
	private int nEnemy8;

	private static int currentLevel = 0;

	public Level(int currentLevel) {
		this.currentLevel = currentLevel;
		setNumberOfEnemies();
	}

	private void setNumberOfEnemies() {

		if (currentLevel == 1) {
			nEnemy1 = 6;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 0;
			nEnemy6 = 0;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 2) {
			nEnemy1 = 3;
			nEnemy2 = 3;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 0;
			nEnemy6 = 0;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 3) {
			nEnemy1 = 2;
			nEnemy2 = 2;
			nEnemy3 = 2;
			nEnemy4 = 0;
			nEnemy5 = 0;
			nEnemy6 = 0;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 4) {

			nEnemy1 = 1;
			nEnemy2 = 1;
			nEnemy3 = 2;
			nEnemy4 = 2;
			nEnemy5 = 0;
			nEnemy6 = 0;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 5) {
			nEnemy1 = 0;
			nEnemy2 = 4;
			nEnemy3 = 3;
			nEnemy4 = 0;
			nEnemy5 = 0;
			nEnemy6 = 0;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 6) {
			nEnemy1 = 0;
			nEnemy2 = 2;
			nEnemy3 = 3;
			nEnemy4 = 2;
			nEnemy5 = 0;
			nEnemy6 = 0;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 7) {
			nEnemy1 = 0;
			nEnemy2 = 2;
			nEnemy3 = 3;
			nEnemy4 = 0;
			nEnemy5 = 2;
			nEnemy6 = 0;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 8) {
			nEnemy1 = 0;
			nEnemy2 = 1;
			nEnemy3 = 2;
			nEnemy4 = 4;
			nEnemy5 = 0;
			nEnemy6 = 0;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 9) {
			nEnemy1 = 0;
			nEnemy2 = 1;
			nEnemy3 = 1;
			nEnemy4 = 4;
			nEnemy5 = 1;
			nEnemy6 = 0;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 10) {
			nEnemy1 = 0;
			nEnemy2 = 1;
			nEnemy3 = 1;
			nEnemy4 = 1;
			nEnemy5 = 3;
			nEnemy6 = 1;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 11) {
			nEnemy1 = 0;
			nEnemy2 = 1;
			nEnemy3 = 2;
			nEnemy4 = 3;
			nEnemy5 = 1;
			nEnemy6 = 1;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 12) {
			nEnemy1 = 0;
			nEnemy2 = 1;
			nEnemy3 = 1;
			nEnemy4 = 1;
			nEnemy5 = 4;
			nEnemy6 = 1;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 13) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 3;
			nEnemy4 = 3;
			nEnemy5 = 3;
			nEnemy6 = 0;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 14) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 0;
			nEnemy6 = 7;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 15) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 1;
			nEnemy4 = 3;
			nEnemy5 = 3;
			nEnemy6 = 0;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 16) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 3;
			nEnemy5 = 4;
			nEnemy6 = 0;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 17) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 5;
			nEnemy4 = 0;
			nEnemy5 = 2;
			nEnemy6 = 0;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 18) {
			nEnemy1 = 3;
			nEnemy2 = 3;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 0;
			nEnemy6 = 0;
			nEnemy7 = 2;
			nEnemy8 = 0;
		} else if (currentLevel == 19) {
			nEnemy1 = 1;
			nEnemy2 = 1;
			nEnemy3 = 3;
			nEnemy4 = 0;
			nEnemy5 = 0;
			nEnemy6 = 1;
			nEnemy7 = 2;
			nEnemy8 = 0;
		} else if (currentLevel == 20) {
			nEnemy1 = 0;
			nEnemy2 = 1;
			nEnemy3 = 1;
			nEnemy4 = 1;
			nEnemy5 = 2;
			nEnemy6 = 1;
			nEnemy7 = 2;
			nEnemy8 = 0;
		} else if (currentLevel == 21) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 4;
			nEnemy6 = 3;
			nEnemy7 = 2;
			nEnemy8 = 0;
		} else if (currentLevel == 22) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 4;
			nEnemy4 = 3;
			nEnemy5 = 1;
			nEnemy6 = 0;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 23) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 2;
			nEnemy4 = 2;
			nEnemy5 = 2;
			nEnemy6 = 2;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 24) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 1;
			nEnemy4 = 1;
			nEnemy5 = 4;
			nEnemy6 = 2;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 25) {
			nEnemy1 = 0;
			nEnemy2 = 2;
			nEnemy3 = 1;
			nEnemy4 = 1;
			nEnemy5 = 2;
			nEnemy6 = 2;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 26) {
			nEnemy1 = 1;
			nEnemy2 = 1;
			nEnemy3 = 1;
			nEnemy4 = 1;
			nEnemy5 = 2;
			nEnemy6 = 1;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 27) {
			nEnemy1 = 1;
			nEnemy2 = 1;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 5;
			nEnemy6 = 1;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 28) {
			nEnemy1 = 0;
			nEnemy2 = 1;
			nEnemy3 = 3;
			nEnemy4 = 3;
			nEnemy5 = 1;
			nEnemy6 = 0;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 29) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 2;
			nEnemy6 = 5;
			nEnemy7 = 2;
			nEnemy8 = 0;
		} else if (currentLevel == 30) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 3;
			nEnemy4 = 2;
			nEnemy5 = 1;
			nEnemy6 = 2;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 31) {
			nEnemy1 = 0;
			nEnemy2 = 2;
			nEnemy3 = 2;
			nEnemy4 = 2;
			nEnemy5 = 2;
			nEnemy6 = 2;
			nEnemy7 = 0;
			nEnemy8 = 0;
		} else if (currentLevel == 32) {
			nEnemy1 = 0;
			nEnemy2 = 1;
			nEnemy3 = 1;
			nEnemy4 = 3;
			nEnemy5 = 4;
			nEnemy6 = 0;
			nEnemy7 = 1;
			nEnemy8 = 0;
		} else if (currentLevel == 33) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 2;
			nEnemy4 = 2;
			nEnemy5 = 3;
			nEnemy6 = 1;
			nEnemy7 = 2;
			nEnemy8 = 0;
		} else if (currentLevel == 34) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 2;
			nEnemy4 = 3;
			nEnemy5 = 3;
			nEnemy6 = 0;
			nEnemy7 = 2;
			nEnemy8 = 0;
		} else if (currentLevel == 35) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 2;
			nEnemy4 = 1;
			nEnemy5 = 3;
			nEnemy6 = 1;
			nEnemy7 = 2;
			nEnemy8 = 0;
		} else if (currentLevel == 36) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 2;
			nEnemy4 = 2;
			nEnemy5 = 3;
			nEnemy6 = 0;
			nEnemy7 = 3;
			nEnemy8 = 0;
		} else if (currentLevel == 37) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 2;
			nEnemy4 = 1;
			nEnemy5 = 3;
			nEnemy6 = 1;
			nEnemy7 = 3;
			nEnemy8 = 0;
		} else if (currentLevel == 38) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 2;
			nEnemy4 = 2;
			nEnemy5 = 3;
			nEnemy6 = 0;
			nEnemy7 = 3;
			nEnemy8 = 0;
		} else if (currentLevel == 39) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 1;
			nEnemy4 = 1;
			nEnemy5 = 2;
			nEnemy6 = 2;
			nEnemy7 = 4;
			nEnemy8 = 0;
		} else if (currentLevel == 40) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 1;
			nEnemy4 = 2;
			nEnemy5 = 3;
			nEnemy6 = 0;
			System.out.println("Reaching where we need to");
			nEnemy7 = 4;
			nEnemy8 = 0;
		} else if (currentLevel == 41) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 1;
			nEnemy4 = 1;
			nEnemy5 = 3;
			nEnemy6 = 1;
			nEnemy7 = 4;
			nEnemy8 = 0;
		} else if (currentLevel == 42) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 1;
			nEnemy5 = 3;
			nEnemy6 = 1;
			nEnemy7 = 5;
			nEnemy8 = 0;
		} else if (currentLevel == 43) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 1;
			nEnemy5 = 2;
			nEnemy6 = 1;
			nEnemy7 = 6;
			nEnemy8 = 0;
		} else if (currentLevel == 44) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 1;
			nEnemy5 = 2;
			nEnemy6 = 1;
			nEnemy7 = 6;
			nEnemy8 = 0;
		} else if (currentLevel == 45) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 2;
			nEnemy6 = 2;
			nEnemy7 = 6;
			nEnemy8 = 0;
		} else if (currentLevel == 46) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 2;
			nEnemy6 = 2;
			nEnemy7 = 6;
			nEnemy8 = 0;
		} else if (currentLevel == 47) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 2;
			nEnemy6 = 2;
			nEnemy7 = 6;
			nEnemy8 = 0;
		} else if (currentLevel == 48) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 2;
			nEnemy6 = 1;
			nEnemy7 = 6;
			nEnemy8 = 1;
		} else if (currentLevel == 49) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 1;
			nEnemy6 = 2;
			nEnemy7 = 6;
			nEnemy8 = 1;
		} else if (currentLevel == 50) {
			nEnemy1 = 0;
			nEnemy2 = 0;
			nEnemy3 = 0;
			nEnemy4 = 0;
			nEnemy5 = 1;
			nEnemy6 = 2;
			nEnemy7 = 5;
			nEnemy8 = 2;
		}
	}

	public void setLevel(int level) {
		currentLevel = level;
	}

	public int getLevel() {
		return currentLevel;
	}

	public int getnEnemy1() {
		return nEnemy1;
	}

	public int getnEnemy2() {
		return nEnemy2;
	}

	public int getnEnemy3() {
		return nEnemy3;
	}

	public int getnEnemy4() {
		return nEnemy4;
	}

	public int getnEnemy5() {
		return nEnemy5;
	}

	public int getnEnemy6() {
		return nEnemy6;
	}

	public int getnEnemy7() {
		return nEnemy7;
	}

	public int getnEnemy8() {
		return nEnemy8;
	}

	public static int getCurrentLevel() {
		return currentLevel;
	}


	public void setCurrentLevel( int level) {
		Level.currentLevel = level;
	}
}