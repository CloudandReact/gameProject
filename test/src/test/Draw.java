package test;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Graphics;

public class Draw extends JPanel{
	int gridHeight=13;
	int gridWidth=31;
	public void drawPlayer(){
		
	}
	public void drawConcrete(){
		
	} 
	public void drawMap(int gridHeight, int gridWidth){
		for(int i=0;i<gridHeight;i++){
			for(int j=0;j<gridWidth;j++){
				
			}
		}
		
		
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0;i<gridHeight;i++){
			g.drawLine(10+i*30, 10, 10+i*30, 15*30+10);
		}
		for(int j=0;j<gridWidth;j++){
			g.drawLine(10, 10+j*30, 15*30+10,10+j*30);
		}
		//
	}

}
