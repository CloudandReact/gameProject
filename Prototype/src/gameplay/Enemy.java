package gameplay;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import Astar.*;

public class Enemy implements Mover {
	private String enemy = "enemy.png";
	private Image image;

	private Grid grid;

	private int enemyDirection;
	private static int numberOfEnemies;

	private Path path;
	PathFinder finder;

	public Enemy(Grid grid) {
		loadImage();
		this.grid = grid;
		placeEnemies();
		numberOfEnemies = 0;

	}

	private void placeEnemies() {

		grid.setContents(1, 11, Cell.ENEMY);

		// for (int i = 2; i < Bomberman.WIDTH; i++) {
		// for (int j = 2; j < Bomberman.HEIGHT; j++) {
		// int rand = randInt(1, 55);
		// if (rand == 5) {
		// if(grid.getContents(i,j) == Cell.EMPTY){
		// grid.setContents(i, j, Cell.ENEMY);
		// numberOfEnemies++;
		// }
		// }
		// }
		// }

		System.out.println("Number of Enemies..: " + numberOfEnemies);

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

	public static int getNumberOfEnemies() {
		return numberOfEnemies;
	}

	//
	// public void move(){
	// Grid grid = null;
	// AStarPathFinder finder = new AStarPathFinder(grid);
	//
	// }

	public void aStarMovement(int targetX, int targetY) {
		finder = new AStarPathFinder(grid, 2);

		for (int posX = 1; posX < Bomberman.WIDTH - 1; posX++) {
			for (int posY = 1; posY < Bomberman.HEIGHT - 1; posY++) {
				if (grid.getContents(posX, posY) == Cell.ENEMY) {

	 				path = finder.findPath(this, posX, posY, targetX, targetY);

					if (path != null) {

						grid.setContents(posX, posY, Cell.EMPTY);
						// System.out.println("Player position: X: " + targetX +
						// " Y: " + targetY + " xTarget IS: " + path.getX(1) +
						// " yTarget IS: " + path.getY(1));
						grid.setContents(path.getX(1), path.getY(1), Cell.ENEMY);
						path = null;
						
						
						// ENEMYMOVE

						
					}

					else {
						//System.out.println("Path is null");
					}
				}
			}
		}

	}

	public void move() {

		for (int posX = 1; posX < Bomberman.WIDTH - 1; posX++) {
			for (int posY = 1; posY < Bomberman.HEIGHT - 1; posY++) {
				if (grid.getContents(posX, posY) == Cell.ENEMY) {
					int rand = randInt(1, 2);
					if (rand == 1) {
						if (grid.getContents(posX + enemyDirection, posY) != Cell.BRICK
								&& grid.getContents(posX + enemyDirection, posY) != Cell.CONCRETE) {
							enemyDirection = 1;
							// System.out.println("X IS POSITIVE");
						} else {
							enemyDirection = -1;
							// System.out.println("X IS NEGATIVE");
						}

						if (grid.getContents(posX + enemyDirection, posY) == Cell.EMPTY
								|| grid.getContents(posX + enemyDirection, posY) == Cell.PLAYER) {
							grid.setContents(posX, posY, Cell.EMPTY);
							grid.setContents(posX + enemyDirection, posY,
									Cell.ENEMY);
						}

					}

					else {
						if (grid.getContents(posX, posY + enemyDirection) != Cell.BRICK
								&& grid.getContents(posX, posY + enemyDirection) != Cell.CONCRETE) {

							enemyDirection = 1;
							// System.out.println("Y IS POSITIVE");

						} else {
							enemyDirection = -1;
							// System.out.println("Y IS NEGATIVE");

						}
						if (grid.getContents(posX, posY + enemyDirection) == Cell.EMPTY
								|| grid.getContents(posX, posY + enemyDirection) == Cell.PLAYER) {
							grid.setContents(posX, posY, Cell.EMPTY);
							grid.setContents(posX, posY + enemyDirection,
									Cell.ENEMY);
						}
					}
				}
			}
		}
	}
}
