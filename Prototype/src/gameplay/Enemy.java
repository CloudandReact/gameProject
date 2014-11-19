package gameplay;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy implements Runnable {
	private String enemy = "enemy.png";
	private Image image;
	
	public Enemy(Cell[][] gridMap)
	{
		loadImage();
		placeEnemies(gridMap);

	}
	
	private void placeEnemies(Cell[][] gridMap){
		for (int i = 0; i < 33; i++)
		{
			for (int j = 0; j < 14; j++)
			{
				int rand = randInt(1,55);
				if(rand == 5){
					gridMap[i][j] = Cell.ENEMY;			
				}
			
				if ((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1)) {
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
		ImageIcon ii = new ImageIcon(getClass().getResource(enemy));
		image = ii.getImage();
	}
	
	public Image getImage() {
		return image;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
