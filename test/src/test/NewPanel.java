package test;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Graphics;

public class NewPanel extends JPanel{
	int gridHeight=13;
	int gridWidth=31;
	Player newPlayer= new Player(10,10);
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
		for(int i=0;i<gridWidth;i++){
			//numbers can be modified as required to fit the grid
			// 10,10 is the starting point of our grid
			//draws the line in horizontal from 10,10 then to 10+13*28 in horizontal
			//the vertical increments by 28 each time they are both the same since line only in horizontal
			g.drawLine(10, 10+i*28, 10+13*28, 10+i*28);
		}
		for(int j=0;j<=gridHeight;j++){   
			g.drawLine(10+j*28, 10, 10+j*28,30*28+10);
		}
		//draw player;
		
		
	}

}
