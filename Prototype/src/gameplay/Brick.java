package gameplay;

import java.awt.Image;
import java.lang.reflect.Array;
import java.util.Random;

import javax.swing.ImageIcon;

public class Brick {
	
	private String brick = "brick.png";
	private Image image;
	
	public Brick(Cell[][] gridMap)
	{
		loadImage();
		
		for (int i = 0; i < 33; i++)
		{
			for (int j = 0; j < 14; j++)
			{
				int rand = randInt(1,3);
				if(rand == 1){
					gridMap[i][j] = Cell.BRICK;			
				}
			
				if ((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1)){
					gridMap[i][j] = Cell.EMPTY;
				}
			}
		}
	}

	
	public static int randInt(int min, int max) {

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
