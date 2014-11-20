package menu;

public class FileWritingModifications {
 Validate modificationsAreValid= new Validate();
 
 public boolean areCredentialsValid(String realName,String password,String retypePassword){
	 if(modificationsAreValid.validateModifyAccount(realName,password,retypePassword)){
		 return true;
	 }
	 else
	 {
		 return false;
	 }
 
}}
