package gameplay;

import java.awt.Image;
import java.lang.reflect.Array;
import java.util.Random;

import javax.swing.ImageIcon;

public class Brick {
	
	private String brick = "brick.png";
	private Image image;
	private Boolean powerUpPlaced = false;
	
	public Brick(Cell[][] gridMap)
	{
		loadImage();
		placeBricks(gridMap);

	}
	
	private void placeBricks(Cell[][] gridMap){
		for (int i = 1; i < 32; i++)
		{
			for (int j = 1; j < 14; j++)
			{
				int rand = randInt(1,3);
				if(rand == 1){
					gridMap[i][j] = Cell.BRICK;			
				}
				
				/*if(!powerUpPlaced){
					
				}			
				powerUpPlaced = true;*/

				if ((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1)) {
					gridMap[i][j] = Cell.EMPTY;
				}
			}
		}
	}
	
	private static int randInt(int min, int max) {

	    Random rand = new Random();
	    
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(brick));
		image = ii.getImage();
	}
	
	public Image getImage() {
		return image;
	}
	
}
