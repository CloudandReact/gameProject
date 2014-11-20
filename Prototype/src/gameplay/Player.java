package gameplay;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

	private String player = "bomberman.png";

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int posX = 1;
	private int posY = 1;
	
	private Image image;
	Cell[][] gridMap;
	private Bomb bomb; 
	private GameState gameState;


	public Player(Cell[][] gridMap, GameState x) {
		loadImage();
		this.setX(25);
		this.setY(25);
		this.gridMap = gridMap;
		this.gridMap[1][1] = Cell.PLAYER;
		gameState = x;
	}

	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(player));
		image = ii.getImage();
	}

	public void move() {

		if (dx > 0) {
			if (gridMap[posX + (dx / 25)][posY] == Cell.EMPTY 
					|| gridMap[posX + (dx / 25)][posY] == Cell.POWERUPS 
					|| gridMap[posX + (dx / 25)][posY] == Cell.EXITWAY
					) {
				
				if (gridMap[posX][posY] == Cell.PLAYERANDBOMB) {
					gridMap[posX][posY] = Cell.BOMB;
				}
				else if (gridMap[posX][posY] == Cell.EXITWAY) {
					gridMap[posX][posY] = Cell.PLAYERANDEXITWAY;
				}
				else if (gridMap[posX][posY] == Cell.PLAYERANDEXITWAY) {
					gridMap[posX][posY] = Cell.EXITWAY;
				}
				else {
					gridMap[posX][posY] = Cell.EMPTY;
				}
				posX++;
				if(gridMap[posX][posY] == Cell.EXITWAY){
					gridMap[posX][posY] = Cell.PLAYERANDEXITWAY;
				}
				else{
				gridMap[posX][posY] = Cell.PLAYER;
				}
				x += dx;
			}
		}
		if (dx < 0) {
			if (gridMap[posX + (dx / 25)][posY] == Cell.EMPTY 
					|| gridMap[posX + (dx / 25)][posY] == Cell.POWERUPS
					|| gridMap[posX + (dx / 25)][posY] == Cell.EXITWAY
					) {
				
				if (gridMap[posX][posY] == Cell.PLAYERANDBOMB) {
					gridMap[posX][posY] = Cell.BOMB;
				}
				else if (gridMap[posX][posY] == Cell.EXITWAY) {
					gridMap[posX][posY] = Cell.PLAYERANDEXITWAY;
				}
				else if (gridMap[posX][posY] == Cell.PLAYERANDEXITWAY) {
					gridMap[posX][posY] = Cell.EXITWAY;
				}
				else {
					gridMap[posX][posY] = Cell.EMPTY;
				}
				posX--;
				if(gridMap[posX][posY] == Cell.EXITWAY){
					gridMap[posX][posY] = Cell.PLAYERANDEXITWAY;
				}
				else{
				gridMap[posX][posY] = Cell.PLAYER;
				}
				x += dx;
			}

		}

		if (dy > 0) {
			if (gridMap[posX][posY + (dy / 25)] == Cell.EMPTY 
					|| gridMap[posX][posY + (dy / 25)] == Cell.POWERUPS
					||gridMap[posX][posY + (dy / 25)] == Cell.EXITWAY
					) {
				if (gridMap[posX][posY] == Cell.PLAYERANDBOMB) {
					gridMap[posX][posY] = Cell.BOMB;
				}
				else if (gridMap[posX][posY] == Cell.EXITWAY) {
					gridMap[posX][posY] = Cell.PLAYERANDEXITWAY;
				}
				else if (gridMap[posX][posY] == Cell.PLAYERANDEXITWAY) {
					gridMap[posX][posY] = Cell.EXITWAY;
				}
				else {
					gridMap[posX][posY] = Cell.EMPTY;
				}
				posY++;
				if(gridMap[posX][posY] == Cell.EXITWAY){
					gridMap[posX][posY] = Cell.PLAYERANDEXITWAY;
				}
				else{
				gridMap[posX][posY] = Cell.PLAYER;
				}
				y += dy;
			}
		}
		if (dy < 0) {
			if (gridMap[posX][posY + (dy / 25)] == Cell.EMPTY 
					|| gridMap[posX][posY + (dy / 25)] == Cell.POWERUPS
					|| gridMap[posX][posY + (dy / 25)] == Cell.EXITWAY
					) {
				if (gridMap[posX][posY] == Cell.PLAYERANDBOMB) {
					gridMap[posX][posY] = Cell.BOMB;
				}
				else if (gridMap[posX][posY] == Cell.EXITWAY) {
					gridMap[posX][posY] = Cell.PLAYERANDEXITWAY;
				}
				else if (gridMap[posX][posY] == Cell.PLAYERANDEXITWAY) {
					gridMap[posX][posY] = Cell.EXITWAY;
				}
				else {
					gridMap[posX][posY] = Cell.EMPTY;
				}
				posY--;
				if(gridMap[posX][posY] == Cell.EXITWAY){
					gridMap[posX][posY] = Cell.PLAYERANDEXITWAY;
				}
				else {
				gridMap[posX][posY] = Cell.PLAYER;
				}
				y += dy;
			}

		}

		

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int xNew) {
		x = xNew;
	}

	public void setY(int yNew) {
		y = yNew;
	}

	public Image getImage() {
		return image;
	}
	

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (dx != 0 || dy != 0) {
			return;
		}
		
		if (key == KeyEvent.VK_ESCAPE){
			if(gameState.getState() == State.RUNNING){
				gameState.setState(State.PAUSE);		
			}
			else{
				gameState.setState(State.RUNNING);
			}
			System.out.println("STATE: " + gameState.getState());
		}
		
		if (key == KeyEvent.VK_X){
			if(gridMap[posX][posY] != Cell.PLAYERANDBOMB){
				System.out.println("BOMBAMAN<>BOMBAMAN FRENLY NEIGBOHUD BOMBAMAN");
				gridMap[posX][posY] = Cell.PLAYERANDBOMB;
				
				bomb = new Bomb(posX, posY, gridMap);
				Thread t = new Thread(bomb);
		        t.start();
			}

		}

		if (key == KeyEvent.VK_LEFT) {
			dx = -25;
		}

		else if (key == KeyEvent.VK_RIGHT) {
			dx = 25;
		}

		else if (key == KeyEvent.VK_UP) {
			dy = -25;
		}

		else if (key == KeyEvent.VK_DOWN) {
			dy = 25;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_X){
			
		}
		if (key == KeyEvent.VK_ESCAPE){
		
		}

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

}
