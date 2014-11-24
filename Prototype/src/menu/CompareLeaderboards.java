package menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.io.File;

public class CompareLeaderboards {

	public CompareLeaderboards() {
		readFile();

	}

	String csvFileLoc = System.getProperty("user.dir") + "\\" + "src" + "\\"
			+ "menu" + "\\" + ("leaderboard.csv");
	BufferedReader br = null;
	String line = "";
	String commaSeparator = ",";
	private int[] userScore = new int[10];
	private String[] userName = new String[10];
	String[] userInfo = new String[20];
	int k = 0;
	int h = 0;
	int z = 10;
	int r = 0;
	int size = 0;
	int count = 0;
	int tempS;
	String tempN;
	int playerScore = 58;
	String playerName = "John";
	boolean breaking = false;

	public void run() {
		readFile();
		writeFile();

	}

	public void readFile() {
		try {
			File file = new File(System.getProperty("user.dir") + "\\" + "src"
					+ "\\" + "menu" + "\\" + ("leaderboard.csv"));
			if (!file.exists()) {
				file.createNewFile();
			}

			int a = 1;
			br = new BufferedReader(new FileReader(csvFileLoc));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				if (a == 1) {
					String[] userDetails = line.split(commaSeparator);
					size = userDetails.length;
					count = size;
					for (int j = 0; j < size; j = j + 2) {
						userName[k] = userDetails[j]; // stores usernames
						int foo = Integer.parseInt(userDetails[j + 1]); // converts
																		// string
																		// to
																		// int
						userScore[k] = foo; // stores users highscores
						k++;
					}

				}
			}
			a++;
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		for (int i = 0; i < (size / 2); i++) {
			System.out.println(userName[i] + " = " + userScore[i]);

		}

		// checking

		for (int i = 0; i < (size / 2); i++) {
			if (userScore[i] < playerScore) {
				if (breaking == false) {
					z = i;
					breaking = true;
				}
			}
			// else if(i==9 && userScore[i] > playerScore){
			// }
		}

		// storing
		if (z < 10) {
			for (r = z; r < (size / 2); r++) {
				tempN = userName[r];
				tempS = userScore[r];
				userName[r] = playerName;
				userScore[r] = playerScore;
				playerName = tempN;
				playerScore = tempS;
			}
		}
		// if(((size/2) < 10) && breaking == false){
		// userName[(size/2)] = playerName;
		// userScore[(size/2)] = playerScore;
		// count = count + 2;
		// }
		// if((size/2) < 10 && breaking ==true){
		// userName[r] = playerName;
		// userScore[r] = playerScore;
		// count = count + 2;
		// }
		if (size / 2 < 10) {
			if (breaking == false) {
				userName[(size / 2)] = playerName;
				userScore[(size / 2)] = playerScore;
			} else if (breaking == true) {
				userName[r] = playerName;
				userScore[r] = playerScore;
			}

			count = count + 2;
		}

		for (int s = 0; s < count; s = s + 2) {
			userInfo[s] = userName[h];
			String foo = String.valueOf(userScore[h]);
			userInfo[s + 1] = foo;
			h++;
		}
		for (int i = 0; i < count; i++) {
			System.out.println(userInfo[i] + ",  ");

		}
	}

	// writing to file
	public void writeFile() {
		try {
			// File userInfo = new File(System.getProperty("user.dir") + "\\" +
			// "src" + "\\" + "menu" + "\\" + ("userInfo.csv"));
			File file = new File(System.getProperty("user.dir") + "\\" + "src"
					+ "\\" + "menu" + "\\" + ("leaderboard.csv"));

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter writer = new FileWriter(file.getAbsoluteFile());
			for (int l = 0; l < count; l++) {

				writer.append(userInfo[l]);
				writer.append(",");
			}

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void display() {
	}

	public String[] getUserName() {
		return userName;
	}

	public void setUserName(String[] userName) {
		this.userName = userName;
	}

	public int[] getUserScore() {
		return userScore;
	}

	public void setUserScore(int[] userScore) {
		this.userScore = userScore;
	}
}