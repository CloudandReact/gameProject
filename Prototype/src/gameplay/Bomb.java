package gameplay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

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
	
	public void explode(){
		
	}

	
//	public void keyPressed(KeyEvent e) {
//
//		int key = e.getKeyCode();
//		if (key == KeyEvent.VK_X) {
//			System.out.println("BOMBERMAAAAAAAN");
//			
//		}
//	}

//	public void keyReleased(KeyEvent e) {
//		int key = e.getKeyCode();
//
//		if (key == KeyEvent.VK_X) {
//			;
//		}
//
//	}
}

