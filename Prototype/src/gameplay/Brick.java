package gameplay;

import java.awt.Image;
import java.lang.reflect.Array;
import java.util.Random;

import javax.swing.ImageIcon;

public class Brick {

	private String imageNameBrick = "brick.png";
	private Image imageBrick;
	private Boolean powerUpPlaced = false;

	private Grid grid;
	
	/**
	 * Bricks which are to be placed randomly on the grid.
	 * @param grid The grid on which we want to place the bricks.
	 */
	public Brick(Grid grid) {
		
		loadImage();
		this.grid = grid;
		placeBricks();

	}

	/**
	 * Places bricks randomly on the grid.
	 */
	private void placeBricks() {
		
		for (int i = 1; i < Bomberman.WIDTH - 1; i++) {
			for (int j = 1; j < Bomberman.HEIGHT - 1; j++) {
				
				int rand = randInt(1, 5);
				
				if (rand == 1) {
					grid.setContents(i, j, Tile.BRICK);
				}

				// Bricks cannot be placed in the three top left tiles.
				if ((i == 1 && j == 1) || (i == 1 && j == 2)
						|| (i == 2 && j == 1)) {
					grid.setContents(i, j, Tile.EMPTY);
				}
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

	private static int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
	
	/**
	 * Loads image for the bricks. 
	 */

	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(imageNameBrick));
		imageBrick = ii.getImage();
	}
	
	/**
	 * Get the image for bricks.  
	 * @return
	 */

	public Image getImage() {
		return imageBrick;
	}

}
