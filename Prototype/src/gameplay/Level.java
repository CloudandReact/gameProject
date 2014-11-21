package gameplay;

public class Level {
	
	private int[] nEnemy1 = new int[50];
	private int[] nEnemy2 = new int[50];
	private int[] nEnemy3 = new int[50];
	private int[] nEnemy4 = new int[50];
	private int[] nEnemy5 = new int[50];
	private int[] nEnemy6 = new int[50];
	private int[] nEnemy7 = new int[50];
	
	
	private int currentLevel;
	
	public Level(){
		currentLevel = 0;
		setNumberOfEnemies();
	}
	
	public void setNumberOfEnemies(){			
		
	}
	
	public void setLevel(int level){
		currentLevel = level;
	}
	
	public int getLevel(){
		return currentLevel;
	}
	
	
}
