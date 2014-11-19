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
	private int dimension = 25;
	
	private Player player;
	private Timer timer;
	private Brick brick;
	private Bomb bomb;
	private Enemy enemy;
	private GameState gameState;
	private Concrete concrete;
	private Boolean isPaused;
	// Concrete
	// private String concrete = "concrete.png";
	// private Image imgConcrete;
	
	
	Grid grid = new Grid();
	Cell[][] gridMap = grid.getGridMap();
	
	Graphics2D g2d;	
		
	public Render() {
		gameState = new GameState();
		brick = new Brick(gridMap);
		bomb = new Bomb();
		enemy = new Enemy(gridMap);
		concrete = new Concrete(gridMap);
		player = new Player(gridMap, gameState);
		
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.darkGray);
		setDoubleBuffered(true);
		setFocusable(true);
				
		isPaused = false;
		timer = new Timer(100, this);
		timer.start();
		
		
	}

	public void paint(Graphics g) {
	
		super.paint(g);
		
		g2d = (Graphics2D) g;
		g2d.setColor(Color.darkGray);
		
		System.out.println("Your score is.... : " + Player.getScore());
		
		if(gameState.getState() == State.PAUSE){
			
		}
		else{
			for(int i = 0; i < 33; i++){
				for(int j = 0; j < 15; j++){
			        switch (gridMap[i][j]) {
			        case PLAYER:
			    		g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
			        	continue;
			        case BOMB: 
						g2d.drawImage(bomb.getImageBomb(), dimension * i , dimension * j, this);
						continue;
			        case CONCRETE:
						g2d.drawImage(concrete.getImage(), dimension * i, dimension * j, this);
						continue;
			        case BRICK:
						g2d.drawImage(brick.getImage(), dimension * i, dimension * j, this);
						continue;
			        case PLAYERANDBOMB:
						g2d.drawImage(bomb.getImageBombPlayer(), dimension * i , dimension * j, this);
			        	continue;
			        case EXPLODE:
			        	g2d.drawImage(bomb.getImageBombExplode(), dimension * i , dimension * j, this);
			        	gridMap[i][j] = Cell.EMPTY;
			        	continue;
			        case ENEMY:
			        	g2d.drawImage(enemy.getImage(), dimension * i , dimension * j, this);
			        	continue;
			        default:
						break;          	
			        }
				}
			}
			enemy.move();
			
		}
		
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
		

	}

		
	public void actionPerformed(ActionEvent e) {
		if(gameState.getState() == State.RUNNING){
			player.move();
			repaint();
		}

		

	}

	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
			
		}

		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
	}


}
	


