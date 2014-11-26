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
	//String [] powerups = {"bombsP.png","flamesP.png","speedP.png","detonatorsP.png","bombPassP.png","wallPassP.png","flamePassP.png"};
	
	private Image imageb;
	private Image imagef;
	private Image images;
	private Image imaged;
	private Image imageBP;
	private Image imageWP;
	private Image imageFP;
	
	private Grid grid;
	
	
	public PowerUps(Grid grid){
		this.grid = grid;
		loadImage();
		placePowerups();

	}
	
	
	private void placePowerups(){
		
		int randX = randInt(2,31);
		int randY = randInt(2,13); 
		
		grid.setContents(1,3,Cell.BRICKANDPOWERUPS);
		/*
		switch(level) {
		case 1:
		   	g2d.drawImage(powerups.getBombsImage(), dimension * i, dimension * j, this);
		    continue;
		case 2:
		   	g2d.drawImage(powerups.getFlamesImage(), dimension * i, dimension * j, this);
		    continue;
		case 3:
		   	g2d.drawImage(powerups.getBombsImage(), dimension * i, dimension * j, this);
		    continue;
		case 4:
		   	g2d.drawImage(powerups.getBombsImage(), dimension * i, dimension * j, this);
		    continue;
		case 5:
		   	g2d.drawImage(powerups.getBombsImage(), dimension * i, dimension * j, this);
		    continue;
		case 6:
		   	g2d.drawImage(powerups.getBombsImage(), dimension * i, dimension * j, this);
		    continue;
		case 7:
		   	g2d.drawImage(powerups.getBombsImage(), dimension * i, dimension * j, this);
		    continue;
		}
		*/
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
	
	
}
