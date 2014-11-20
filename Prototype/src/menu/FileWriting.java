package menu;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class FileWriting {
	String usernameFromFile = "";
	String passwordFromFile = "";
	String lineInfo[];
	private boolean isLoginCredFound = false;
	String lineOfFile[] = new String[100];
	ArrayList<CSVRecord> userFileInfo1;

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
		String filename = System.getProperty("user.dir")+"\\" +"src" +"\\"+"menu"+ "\\"+ ("userInfo.csv");
		System.getProperty("user.dir" + "\\" + "userInfo.csv");
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
		String fileName = System.getProperty("user.dir")+"\\" +"src" +"\\"+"menu"+ "\\"+ ("userInfo.csv");
		// String fileName=("userInfo.csv");
		File userInfo = new File(fileName);
		// check if file exists otherwise create new file...
		if (userInfo.exists()) {
		} else {
			userInfo.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(fileName, true);
		CSVPrinter writer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
		String comma = ",";
		writer.printRecord(realName, username, password);
		// writer.newLine();
		// writer.flush();
		writer.close();

	}

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
	public ArrayList<CSVRecord> listOf(){
		return userFileInfo1;
	}
}