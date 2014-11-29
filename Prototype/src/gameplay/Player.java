package gameplay;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

	private String playerImage = "bomberman.png";

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int posX;
	private int posY;
	private static int score;
	private static int livesLeft;
	private int movementSpeed;

	
	private int range;
	private int bombs;
	private static int bombsOnGround;
	
	
	private int countForMovementSpeed;

	private Image image;
	private Grid grid;
	private Bomb bomb;

	private long startTime;
	private PowerUps powerup;
	private Boolean isBombPlaced;

	public Player() { 

	}

	public Player(Grid grid, Bomb bomb, PowerUps powerup) {
		movementSpeed = 2;
		livesLeft = 2;
		loadImage();
		this.setX(25);
		this.setY(25);
		this.posX = 1;
		this.posY = 1;
		this.grid = grid;
		this.bomb = bomb;
		this.grid.setContents(1, 1, Cell.PLAYER);
		setScore(0);
		this.isBombPlaced = false;
		this.powerup = powerup;
		this.range = 1;
		this.bombs = 2;
		this.bombsOnGround = 0;
	}
	
//	public void exitWayLogic(int posX, int posY){
//		
//		if(grid.getContents(posX, posY) == Cell.EXITWAY && GameState.getState() == State.RUNNINGANDLEVELOVER){
//			level.loadnextLevel();
//		}
//		
//	}
	public void move() {
		if(countForMovementSpeed == movementSpeed){	
			if (dx != 0) {		
				//exitWayLogic(dx/25, 0);
			
				
				// Player dies if walking into enemy
				if (grid.getContents(posX + (dx / 25), posY) == Cell.BALLOOM
						|| grid.getContents(posX + (dx / 25), posY) == Cell.ONEAL
						|| grid.getContents(posX + (dx / 25), posY) == Cell.DOLL
						|| grid.getContents(posX + (dx / 25), posY) == Cell.MINVO
						|| grid.getContents(posX + (dx / 25), posY) == Cell.KONDORIA
						|| grid.getContents(posX + (dx / 25), posY) == Cell.OVAPI
						|| grid.getContents(posX + (dx / 25), posY) == Cell.PASS
						|| grid.getContents(posX + (dx / 25), posY) == Cell.PONTAN
						|| grid.getContents(posX + (dx / 25), posY) == Cell.KONDORIAANDBRICK
						|| grid.getContents(posX + (dx / 25), posY) == Cell.OVAPIANDBRICK
						|| grid.getContents(posX + (dx / 25), posY) == Cell.PONTANANDBRICK) {
					grid.setContents(posX, posY, Cell.EMPTY);
				}

				//
				if (grid.getContents(posX + (dx / 25), posY) == Cell.EMPTY
						|| grid.getContents(posX + (dx / 25), posY) == Cell.POWERUPS
						|| grid.getContents(posX + (dx / 25), posY) == Cell.EXITWAY
						|| (grid.getContents(posX + (dx / 25), posY) == Cell.BOMB && PowerUps.getBombpass() == true)
						|| (grid.getContents(posX + (dx / 25), posY) == Cell.BRICK && PowerUps.getWallpass() == true)
						|| (grid.getContents(posX + (dx / 25), posY) == Cell.BRICKANDEXITWAY && PowerUps.getWallpass() == true)
						|| (grid.getContents(posX + (dx / 25), posY) == Cell.BRICKANDPOWERUPS && PowerUps.getWallpass() == true)) {

					if (grid.getContents(posX, posY) == Cell.PLAYERANDBOMB) {
						grid.setContents(posX, posY, Cell.BOMB);

					}
					else if (grid.getContents(posX, posY) == Cell.PLAYERANDBRICK) {
						grid.setContents(posX, posY, Cell.BRICK);
					}
					else if (grid.getContents(posX, posY) == Cell.PLAYERANDBRICKANDEXITWAY) {
						grid.setContents(posX, posY, Cell.BRICK);
					}
					else if (grid.getContents(posX, posY) == Cell.PLAYERANDBRICKANDPOWERUPS) {
						grid.setContents(posX, posY, Cell.BRICKANDPOWERUPS);
					}
					else if (grid.getContents(posX, posY) == Cell.PLAYERANDBOMB) {
						grid.setContents(posX, posY, Cell.BOMB);
					}
					else if (grid.getContents(posX, posY) == Cell.PLAYERANDEXITWAY) {
						grid.setContents(posX, posY, Cell.EXITWAY);
					}

					else {
						grid.setContents(posX, posY, Cell.EMPTY);
					}

					if (dx < 0) {
						posX--;
					} else {
						posX++;
					}

					x += dx;

					if (grid.getContents(posX, posY) == Cell.EXITWAY) {
						grid.setContents(posX, posY, Cell.PLAYERANDEXITWAY);

					} else if(grid.getContents(posX, posY) == Cell.BOMB && PowerUps.getBombpass() == true){
						grid.setContents(posX, posY, Cell.PLAYERANDBOMB);
					}
					else if(grid.getContents(posX, posY) == Cell.BRICK && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Cell.PLAYERANDBRICK);
					}
					else if(grid.getContents(posX, posY) == Cell.BRICKANDEXITWAY && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Cell.PLAYERANDBRICKANDEXITWAY);
					}
					else if(grid.getContents(posX, posY) == Cell.BRICKANDPOWERUPS && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Cell.PLAYERANDBRICKANDPOWERUPS);
					}
					else if(grid.getContents(posX, posY) == Cell.POWERUPS){
						grid.setContents(posX, posY, Cell.PLAYER);
						powerup.setGotPowerup(true);
						System.out.println("Got the powerup!!!!!!");
						powerup.givePowerUp();
					}
					
					else {
						grid.setContents(posX, posY, Cell.PLAYER);

					}

				}
			}

			if (dy != 0) {
				
				//exitWayLogic(0, dy/25);

				// Player dies if walking into enemy
				if (grid.getContents(posX, posY + (dy / 25)) == Cell.BALLOOM
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.ONEAL
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.DOLL
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.MINVO
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.KONDORIA
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.OVAPI
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.PASS
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.PONTAN
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.KONDORIAANDBRICK
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.OVAPIANDBRICK
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.PONTANANDBRICK) {
					grid.setContents(posX, posY, Cell.EMPTY);
					
				}
				//
				if (grid.getContents(posX, posY + (dy / 25)) == Cell.EMPTY
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.POWERUPS
						|| grid.getContents(posX, posY + (dy / 25)) == Cell.EXITWAY
						|| (grid.getContents(posX, posY + (dy / 25)) == Cell.BOMB && PowerUps.getBombpass() == true)
						|| (grid.getContents(posX, posY + (dy / 25)) == Cell.BRICK && PowerUps.getWallpass() == true)
						|| (grid.getContents(posX, posY + (dy / 25)) == Cell.BRICKANDEXITWAY && PowerUps.getWallpass() == true)
						|| (grid.getContents(posX, posY + (dy / 25)) == Cell.BRICKANDPOWERUPS && PowerUps.getWallpass() == true)) {

					if (grid.getContents(posX, posY) == Cell.PLAYERANDBOMB) {
						grid.setContents(posX, posY, Cell.BOMB);

					}
					else if (grid.getContents(posX, posY) == Cell.PLAYERANDBRICK) {
						grid.setContents(posX, posY, Cell.BRICK);
					}
					else if (grid.getContents(posX, posY) == Cell.PLAYERANDBRICKANDEXITWAY) {
						grid.setContents(posX, posY, Cell.BRICKANDEXITWAY);
					}
					else if (grid.getContents(posX, posY) == Cell.PLAYERANDBRICKANDPOWERUPS) {
						grid.setContents(posX, posY, Cell.BRICKANDPOWERUPS);
					}
					else if (grid.getContents(posX, posY) == Cell.PLAYERANDBOMB) {
						grid.setContents(posX, posY, Cell.BOMB);
					}
					else if (grid.getContents(posX, posY) == Cell.PLAYERANDEXITWAY) {
						grid.setContents(posX, posY, Cell.EXITWAY);
					}

					else {
						grid.setContents(posX, posY, Cell.EMPTY);
					}

					if (dy < 0) {
						posY--;
					}

					else {
						posY++;
					}
					y += dy;

					if (grid.getContents(posX, posY) == Cell.EXITWAY) {
						grid.setContents(posX, posY, Cell.PLAYERANDEXITWAY);

					}
					
					else if(grid.getContents(posX, posY) == Cell.BOMB && PowerUps.getBombpass() == true){
						grid.setContents(posX, posY, Cell.PLAYERANDBOMB);
					}
					
					else if(grid.getContents(posX, posY) == Cell.BRICK && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Cell.PLAYERANDBRICK);
					}
					else if(grid.getContents(posX, posY) == Cell.BRICKANDEXITWAY && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Cell.PLAYERANDBRICKANDEXITWAY);
					}
					else if(grid.getContents(posX, posY) == Cell.BRICKANDPOWERUPS && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Cell.PLAYERANDBRICKANDPOWERUPS);
					}
					else if(grid.getContents(posX, posY) == Cell.POWERUPS){
						grid.setContents(posX, posY, Cell.PLAYER);
						powerup.setGotPowerup(true);
						System.out.println("Got the powerup!!!!!!");
						powerup.givePowerUp();
					}
					
					else {
						grid.setContents(posX, posY, Cell.PLAYER);

					}

				}
			}

			
			
			
			
			
			countForMovementSpeed = 0;
		}
		countForMovementSpeed++;
		
	}

	public Boolean getBombStatus() {
		return isBombPlaced;
	}

	public void setBombStatus(Boolean x) {
		isBombPlaced = x;
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

	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(playerImage));
		image = ii.getImage();
	}

	public Image getImage() {
		return image;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (dx != 0 || dy != 0) {
			return;
		}

		// Pausing
		if (key == KeyEvent.VK_SPACE) {
			if (GameState.getState() == State.RUNNING) {
				GameState.setState(State.PAUSE);
			} 
			
			else if(GameState.getState() == State.PAUSE) {
				GameState.setState(State.RUNNING);
			}
			
			if(GameState.getState() == State.RUNNINGANDLEVELOVER){
				GameState.setState(State.PAUSEANDLEVELOVER);
			}
			else if(GameState.getState() == State.PAUSEANDLEVELOVER) {
				GameState.setState(State.RUNNINGANDLEVELOVER);
			}
			
			System.out.println("STATE: " + GameState.getState());
		}

		// Bomb Logic
		if (key == KeyEvent.VK_X) {
			if ((GameState.getState() == State.RUNNING || GameState.getState() == State.RUNNINGANDLEVELOVER)) {
				if (grid.getContents(posX, posY) != Cell.PLAYERANDBOMB
						&& (grid.getContents(posX, posY) != Cell.PLAYERANDEXITWAY && powerup.getDetonate() == false)) {
					if(getBombsOnGround() < getBombs()){
						grid.setContents(posX, posY, Cell.PLAYERANDBOMB);
						
						// THIS IS WHERE WE SET THE RANGE!!!!!!!!!!!!!!!!!!! SET IT
						// AS HIGH AS YOU WANT, FRIENDS
					
						isBombPlaced = true;
						//bomb.setRange(1);
					
//						setBombsOnGround(getBombsOnGround() + 1);
//						System.out.println(range);
//						bomb.setRange(range);
//						bomb.setBombs(bombs);
//						bomb.setPosition(posX, posY);
//						initializeTimer();
						
						setBombsOnGround(getBombsOnGround() + 1);
						bomb.setBombs(bombs);
						bomb.setRange(range);
						bomb.setPosition(posX, posY);
						//Bomb workPlease = new Bomb(grid);
						Thread t = new Thread(bomb);
				        t.start();
				        //isBombPlaced = true;
					
					
					}
				}
			}

		}

		if (key == KeyEvent.VK_C) {
			//detonate whenever
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

		if (key == KeyEvent.VK_X) {

		}
		if (key == KeyEvent.VK_C) {

		}
		if (key == KeyEvent.VK_ESCAPE) {

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

	public static int getScore() {
		return score;
	}

	public void initializeTimer() {
		startTime = System.currentTimeMillis();
	}

	public long getInitialTime() {
		return startTime;
	}

	public static void setScore(int score) {
		Player.score = score;
	}

	public static int getLivesLeft() {
		return livesLeft;
	}
	
	public void toggleMovementSpeed(){
		if(movementSpeed == 2){
			this.movementSpeed = 1;
		}
		else{
			this.movementSpeed = 2;
		}
	}

	public static void setLivesLeft(int livesLeft) {
		Player.livesLeft = livesLeft;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	public static int getBombsOnGround() {
		return bombsOnGround;
	}

	public static void setBombsOnGround(int bombsOnGround) {
		Player.bombsOnGround = bombsOnGround;
	}

	// public void setUsername(String name) {
	// this.username = name;
	// }

}
