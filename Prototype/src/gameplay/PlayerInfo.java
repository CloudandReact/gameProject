package gameplay;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

/**
 * This class 
 */

public class PlayerInfo implements Serializable {
	
	public static String usernameStatic = "";
	public static String passwordStatic;

	
	
	public static int playerScore = 0;
	public static int unlockedLevel = 1;
	public static int currentLevel = 1;
	public static int numberOfGames;
	public static int gameOfuser;
	
	public static ArrayList<CSVRecord> allUserStatistics = new ArrayList<CSVRecord>();
	
	public static boolean viewedLeaderBoard=false;	
	
	public PlayerInfo(){
		
	}
	
	/**
	 * Sets the player username.
	 * @param username the username to written to the user info file
	 */
	
	public static void setUsername(String username){
		usernameStatic = username;
	}
	
	public static String getUsername(){
		return usernameStatic;
	}
	
	public static int getUnlockedLevel(){
		return unlockedLevel;
	}
	
	 public static ArrayList<CSVRecord> allPlayersStats(){
		return allUserStatistics;	
	}
	
	
}