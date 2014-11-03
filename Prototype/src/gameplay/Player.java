package gameplay;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

	private String player = "sponge.jpg";

	private int dx;
	private int dy;
	private int x;
	private int y;
	private Image image;

	public Player() {
		loadImage();
		this.setX(0);
		this.setY(0);
	}

	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(player));
		image = ii.getImage();
	}

	public void move() {
		
		if (((x + dx) % 50 == 25) && ((y + dy) % 50 == 25))
		{
				
		}
		else
		{
			if (x + dx < 0)
				x = 0;
			else if (x + dx >= 750)
				x = 750;
			else
				x += dx;

			if ((y + dy) < 0)
			{
				y = 0;
			}

			else if ((y + dy) >= 300) 
			{
				y = 300;
			}

			else
				y += dy;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int xNew) {
		x = xNew;
	}

	public void setY(int yNew) {
		y = yNew;
	}

	public Image getImage() {
		return image;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -25;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 25;
		}

		if (key == KeyEvent.VK_UP) {
			dy = -25;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 25;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

}