package gameplay;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb implements Runnable {

	private int range;
	private int currentRange;
	private int counter;

	private int posX;
	private int posY;
	private Cell[][] gridMap;

	private String bomb = "bomb.png";
	private String bombPlayer = "bomb&Bomberman.jpg";
	private String bombExploding = "explosion.png";
	private String bombAndExitways = "bombandexitway.png";
	private static int numberOfEnemiesKilled;

	private Image image;
	private Image image2;
	private Image explode;
	private Image bombandexitway;

	Player player;
	long startTime;
	long currentTime;
	
	/*
	 * RANGE IS DEFAULTED TO 1 IN CONSTRUCTORS. TO SET RANGE USE SETTER ON BOMB OBJECT. USE THE SAME BOMB OBJECT SUPPLIED TO THE THREAD. 
	 */

	public Bomb() {
		loadImage();
		this.range = 1;
	}

	public Bomb(int posX, int posY, Cell[][] gridMap, Player player) {
		this.gridMap = gridMap;
		this.player = player;
		this.posX = posX;
		this.posY = posY;
		this.range = 1;
		this.currentRange = 0;
	}
	
	public void setRange(int range){
		this.range = range;
	}

	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(bomb));
		image = ii.getImage();

		ImageIcon bombP = new ImageIcon(getClass().getResource(bombPlayer));
		image2 = bombP.getImage();

		ImageIcon bombExplode = new ImageIcon(getClass().getResource(
				bombExploding));
		explode = bombExplode.getImage();

		ImageIcon bombAndExitway = new ImageIcon(getClass().getResource(
				bombAndExitways));
		bombandexitway = bombAndExitway.getImage();
	}

	public Image getImageBomb() {
		return image;
	}

	public Image getImageBombPlayer() {
		return image2;
	}

	public Image getImageBombExplode() {
		return explode;
	}

	public Image getImageBombAndExitway() {
		return bombandexitway;
	}

	public void bombLogic(int posX, int posY, Cell[][] gridMap) {

	}

	public static int getNumberOfEnemiesKilled() {
		return numberOfEnemiesKilled;
	}

	@Override
	public void run() {

		startTime = System.currentTimeMillis();

		currentRange = 0;
		// wait before exploding
		while(true){
			if ((currentTime = System.currentTimeMillis()) - startTime == 2000) {
				System.out.println("StartTime: " + startTime + " CurrentTime: "+ currentTime);
				break;
			}
		}
		
			
		while (currentRange <= range) {

			if (gridMap[posX + currentRange][posY] != Cell.CONCRETE) {
				
				if (gridMap[posX + currentRange][posY] == Cell.BRICKANDPOWERUPS) {
					gridMap[posX + currentRange][posY] = Cell.POWERUPS;

				} 
				else if (gridMap[posX + currentRange][posY] == Cell.BRICKANDEXITWAY) {
					gridMap[posX + currentRange][posY] = Cell.EXITWAY;
				} 
				else if (gridMap[posX + currentRange][posY] == Cell.EXITWAY) {
					gridMap[posX + currentRange][posY] = Cell.EXITWAY;
				} 
				else if (gridMap[posX + currentRange][posY] == Cell.ENEMY) {

					numberOfEnemiesKilled++;
					System.out.println(numberOfEnemiesKilled);
					Player.setScore(Player.getScore() + 100);
					gridMap[posX + currentRange][posY] = Cell.EXPLODE;

				} 
				else {
					gridMap[posX + currentRange][posY] = Cell.EXPLODE;
				}
				
				currentRange++;
			} 
			
			else if(gridMap[posX + currentRange][posY] == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}
		
		currentRange = 0;
		
		while (currentRange <= range) {

			if (gridMap[posX - currentRange][posY] != Cell.CONCRETE) {
				
				if (gridMap[posX - currentRange][posY] == Cell.BRICKANDPOWERUPS) {
					gridMap[posX - currentRange][posY] = Cell.POWERUPS;

				} 
				else if (gridMap[posX - currentRange][posY] == Cell.BRICKANDEXITWAY) {
					gridMap[posX - currentRange][posY] = Cell.EXITWAY;
				} 
				else if (gridMap[posX - currentRange][posY] == Cell.EXITWAY) {
					gridMap[posX - currentRange][posY] = Cell.EXITWAY;
				} 
				else if (gridMap[posX - currentRange][posY] == Cell.ENEMY) {

					numberOfEnemiesKilled++;
					System.out.println(numberOfEnemiesKilled);
					Player.setScore(Player.getScore() + 100);
					gridMap[posX - currentRange][posY] = Cell.EXPLODE;

				} 
				else {
					gridMap[posX - currentRange][posY] = Cell.EXPLODE;
				}
				
				currentRange++;
			} 
			
			else if(gridMap[posX - currentRange][posY] == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}
		
		currentRange = 0;
		
		while (currentRange <= range) {
			System.out.println("CURRENT RANGE IN Y: ..." + currentRange);
			if (gridMap[posX][posY + currentRange] != Cell.CONCRETE) {
				
				if (gridMap[posX][posY + currentRange] == Cell.BRICKANDPOWERUPS) {
					gridMap[posX][posY + currentRange] = Cell.POWERUPS;

				} 
				else if (gridMap[posX][posY + currentRange] == Cell.BRICKANDEXITWAY) {
					gridMap[posX][posY + currentRange] = Cell.EXITWAY;
				} 
				else if (gridMap[posX][posY + currentRange] == Cell.EXITWAY) {
					gridMap[posX][posY + currentRange] = Cell.EXITWAY;
				} 
				else if (gridMap[posX][posY + currentRange] == Cell.ENEMY) {

					numberOfEnemiesKilled++;
					System.out.println("Killed one!");
					System.out.println(numberOfEnemiesKilled);
					Player.setScore(Player.getScore() + 100);
					gridMap[posX][posY + currentRange] = Cell.EXPLODE;

				} 
				else {
					gridMap[posX][posY + currentRange] = Cell.EXPLODE;
				}
				currentRange++;
			} 
			
			else if(gridMap[posX][posY + currentRange] == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}
		
		currentRange = 0;
		
		while (currentRange <= range) {
			if (gridMap[posX][posY - currentRange] != Cell.CONCRETE) {
				
				if (gridMap[posX][posY - currentRange] == Cell.BRICKANDPOWERUPS) {
					gridMap[posX][posY - currentRange] = Cell.POWERUPS;

				} 
				else if (gridMap[posX][posY - currentRange] == Cell.BRICKANDEXITWAY) {
					gridMap[posX][posY - currentRange] = Cell.EXITWAY;
				} 
				else if (gridMap[posX][posY - currentRange] == Cell.EXITWAY) {
					gridMap[posX][posY - currentRange] = Cell.EXITWAY;
				} 
				else if (gridMap[posX][posY - currentRange] == Cell.ENEMY) {

					numberOfEnemiesKilled++;
					System.out.println("Killed one!");
					System.out.println(numberOfEnemiesKilled);
					Player.setScore(Player.getScore() + 100);
					gridMap[posX][posY - currentRange] = Cell.EXPLODE;

				} 
				else {
					gridMap[posX][posY - currentRange] = Cell.EXPLODE;
				}
				currentRange++;
			} 
			
			else if(gridMap[posX][posY - currentRange] == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the concrete
				break;
			}
		}
			
		player.setBombStatus(false);
		currentRange = 0;
				
	}

}
