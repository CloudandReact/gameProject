package gameplay;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb implements Runnable {

	
	private int range;
	private int counter;
	
	private int posX;
	private int posY;
	private Cell[][] gridMap;
	
	private String bomb = "bomb.png";
	private String bombPlayer = "bomb&Bomberman.jpg";
	private String bombExploding = "explosion.png";
	
	private Image image;
	private Image image2;
	private Image explode;
	
	long startTime;
	long currentTime;
	
	
		
	public Bomb(){
		loadImage();
	}
	
	public Bomb(int x, int y, Cell[][] z){
		posX = x;
		posY = y;
		gridMap = z;
	}
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(bomb));
		image = ii.getImage();
		
		ImageIcon bombP = new ImageIcon(getClass().getResource(bombPlayer));
		image2 = bombP.getImage();
		
		ImageIcon bombExplode = new ImageIcon(getClass().getResource(bombExploding));
		explode = bombExplode.getImage();
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
	
	public void bombLogic(int posX, int posY, Cell[][] gridMap){

	}

	@Override
	public void run() {

		startTime = System.currentTimeMillis();
		
		while(true){
			if((currentTime = System.currentTimeMillis()) - startTime == 3000){
				System.out.println("StartTime: " + startTime + " CurrentTime: " + currentTime );
				
				gridMap[posX][posY] = Cell.EMPTY;
				
				if(gridMap[posX+1][posY] == Cell.BRICK || gridMap[posX-1][posY] == Cell.ENEMY){
					gridMap[posX+1][posY] = Cell.EXPLODE;
				}
				
				if(gridMap[posX-1][posY] == Cell.BRICK || gridMap[posX-1][posY] == Cell.ENEMY){
					gridMap[posX-1][posY] = Cell.EXPLODE;
				}
				
				if(gridMap[posX][posY+1] == Cell.BRICK || gridMap[posX-1][posY] == Cell.ENEMY){
					gridMap[posX][posY+1] = Cell.EXPLODE;
				}
				
				if(gridMap[posX][posY-1] == Cell.BRICK || gridMap[posX-1][posY] == Cell.ENEMY){
					gridMap[posX][posY-1] = Cell.EXPLODE;
				}
				
				if(gridMap[posX][posY] != Cell.CONCRETE){
					gridMap[posX][posY] = Cell.EXPLODE;
				}
				
				if(gridMap[posX+1][posY] != Cell.CONCRETE){
					gridMap[posX+1][posY] = Cell.EXPLODE;
				}
				
				if(gridMap[posX-1][posY] != Cell.CONCRETE){
					gridMap[posX-1][posY] = Cell.EXPLODE;
				}
				
				if(gridMap[posX][posY+1] != Cell.CONCRETE){
					gridMap[posX][posY+1] = Cell.EXPLODE;
				}
				
				if(gridMap[posX][posY-1] != Cell.CONCRETE){
					gridMap[posX][posY-1] = Cell.EXPLODE;
				}
				
				
				break;
			}
		}
	}
	
}

