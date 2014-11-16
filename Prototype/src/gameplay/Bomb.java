package gameplay;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb {

	private int range;
	private int counter;
	private int posX;
	private int posY;
	
	private String bomb = "bomb.png";
	private String bombPlayer = "bomb&Bomberman.jpg";
	
	private Image image;
	private Image image2;
	
	long startTime;
	long currentTime;
	
	
		
	public Bomb(){
		loadImage();
	}
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(bomb));
		image = ii.getImage();
		
		ImageIcon bombP = new ImageIcon(getClass().getResource(bombPlayer));
		image2 = bombP.getImage();
	}
	
	public Image getImageBomb() {
		return image;
	}
	
	public Image getImageBombPlayer(){
		return image2;
	}
	
	public void bombLogic(int posX, int posY, Cell[][] gridMap){
		startTime = System.currentTimeMillis();
		
		while(true){
			if((currentTime = System.currentTimeMillis()) - startTime == 3000){
				System.out.println("StartTime: " + startTime + " CurrentTime: " + currentTime );
				
				gridMap[posX][posY] = Cell.EMPTY;
				
				if(gridMap[posX+1][posY] == Cell.BRICK){
					gridMap[posX+1][posY] = Cell.EMPTY;
				}
				
				if(gridMap[posX-1][posY] == Cell.BRICK){
					gridMap[posX-1][posY] = Cell.EMPTY;
				}
				
				if(gridMap[posX][posY+1] == Cell.BRICK){
					gridMap[posX][posY+1] = Cell.EMPTY;
				}
				
				if(gridMap[posX][posY-1] == Cell.BRICK){
					gridMap[posX][posY-1] = Cell.EMPTY;
				}
				
				break;
			}
		}
	}
	
}

