package gameplay;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb {

	private int range;
	private int currentRange;
	private int counter;

	private int posX;
	private int posY;

	private String bomb = "bomb.png";
	private String bombPlayer = "bomb&Bomberman.jpg";
	private String bombExploding = "explosion.png";
	private String bombAndExitways = "bombandexitway.png";
	private static int numberOfEnemiesKilled;

	private Image image;
	private Image image2;
	private Image explode;
	private Image bombandexitway;


	
	private Grid grid;
	
	/*
	 * RANGE IS DEFAULTED TO 1 IN CONSTRUCTORS. TO SET RANGE USE SETTER ON BOMB OBJECT. USE THE SAME BOMB OBJECT SUPPLIED TO THE THREAD. 
	 */

	public Bomb(Grid grid) {
		loadImage();
		this.range = 1;
		this.grid = grid;
	}
	
	public void setRange(int range){
		this.range = range;
	}
	
	public void setPosition(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		
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


	public static int getNumberOfEnemiesKilled() {
		return numberOfEnemiesKilled;
	}
	
	public void explode(){
		
		currentRange = 0;
		System.out.println("Explode method");
		
		while (currentRange <= range) {
			System.out.println("PosX is ..:" + posX + "PosY is. ..: " + posY);
			System.out.println(currentRange);
			if (grid.getContents(posX + currentRange,posY) != Cell.CONCRETE) {
				
				if (grid.getContents(posX + currentRange,posY) == Cell.BRICKANDPOWERUPS) {
					grid.setContents(posX + currentRange,posY,Cell.POWERUPS);

				} 
				else if (grid.getContents(posX + currentRange,posY) == Cell.BRICKANDEXITWAY) {
					grid.setContents(posX + currentRange,posY,Cell.EXITWAY);
				} 
				else if (grid.getContents(posX + currentRange,posY) == Cell.EXITWAY) {
					grid.setContents(posX + currentRange,posY,Cell.EXITWAY);
				} 
				else if (grid.getContents(posX + currentRange,posY) == Cell.ENEMY) {

					numberOfEnemiesKilled++;
					System.out.println(numberOfEnemiesKilled);
					Player.setScore(Player.getScore() + 100);
					grid.setContents(posX + currentRange,posY,Cell.EXPLODE);

				} 
				else {
					grid.setContents(posX + currentRange,posY, Cell.EXPLODE);
				}
				
				currentRange++;
			} 
			
			else if(grid.getContents(posX + currentRange,posY) == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}
		
		currentRange = 0;
		
		while (currentRange <= range) {

			if (grid.getContents(posX - currentRange,posY) != Cell.CONCRETE) {
				
				if (grid.getContents(posX - currentRange,posY) == Cell.BRICKANDPOWERUPS) {
					grid.setContents(posX - currentRange,posY,Cell.POWERUPS);

				} 
				else if (grid.getContents(posX - currentRange,posY) == Cell.BRICKANDEXITWAY) {
					grid.setContents(posX - currentRange,posY, Cell.EXITWAY);
				} 
				else if (grid.getContents(posX - currentRange,posY) == Cell.EXITWAY) {
					grid.setContents(posX - currentRange,posY,Cell.EXITWAY);
				} 
				else if (grid.getContents(posX - currentRange,posY) == Cell.ENEMY) {

					numberOfEnemiesKilled++;
					System.out.println(numberOfEnemiesKilled);
					Player.setScore(Player.getScore() + 100);
					grid.setContents(posX - currentRange,posY, Cell.EXPLODE);

				} 
				else {
					grid.setContents(posX - currentRange,posY, Cell.EXPLODE);
				}
				
				currentRange++;
			} 
			
			else if(grid.getContents(posX - currentRange,posY) == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}
		
		currentRange = 0;
		
		while (currentRange <= range) {
			System.out.println("CURRENT RANGE IN Y: ..." + currentRange);
			if (grid.getContents(posX,posY + currentRange) != Cell.CONCRETE) {
				
				if (grid.getContents(posX,posY + currentRange) == Cell.BRICKANDPOWERUPS) {
					grid.setContents(posX,posY + currentRange, Cell.POWERUPS);

				} 
				else if (grid.getContents(posX,posY + currentRange) == Cell.BRICKANDEXITWAY) {
					grid.setContents(posX,posY + currentRange,Cell.EXITWAY);
				} 
				else if (grid.getContents(posX,posY + currentRange) == Cell.EXITWAY) {
					grid.setContents(posX,posY + currentRange,Cell.EXITWAY);
				} 
				else if (grid.getContents(posX,posY + currentRange) == Cell.ENEMY) {

					numberOfEnemiesKilled++;
					System.out.println("Killed one!");
					System.out.println(numberOfEnemiesKilled);
					Player.setScore(Player.getScore() + 100);
					grid.setContents(posX,posY + currentRange,Cell.EXPLODE);

				} 
				else {
					grid.setContents(posX,posY + currentRange,Cell.EXPLODE);
				}
				currentRange++;
			} 
			
			else if(grid.getContents(posX,posY + currentRange) == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}
		
		currentRange = 0;
		
		while (currentRange <= range) {
			if (grid.getContents(posX,posY - currentRange) != Cell.CONCRETE) {
				
				if (grid.getContents(posX,posY - currentRange) == Cell.BRICKANDPOWERUPS) {
					grid.setContents(posX,posY - currentRange,Cell.POWERUPS);

				} 
				else if (grid.getContents(posX,posY - currentRange) == Cell.BRICKANDEXITWAY) {
					grid.setContents(posX,posY - currentRange,Cell.EXITWAY);
				} 
				else if (grid.getContents(posX,posY - currentRange) == Cell.EXITWAY) {
					grid.setContents(posX,posY - currentRange,Cell.EXITWAY);
				} 
				else if (grid.getContents(posX,posY - currentRange) == Cell.ENEMY) {

					numberOfEnemiesKilled++;
					System.out.println("Killed one!");
					System.out.println(numberOfEnemiesKilled);
					Player.setScore(Player.getScore() + 100);
					grid.setContents(posX,posY - currentRange,Cell.EXPLODE);

				} 
				else {
					grid.setContents(posX,posY - currentRange,Cell.EXPLODE);
					System.out.println("HERE");
				}
				
				currentRange++;
			} 
			
			else if(grid.getContents(posX,posY - currentRange) == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the concrete
				break;
			}
		}
			
		currentRange = 0;
	}

}
