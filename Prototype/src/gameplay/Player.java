package gameplay;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player {
	
	private String player = "player.png";
	
	private int dx;
	private int dy;
	private int x;
	private int y;
	private Image image;
	
	
	public Player() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(player));
        image = ii.getImage();
        this.setX(0);
        this.setY(0);
	}
	
	public void move(){
		y += dx;
		x += dy;
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return x;
	}
	
	public void setX(int xNew) {
		x = xNew;
	}
	
	public void setY(int yNew) {
		y = yNew;
	}
	
	public Image getImage(){
		return image;
	}
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
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
