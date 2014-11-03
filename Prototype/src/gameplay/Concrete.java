package gameplay;

import java.util.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

public class Concrete {

	private String concrete = "blueblock.png";
	
/*	private int positionX;
	private int positionY;
	private int width;
	private int height;
	private final boolean cantPass = true;*/
	private Image image;


	
	public Concrete() {
		loadImage();
		
	}

	private void loadImage() 
	{
		ImageIcon iii = new ImageIcon(getClass().getResource(concrete));
		image = iii.getImage();
	}
	
	public Image getImage(){
		return image;
	}

	
	
	
/*	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
*/
	/*
	 * public LoadImageApp() { try { img = ImageIO.read(new File("wall.jpg")); }
	 * catch (IOException e) { }
	 * 		for (int x = 0; x <= gridLength; x++){
			for(int y = 0; y <= gridHeight; y++){
				concrete = new Concrete();
			}
			
		}
		
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
/*
	public Dimension getPreferredSize() {
		if (img == null) {
			return new Dimension(25, 25);
		} else {
			return new Dimension(img.getWidth(null), img.getHeight(null));
		}
	}*/

}