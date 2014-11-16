package gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Render extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int gridLength = 32;
	private int gridHeight = 14;

	private Player player;
	private Timer timer;
	private Brick brick;
	private Bomb bomb;
	private static Boolean isBombPlaced = false;
	
	// Concrete
	private String concrete = "concrete.png";
	private Image imgConcrete;
	
	Grid grid = new Grid();
	Cell[][] gridMap = grid.getGridMap();
	
	Graphics2D g2d;
	
	

	public Render() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.darkGray);
		setDoubleBuffered(true);
		setFocusable(true);
		
		
		player = new Player(gridMap);
		brick = new Brick(gridMap);
		bomb = new Bomb();
		
		this.loadConcrete();
		//concrete = new Concrete();

		timer = new Timer(100, this);
		timer.start();

	}

	public void paint(Graphics g) {

		super.paint(g);

		g2d = (Graphics2D) g;
		g2d.setColor(Color.darkGray);
		g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
		
		if(isBombPlaced){
			g2d.drawImage(bomb.getImage(), player.getX(), player.getY(), this);
			
		}
		isBombPlaced = false;
		
		// Border
		createBorder();
		// Concrete **NOTE WE CAN CHANGE THIS: i.e. drawGrid**
		placeConcrete();
		// Bricks
		placeBricks();
	
		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}
	
	private void placeBricks(){	
		for (int x = 0; x <= gridLength; x++) {
			for (int y = 0; y <= gridHeight; y++) {
				if (gridMap[x][y] == Cell.BRICK) {
					g2d.drawImage(brick.getImage(), 25 * x, 25 * y, this);
				}
			}
		}
	}
	
	private void createBorder(){
		for (int x = 0; x <= gridLength; x++) {
			for (int y = 0; y <= gridHeight; y++) {
				if ((x == 0) || (x == gridLength) || (y == 0) || (y == gridHeight)){
					g2d.drawImage(imgConcrete, 25 * x, 25 * y, this);
					gridMap[x][y] = Cell.CONCRETE;
				}
			}
		}	
	}
	
	private void placeConcrete(){
		for (int x = 2; x < gridLength; x = x + 2) {
			for (int y = 2; y < gridHeight; y = y + 2) {
				gridMap[x][y] = Cell.CONCRETE;
				g2d.drawImage(imgConcrete, 25 * x, 25 * y, this);
			}
		}
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
	private void loadConcrete() {
		ImageIcon iC = new ImageIcon(getClass().getResource(concrete));
		imgConcrete = iC.getImage();
	}
	
	public static void setIsBombPlaced(Boolean x){
		isBombPlaced = x;
		
	}

}
	


