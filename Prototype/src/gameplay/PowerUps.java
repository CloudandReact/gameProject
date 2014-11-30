package gameplay;

import java.awt.Image;

import java.util.Random;

import javax.swing.ImageIcon;

public class PowerUps {

	private String bombsP= "bombsp.png";
	private String flamesP = "flamesp.png";
	private String speedP = "speedp.png";
	private String detonatorsP = "detonatorp.png";
	private String bombPassP = "bombpassp.png";
	private String wallPassP = "wallpassp.png";
	private String flamePassP = "flamepassp.png"; 
	private static Boolean wallpass;
	private static Boolean bombpass; 
	private static Boolean flamepass;
	private static Boolean detonate; 
	private static Boolean gotPowerup;
	private static Boolean powerupPlaced; //use this to make sure powerup is not printed twice in same level if player dies
	
	private Image imageb;
	private Image imagef;
	private Image images;
	private Image imaged;
	private Image imageBP;
	private Image imageWP;
	private Image imageFP;
	private static int currentLevel;
	
	private Grid grid;
	private Player player;
	private Level level;
	
	public PowerUps(Grid grid, Level level){
		//draw
		this.grid = grid;
		this.level = level;
		loadImage();
		
	}
	
	public PowerUps(Grid grid, Player player, Level level){
		//currentLevel = clevel.getCurrentclevel();
		this.grid = grid;
		this.player = player;
		this.level = level;
		PowerUps.powerupPlaced = false;
		PowerUps.bombpass = false;
		PowerUps.wallpass = false;
		PowerUps.flamepass = false;
		PowerUps.detonate = false;
		PowerUps.gotPowerup = false;
		loadImage();
		placePowerups();
		givePowerUp();
		gotPowerup = false;
	}
	
	public void givePowerUp(){
		System.out.println(PowerUps.getClevel());
		System.out.println("current Range is "+ player.getRange());
		int clevel = PowerUps.getClevel();
		if(gotPowerup == true){
			
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
				int currentBomb = player.getBombs();
				player.setBombs(currentBomb + 1);
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
				// (not included in our gameplay so I dont know what
				// to do with this)
			}
			PowerUps.setGotPowerup(false);
		}
	}
	private void placePowerups(){
		while(powerupPlaced == false){
		int randX = randInt(2,31);
		int randY = randInt(2,12); 
			if (grid.getContents(randX, randY) == Cell.EMPTY) {
					grid.setContents(randX,randY,Cell.BRICKANDPOWERUPS);
					powerupPlaced = true;
			}
		}
		
		
	}
	
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	private void loadImage() {
		ImageIcon bomb = new ImageIcon(getClass().getResource(bombsP));
		imageb = bomb.getImage();
		
		ImageIcon flame = new ImageIcon(getClass().getResource(flamesP));
		imagef = flame.getImage();
		
		ImageIcon speed = new ImageIcon(getClass().getResource(speedP));
		images = speed.getImage();
		
		ImageIcon detonator = new ImageIcon(getClass().getResource(detonatorsP));
		imaged = detonator.getImage();
		
		ImageIcon bombpass = new ImageIcon(getClass().getResource(bombPassP));
		imageBP = bombpass.getImage();
		
		ImageIcon wallpass = new ImageIcon(getClass().getResource(wallPassP));
		imageWP = wallpass.getImage();
		
		ImageIcon flamepass = new ImageIcon(getClass().getResource(flamePassP));
		imageFP = flamepass.getImage();
	}
	
	public Image getBombsImage() {
		return imageb;
	}
	
	public Image getFlamesImage() {
		return imagef;
	}
	
	public Image getSpeedImage() {
		return images;
	}
	
	public Image getDetonatorsImage() {
		return imaged;
	}
	
	public Image getBombPassImage() {
		return imageBP;
	}
	
	public Image getWallPassImage() {
		return imageWP;
	}
	
	public Image getFlamePassImage() {
		return imageFP;
	}


	public static Boolean getWallpass() {
		return wallpass;
	}


	public void setWallpass(Boolean wallpass) {
		PowerUps.wallpass = wallpass;
	}


	public static Boolean getBombpass() {
		return bombpass;
	}


	public void setBombpass(Boolean bombpass) {
		PowerUps.bombpass = bombpass;
	}


	public static Boolean getFlamepass() {
		return flamepass;
	}


	public void setFlamepass(Boolean flamepass) {
		PowerUps.flamepass = flamepass;
	}


	public Boolean haveDetonate() {
		return detonate;
	}


	public void setDetonate(Boolean detonate) {
		PowerUps.detonate = detonate;
	}


	public Boolean getGotPowerup() {
		return gotPowerup;
	}


	public static void setGotPowerup(Boolean gotpowerup) {
		PowerUps.gotPowerup = gotpowerup;
	}

	public static int getClevel() {
		return currentLevel;
	}

	public static void setClevel(int clevel) {
		PowerUps.currentLevel = clevel;
	}

	
}