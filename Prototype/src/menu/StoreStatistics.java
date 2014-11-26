package menu;

import gameplay.PlayerInfo;

import java.io.IOException;
import java.util.ArrayList;


import org.apache.commons.csv.CSVRecord;

public class StoreStatistics {
	FileWriting checkingStats= new FileWriting();
	int numberOfGames;
	ArrayList<CSVRecord> usersStatistics;
	ArrayList<CSVRecord> currentUserStatistics;
	
	
	String playerUsername=PlayerInfo.usernameStatic;
	int levelAvailible=1;;
	
	public void checkNumberOfGames(){
		try {
			checkingStats.openStatistics();
			usersStatistics=checkingStats.allPlayersStats();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int numberOfGames(){
		currentUserStatistics= new ArrayList<CSVRecord>();
		numberOfGames=0;
		for(int i=0;i<usersStatistics.size();i++){
			if(playerUsername.equals(usersStatistics.get(i).get(0))){
				System.out.println(currentUserStatistics);
				System.out.println(usersStatistics.get(i));
				currentUserStatistics.add(usersStatistics.get(i));
				int levelInFile= Integer.parseInt(usersStatistics.get(i).get(1));
				if(levelAvailible<levelInFile){
					levelAvailible=levelInFile;
					
				}
				
				numberOfGames++;
			}
			else{
				
			}
			
		}
		//set player leve Availible
		PlayerInfo.unlockedLevel=levelAvailible;
		PlayerInfo.numberOfGames=numberOfGames;
		return numberOfGames;
	}
	public ArrayList<CSVRecord> currentUserInfo(){
		return currentUserStatistics;
	}
	public void leaderboard(){
		checkNumberOfGames();
		//usersStatistics.sort();
		
		
		
		
	}
	
}
