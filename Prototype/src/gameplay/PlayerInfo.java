package gameplay;

public class PlayerInfo {
	
	public static String usernameStatic = "";
	public static int playerScore=100;
	public static int unlockedLevel=1;
	public static String passwordStatic;
	//number of games to check if loadable
	public static int numberOfGames;
	// if new game zero user can have multiple games stored
	public static int gameOfuser;
	
	
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
	
	
}