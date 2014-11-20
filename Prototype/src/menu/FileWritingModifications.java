package menu;
import gameplay.PlayerInfo;
public class FileWritingModifications {
 Validate modificationsAreValid= new Validate();
 
 public boolean areCredentialsValid(String realName,String password,String retypePassword){
	 if(modificationsAreValid.validateCreateAccount(realName,PlayerInfo.getUsername(),password,retypePassword)){
		 return true;
	 }
	 else
	 {
		 return false;
	 }
 
}}
