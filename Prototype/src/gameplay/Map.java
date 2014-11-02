package gameplay;

import javax.swing.*;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Map extends JPanel implements ActionListener
{
	private int gridLength = 31;
	private int gridHeight = 13;
	
	private Player player;
	private Timer timer;
	
	public Map() {
		addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        player = new Player();

        timer = new Timer(5, this);
        timer.start();
        player.setX(50);
        player.setY(50);

	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i <= gridLength; i++)
		{
			g2d.drawLine(25*i, 0, 25 * i, 25 * gridHeight);
			
		}
		for (int j = 0; j <= gridHeight; j++)
		{
			g2d.drawLine(0, 25 * j, 25 * gridLength, 25 *j);
		}

		//g2d.setColor(Color.blue);
		//g2d.fillRect(0,0,25,25);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		player.move();
		repaint();
		
	}
	
	private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }

	public void initMap() {
		repaint();
		
	}
	
	
}
