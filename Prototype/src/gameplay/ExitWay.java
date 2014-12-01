package gameplay;

import java.awt.Image;
import java.io.Serializable;
import java.util.Random;

import javax.swing.ImageIcon;


/**
 * <p>
 * The <code>ExitWay</code> class stores pseudo-randomly places an exitway on
 * the grid and loads it's image.
 * </p>
 * 
 * @author Rakinul Huq
 * @see Game
 * @see GameState
 * @see Player
 */


public class ExitWay implements Serializable {

	private static final long serialVersionUID = 1L;
	private String imageNameExitway = "exitway.png";
	private String imageNamePlayerAndExitway = "playerandexitway.png";
	private static Boolean exitwayPlaced = false;

	ImageIcon exitway;
	ImageIcon playerandexitway;

	private Grid grid;

	/**
	 * Constructor that initializes a <code>Exitway</code> object which is to be
	 * placed on the grid.
	 * 
	 * @param grid
	 *            Grid on which the exitway is placed.
	 */

	public ExitWay(Grid grid) {
		this.grid = grid;
		loadImage();
		placeExitWay();

	}

	/**
	 * Method that places the ExitWay in a pseudo-random location under a brick.
	 */
	private void placeExitWay() {
		while (!exitwayPlaced) {
			int randX = randInt(2, 30);
			int randY = randInt(2, 12);

			if (grid.getContents(randX, randY) == Tile.EMPTY) {
				grid.setContents(randX, randY, Tile.BRICKANDEXITWAY);
				exitwayPlaced = true;
			}

		}

	}

	/**
	 * Given two integers, min and max, this method will return a pseudo-random
	 * integer value contained between these two integers.
	 * 
	 * @param min
	 *            Minimum value which may be returned.
	 * @param max
	 *            Maximum value which may be returned.
	 * @return An <code>int</code> between min and max inclusively.
	 */

	public static int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	/**
	 * Method that loads images related to ExitWay.
	 */
	private void loadImage() {
		exitway = new ImageIcon(getClass().getResource(imageNameExitway));

		playerandexitway = new ImageIcon(getClass().getResource(imageNamePlayerAndExitway));

	}

	/**
	 * Gets the image of the ExitWay.
	 * 
	 * @return the image corresponding to the exitway.
	 */
	public Image getImageExitway() {
		return exitway.getImage();
	}

	/**
	 * Gets the image of the PLAYERANDEXITWAY.
	 * 
	 * @return the image corresponding to the PLAYERANDEXITWAY.
	 */
	public Image getImagePlayerAndExitway() {
		return playerandexitway.getImage();

	}

}
