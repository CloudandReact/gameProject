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
	
	private static final long serialVersionUID = 1L;
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
		concrete = new Concrete();

		timer = new Timer(200, this);
		timer.start();

	}

	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.darkGray);

		g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);

		
	
		for (int p = 0; p < 775 ; p++){
			
			g2d.drawImage(concrete.getImage(), 25*p, 0, this);
		}
		for (int q = 0; q < 775 ; q++){
			
			g2d.drawImage(concrete.getImage(), 25*q, 300, this);
		}
		
		for (int l = 0; l < 345 ; l++){
			
			g2d.drawImage(concrete.getImage(), 0, 25*l, this);
		}
		for (int m= 0; m < 345 ; m++){
			
			g2d.drawImage(concrete.getImage(), 750, 25*m, this);
		}
	for (int x = 2; x < gridLength - 2; x = x+2){
	
			for(int y = 2; y < gridHeight - 2; y=y+2){
			
				g2d.drawImage(concrete.getImage(), 25*x, 25*y, this);
			}
			
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}

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
