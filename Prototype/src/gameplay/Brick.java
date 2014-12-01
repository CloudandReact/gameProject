package gameplay;

import java.awt.Image;
import java.lang.reflect.Array;
import java.util.Random;

import javax.swing.ImageIcon;

public class Brick {
	
	private String brick = "brick.png";
	private Image image;
	private Boolean powerUpPlaced = false;
	
	private Grid grid;
	
	public Brick(Grid grid){
		loadImage();
		this.grid = grid;
		placeBricks();

	}
	
	private void placeBricks(){
		for (int i = 1; i < Bomberman.WIDTH-1; i++){
			for (int j = 1; j < Bomberman.HEIGHT-1; j++){
				int rand = randInt(1,5);
				if(rand == 1){
					grid.setContents(i, j, Tile.BRICK);			
				}
				
				if ((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1)) {
					grid.setContents(i, j, Tile.EMPTY);	
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
