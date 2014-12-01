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
/**
 * FilWriting implements all file writing to userInfo.csv,userStatistics.csv and saving a game
 * to whatever file the user selects. All userInfo after being pulled from the files is stored in ArrayList<CSVRecord>  
 * to compare with input from the user. As well to serialize the saved game all the objects neccessary are used as inputs.
 * 
 */
public class FileWriting implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String usernameFromFile = "";
	String passwordFromFile = "";
	String lineInfo[];
	private boolean isLoginCredFound = false;
	ArrayList<CSVRecord> loginInfo;
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
	
	/**
	 * This method oops through an arraylist of usernames and passwords and checks if there is a match. 
	 * Then lets the user log in or rejects username and password combination
	 * @param username
	 * 	A string containing the user's username
	 * @param password
	 * 	A string containing the user's password
	 * @return
	 */
	public boolean loginIsValid(String username, String password) {

		for (int i = 0; i < (loginInfo).size(); i = i+1) {
			
			// String line[]=lineOfFile[i].split(",");
			System.out.println("value user name" + username);
			System.out.println("value pasword " + password);
			System.out.println(loginInfo.get(i));
			
			System.out.println(loginInfo.size());

			if (loginInfo.get(i).get(1).equals(username)
					&& loginInfo.get(i).get(2).equals(password)) {
				return true;
			}
		}
		
		return false;

	}
	/**
	 * checks if the username is availible and then loops through file checking if username is taken if yes returna a warning to the 
	 * user and does let the user create an account
	 * @param username
	 *  String of the user's username.
	 * @return
	 */

	public boolean isUserNameAvailible(String username) {
		for (int i = 0; i < (loginInfo).size(); i = i+1) {
			System.out.println("use file " + loginInfo.get(i).get(1));
			if (loginInfo.get(i).get(1).equals(username)) {
				return false;
			}
		}
		return true;

	}
	/**
	 * first checks if the file exists then opens it and reads all the data using apache csv parser into an arraylist with usernames and passwords
	 */

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
			loginInfo = (ArrayList<CSVRecord>) parser.getRecords();
			
			parser.close();
			

		} catch (Exception e) {
			System.out.println(e + " array out of bounds");
			e.printStackTrace();
		}

	}

	public boolean validLoginCredentials() {
		return isLoginCredFound;
	}
    /**
     * Wites the realname,username,password to the file at a new line this method is only called after having checked that
     * the combination is a valid one.
     * @param realName
     * 		String of the user's realName
     * @param username
     * 		String of the user's username
     * @param password
     * 		String of the user's password
     * @param retypePassword
     * 		String of retyping the user's password for verification purposes.
     * @throws IOException
     */
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
	/**
	 * overwrites to file when the user tries to modify his account assuming correct input of realname and password
	 * The user is not allowed to modify his username
	 * @param realName
	 * 		String of user's realname.
	 * @param username
	 * 		String of user's username not modifiable in this method
	 * @param password
	 * 		String of user's password
	 * @param retypePassword
	 * 		String of user's retyped password
	 * @throws IOException
	 */
	public void overwriteToFileString (String realName, String username, String password,String retypePassword) throws IOException {
		String fileName = System.getProperty("user.dir")+"//" +"src" +"//"+"menu"+ "//"+ ("userInfo.csv");
		
		File userInfo = new File(fileName);
		// check if file exists otherwise create new file...
		if (userInfo.exists()) 
		{
		} 
		else {
			userInfo.createNewFile();
		}
		int usernamePosition=1;
		FileWriter fileWriter = new FileWriter(fileName,false);
		CSVPrinter writer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
		for (int i = 0; i < (loginInfo).size(); i = i+1) {
		
				if(loginInfo.get(i).get(usernamePosition).equals(PlayerInfo.getUsername())){
						writer.printRecord(realName, username, password);
		// writer.newLine();
		// writer.flush();
		
				}
				else {
					writer.printRecord(loginInfo.get(i));
				}
	
		}
		writer.close();
		}
	/**
	 * this method opens the statistics file for reading and then stores the data in an arraylist 
	 * called user statistiscs.
	 * @throws IOException
	 */
	
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
			
			
		
	/**
	 * This writes to statistics the username, score and level unlocked. It will overwrite should the user already have a stored data point.
	 * @param username
	 * 		String user's username
	 * @param score
	 * 		Int user's score
	 * @param levelUnlocked
	 * 		Int levelUnlocked
	 * @param currentGameOfUser
	 * @throws IOException
	 */
	public void writeToStatisticsFile(String username , int score , int levelUnlocked) throws IOException{
		///String fileName = System.getProperty("user.dir")+"\\" +"src" +"\\"+"menu"+ "\\"+ ("userStatistics.csv");
		String fileName = System.getProperty("user.dir")+"//" +"src" +"//"+"menu"+ "//"+ ("userStatistics.csv");
		File userInfo = new File(fileName);
		// check if file exists otherwise create new file...
		if (userInfo.exists()) {
		} else {
			userInfo.createNewFile();
		}
		
		//false overwrites
		FileWriter fileWriter = new FileWriter(fileName, false);
		CSVPrinter writer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
		//check to see if the user already has stats stored
		boolean isUserStatsStored=false;
		
		for(int i=0;i<userStatistics.size();i++){
			//checks to see if the current game statistics is already stored in the file
			// we can do this because only one statistics data point per user
			
			if(PlayerInfo.usernameStatic.equals(userStatistics.get(i).get(0)))
			{
				writer.printRecord(username, score, levelUnlocked);
				isUserStatsStored=true;
			}
			else{
				writer.printRecord(userStatistics.get(i));
			}
		}
		if(!isUserStatsStored){
			writer.printRecord(username,score,levelUnlocked);
		}
		
		
		writer.printRecord(username, score, levelUnlocked);
		
		writer.close();

	}
	/**
	 * Takes all the input neccesary to serialize the game and then save it in a file of the users choice. 
	 * All of these objects are serializable
	 * @param fileName
	 * 		String filename of the saved game file
	 * @param level
	 * 		Object of type Level storing all the level information
	 * @param enemy
	 * 		Object of type Enemy storing all the enemy information
	 * @param player
	 * 		Object of type Player storing all the player information
	 * @param concrete
	 * 		Object of type Concrete storing all the concrete information.
	 * @param brick
	 * 		Object of type Brick storing all the brick information.
	 * @param powerUps
	 * 		Object of type PowerUps stores the all the powerups information.
	 * @param exitWays
	 * 		Object of type ExitWays stores all the exitways information.
	 * @param bomb
	 * 		Object of type Bomb stores all the Bomb information.
	 * @param grid
	 * 		Object of type Grid stores all the grid information.
	 * @param playerScore
	 * 		Integer storing the current player score.
	 * @param currentLevel
	 * 		Integer storing the current level.
	 * @param currentLives
	 * 		Integer storing the current level.
	 * @param timer
	 * 		Int storing the time left in the timer.
	 * @param flamePass
	 * 		Boolean of type flamePass does the player have the flamePass powerup.
	 * @param bombPass
	 * 		Boolean of type bombPass does the player have the flamePass powerup.
	 * @param wallPass
	 * 		Boolean of type wallPass does the player have the flamePass powerup.
	 * @param detonate
	 * 		Boolean of type detonate does the player have the flamePass powerup.
	 * @throws IOException
	 */
	
	public void saveGame(String fileName,Level level,Enemy enemy, Player player, Concrete concrete, Brick brick, PowerUps powerUps,ExitWay exitWays,
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
		oos.writeObject(player);
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
	/**
	 * Loads all the objects stored in the filename and initializes them and they are called to loadgame by the getters below.
	 * @param fileName
	 * 		String the selected user's filename when he selects loadGame
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
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
	/**
	 * Checks if the username and password combination is valid
	 * @param realName
	 * 			String of the user's realname
	 * @param username
	 * 			String of the user's username
	 * @param password
	 * 			String of the user's password
	 * @param retypePassword
	 * 			String of then user's retype password
	 * @return
	 */
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
	/**
	 * the remaining methods are all getters
	 * @return
	 */
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
