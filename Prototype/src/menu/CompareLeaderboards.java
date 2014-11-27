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
	//store the arraylist of all the player stats
	ArrayList<CSVRecord> allUsersStatistics;
	ArrayList<CSVRecord> allUsersStatistics1;
	Map <Integer, String> statsAscending= new TreeMap<Integer, String>();
	Map <Integer, String> statsFormatted= new HashMap <Integer, String>();
	ArrayList<String> userNames= new ArrayList<String>();
	ArrayList <Integer> userScores= new ArrayList<Integer>();
	
	public void setList(){
		
	}
	
	/*public void setArrayLists(){
		allUsersStatistics1 = PlayerInfo.allPlayersStats();
		Collection.sort(allUsersStatistics1);
		allUsersStatistics=allUsersStatistics1;
		int currentMax=Integer.parseInt(allUsersStatistics.get(0).get(2));
		int position=0;
		for(int i=0;i<allUsersStatistics.size();i++){
		currentMax=Integer.parseInt(allUsersStatistics.get(0).get(2));
			for(int j=0;j<allUsersStatistics.size();j++){
				if(currentMax>Integer.parseInt(allUsersStatistics.get(j).get(2))){
					
				}else{
					currentMax=Integer.parseInt(allUsersStatistics.get(j).get(2));
					position=j;
				}
				
				
				
		}
			userNames.add(allUsersStatistics.get(i).get(0));
			userScores.add(currentMax);
			
			allUsersStatistics.remove(position);
		
	}
		userNames.add(allUsersStatistics.get(0).get(0));
		userScores.add(Integer.parseInt(allUsersStatistics.get(0).get(2)));
		
}
	public ArrayList<Integer> getUserScores(){
		return userScores;
	}
	public ArrayList<String> getUsernames(){
		return userNames;
	}
	/*boolean compare=true;
	int checking=1;
	public void compareForDupl(){
		while(compare){
			compare=false;
		for(int i=0;i<userScores.size()-1;i++){
			if(userScores.get(i)==userScores.get(i+1) && userNames.get(i).compareTo(userNames.get(i+1))>0){
				//swap them
				
				String currentString=userNames.get(i);
				String nextString=userNames.get(i+1);
				int currentInt= userScores.get(i);
				int nextInt=userScores.get(i+1);
				userNames.set(i,nextString);
				userNames.set(i+1,currentString);
				userScores.set(i,nextInt);
				userScores.set(i+1,currentInt);
				compare=true;
				System.out.println(currentInt);
				System.out.println(nextInt);
				
				
			}
			
		}
		}
	}*/
	/*MultiMap <Integer, String> statsFormatted= new HashMultiMap<Integer , String>();
	Map <Integer, String> topTenScores;
	//check current player stats to see if they top leaderboard
	int scores*/
	
	 
	public void placeInMap(){
		allUsersStatistics=PlayerInfo.allPlayersStats();
		for(int i=0;i<allUsersStatistics.size();i++){
			//store in hashmap
			statsFormatted.put(Integer.parseInt(allUsersStatistics.get(i).get(2)),(allUsersStatistics.get(i).get(0)));
			userNames.add(allUsersStatistics.get(i).get(0));
			
			userScores.add(Integer.parseInt(allUsersStatistics.get(i).get(2)));
			if(PlayerInfo.usernameStatic.equals(userNames.get(i))&&!PlayerInfo.viewedLeaderBoard){
				PlayerInfo.playerScore+=userScores.get(i);
				PlayerInfo.viewedLeaderBoard=true;
			}
		}
		//store the stats in asceding order
		statsAscending= new TreeMap<Integer , String>(statsFormatted);
		System.out.println(statsAscending);
		System.out.println(userNames);
		System.out.println(userScores);
	}
	public void sortPlayers(){
		
		for(int i=0;i<userNames.size();i++){
			for(int j=0;j<userNames.size()-1;j++)
			if(userScores.get(j)<userScores.get(j+1)){
			String currentString=userNames.get(j);
			String nextString=userNames.get(j+1);
			int currentInt= userScores.get(j);
			int nextInt=userScores.get(j+1);
			userNames.set(j,nextString);
			userNames.set(j+1,currentString);
			userScores.set(j,nextInt);
			userScores.set(j+1,currentInt);
			}
		}
		System.out.println(userNames);
		System.out.println(userScores);
	}
	public void sortPlayersSameScore(){
		for(int i=0;i<userNames.size();i++){
			for(int j=0;j<userNames.size()-1;j++)
			if(userScores.get(j)==userScores.get(j+1) &&userNames.get(i).compareTo(userNames.get(j+1))>0){
			String currentString=userNames.get(j);
			String nextString=userNames.get(j+1);
			int currentInt= userScores.get(j);
			int nextInt=userScores.get(j+1);
			userNames.set(j,nextString);
			userNames.set(j+1,currentString);
			userScores.set(j,nextInt);
			userScores.set(j+1,currentInt);
			}
		}
		System.out.println(userNames);
		System.out.println(userScores);
	}
	int Score=0;
	public int  setPlayerScore(){
		for(int i=0;i<userNames.size();i++){
			if(PlayerInfo.usernameStatic.equals(userNames.get(i))){
				Score=PlayerInfo.playerScore+userScores.get(i);
			}
			
		}
		return Score;
	}
	public ArrayList<Integer> getUserScores(){
		return userScores;
	}
	public ArrayList<String> getUsernames(){
		return userNames;
	}
		
	}

			 
		
