package menu;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Validate {
	boolean realNameValid=false;;
	boolean usernameValid=false;
	boolean passwordValid=false;
	boolean passwordSame=false;
	public boolean validateCreateAccount(String realName,String username,String password, String retypePassword)
	{
		if(isRealNameValid(realName)&&isUserNameValid( username) && isPasswordValid(password)&&arePasswordSame(password,retypePassword)){
			return true;
		}
		return false;
	}
	
	 public boolean isRealNameValid(String realName){
		 if(realName.indexOf(" ")>0){
			 String realNameSplit[]=realName.split(" ");
			 System.out.println(realNameSplit.length);
			 if(realNameSplit.length>2){
				 return false;
			 }else{ 
				 //latin characters \\p{L}]
				 //dont need to use matching cause will only match specific
				 if(realNameSplit[0].matches("\\p{L}+")&&realNameSplit[1].matches("\\p{L}+")){
					 realNameValid=true;
					 
					return true; 
				 }
			 }
			 
			 }
		 return false;
		 
	 }	 //create indentical method without input as getters
	 public boolean isRealNameValid(){
		 return realNameValid;
	 }
	public boolean isUserNameValid(String username){
		//latin characters problems\\p{L}
		//split i
		String whitespaceInUserName[]=username.split(" ");
		if(username.matches("[\\p{L}\\s\\d]+")&&username.length()>=6 &&whitespaceInUserName.length==1){
			usernameValid=true;
			System.out.println("username is valid");
			return true; 
		 }return false;
		 
	 } public boolean isUserNameValid(){
		 return usernameValid;
	 }
	 public boolean isPasswordValid(String password){
		  Pattern number = Pattern.compile("[0-9]{1,}");
		  Pattern letterLowerCase=Pattern.compile("[a-z]");
		  Pattern letterUpperCase=Pattern.compile("[A-Z]");
		  Pattern specialCharacter= Pattern.compile("[\\p{Punct}]");
	      Matcher hasNumber = number.matcher(password);	      
	      Matcher hasLetterLowerCase=letterLowerCase.matcher(password);
	      Matcher hasLetterUpperCase=letterUpperCase.matcher(password);
	      Matcher hasSpecialCharacter=specialCharacter.matcher(password);
	      
	      /*System.out.println(password.matches("[0-9a-zA-Z]{8,}")+ " password matches");
	      System.out.println((password.length()>=8) + " size is greater");
	      System.out.println(hasNumber.find()+ " has number"+ hasLetterLowerCase.find() + " has lower case"+ hasLetterUpperCase.find() +  " has upper case");*/
		 if(((password.length()>=8) && password.matches("[0-9a-zA-Z\\p{Punct}]{8,}"))&& ((hasNumber.find()&& hasLetterLowerCase.find() )&&hasLetterUpperCase.find()) &&hasSpecialCharacter.find())
		 {
			 passwordValid=true;
			 return true;
		 }
		 return false;
	 }  public boolean isPasswordValid(){
		 return passwordValid;
	 }
	 public boolean arePasswordSame(String password, String retypePassword){
		 System.out.println(password.equals(retypePassword));
		 
		 
		 if(password.equals(retypePassword)){
			 passwordSame=true;
			 return true;
		 } 
		 return false;
	 }
	 public boolean arePasswordSame(){
		 return passwordSame;
	 }
	 
	 

}
