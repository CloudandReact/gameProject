package menu;

import gameplay.PlayerInfo;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.io.File;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.apache.commons.csv.CSVRecord;
/**
 * CompareLeaderboards this stores the allUserStatistics then sorts it by score and name and then it is called
 * in Leaderboards to display
 * @author elliot
 *
 */

public class CompareLeaderboards {
	

	ArrayList<CSVRecord> allUsersStatistics;
	ArrayList<CSVRecord> allUsersStatistics1;
	
	ArrayList<String> userNames = new ArrayList<String>();
	ArrayList<Integer> userScores = new ArrayList<Integer>();

	/**
	 placeInArrayList stores the different usernames and scores in 2 seperate arraylists.
	 */

	public void placeInArrayList() {
		allUsersStatistics = PlayerInfo.getAllPlayersStats();
		for (int i = 0; i < allUsersStatistics.size(); i++) {
			
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
	 * sortPlayers sorts the players in the arraylist based on socre. Loops through the arraylist and swaps values which are greater.
	 * But does not sort players with same score.
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
	 * SortPlayersSameScore is called after sortPlayers and sorts the players with different names and the same score
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

	int playersScore = 0;
	/**
	 * setPlayerScore sets the player score and then returns it by summing up his previous score with his current score
	 * @return
	 */
	public int setPlayerScore() {
		for (int i = 0; i < userNames.size(); i++) {
			if (PlayerInfo.usernameStatic.equals(userNames.get(i))) {
				playersScore = PlayerInfo.playerScore + userScores.get(i);
			}

		}
		return playersScore;
	}

	public ArrayList<Integer> getUserScores() {
		return userScores;
	}

	public ArrayList<String> getUsernames() {
		return userNames;
	}

}
