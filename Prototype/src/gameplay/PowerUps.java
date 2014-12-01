package gameplay;

import java.awt.Image;
import java.io.Serializable;
import java.util.Random;

import javax.swing.ImageIcon;

public class PowerUps implements Serializable{

	private String bombsP= "bombsp.png";
	private String flamesP = "flamesp.png";
	private String speedP = "speedp.png";
	private String detonatorsP = "detonatorp.png";
	private String bombPassP = "bombpassp.png";
	private String wallPassP = "wallpassp.png";
	private String flamePassP = "flamepassp.png"; 

	static boolean wallpass;
	static boolean bombpass; 
	static boolean flamepass;
	static boolean detonate; 
	private static boolean gotPowerup;
	private static boolean powerupPlaced; 

	private static int currentLevel;
	
	private Grid grid;
	private Player player;
	private Level level;
	
	ImageIcon bomb;
	ImageIcon flame;
	ImageIcon speed;
	ImageIcon detonator;
	ImageIcon bombPass;
	ImageIcon wallPass;
	ImageIcon flamePass;
	
	/**
	 * PowerUps constructor  
	 * @param grid is the grid that we place the powerups on
	 * @param level is required to get which level the player is in
	 */
	public PowerUps(Grid grid, Level level){
		//draw
		this.grid = grid;
		this.level = level;
		loadImage();
	}
	
	/**
	 * <p> Constructor that initializes a <code>PowerUps</code> object that  
	 * </p> 
	 * @param grid an object of <code>Grid</code> which contains the game's map and required methods access and alter it.
	 * @param player an object of <code>Player</code> is the player that will be given the powerup if he gets it
	 * @param level an object of <code>Level</code> which contains the required information about the level.
	 */
	public PowerUps(Grid grid, Player player, Level level){
		this.grid = grid;
		this.player = player;
		this.level = level;
		
		PowerUps.gotPowerup = false;
		PowerUps.powerupPlaced = false;
		PowerUps.bombpass = false;
		PowerUps.wallpass = false;
		PowerUps.flamepass = false;
		PowerUps.detonate = false;
		PowerUps.gotPowerup = false;
		
		loadImage();
		placePowerups();
		givePowerUp();
	}
	
	/**
	 * Method that gives the player the necessary PowerUp depending on which level the player is on. 
	 * This method compares which level the player is playing, and according to that gives the required PowerUp to the player. 
	 */
	public void givePowerUp(){
		System.out.println(PowerUps.getClevel());
		System.out.println("current Range is "+ player.getRange());
		int clevel = level.getCurrentLevel();
		
		if(gotPowerup){
			
			// FLAMES
			if (clevel == 1 || clevel == 7 || clevel == 15
					|| clevel == 27 || clevel == 38) {
				int currentRange = player.getRange();
				player.setRange(currentRange+1);
			}
			// BOMB
			else if (clevel == 2 || clevel == 5
					|| clevel == 6 || clevel == 11 || clevel == 12
					|| clevel == 17 || clevel == 19 || clevel == 23
					|| clevel == 28 || clevel == 32) {
				int currentBomb = player.getNumberOfBombs();
				player.setNumberOfBombs(currentBomb + 1);
			}
			// SPEED
			else if (clevel == 4) {
				player.toggleMovementSpeed();
			}
			// DETONATOR
			else if (clevel == 3 || clevel == 8 || clevel == 13
					|| clevel == 20 || clevel == 22 || clevel == 24
					|| clevel == 29 || clevel == 33 || clevel == 27
					|| clevel == 41 || clevel == 44 || clevel == 48) {
				setDetonate(true);
			}
			// BOMBPASS
			else if (clevel == 9 || clevel == 14 || clevel == 18
					|| clevel == 21 || clevel == 25 || clevel == 35
					|| clevel == 43 || clevel == 47) {
				setBombpass(true);
			}
			// WALLPASS
			else if (clevel == 10 || clevel == 16 || clevel == 31
					|| clevel == 39 || clevel == 42 || clevel == 46) {
				setWallpass(true);
			}
			// FLAMEPASS
			else if (clevel == 30 || clevel == 36 || clevel == 49) {
				setFlamepass(true);
			}
			// MYSTERY
			else if (clevel == 26 || clevel == 34 || clevel == 40
					|| clevel == 45 || clevel == 50) {
				
				// g2d.drawImage(powerups.getMysteryImage(),
				// Bomberman.TILE_SIZE * i, Bomberman.TILE_SIZE * j, this);
				// continue;
				// becomes immune to monsters and explosions
				// (not included in our gameplay so I don't know what to do with this)
			}
			PowerUps.setGotPowerup(false);
		}
	}
	
	/**
	 * Method places the <code>BRICKANDPOWERUP</code> (brick) on the grid. 
	 * The <code>PowerUp</code> will be placed under this <code>BRICKANDPOWERUP</code> (brick).
	 */
	private void placePowerups(){
		while(powerupPlaced == false){
		int randX = randInt(2,30);
		int randY = randInt(2,12); 
			if (grid.getContents(randX, randY) == Tile.EMPTY) {
					grid.setContents(randX,randY,Tile.BRICKANDPOWERUPS);
					powerupPlaced = true;
			}
		}
		
		
	}
	
	/**
	 * Given two integers, min and max, this method will return a pseudo-random
	 * integer value contained between these two integers.
	 * @param min Minimum value which may be returned.
	 * @param max Maximum value which may be returned.
	 * @return An integer between min and max inclusively.
	 */
	public static int randInt(int min, int max) {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	/**
	 * Loads the images of the <code>PowerUps</code>.
	 */
	private void loadImage() {
		bomb = new ImageIcon(getClass().getResource(bombsP));
		
		flame = new ImageIcon(getClass().getResource(flamesP));
		
		speed = new ImageIcon(getClass().getResource(speedP));
		
		detonator = new ImageIcon(getClass().getResource(detonatorsP));
		
		bombPass = new ImageIcon(getClass().getResource(bombPassP));
		
		wallPass = new ImageIcon(getClass().getResource(wallPassP));
		
		flamePass = new ImageIcon(getClass().getResource(flamePassP));
		
	}
	
	/**
	 * Gets the image of the bomb powerup
	 * @return the image corresponding to the powerup bomb.
	 */
	public Image getBombsImage() {
		return bomb.getImage();
	}
	
	/**
	 * Gets the image of the flames powerup 
	 * @return the image corresponding to the powerup flame.
	 */
	public Image getFlamesImage() {
		return flame.getImage();
	}
	
	/**
	 * Gets the image of the speed powerup 
	 * @return the image corresponding to the powerup speed.
	 */
	public Image getSpeedImage() {
		return speed.getImage();
	}
	
	/**
	 * Gets the image of the detonator powerup 
	 * @return the image corresponding to the powerup detonator.
	 */
	public Image getDetonatorsImage() {
		return detonator.getImage();
	}
	
	/**
	 * Gets the image of the bombpass powerup 
	 * @return the image corresponding to the powerup bombpass.
	 */
	public Image getBombPassImage() {
		return bombPass.getImage();
	}
	
	/**
	 * Gets the image of the wallpass powerup 
	 * @return the image corresponding to the powerup wallpass.
	 */
	public Image getWallPassImage() {
		return wallPass.getImage();
	}
	
	/**
	 * Gets the image of the flamepass powerup 
	 * @return the image corresponding to the powerup flamepass.
	 */
	public Image getFlamePassImage() {
		return flamePass.getImage();
	}
	
	/**
	 * Returns true if player gets the wallpass powerup 
	 * @return the <code>boolean</code> corresponding to the wallpass powerup.
	 */
	public static boolean getWallpass() {
		return wallpass;
	}
	
	/**
	 * Sets the wallpass attribute.
	 * @param wallpass the boolean representing whether the player got the wallpass powerup,
	 */
	public void setWallpass(boolean wallpass) {
		PowerUps.wallpass = wallpass;
	}

	/**
	 * Returns true if player gets the bombpass powerup 
	 * @return the <code>boolean</code> corresponding to the bombpass powerup.
	 */
	public static boolean getBombpass() {
		return bombpass;
	}

	/**
	 * Sets the bombpass attribute.
	 * @param bombpass the boolean representing whether the player got the bombpass powerup,
	 */
	public void setBombpass(boolean bombpass) {
		PowerUps.bombpass = bombpass;
	}

	/**
	 * Returns true if player gets the flamepass powerup 
	 * @return the <code>boolean</code> corresponding to the flamepass powerup.
	 */
	public static boolean getFlamepass() {
		return flamepass;
	}

	/**
	 * Sets the flamepass attribute.
	 * @param flamepass the boolean representing whether the player got the flamepass powerup,
	 */
	public void setFlamepass(boolean flamepass) {
		PowerUps.flamepass = flamepass;
	}

	/**
	 * Returns true if player gets the detonator powerup 
	 * @return the <code>boolean</code> corresponding to the detonator powerup.
	 */
	public static boolean haveDetonate() {
		return detonate;
	}

	/**
	 * Sets the detonator attribute.
	 * @param detonate the boolean representing whether the player got the detonate powerup,
	 */
	public void setDetonate(boolean detonate) {
		PowerUps.detonate = detonate;
	}

	/**
	 * Returns true if player gets the powerup in the current level.
	 * @return the <code>boolean</code> corresponding.
	 */
	public boolean getGotPowerup() {
		return gotPowerup;
	}

	/**
	 * Sets the gotPowerup attribute.
	 * @param gotpowerup the boolean representing whether the player got the powerup.
	 */
	public static void setGotPowerup(boolean gotpowerup) {
		PowerUps.gotPowerup = gotpowerup;
	}

	/**
	 * Returns which level the player is in.
	 * @return the <code<int</code> corresponding to the players current level.
	 */
	public static int getClevel() {
		return currentLevel;
	}

	/**
	 * Sets the current level attribute.
	 * @param clevel the int of the current level.
	 */
	public static void setClevel(int clevel) {
		PowerUps.currentLevel = clevel;
	}
	
}
