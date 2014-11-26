package gameplay;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import Astar.*;

public class Enemy implements Mover {
	private String enemy = "Balloom.png";
	private Image image;

	private Grid grid;
	private Grid tempGrid;
	
	private Render render;	
	private Level level;
	
	private EnemyTracker enemyTracker;
	
	private static int enemyCount;
	
	private int nEnemy1;
	private int nEnemy2;
	private int nEnemy3;
	private int nEnemy4;
	private int nEnemy5;
	private int nEnemy6;
	private int nEnemy7;
	private int nEnemy8;
	
	
	
	private Path path;
	PathFinder finder;

	public Enemy(Grid grid, Render render, Level level) {
		this.level = level;
		enemyCount = 0;
		this.render = render;
		loadImage();
		this.grid = grid;
		this.tempGrid = new Grid();
		placeEnemies();
		
		this.nEnemy1 = level.getnEnemy1();
		this.nEnemy2 = level.getnEnemy2();
		this.nEnemy3 = level.getnEnemy3();
		this.nEnemy4 = level.getnEnemy4();
		this.nEnemy5 = level.getnEnemy5();
		this.nEnemy6 = level.getnEnemy6();
		this.nEnemy7 = level.getnEnemy7();
		this.nEnemy8 = level.getnEnemy8();
		

		

	}

	private void placeEnemies() {

		
		grid.setContents(1,11,Cell.ENEMY);
		grid.setContents(2,11,Cell.ENEMY);
		
		System.out.println("Number of Enemies..: " + enemyCount);

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

	public static int getEnemyCount() {
		return enemyCount;
	}

	
	public void copyGrid(){
		for (int posX = 0; posX < Bomberman.WIDTH; posX++) {
			for (int posY = 0; posY < Bomberman.HEIGHT; posY++) {
				tempGrid.setContents(posX,posY,grid.getContents(posX,posY));
			}
		}		
	}
	
	public void move(int targetX, int targetY){
		copyGrid();
		
		
		
		for (int posX = 1; posX < Bomberman.WIDTH - 1; posX++) {
			for (int posY = 1; posY < Bomberman.HEIGHT - 1; posY++) {
				
				switch(tempGrid.getContents(posX,posY)) {
				case ENEMY: // Call move method for this enemy
					continue;
					
				default:
					break;
				}		
					
			}
		}
		
		
	}

	public void aStarMovement(int targetX, int targetY) {
		
		finder = new AStarPathFinder(grid, 3);
		
		
		// Create the needed enemy types, when we find an enemy on the grid we call the appropriate move method.. Kappa
		
		copyGrid();				

		for (int posX = 1; posX < Bomberman.WIDTH - 1; posX++) {
			for (int posY = 1; posY < Bomberman.HEIGHT - 1; posY++) {
				if (tempGrid.getContents(posX, posY) == Cell.ENEMY) {

					path = finder.findPath(this, posX, posY, targetX, targetY);

					if (path != null) {

						grid.setContents(posX, posY, Cell.EMPTY);
						// System.out.println("Player position: X: " + targetX +
						// " Y: " + targetY + " xTarget IS: " + path.getX(1) +
						// " yTarget IS: " + path.getY(1));
						
						if(grid.getContents(path.getX(1), path.getY(1)) == Cell.PLAYER){
							grid.setContents(path.getX(1), path.getY(1), Cell.ENEMY);
							GameState.setState(State.PLAYERDEAD);
							render.setIsPlayerAlive(false);
						}
						
						grid.setContents(path.getX(1), path.getY(1), Cell.ENEMY);
						path = null;

						// ENEMYMOVE

					}

					else {
						// System.out.println("Path is null");
					}
				}
			}
		}
		
		

	}

//	public void move() {
//
////		for (int posX = 1; posX < Bomberman.WIDTH - 1; posX++) {
////			for (int posY = 1; posY < Bomberman.HEIGHT - 1; posY++) {
////				if (grid.getContents(posX, posY) == Cell.ENEMY) {
////					int rand = randInt(1, 2);
////					if (rand == 1) {
////						if (grid.getContents(posX + enemyDirection, posY) != Cell.BRICK
////								&& grid.getContents(posX + enemyDirection, posY) != Cell.CONCRETE) {
////							enemyDirection = 1;
////							// System.out.println("X IS POSITIVE");
////						} else {
////							enemyDirection = -1;
////							// System.out.println("X IS NEGATIVE");
////						}
////
////						if (grid.getContents(posX + enemyDirection, posY) == Cell.EMPTY
////								|| grid.getContents(posX + enemyDirection, posY) == Cell.PLAYER) {
////							grid.setContents(posX, posY, Cell.EMPTY);
////							grid.setContents(posX + enemyDirection, posY,
////									Cell.ENEMY);
////						}
////
////					}
////
////					else {
////						if (grid.getContents(posX, posY + enemyDirection) != Cell.BRICK
////								&& grid.getContents(posX, posY + enemyDirection) != Cell.CONCRETE) {
////
////							enemyDirection = 1;
////							// System.out.println("Y IS POSITIVE");
////
////						} else {
////							enemyDirection = -1;
////							// System.out.println("Y IS NEGATIVE");
////
////						}
////						if (grid.getContents(posX, posY + enemyDirection) == Cell.EMPTY
////								|| grid.getContents(posX, posY + enemyDirection) == Cell.PLAYER) {
////							grid.setContents(posX, posY, Cell.EMPTY);
////							grid.setContents(posX, posY + enemyDirection,
////									Cell.ENEMY);
////						}
////					}
////				}
////			}
//		}
//	}
}
