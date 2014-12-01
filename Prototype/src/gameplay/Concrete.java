package gameplay;

import java.awt.*;

import javax.swing.*;

public class Concrete {
	
	private String concrete = "concrete.png";

	private Image imageConcrete;

	private Grid grid;
	
	/**
	 * Concrete walls which are placed on the border and in the grid.
	 * @param grid The grid on which concrete walls are placed.
	 */

	public Concrete(Grid grid) {
		this.grid = grid;
		loadImage();
		placeConcreteOnTheBorder();
		placeConcreteOnTheMap();

	}

	/**
	 *  Places concrete walls on the border of the map.
	 */
	private void placeConcreteOnTheBorder() {

		for (int x = 0; x < Bomberman.WIDTH; x++) {
			for (int y = 0; y < Bomberman.HEIGHT; y++) {
				if ((x == 0) || (x == Bomberman.WIDTH - 1) || (y == 0)
						|| (y == Bomberman.HEIGHT - 1)) {
					grid.setContents(x, y, Tile.CONCRETE);
				}
			}
		}
	}
	
	/**
	 * Place concrete walls whenever the indexes of x and y are both even, starting
	 * at index 2 and ending at index 30.
	 */
	
	private void placeConcreteOnTheMap() {
		
		for (int x = 2; x < Bomberman.WIDTH; x = x + 2) {
			for (int y = 2; y < Bomberman.HEIGHT; y = y + 2) {
				grid.setContents(x, y, Tile.CONCRETE);
			}
		}
	}
	
	/**
	 * Load images of the concrete walls.
	 */

	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(concrete));
		imageConcrete = ii.getImage();
	}
	
	/**
	 * Get the image for concrete walls. 
	 * @return Image for concrete walls.
	 */

	public Image getImage() {
		return imageConcrete;
	}

}