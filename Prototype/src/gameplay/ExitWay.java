package gameplay;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class ExitWay {

	private String exitway = "exitway.png";
	private String playerandexitway = "playerandexitway.png";
	private Image imageExitway;
	private Image imagePlayerAndExitway;
	private static Boolean access = false;
	
	Grid grid;
	
	public ExitWay (Grid grid){
		this.grid = grid;
		loadImage();
		placeExitWay();

	}
	
	private void placeExitWay(){
		
		int randX = randInt(2,31);
		int randY = randInt(2,13); 
		
		grid.setContents(4,1,Cell.BRICKANDEXITWAY);
		
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();
	    
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(exitway));
		imageExitway = ii.getImage();

		ImageIcon playerandexitways = new ImageIcon(getClass().getResource(playerandexitway));
		imagePlayerAndExitway = playerandexitways.getImage();
	}
	
	public Image getImageExitway() {
		return imageExitway;
	}
	
	public Image getImagePlayerAndExitway() {
		return imagePlayerAndExitway;
	}
	
	public static void exitWayAccessible(){
//		if(Enemy.getNumberOfEnemies() == Bomb.getNumberOfEnemiesKilled()){
//			access = true;
//		}
		
	}
	public static boolean getExitWayAccessible(){
		return access;
	}
}
