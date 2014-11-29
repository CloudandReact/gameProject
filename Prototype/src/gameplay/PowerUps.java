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
	private static Boolean wallpass = false;
	private static Boolean bombpass = false; 
	private static Boolean flamepass = false;
	private static Boolean detonate = false; 
	private static Boolean gotPowerup= false; //remember to set this to false when player moves to next clevel
	//String [] powerups = {"bombsP.png","flamesP.png","speedP.png","detonatorsP.png","bombPassP.png","wallPassP.png","flamePassP.png"};
	
	private Image imageb;
	private Image imagef;
	private Image images;
	private Image imaged;
	private Image imageBP;
	private Image imageWP;
	private Image imageFP;
	private static int cclevel;
	
	private Grid grid;
	private Player player;
	private Level level;
	
	// use Level object Rakinul
	
	public PowerUps(Grid grid, Player player, Level level){
		//cclevel = clevel.getCurrentclevel();
		this.grid = grid;
		this.player = player;
		this.level = level;
		loadImage();
		placePowerups();
		givePowerUp();
		gotPowerup = false;
	}
	
	public void givePowerUp(){
		//int clevel = PowerUps.getClevel();
		int clevel = 10;
		if(gotPowerup == true){
			
			// FLAMES
			if (clevel == 1 || clevel == 7 || clevel == 15
					|| clevel == 27 || clevel == 38) {
				System.out.println("I AM HEREEES AESGSGSGA GASG  ");
				int currentRange = player.getRange();
				System.out.println("current Range is "+currentRange);
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
		
		//int randX = randInt(2,31);
		//int randY = randInt(2,12); 
		//grid.setContents(randX,randY,Cell.BRICKANDPOWERUPS);
		grid.setContents(1,4,Cell.BRICKANDPOWERUPS);
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


	public Boolean getDetonate() {
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
		return cclevel;
	}

	public static void setClevel(int clevel) {
		PowerUps.cclevel = clevel;
	}

	
	
}