package gameplay;

import java.awt.Image;
import java.io.Serializable;
import java.util.Random;

import javax.swing.ImageIcon;

public class ExitWay implements Serializable{

	private String imageNameExitway = "exitway.png";
	private String imageNamePlayerAndExitway = "playerandexitway.png";
	
	private Image imageExitway;
	private Image imagePlayerAndExitway;
	
	private static Boolean access = false;
	private static Boolean exitwayPlaced = false;
	ImageIcon exitway;
	ImageIcon playerandexitway;
	
	private Grid grid;
	
	/**
	 * Exitway which is to be placed on the grid.
	 * @param grid Grid on which the exitway is placed.
	 */
	
	public ExitWay (Grid grid){
		this.grid = grid;
		loadImage();
		placeExitWay();

	}
	
	/**
	 * Places the exitway in a semi-random location under a brick.
	 */
	private void placeExitWay(){
		while(exitwayPlaced == false){
			int randX = randInt(2,30);
			int randY = randInt(2,12); 
		
			if(grid.getContents(randX, randY) == Tile.EMPTY){
				grid.setContents(randX,randY,Tile.BRICKANDEXITWAY);
				exitwayPlaced = true;
			}
		
		}
		
	}
	
	/**
	 * Given two integers, min and max, this method will return a pseudo-random
	 * integer value contained between these two integers.
	 * 
	 * @param min Minimum value which may be returned.
	 * @param max Maximum value which may be returned.
	 * @return An integer between min and max inclusively.
	 */
	public static int randInt(int min, int max) {

	    Random rand = new Random();
	    
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	/**
	 * Loads images related to exitway
	 */	
	private void loadImage() {
		 exitway = new ImageIcon(getClass().getResource(imageNameExitway));
		

		playerandexitway = new ImageIcon(getClass().getResource(imageNamePlayerAndExitway));
		
	}
	
	public Image getImageExitway() {
		return exitway.getImage();
	}
	
	public Image getImagePlayerAndExitway() {
		return playerandexitway.getImage();
		
	}

}

