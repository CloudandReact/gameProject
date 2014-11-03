package gameplay;

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

public class Map extends JPanel implements ActionListener {
	private int gridLength = 31;
	private int gridHeight = 13;

	private Player player;
	private Timer timer;
	private Concrete concrete;

	public Map() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.darkGray);
		setDoubleBuffered(true);
		setFocusable(true);

		player = new Player();

		timer = new Timer(125, this);
		timer.start();
		

	}

	@Override
<<<<<<< HEAD
	
	 public void paint(Graphics g) 
	{

		  super.paint(g);

		  Graphics2D g2d = (Graphics2D) g;

		  for (int i = 0; i <= gridLength; i++) {
		   g2d.drawLine(25 * i, 0, 25 * i, 25 * gridHeight);

		  }
		  for (int j = 0; j <= gridHeight; j++) {
		   g2d.drawLine(0, 25 * j, 25 * gridLength, 25 * j);
		  }

		  g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);

		  Toolkit.getDefaultToolkit().sync();
		  g.dispose();

		  
	 }
	
=======
	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.darkGray);
		
		for (int i = 0; i <= gridLength; i++) {
			g2d.drawLine(25 * i, 0, 25 * i, 25 * gridHeight);

		}
		for (int j = 0; j <= gridHeight; j++) {
			g2d.drawLine(0, 25 * j, 25 * gridLength, 25 * j);
		}

		g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
		//g2d.drawImage(concrete.getImage(), 50, 50, this);
		
	/*	for (int x = 1; x <= gridLength; x++){
			for(int y = 1; y <= gridHeight; y++){
				g2d.drawImage(concrete.getImage(), 25*x, 25*y, this);
			}
			
		}*/
	

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

		
	}

>>>>>>> FETCH_HEAD
	public void actionPerformed(ActionEvent e) {
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
