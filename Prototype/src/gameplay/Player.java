package gameplay;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;
	private final int INITIAL_FRAME_POSITION = 25;
	
	private final int PLAYER_STARTING_TILE = 1;
    private final int left = -1 * Bomberman.TILE_SIZE;
    private final int right = Bomberman.TILE_SIZE;
    private final int down = Bomberman.TILE_SIZE;
    private final int up = -1 * Bomberman.TILE_SIZE;
    private final int notMoving = 0;
	
    private String playerImage = "bomberman.png";
	private int dx;
	private int dy;
	private int x;
	private int y;
	private int posX;
	private int posY;
	private int movementSpeed;
	private int countForMovementSpeed;	
	private int range;
	private int numberOfBombs;

	private static int bombNumber;
	private static int currentBombCounter;
	private static int score;
	private static int bombsOnGround;
	public static int livesLeft;
	
	private boolean detonatePressed;
	
	private Grid grid;
	private Enemy enemy;
	private PowerUps powerup;
	
	ImageIcon iconPlayer;



	/**
	 * <p> Constructor that initializes a <code>Player</code> object which initializes the user's starting position on the map, sets its
	 * position on the frame, initializes all bomb attributes to their basic values, sets the score for the session to zero, initializes
	 * movement speed and loads the player image. Additionally, the required game objects are initialized. A player utilizes a Grid, Enemy,
	 * and PowerUps object. </p>
	 * 
	 * A player object deals with the basic player movement and collisions, powerup logic, and places bomb accordingly.
	 * Additionally, all user input is taken through the player class. 
	 * 
	 * @param grid 
	 * 			an object of <code>Grid</code> which contains the game's map and required methods access and alter it.
	 * @param enemy 
	 * 			an object of <code>Enemy</code> which contains information about the enemies on the map. 
	 * @param level 
	 * 			an object of <code>Level</code> which contains the required information about the level.
	 * 
	 */
	public Player(Grid grid, Enemy enemy, Level level) {
		 
		this.grid = grid;
		this.enemy = enemy;
		
		posX = PLAYER_STARTING_TILE;
		posY = PLAYER_STARTING_TILE;	
		setX(INITIAL_FRAME_POSITION);
		setY(INITIAL_FRAME_POSITION);
		setBombsOnGround(0);
		setRange(1);
		setNumberOfBombs(1);
		setBombNumber(10);
		setCurrentBombCounter(10);
		setScore(0);
		setLivesLeft(2);	
		setDetonatePressed(false);

		toggleMovementSpeed();
		loadImage();
	
		this.powerup = new PowerUps(grid, this, level);
		this.grid.setContents(posX, posY, Tile.PLAYER);

	}
	
	
	/**
	 * <p>Move method called at every user input. Based on the key pressed, it updates the players position on the grid accordingly. 
	 * This method deals with collisions, powerups, and player death. </p> 
	 * 
	 * <p>Move only occurs when the countForMovementSpeed is equal to
	 * the actual movement speed, which is defaulted to 2. The count is updated on each call, and is reset when the move is executed. 
	 * When the player finds the movement speed power up, the movementSpeed is set to 1, and move functions on each call. </p>
	 */
	
	public void move() {
		
		if(countForMovementSpeed == movementSpeed){	
			if (dx != 0) {		

				if(grid.checkIfEnemy(posX + (dx / 25), posY)){
					grid.setContents(posX, posY, Tile.EMPTY);
				}
	
				
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
					else if (grid.getContents(posX, posY) == Tile.PLAYERANDBOMB) {
						grid.setContents(posX, posY, Tile.BOMB);
					}
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
						PowerUps.setGotPowerup(true);
						powerup.givePowerUp();
					}
					
					else {
						grid.setContents(posX, posY, Tile.PLAYER);

					}

				}
			}

			if (dy != 0) {
				
			
				if(grid.checkIfEnemy(posX, posY + (dy / 25))){
					grid.setContents(posX, posY, Tile.EMPTY);
				}
							 				
			
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
						PowerUps.setGotPowerup(true);
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
	
	
	 /**
     * <p> This method performs a check on if the following keys are
     * pressed: up, down, right, left, b, space, and x. 
     * Based on the key, It then processes the required logic. . </p>
     * 
     */ 
	
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (dx != 0 || dy != 0) {
			return;
		}
		
		switch(key){
		case KeyEvent.VK_LEFT:
			dx = left;
			break;
		case KeyEvent.VK_RIGHT:
			dx = right;
			break;
		case KeyEvent.VK_UP:
			dy = up;
			break;
		case KeyEvent.VK_DOWN:
			dy = down;
			break;	
		case KeyEvent.VK_B:
			setDetonatePressed(true);
			break;
		case KeyEvent.VK_SPACE:	
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
			break;		
		case KeyEvent.VK_X:
			if ((GameState.getState() == State.RUNNING || GameState.getState() == State.RUNNINGANDLEVELOVER)) {
				if (grid.getContents(posX, posY) != Tile.PLAYERANDBOMB && (grid.getContents(posX, posY) != Tile.PLAYERANDEXITWAY)) {
					if(getBombsOnGround() < getNumberOfBombs()){
						grid.setContents(posX, posY, Tile.PLAYERANDBOMB);
						
						setBombsOnGround(getBombsOnGround() + 1);
						Bomb placingBomb = new Bomb(grid, this.enemy, this);
						placingBomb.setNumberOfBombs(1);
						placingBomb.setRange(range);
						placingBomb.setPosition(posX,posY);
						
						if(hasDetonate()){
							placingBomb.setBombNumber(getCurrentBombCounter());
							currentBombCounter--;
						}
								
						Thread t = new Thread(placingBomb);
				        t.start();
					}
				}
			}
			break;
		default:
			break;
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		
		switch(key){
		case KeyEvent.VK_X:
			break;
		case KeyEvent.VK_B:
			if(getBombsOnGround() == 0){
		        setBombNumber(10);
		        setCurrentBombCounter(10);
		    }
			break;
		case KeyEvent.VK_LEFT:
			dx = notMoving;
			break;	
		case KeyEvent.VK_RIGHT:
			dx = notMoving;
			break;	
		case KeyEvent.VK_UP:
			dy = notMoving;
			break;	
		case KeyEvent.VK_DOWN:
			dy = notMoving;
			break;	
		default:
			break;	
		}		

	}
	
	
	
	/**
	 * Method which allows you to determine the players position on the x axis. 
	 * Note: Each tile has a TILE_SIZE of 25. 
	 * @return the <code>int</code> corresponding to the player's position relative to the frame's initial point along the 'x' axis. 
	 */
	
	public int getX() {
		return x;
	}
	
	
	/**
	 * Method which allows you to set the players position on the x axis. 
	 * @param xNew The new position of the player along the x axis. 
	 */
	public void setX(int xNew) {
		x = xNew;
	}
	
	
	/**
	 * Method which allows you to determine the players position on the y axis. 
	 * Note: Each tile has a TILE_SIZE of 25. 
	 * @return the <code>int</code> corresponding to the player's position relative to the frame's initial point along the y axis. 
	 */
	
	public int getY() {
		return y;
	}
	
	
	/**
	 * Method which allows you to set the players position on the y axis. 
	 * @param yNew The new position of the player along the y axis.
	 */
	public void setY(int yNew) {
		y = yNew;
	}
	
	
	
	
	/**
	 * Method used to load the player's image. This corresponds to the bomberman icon. 
	 */
	
	private void loadImage() {
		 iconPlayer= new ImageIcon(getClass().getResource(playerImage));
		
	}
	
	
	/**
	 * Gets the image for the player.
	 * 
	 * @return the <code>Image</code> shown when player is in a tile by itself.
	 */

	public Image getImage() {
		return iconPlayer.getImage();
		
	}
	
	/**
	 *<p> Returns the bombNumber which keeps track of the bombs placed on the map: required for the detonate powerup logic. </p>
	 * This method allows you to determine the bomb number.
	 * <p> Note: bombNumber is reset when there are no bombs present on the grid. </p>
	 * @return the <code>int</code> corresponding to the bombNumber attribute. 
	 */
	public static int getBombNumber(){
		return bombNumber;
	}
	
	/**
	 * <p> Required for detonate powerup logic. </p> 
	 * <p> Sets the bombNumber which keeps track of the bombs placed on the map. </p>
	 * This method allows you to set the bomb number. This is primarily used in the bomb class: after 
	 * the explode() method is called, it is incremented.	
	 * <p> Note: bombNumber is reset when there are no bombs present on the grid. </p>
	 * @param bombNumber The number corresponding to the bomb placed on the grid. The oldest bomb should be set to 10. 
	 */
	
	public static void setBombNumber(int bombNumber){
		Player.bombNumber = bombNumber;
	}
	
	/**<p> Required for detonate powerup logic. </p> 
	 * Gets the counter for the integer which keeps track of the bomb number, and is passed to the new bomb object and decremented.
	 * This is cross checked with bombNumber; if they match, the bomb explodes. 
	 * <p> </p> 
	 * @return the <code>int</code> corresponding to the currentBombCounter attribute. 
	 */
	public static int getCurrentBombCounter(){
		return currentBombCounter;
	}
	
	/**<p> Required for detonate powerup logic. </p> 
	 * Sets the counter for the integer which keeps track of the bomb number, and is passed to the new bomb object and decremented.
	 * This is cross checked with bombNumber; if they match, the bomb explodes. 
	 * @param currentBombCounter The number corresponding to the bomb number. 
	 */
	public static void setCurrentBombCounter(int currentBombCounter){
		Player.currentBombCounter = currentBombCounter;
	}
	
	/**
	 * Gets the player's score. 
	 * @return the <code>int</code> corresponding to the Player's score. 
	 */
	
	public static int getScore() {
		return score;
	}
	
	/**
	 * Sets the player's score. Required when enemies are killed in the bomb class. 
	 * @param score The Player's score. 
	 */
	public static void setScore(int score) {
		Player.score = score;
	}
	
	
	/**
	 * Gets the number of lives left. 
	 * @return the <code>int</code> corresponding the Player's number of lives left. 
	 */

	public static int getLivesLeft() {
		return livesLeft;
	}
	
	/**
	 * Sets the players number of live's left. 
	 * @param livesLeft The Player's number of lives left.
	 */
	
	public static void setLivesLeft(int livesLeft) {
		Player.livesLeft = livesLeft;
	}
	
	/**
	 * Gets the current player's range, which corresponds to his bomb range.
	 * Varies depending on bomb powerup. 
	 * @return the <code>int</code> corresponding to the player range attribute. 
	 */

	public int getRange() {
		return range;
	}
	
	/**
	 * Sets the current player's range, which corresponds to his bomb range.
	 * Varies depending on bomb powerup.
	 * @param range The range of the bomb explosion. 
	 */

	public void setRange(int range) {
		this.range = range;
	}
	
	/**
	 * Gets the number of bombs available to the Player. 
	 * @return the <code>int</code> getNumberOfBombs representing the number of bombs that can be placed at one time by the Player. 
	 */
	
	public int getNumberOfBombs() {
		return numberOfBombs;
	}

	public void setNumberOfBombs(int numberOfBombs) {
		this.numberOfBombs = numberOfBombs;
	}
	
	/**
	 * Returns the number of bombs present on the grid.
	 * @return the <code>int</code> corresponding to the number of bombs that are currently on the grid. 
	 */
	public static int getBombsOnGround() {
		return bombsOnGround;
	}
	
	/**
	 * Sets the number of bombs present on the grid.
	 * @param bombsOnGround The new integer representing the number of bombs present on the grid. 
	 */
	
	public static void setBombsOnGround(int bombsOnGround) {
		Player.bombsOnGround = bombsOnGround;
	}
	
	/**
	 * Returns true if 'C' has been pressed to detonate the bomb. 
	 * @return the <code>boolean</code> representing whether or not C has been pressed. 
	 */
	public Boolean getDetonatePressed() {
		return detonatePressed;
	}
	
	/**
	 * Sets the detonatePressed attribute.
	 * @param detonatePressed The boolean representing whether or not C has been pressed. 
	 */
	public void setDetonatePressed(boolean detonatePressed) {
		this.detonatePressed = detonatePressed;
	}
	
	/**
	 * Checks if the Player has the detonate powerup. 
	 * @return the <code>boolean</code> representing whether or not the player has the detonate powerup. 
	 */
	
	public boolean hasDetonate(){
		return PowerUps.haveDetonate();
	}
	
	/**
	 * Method to change the Player's movement speed. As it can only be 1 or 2, (1 being the faster of the two), it only needs to be toggled. 
	 */
	
	public void toggleMovementSpeed(){
		if(movementSpeed == 2){
			this.movementSpeed = 1;
		}
		else{
			this.movementSpeed = 2;
		}
	}
}
