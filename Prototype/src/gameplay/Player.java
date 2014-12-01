package gameplay;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String playerImage = "bomberman.png";

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int posX;
	private int posY;
	private static int score;
	public static int livesLeft;
	private int movementSpeed;
	
	private static int bombNumber;
	private static int currentBombCounter;

	
	private int range;
	private int bombs;
	private static int bombsOnGround;
	
	
	private int countForMovementSpeed;

	private Image image;
	ImageIcon ii;
	private Grid grid;
	private Bomb bomb;
	private Enemy enemy;
	
	private long startTime;
	private PowerUps powerup;
	private Boolean isBombPlaced;
	private Boolean detonatePressed;
	
	public Player() { 

	}

	public Player(Grid grid, Bomb bomb, Enemy enemy, Level level) {
		this.enemy = enemy;
		movementSpeed = 2;
		livesLeft = 2;
		loadImage();
		this.setX(25);
		this.setY(25);
		this.posX = 1;
		this.posY = 1;
		this.grid = grid;
		this.bomb = bomb;
		this.grid.setContents(1, 1, Tile.PLAYER);
		setScore(0);
		this.isBombPlaced = false;
		this.setDetonatePressed(false);
		this.range = 1;
		this.bombs = 1;
		bombNumber = 10;
		currentBombCounter = 10;
		bombsOnGround = 0;
		
		this.powerup = new PowerUps(grid, this, level);

	}
	
//	public void exitWayLogic(int posX, int posY){
//		
//		if(grid.getContents(posX, posY) == Tile.EXITWAY && GameState.getState() == State.RUNNINGANDLEVELOVER){
//			level.loadnextLevel();
//		}
//		
//	}
	public void move() {
		if(countForMovementSpeed == movementSpeed){	
			if (dx != 0) {		
				//exitWayLogic(dx/25, 0);
			
				
				// Player dies if walking into enemy
				if (grid.getContents(posX + (dx / 25), posY) == Tile.BALLOOM
						|| grid.getContents(posX + (dx / 25), posY) == Tile.ONEAL
						|| grid.getContents(posX + (dx / 25), posY) == Tile.DOLL
						|| grid.getContents(posX + (dx / 25), posY) == Tile.MINVO
						|| grid.getContents(posX + (dx / 25), posY) == Tile.KONDORIA
						|| grid.getContents(posX + (dx / 25), posY) == Tile.OVAPI
						|| grid.getContents(posX + (dx / 25), posY) == Tile.PASS
						|| grid.getContents(posX + (dx / 25), posY) == Tile.PONTAN
						|| grid.getContents(posX + (dx / 25), posY) == Tile.KONDORIAANDBRICK
						|| grid.getContents(posX + (dx / 25), posY) == Tile.OVAPIANDBRICK
						|| grid.getContents(posX + (dx / 25), posY) == Tile.PONTANANDBRICK) {
					grid.setContents(posX, posY, Tile.EMPTY);
				}

				//
				if (grid.getContents(posX + (dx / 25), posY) == Tile.EMPTY
						|| grid.getContents(posX + (dx / 25), posY) == Tile.POWERUPS
						|| grid.getContents(posX + (dx / 25), posY) == Tile.EXITWAY
						|| (grid.getContents(posX + (dx / 25), posY) == Tile.BOMB && PowerUps.getBombpass() == true)
						|| (grid.getContents(posX + (dx / 25), posY) == Tile.BRICK && PowerUps.getWallpass() == true)
						|| (grid.getContents(posX + (dx / 25), posY) == Tile.BRICKANDEXITWAY && PowerUps.getWallpass() == true)
						|| (grid.getContents(posX + (dx / 25), posY) == Tile.BRICKANDPOWERUPS && PowerUps.getWallpass() == true)) {

					if (grid.getContents(posX, posY) == Tile.PLAYERANDBOMB) {
						grid.setContents(posX, posY, Tile.BOMB);

					}
					else if (grid.getContents(posX, posY) == Tile.PLAYERANDBRICK) {
						grid.setContents(posX, posY, Tile.BRICK);
					}
					else if (grid.getContents(posX, posY) == Tile.PLAYERANDBRICKANDEXITWAY) {
						grid.setContents(posX, posY, Tile.BRICKANDEXITWAY);
					}
					else if (grid.getContents(posX, posY) == Tile.PLAYERANDBRICKANDPOWERUPS) {
						grid.setContents(posX, posY, Tile.BRICKANDPOWERUPS);
					}
//					else if (grid.getContents(posX, posY) == Tile.PLAYERANDBOMB) {
//						grid.setContents(posX, posY, Tile.BOMB);
//					}
					else if (grid.getContents(posX, posY) == Tile.PLAYERANDEXITWAY) {
						grid.setContents(posX, posY, Tile.EXITWAY);
					}

					else {
						grid.setContents(posX, posY, Tile.EMPTY);
					}

					if (dx < 0) {
						posX--;
					} else {
						posX++;
					}

					x += dx;

					if (grid.getContents(posX, posY) == Tile.EXITWAY) {
						grid.setContents(posX, posY, Tile.PLAYERANDEXITWAY);

					} else if(grid.getContents(posX, posY) == Tile.BOMB && PowerUps.getBombpass() == true){
						grid.setContents(posX, posY, Tile.PLAYERANDBOMB);
					}
					else if(grid.getContents(posX, posY) == Tile.BRICK && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Tile.PLAYERANDBRICK);
					}
					else if(grid.getContents(posX, posY) == Tile.BRICKANDEXITWAY && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Tile.PLAYERANDBRICKANDEXITWAY);
					}
					else if(grid.getContents(posX, posY) == Tile.BRICKANDPOWERUPS && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Tile.PLAYERANDBRICKANDPOWERUPS);
					}
					else if(grid.getContents(posX, posY) == Tile.POWERUPS){
						grid.setContents(posX, posY, Tile.PLAYER);
						powerup.setGotPowerup(true);
						System.out.println("Got the powerup!!!!!!");
						powerup.givePowerUp();
					}
					
					else {
						grid.setContents(posX, posY, Tile.PLAYER);

					}

				}
			}

			if (dy != 0) {
				
				//exitWayLogic(0, dy/25);

				// Player dies if walking into enemy
				if (grid.getContents(posX, posY + (dy / 25)) == Tile.BALLOOM
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.ONEAL
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.DOLL
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.MINVO
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.KONDORIA
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.OVAPI
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.PASS
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.PONTAN
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.KONDORIAANDBRICK
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.OVAPIANDBRICK
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.PONTANANDBRICK) {
					grid.setContents(posX, posY, Tile.EMPTY);
					
				}
				//
				if (grid.getContents(posX, posY + (dy / 25)) == Tile.EMPTY
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.POWERUPS
						|| grid.getContents(posX, posY + (dy / 25)) == Tile.EXITWAY
						|| (grid.getContents(posX, posY + (dy / 25)) == Tile.BOMB && PowerUps.getBombpass() == true)
						|| (grid.getContents(posX, posY + (dy / 25)) == Tile.BRICK && PowerUps.getWallpass() == true)
						|| (grid.getContents(posX, posY + (dy / 25)) == Tile.BRICKANDEXITWAY && PowerUps.getWallpass() == true)
						|| (grid.getContents(posX, posY + (dy / 25)) == Tile.BRICKANDPOWERUPS && PowerUps.getWallpass() == true)) {

					if (grid.getContents(posX, posY) == Tile.PLAYERANDBOMB) {
						grid.setContents(posX, posY, Tile.BOMB);

					}
					else if (grid.getContents(posX, posY) == Tile.PLAYERANDBRICK) {
						grid.setContents(posX, posY, Tile.BRICK);
					}
					else if (grid.getContents(posX, posY) == Tile.PLAYERANDBRICKANDEXITWAY) {
						grid.setContents(posX, posY, Tile.BRICKANDEXITWAY);
					}
					else if (grid.getContents(posX, posY) == Tile.PLAYERANDBRICKANDPOWERUPS) {
						grid.setContents(posX, posY, Tile.BRICKANDPOWERUPS);
					}
					else if (grid.getContents(posX, posY) == Tile.PLAYERANDBOMB) {
						grid.setContents(posX, posY, Tile.BOMB);
					}
					else if (grid.getContents(posX, posY) == Tile.PLAYERANDEXITWAY) {
						grid.setContents(posX, posY, Tile.EXITWAY);
					}

					else {
						grid.setContents(posX, posY, Tile.EMPTY);
					}

					if (dy < 0) {
						posY--;
					}

					else {
						posY++;
					}
					y += dy;

					if (grid.getContents(posX, posY) == Tile.EXITWAY) {
						grid.setContents(posX, posY, Tile.PLAYERANDEXITWAY);

					}
					
					else if(grid.getContents(posX, posY) == Tile.BOMB && PowerUps.getBombpass() == true){
						grid.setContents(posX, posY, Tile.PLAYERANDBOMB);
					}
					
					else if(grid.getContents(posX, posY) == Tile.BRICK && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Tile.PLAYERANDBRICK);
					}
					else if(grid.getContents(posX, posY) == Tile.BRICKANDEXITWAY && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Tile.PLAYERANDBRICKANDEXITWAY);
					}
					else if(grid.getContents(posX, posY) == Tile.BRICKANDPOWERUPS && PowerUps.getWallpass() == true){
						grid.setContents(posX, posY, Tile.PLAYERANDBRICKANDPOWERUPS);
					}
					else if(grid.getContents(posX, posY) == Tile.POWERUPS){
						grid.setContents(posX, posY, Tile.PLAYER);
						powerup.setGotPowerup(true);
						System.out.println("Got the powerup!!!!!!");
						powerup.givePowerUp();
					}
					
					else {
						grid.setContents(posX, posY, Tile.PLAYER);

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
		 ii= new ImageIcon(getClass().getResource(playerImage));
		
	}

	public Image getImage() {
		image = ii.getImage();
		return image;
	}
	
	public static int getBombNumber(){
		return bombNumber;
	}
	
	public static void setBombNumber(int bombNumber){
		Player.bombNumber = bombNumber;
	}
	
	public static int getCurrentBombCounter(){
		return currentBombCounter;
	}
	
	public static void setCurrentBombCounter(int currentBombCounter){
		Player.currentBombCounter = currentBombCounter;
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
				if (grid.getContents(posX, posY) != Tile.PLAYERANDBOMB && (grid.getContents(posX, posY) != Tile.PLAYERANDEXITWAY)) {
					if(getBombsOnGround() < getBombs()){
						grid.setContents(posX, posY, Tile.PLAYERANDBOMB);
						
						
						setBombsOnGround(getBombsOnGround() + 1);
						//bomb.setBombs(1);
						//bomb.setRange(range);
						//bomb.setPosition(posX, posY);
						//Bomb workPlease = new Bomb(grid);
						Bomb placingBomb = new Bomb(grid, this.enemy, this);
						placingBomb.setBombs(1);
						placingBomb.setRange(range);
						placingBomb.setPosition(posX,posY);
						
						if(hasDetonate()){
							placingBomb.setBombNumber(getCurrentBombCounter());
							currentBombCounter--;
						}
						
						
						Thread t = new Thread(placingBomb);
				        t.start();
				        isBombPlaced = true;
				        
				        System.out.println("DID WE GET HERE");
					
				   
					}
				}
			}

		}

		if (key == KeyEvent.VK_B) {
			setDetonatePressed(true);
	
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
		if (key == KeyEvent.VK_B) {
			if(getBombsOnGround() == 0){
				System.out.println("resetting...");
		        setBombNumber(10);
		        setCurrentBombCounter(10);
		        System.out.println(getBombNumber() + "///" + getCurrentBombCounter());
		    }
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
	
	public boolean hasDetonate(){
		return powerup.haveDetonate();
	}
	public static void setBombsOnGround(int bombsOnGround) {
		Player.bombsOnGround = bombsOnGround;
	}

	public Boolean getDetonatePressed() {
		return detonatePressed;
	}

	public void setDetonatePressed(Boolean detonatePressed) {
		this.detonatePressed = detonatePressed;
	}

	// public void setUsername(String name) {
	// this.username = name;
	// }

}
