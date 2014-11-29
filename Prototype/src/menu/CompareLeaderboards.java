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

public class CompareLeaderboards {
	

	ArrayList<CSVRecord> allUsersStatistics;
	ArrayList<CSVRecord> allUsersStatistics1;
	Map<Integer, String> statsAscending = new TreeMap<Integer, String>();
	Map<Integer, String> statsFormatted = new HashMap<Integer, String>();
	ArrayList<String> userNames = new ArrayList<String>();
	ArrayList<Integer> userScores = new ArrayList<Integer>();

	public void setList() {

	}

	public void placeInMap() {
		allUsersStatistics = PlayerInfo.allPlayersStats();
		for (int i = 0; i < allUsersStatistics.size(); i++) {
			// store in hashmap
			statsFormatted.put(
					Integer.parseInt(allUsersStatistics.get(i).get(2)),
					(allUsersStatistics.get(i).get(0)));
			userNames.add(allUsersStatistics.get(i).get(0));

			userScores.add(Integer.parseInt(allUsersStatistics.get(i).get(2)));
			if (PlayerInfo.usernameStatic.equals(userNames.get(i))
					&& !PlayerInfo.viewedLeaderBoard) {
				PlayerInfo.playerScore += userScores.get(i);
				PlayerInfo.viewedLeaderBoard = true;
			}
		}
		// store the stats in asceding order
		statsAscending = new TreeMap<Integer, String>(statsFormatted);
		System.out.println(statsAscending);
		System.out.println(userNames);
		System.out.println(userScores);
	}

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

	int Score = 0;

	public int setPlayerScore() {
		for (int i = 0; i < userNames.size(); i++) {
			if (PlayerInfo.usernameStatic.equals(userNames.get(i))) {
				Score = PlayerInfo.playerScore + userScores.get(i);
			}

		}
		return Score;
	}

	public ArrayList<Integer> getUserScores() {
		return userScores;
	}

	public ArrayList<String> getUsernames() {
		return userNames;
	}

}
