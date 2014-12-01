package menu;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import gameplay.Bomb;
import gameplay.Brick;
import gameplay.Concrete;
import gameplay.Enemy;
import gameplay.ExitWay;
import gameplay.Grid;
import gameplay.Level;
import gameplay.Player;
import gameplay.Game;








import gameplay.PowerUps;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import gameplay.PlayerInfo;
public class FileWriting implements Serializable {
	String usernameFromFile = "";
	String passwordFromFile = "";
	String lineInfo[];
	private boolean isLoginCredFound = false;
	String lineOfFile[] = new String[100];
	ArrayList<CSVRecord> userFileInfo1;
	ArrayList<CSVRecord> userStatistics;
	ArrayList<CSVRecord> loadedGame;
	Enemy enemy;
	Brick brick;
	Player p;
	Level level;
	Bomb bomb;
	Concrete concrete;
	Grid grid;
	ExitWay exitWays;
	PowerUps powerUps;
	int playerScore;
	int currentLevel;
	int currentLives;
	int timer;
	boolean loadFlamePass;
	boolean loadBombPass;
	boolean loadDetonate;
	boolean loadWallPass;
	

	public boolean loginIsValid(String username, String password) {

		for (int i = 0; i < (userFileInfo1).size(); i = i+1) {
			
			// String line[]=lineOfFile[i].split(",");
			System.out.println("value user name" + username);
			System.out.println("value pasword " + password);
			System.out.println(userFileInfo1.get(i));
			
			System.out.println(userFileInfo1.size());

			if (userFileInfo1.get(i).get(1).equals(username)
					&& userFileInfo1.get(i).get(2).equals(password)) {
				return true;
			}
		}
		
		return false;

	}

	public boolean isUserNameAvailible(String username) {
		for (int i = 0; i < (userFileInfo1).size(); i = i+1) {
			System.out.println("use file " + userFileInfo1.get(i).get(1));
			if (userFileInfo1.get(i).get(1).equals(username)) {
				return false;
			}
		}
		return true;

	}

	public void openFile() {
		// note u can delete all the special stuff if File userInfo= new
		// File(userInfo.csv) works otherwise
		// find the directory eclipse is in System.getProperty("user.dir") and
		// match them..
		String filename = System.getProperty("user.dir")+"//" +"src" +"//"+"menu"+ "//"+ ("userInfo.csv");
		//String fileName = System.getProperty("user.dir")+"\\" +"src" +"\\"+"menu"+ "\\"+ ("userInfo.csv");
		System.getProperty("user.dir" + "\\" + "userInfo.csv");
		String checkIfFileExists=System.getProperty("user.dir")+"//" +"src" +"//"+"menu"+ "//"+ ("userInfo.csv");
		File userInfo = new File(checkIfFileExists);
		// check if file exists otherwise create new file...
		if (userInfo.exists()) {
		} else {
			try {
				userInfo.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//File userInfo = new File((filename));

		/*if (userInfo.exists()) {
			System.out.println("File exists");
		} else {
			System.out.println("File not found");
		}*/

		try {
			// userFileInfo1 = new ArrayList<String>();

			// Scanner input= new Scanner(userInfo);
			// String line= input.readLine();
			CSVParser parser = new CSVParser(new FileReader(filename),
					CSVFormat.DEFAULT);
			userFileInfo1 = (ArrayList<CSVRecord>) parser.getRecords();
			String line = "";
			parser.close();
			/*
			 * while ((line = br.readLine()) != null) {
			 * 
			 * // use comma as separator
			 * 
			 * String[] userData = line.split(","); for (int i = 0; i <
			 * userData.length - 1; i = i + 3) {
			 * System.out.println("[username = " + userData[i + 1] +
			 * "  password = " + userData[i + 2] + "]");
			 * userFileInfo1.add(userData[i + 1]); userFileInfo1.add(userData[i
			 * + 2]); }
			 * 
			 * } br.close();
			 */

		} catch (Exception e) {
			System.out.println(e + " array out of bounds");
			e.printStackTrace();
		}

	}

	public boolean validLoginCredentials() {
		return isLoginCredFound;
	}

	public void writeToFile(String realName, String username, String password,
		String retypePassword) throws IOException {
		String fileName = System.getProperty("user.dir")+"//" +"src" +"//"+"menu"+ "//"+ ("userInfo.csv");
		//String fileName = System.getProperty("user.dir")+"\\" +"src" +"\\"+"menu"+ "\\"+ ("userInfo.csv");
		// String fileName=("userInfo.csv");
		File userInfo = new File(fileName);
		// check if file exists otherwise create new file...
		if (userInfo.exists()) {
		} else {
			userInfo.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(fileName, true);
		CSVPrinter writer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
		writer.printRecord(realName, username, password);
		// writer.newLine();
		// writer.flush();
		writer.close();

	}
	public void overwriteToFileString (String realName, String username, String password,String retypePassword) throws IOException {
	String fileName = System.getProperty("user.dir")+"//" +"src" +"//"+"menu"+ "//"+ ("userInfo.csv");
	//String fileName = System.getProperty("user.dir")+"\\" +"src" +"\\"+"menu"+ "\\"+ ("userInfo.csv");
	// String fileName=("userInfo.csv");
	File userInfo = new File(fileName);
	// check if file exists otherwise create new file...
	if (userInfo.exists()) 
	{
	} 
	else {
		userInfo.createNewFile();
	}
	FileWriter fileWriter = new FileWriter(fileName,false);
	CSVPrinter writer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
	for (int i = 0; i < (userFileInfo1).size(); i = i+1) {
	
			if(userFileInfo1.get(i).get(1).equals(PlayerInfo.getUsername())){
					writer.printRecord(realName, username, password);
	// writer.newLine();
	// writer.flush();
	
			}
			else {
				writer.printRecord(userFileInfo1.get(i));
			}

	}
	writer.close();
	}
	public void openStatistics() throws IOException{
		// note u can delete all the special stuff if File userInfo= new
		// File(userInfo.csv) works otherwise
		// find the directory eclipse is in System.getProperty("user.dir") and
		// match them..
		String filename = System.getProperty("user.dir")+"//" +"src" +"//"+"menu"+ "//"+ ("userStatistics.csv");
		String checkIfFileExists=System.getProperty("user.dir")+"//" +"src" +"//"+"menu"+ "//"+ ("userStatistics.csv");
		File userInfo = new File((checkIfFileExists));

		if (userInfo.exists()) {
			System.out.println("File exists");
		} else {
			System.out.println("File not found");
		}

		
			// userFileInfo1 = new ArrayList<String>();

			// Scanner input= new Scanner(userInfo);
			// String line= input.readLine();
	
			CSVParser parser = new CSVParser(new FileReader(filename),
					CSVFormat.DEFAULT);
			userStatistics= (ArrayList<CSVRecord>) parser.getRecords();
			parser.close();
	}
			
			
		
	//write to statistics check if already has a score
	//Libraries/Documents
	public void writeToStatisticsFile(String username , int score , int levelUnlocked,int currentGameOfUser) throws IOException{
		///String fileName = System.getProperty("user.dir")+"\\" +"src" +"\\"+"menu"+ "\\"+ ("userStatistics.csv");
		String fileName = System.getProperty("user.dir")+"//" +"src" +"//"+"menu"+ "//"+ ("userStatistics.csv");
		File userInfo = new File(fileName);
		// check if file exists otherwise create new file...
		if (userInfo.exists()) {
		} else {
			userInfo.createNewFile();
		}
		int gamesStored=1;
		//false overwrites
		FileWriter fileWriter = new FileWriter(fileName, false);
		CSVPrinter writer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
		if(currentGameOfUser==0){
			writer.printRecord(username, score, levelUnlocked);
		}
		for(int i=0;i<userStatistics.size();i++){
			//checks to see if the current game is already stored in the file
			if(PlayerInfo.usernameStatic.equals(userStatistics.get(i).get(0))&& currentGameOfUser!=gamesStored){
				writer.printRecord(userStatistics.get(i));
				currentGameOfUser++;
			
			}
			else if(PlayerInfo.usernameStatic.equals(userStatistics.get(i).get(0))&& currentGameOfUser==gamesStored)
			{
				writer.printRecord(username, score, levelUnlocked);
			}
			else{
				writer.printRecord(userStatistics.get(i));
			}
		}
		
		writer.printRecord(username, score, levelUnlocked);
		
		writer.close();

	}
	
	
	public void saveGame(String fileName,Level level,Enemy enemy, Player p, Concrete concrete, Brick brick, PowerUps powerUps,ExitWay exitWays,
			Bomb bomb,Grid grid,int playerScore, int currentLevel,int currentLives , int timer, boolean flamePass, boolean bombPass, boolean wallPass,
		   boolean detonate) throws IOException{
		File savingFile= new File(fileName);
		if(savingFile.exists()){
			//check to overwrite file
			JOptionPane.showMessageDialog(null,"would you like to overwrite file", "error", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			
		}
		
		
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(level);
		oos.writeObject(enemy);
		oos.writeObject(p);
		oos.writeObject(concrete);
		oos.writeObject(brick);
		oos.writeObject(powerUps);
		oos.writeObject(exitWays);
		oos.writeObject(bomb);
		oos.writeObject(grid);
		oos.writeInt(playerScore);
		oos.writeInt(currentLevel);
		oos.writeInt(currentLives);
		oos.writeInt(timer);
		System.out.println("flame pass "+ flamePass);
		System.out.println("Bomb pass "+ bombPass);
		System.out.println("wall pass "+ wallPass);
		System.out.println("flame pass "+ detonate);
		oos.writeBoolean(flamePass);
		oos.writeBoolean(bombPass);
		oos.writeBoolean(wallPass);
		oos.writeBoolean(detonate);
		
		
		oos.close();
		fos.close();
		
		
	}
public void loadGame(String fileName) throws ClassNotFoundException, IOException{
       
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			  level= (Level)ois.readObject();
	          enemy = (Enemy) ois.readObject();
	          p=(Player)ois.readObject();
	          concrete= (Concrete)ois.readObject();
	          brick= (Brick)ois.readObject();
	          powerUps=(PowerUps)ois.readObject();
	          exitWays= (ExitWay) ois.readObject();
	          bomb=(Bomb)ois.readObject();
	          grid= (Grid)ois.readObject();
	          playerScore=(int)ois.readInt();
	          currentLevel=(int)ois.readInt();
	          currentLives=(int)ois.readInt();
	          timer=(int)ois.readInt();
	          loadFlamePass=(boolean)ois.readBoolean();
	          loadWallPass=(boolean)ois.readBoolean();
	          loadBombPass=(boolean) ois.readBoolean();
	          loadDetonate=(boolean)ois.readBoolean();
	          
	          
	          
	          
	         ois.close();
	         fis.close();
	         
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
         
		File loadingFile= new File(fileName);
		if(loadingFile.exists()){
			
		}
		else{
			JOptionPane.showMessageDialog(null,"file doesnt exist", "error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		
		
	}
	
	// creates validate object to check if everything is right in validate
	Validate checkInfo = new Validate();

	public boolean checkIfValid(String realName, String username,
			String password, String retypePassword) {
		System.out.println(realName);
		System.out.println(username);
		System.out.println(password);
		System.out.println(retypePassword);
		if (checkInfo.validateCreateAccount(realName, username, password,
				retypePassword)) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<CSVRecord> allPlayersStats(){
		return userStatistics;
		
	}//returns booleans checking if all inputs are valid
	public boolean isRealNameValid() {
		return checkInfo.isRealNameValid();
	}

	public boolean isUserNameValid() {
		return checkInfo.isUserNameValid();
	}

	public boolean isPasswordValid() {
		return checkInfo.isPasswordValid();
	}

	public boolean arePasswordSame() {
		return checkInfo.arePasswordSame();
	}
	public ArrayList<CSVRecord> loadedGame(){
		return  loadedGame;
	}
	public Enemy getEnemy(){
		return enemy;
	}
	public Player getPlayer(){
		return p;
	}
	public Concrete getConcrete(){
		return concrete;
	}
	
	public Brick getBrick(){
		return brick;
	}
	public PowerUps getPowerUps(){
		return powerUps;
	}
	public ExitWay getExitWays(){
		return exitWays;
	}
	public Bomb getBomb(){
		return bomb;
	}
	public Grid getGrid(){
		return grid;
	}
	public Level getLevel(){
		return level;
	}
	public int getPlayerScore(){
		return playerScore;
	}
	public int getPlayerLevel(){
		return currentLevel;
	}
	public int getPlayerLives(){
		return currentLives;
	}public int getTimer(){
		return timer;
	}public boolean getLoadWallPass(){
		return loadWallPass;
	}
	public boolean getLoadFlamePass(){
		return loadFlamePass;
	}
	public boolean getLoadBombPass(){
		return loadBombPass;
	}
	public boolean getLoadDetonate(){
		return loadDetonate;
	}

	
}