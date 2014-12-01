package menu;

import gameplay.PlayerInfo;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.io.File;
import java.util.Comparator;


import org.apache.commons.csv.CSVRecord;
/**
 * CompareLeaderBoards gets the info from the file in FileWriting reads it into ArrayList and then 
 * sorts new arraylist according to score and username.
 * 
 */

public class CompareLeaderboards {
	

	ArrayList<CSVRecord> allUsersStatistics;
	ArrayList<CSVRecord> allUsersStatistics1;
	ArrayList<String> userNames = new ArrayList<String>();
	ArrayList<Integer> userScores = new ArrayList<Integer>();
	int playerScore = 0;
  /**
   * The method placeInArrayList stores the arraylist of allUseraStatistics into userNames for the names and userScores for the scores.
   * 
   */
	
	public void placeInArrayList() {
		allUsersStatistics = PlayerInfo.allPlayersStats();
		for (int i = 0; i < allUsersStatistics.size(); i++) {
			// store in hashmap
			
			userNames.add(allUsersStatistics.get(i).get(0));

			userScores.add(Integer.parseInt(allUsersStatistics.get(i).get(2)));
			if (PlayerInfo.usernameStatic.equals(userNames.get(i))
					&& !PlayerInfo.viewedLeaderBoard) {
				PlayerInfo.playerScore += userScores.get(i);
				PlayerInfo.viewedLeaderBoard = true;
			}
		}
		// store the stats in asceding order
		
		System.out.println(userNames);
		System.out.println(userScores);
	}
	/**
	 * The method sortPlayers sorts the players based on score.
	 */

	public void sortPlayers() {

		for (int i = 0; i < userNames.size(); i++) {
			for (int j = 0; j < userNames.size() - 1; j++)
				if (userScores.get(j) < userScores.get(j + 1)) {
					String currentString = userNames.get(j);
					String nextString = userNames.get(j + 1);
					int currentInt = userScores.get(j);
					int nextInt = userScores.get(j + 1);
					userNames.set(j, nextString);
					userNames.set(j + 1, currentString);
					userScores.set(j, nextInt);
					userScores.set(j + 1, currentInt);
				}
		}
		System.out.println(userNames);
		System.out.println(userScores);
	}
    /**
     * The method sortPlayersSameScore sorts the players with the same score and different usernames
     */
	public void sortPlayersSameScore() {
		for (int i = 0; i < userNames.size(); i++) {
			for (int j = 0; j < userNames.size() - 1; j++)
				if (userScores.get(j) == userScores.get(j + 1)
						&& userNames.get(i).compareTo(userNames.get(j + 1)) > 0) {
					String currentString = userNames.get(j);
					String nextString = userNames.get(j + 1);
					int currentInt = userScores.get(j);
					int nextInt = userScores.get(j + 1);
					userNames.set(j, nextString);
					userNames.set(j + 1, currentString);
					userScores.set(j, nextInt);
					userScores.set(j + 1, currentInt);
				}
		}
		System.out.println(userNames);
		System.out.println(userScores);
	}

	
    
	/**
	 * sets the player score which is returned and then displayed in leaderboard.
	 * 
	 */
	public int setPlayerScore() {
		for (int i = 0; i < userNames.size(); i++) {
			if (PlayerInfo.usernameStatic.equals(userNames.get(i))) {
				playerScore = PlayerInfo.playerScore + userScores.get(i);
			}

		}
		return playerScore;
	}

	public ArrayList<Integer> getUserScores() {
		return userScores;
	}

	public ArrayList<String> getUsernames() {
		return userNames;
	}

}
