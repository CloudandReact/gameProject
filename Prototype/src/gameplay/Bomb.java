package gameplay;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb extends Player implements Runnable  {

	
	private int range;
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
	
	long startTime;
	long currentTime;
	
	
		
	public Bomb(){
		loadImage();
		range = 1;
	}
	
	public Bomb(int x, int y, Cell[][] z){
		posX = x;
		posY = y;
		gridMap = z;
		range = 1;
	}
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(bomb));
		image = ii.getImage();
		
		ImageIcon bombP = new ImageIcon(getClass().getResource(bombPlayer));
		image2 = bombP.getImage();
		
		ImageIcon bombExplode = new ImageIcon(getClass().getResource(bombExploding));
		explode = bombExplode.getImage();
		
		ImageIcon bombAndExitway = new ImageIcon(getClass().getResource(bombAndExitways));
		bombandexitway = bombAndExitway .getImage();
	}
	
	public Image getImageBomb() {
		return image;
	}
	
	public Image getImageBombPlayer(){
		return image2;
	}
	
	public Image getImageBombExplode(){
		return explode;
	}
	
	public Image getImageBombAndExitway(){
		return bombandexitway;
	}
	
	public void bombLogic(int posX, int posY, Cell[][] gridMap){

	}
	
	public static int getNumberOfEnemiesKilled(){
		return numberOfEnemiesKilled;
	}

	@Override
	public void run() {

		startTime = System.currentTimeMillis();
		
		while(range >= 0){
			
			//if range>1 we'll get array out of bounds at the edges (i.e. trying to check gridMap[-1]->nogood
			if((currentTime = System.currentTimeMillis()) - startTime == 1000){
				System.out.println("StartTime: " + startTime + " CurrentTime: " + currentTime );
				
				//gridMap[posX][posY] = Cell.EMPTY;
				
				if(gridMap[posX][posY] != Cell.CONCRETE){
					gridMap[posX][posY] = Cell.EXPLODE;
				}
				
				if(gridMap[posX+range][posY] != Cell.CONCRETE){

					if(gridMap[posX+range][posY] == Cell.BRICKANDPOWERUPS){
						gridMap[posX+range][posY] = Cell.POWERUPS;
					}
					else if(gridMap[posX+range][posY] == Cell.BRICKANDEXITWAY){
						gridMap[posX+range][posY] = Cell.EXITWAY;
					}
					else if(gridMap[posX+range][posY] == Cell.EXITWAY){
						gridMap[posX+range][posY] = Cell.EXITWAY;
					}
					else if(gridMap[posX+range][posY] == Cell.ENEMY){
							numberOfEnemiesKilled++;
							System.out.println("Killed one!");
							System.out.println(numberOfEnemiesKilled);
							Player.setScore(Player.getScore() + 100);					
							gridMap[posX+range][posY] = Cell.EXPLODE;

					}
					else{
						gridMap[posX+range][posY] = Cell.EXPLODE;
					}


				}
				
				if(gridMap[posX-range][posY] != Cell.CONCRETE){
					if(gridMap[posX-range][posY] == Cell.BRICKANDPOWERUPS){
						gridMap[posX-range][posY] = Cell.POWERUPS;
					}
					else if(gridMap[posX-range][posY] == Cell.BRICKANDEXITWAY){
						gridMap[posX-range][posY] = Cell.EXITWAY;
					}
					else if(gridMap[posX-range][posY] == Cell.EXITWAY){
						gridMap[posX-range][posY] = Cell.EXITWAY;
					}
					else if(gridMap[posX-range][posY] == Cell.ENEMY){
							numberOfEnemiesKilled++;
							System.out.println("Killed one!");
							System.out.println(numberOfEnemiesKilled);
							Player.setScore(Player.getScore() + 100);	
							gridMap[posX-range][posY] = Cell.EXPLODE;
					}
					else{
						gridMap[posX-range][posY] = Cell.EXPLODE;
					}

				
				}
				
				if(gridMap[posX][posY+range] != Cell.CONCRETE){
					if(gridMap[posX][posY+range] == Cell.BRICKANDPOWERUPS){
						gridMap[posX][posY+range] = Cell.POWERUPS;
					}
					else if(gridMap[posX][posY+range] == Cell.BRICKANDEXITWAY){
						gridMap[posX][posY+range] = Cell.EXITWAY;
					}
					else if(gridMap[posX][posY+range] == Cell.EXITWAY){
						gridMap[posX][posY+range] = Cell.EXITWAY;
					}
					else if(gridMap[posX][posY+range] == Cell.ENEMY){
							numberOfEnemiesKilled++;
							System.out.println("Killed one!");
							System.out.println(numberOfEnemiesKilled);
							Player.setScore(Player.getScore() + 100);
							gridMap[posX][posY+range] = Cell.EXPLODE;

					}
					else{
						gridMap[posX][posY+range] = Cell.EXPLODE;
					}
					
				}
				
				if(gridMap[posX][posY-range] != Cell.CONCRETE){
					if(gridMap[posX][posY-range] == Cell.BRICKANDPOWERUPS){
						gridMap[posX][posY-range] = Cell.POWERUPS;
					}
					else if(gridMap[posX][posY-range] == Cell.BRICKANDEXITWAY){
						gridMap[posX][posY-range] = Cell.EXITWAY;
					}
					else if(gridMap[posX][posY-range] == Cell.EXITWAY){
						gridMap[posX][posY-range] = Cell.EXITWAY;
					}
					else if(gridMap[posX][posY-range] == Cell.ENEMY){
							numberOfEnemiesKilled++;
							System.out.println("Killed one!");
							System.out.println(numberOfEnemiesKilled);
							Player.setScore(Player.getScore() + 100);
							gridMap[posX][posY-range] = Cell.EXPLODE;

					}
					else{
						gridMap[posX][posY-range] = Cell.EXPLODE;
					}
					
				}
						
				
				range--;
				
			}
			
		}
		
		range = 1;
		
	}
	
}

