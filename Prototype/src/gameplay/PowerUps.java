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
	private static Boolean gotPowerup= false; //remember to set this to false when player moves to next level
	//String [] powerups = {"bombsP.png","flamesP.png","speedP.png","detonatorsP.png","bombPassP.png","wallPassP.png","flamePassP.png"};
	
	private Image imageb;
	private Image imagef;
	private Image images;
	private Image imaged;
	private Image imageBP;
	private Image imageWP;
	private Image imageFP;
	
	private Grid grid;
	private Player player;
	
	
	public PowerUps(Grid grid, Player player){
		this.grid = grid;
		this.player = player;
		loadImage();
		placePowerups();
		givePowerUp();
		gotPowerup = false;
	}
	
	public void givePowerUp(){
		int level = 1;
		if(gotPowerup == true){
			
			// FLAMES
			if (level == 1 || level == 7 || level == 15
					|| level == 27 || level == 38) {
				int currentRange = player.getRange();
				player.setRange(currentRange+1);
			}

			// BOMB
			else if (level == 2 || level == 5
					|| level == 6 || level == 11 || level == 12
					|| level == 17 || level == 19 || level == 23
					|| level == 28 || level == 32) {
				int currentBomb = player.getBombs();
				player.setBombs(currentBomb + 1);
				
			}
			// SPEED
			else if (level == 4) {
				
			}
			// DETONATOR
			else if (level == 3 || level == 8 || level == 13
					|| level == 20 || level == 22 || level == 24
					|| level == 29 || level == 33 || level == 27
					|| level == 41 || level == 44 || level == 48) {
				
			}
			// BOMBPASS
			else if (level == 9 || level == 14 || level == 18
					|| level == 21 || level == 25 || level == 35
					|| level == 43 || level == 47) {
				
			}
			// WALLPASS
			else if (level == 10 || level == 16 || level == 31
					|| level == 39 || level == 42 || level == 46) {
				
			}
			// FLAMEPASS
			else if (level == 30 || level == 36 || level == 49) {
				
			}
			// MYSTERY
			else if (level == 26 || level == 34 || level == 40
					|| level == 45 || level == 50) {
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
		
		int randX = randInt(2,31);
		int randY = randInt(2,12); 
		
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


	public static Boolean getDetonate() {
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

	
	
}