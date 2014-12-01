package menu;

import gameplay.PlayerInfo;

public class StatisticsWriting {
	public int score = PlayerInfo.playerScore;
	public int unlockedLeve = PlayerInfo.unlockedLevel;
	public String username = PlayerInfo.usernameStatic;
	public int gameOfUser;
	FileWriting writeStatistics = new FileWriting();
	
}