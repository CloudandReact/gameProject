package test;

import java.util.*;
import java.awt.*;

public class Concrete {

		private int positionX;
		private int positionY;
		private int width;
		private int height;
		private final boolean cantPass = true; 

		public static void main(String args[]){

			new concrete();

		}

		public Concrete(int positionX, int positionY){
			super("Concrete");
			this.positionX=positionX;
			this.positionY=positionY;
			//reads the image and stores it in img
			BufferedImage img = null;
			try {
    			img = ImageIO.read(new File("wall.png"));
			} catch (IOException e) {
			}
			//draws the image img, but not sure if it scales the image to one grid box
			boolean Graphics.drawImage(Image img, positionX, positionY, null);

		}
			
		
}