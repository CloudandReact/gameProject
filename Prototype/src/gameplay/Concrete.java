package gameplay;

import java.util.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

public class Concrete extends JPanel {

	private String concrete = "concreteblock.png";
	
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



}