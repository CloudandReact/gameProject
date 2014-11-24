package gameplay;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy {
	private String enemy = "enemy.png";
	private Image image;
	Cell[][] gridMap;
	private int dimension = 25;
	private int enemyDirection;
	private static int numberOfEnemies;

	public Enemy(Cell[][] x) {
		loadImage();
		gridMap = x;
		placeEnemies();
		numberOfEnemies = 0;

	}

	private void placeEnemies() {
		for (int i = 2; i <= 31; i++) {
			for (int j = 2; j <= 13; j++) {
				int rand = randInt(1, 55);
				if (rand == 5) {
					if(gridMap[i][j] == Cell.EMPTY){
						gridMap[i][j] = Cell.ENEMY;
						numberOfEnemies++;					
					}
				}
			}		
		}
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
	
	public static int getNumberOfEnemies(){
		return numberOfEnemies;
	}

	public void move() {

		for (int posX = 1; posX < 32; posX++) {
			for (int posY = 1; posY < 14; posY++) {
				if (gridMap[posX][posY] == Cell.ENEMY) {
					int rand = randInt(1, 2);
					if (rand == 1) {
						if (gridMap[posX + enemyDirection][posY] != Cell.BRICK && gridMap[posX + enemyDirection][posY] != Cell.CONCRETE) {
							enemyDirection = 1;
							//System.out.println("X IS POSITIVE");
						} else {
							enemyDirection = -1;
							//System.out.println("X IS NEGATIVE");
						}

						if (gridMap[posX + enemyDirection][posY] == Cell.EMPTY || gridMap[posX + enemyDirection][posY] == Cell.PLAYER) {
							gridMap[posX][posY] = Cell.EMPTY; 
							gridMap[posX + enemyDirection][posY] = Cell.ENEMY;

						}

					}

					else {
						if (gridMap[posX][posY + enemyDirection] != Cell.BRICK
								&& gridMap[posX][posY + enemyDirection] != Cell.CONCRETE) {
							enemyDirection = 1;
							//System.out.println("Y IS POSITIVE");

						} else {
							enemyDirection = -1;
							//System.out.println("Y IS NEGATIVE");

						}
						if (gridMap[posX][posY + enemyDirection] == Cell.EMPTY
								|| gridMap[posX][posY + enemyDirection] == Cell.PLAYER) {
							gridMap[posX][posY] = Cell.EMPTY;
							gridMap[posX][posY + enemyDirection] = Cell.ENEMY;
						}
					}
				}
			}
		}
	}	
}
