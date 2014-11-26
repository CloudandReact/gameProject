package gameplay;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

public class PlayerInfo {
	//we need to have all player data that needs to be saved
	public static String usernameStatic = "";
	public static int playerScore=100;
	public static int unlockedLevel=1;
	public static String passwordStatic;
	//number of games to check if loadable
	public static int numberOfGames;
	// if new game zero user can have multiple games stored
	public static int gameOfuser;
	//stores current level in game
	public static int currentLevel;
	public static ArrayList<CSVRecord> allUserStatistics=new ArrayList<CSVRecord>();
	boolean gameStarted;
	
	
	public PlayerInfo(){
		
	}
	
	public static void setUsername(String x){
		usernameStatic = x;
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