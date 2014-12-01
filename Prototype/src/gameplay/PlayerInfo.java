package gameplay;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

/**
 * This class stores the username and corresponding statistics to be written to the user info file.
 */

public class PlayerInfo implements Serializable {
	
	public static String usernameStatic = "";
	public static String passwordStatic;

	public static int playerScore = 0;
	public static int unlockedLevel = 1;
	public static int currentLevel = 1;
	
	public static ArrayList<CSVRecord> allUserStatistics = new ArrayList<CSVRecord>();
	
	public static boolean viewedLeaderBoard = false;	
	
	/**
	 * Sets the player username.
	 * @param username the username to written to the user info file
	 */
	
	public static void setUsername(String username){
		usernameStatic = username;
	}
	/**
	 * Gets the username .
	 * @return the player's username
	 */
	public static String getUsername(){
		return usernameStatic;
	}
	
	/**
	 * Gets the highest level unlocked by this player.
	 * @return highest level reached by the player
	 */
	public static int getUnlockedLevel(){
		return unlockedLevel;
	}
	
	/**
	 * Gets a list of all user records.
	 * @return list of user records
	 */
	 public static ArrayList<CSVRecord> getAllPlayersStats(){
		return allUserStatistics;	
	}
	
	
}