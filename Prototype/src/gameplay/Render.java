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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import menu.MainMenu;
import menu.PauseMenu;

public class Render extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Player player;
	private Timer timer;
	private Brick brick;
	private Bomb bomb;
	private Enemy enemy;
	private GameState gameState;
	private Concrete concrete;
	private PowerUps powerups;
	private ExitWay exitway;
	private int numberOfLives;

	private Boolean isPlayerAlive;
	Boolean isNew;

	private Boolean pauseMenuOpen;
	private int count;
	
	// private String username;
	// Concrete
	// private String concrete = "concrete.png";
	// private Image imgConcrete;

	Grid grid = new Grid();

	Graphics2D g2d;

	public Render() {

		System.out.println("HELLO MY NAME IS...: " + PlayerInfo.getUsername());

		initialize();
		

		numberOfLives = 3;
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.darkGray);
		setDoubleBuffered(true);
		setFocusable(true);

	}

	public void initialize() {
		
		count = 0;
		grid.initializeGridMap();
		GameState.setState(State.RUNNING);
		brick = new Brick(grid);
		bomb = new Bomb();
		enemy = new Enemy(grid);
		concrete = new Concrete(grid);
		powerups = new PowerUps(grid);
		exitway = new ExitWay(grid);
		player = new Player(grid); 

		isPlayerAlive = true; 
		pauseMenuOpen = false;
		timer = new Timer(100, this);
		timer.start();
	}

	public void paint(Graphics g) {

		super.paint(g);

		int leftMostVisibleCell = player.getX() / 25 - 7;
		int rightMostVisibleCell = player.getX() / 25 + 7;

		if (leftMostVisibleCell < 0) {
			rightMostVisibleCell = rightMostVisibleCell - leftMostVisibleCell;
			leftMostVisibleCell = 0;
		}

		if (rightMostVisibleCell > 30) {
			leftMostVisibleCell = leftMostVisibleCell
					- (rightMostVisibleCell - 30);
			rightMostVisibleCell = 30;
		}

		//System.out.println(leftMostVisibleCell + " " + rightMostVisibleCell);

		g2d = (Graphics2D) g;
		g2d.setColor(Color.darkGray);

		// System.out.println("Your score is.... : " + Player.getScore());

		if (GameState.getState() == State.PAUSE) {

		} else {
			isPlayerAlive = false;
			for (int i = leftMostVisibleCell; i <= rightMostVisibleCell; i++) {
				for (int j = 0; j < Bomberman.HEIGHT; j++) {
					switch (grid.getContents(i, j)) {
					case PLAYER:
						g2d.drawImage(player.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						isPlayerAlive = true;
						continue;
					case BOMB:
						g2d.drawImage(bomb.getImageBomb(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;
					case CONCRETE:
						g2d.drawImage(concrete.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;
					case BRICK:
						g2d.drawImage(brick.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;
					case PLAYERANDBOMB:
						g2d.drawImage(bomb.getImageBombPlayer(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						isPlayerAlive = true;
						continue;
					case BOMBANDEXITWAY:
						g2d.drawImage(bomb.getImageBombPlayer(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;
					case EXPLODE:
						g2d.drawImage(bomb.getImageBombExplode(),
								Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						grid.setContents(i,j,Cell.EMPTY);
						continue;
					case ENEMY:
						g2d.drawImage(enemy.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;
					case BRICKANDPOWERUPS:
						g2d.drawImage(brick.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;
					case POWERUPS:
						g2d.drawImage(powerups.getBombsImage(), Bomberman.TILE_SIZE *(i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;
					case BRICKANDEXITWAY:
						g2d.drawImage(brick.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;
					case PLAYERANDEXITWAY:
						g2d.drawImage(exitway.getImagePlayerAndExitway(),
								Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						isPlayerAlive = true;
						continue;
					case EXITWAY:
						g2d.drawImage(exitway.getImageExitway(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;
					default:

						break;
					}
				}
			}
			if(count%5==0){
				enemy.aStarMovement(player.getX()/Bomberman.TILE_SIZE, player.getY()/Bomberman.TILE_SIZE);
			}
			count++;
			
			
			if (isPlayerAlive == false) {
				GameState.setState(State.PLAYERDEAD);
				GameState.setState(State.RUNNING);
				timer.stop();
				if (player.getLivesLeft() == 0) {
					System.out.println("No more lives left sorry friend");
					// START FROM LEVEL 1... ADD LEVEL LOGIC, NEXT LEVEL LIVES
					// RESTORED TO 3
				} 
				
				else {
					System.out.println("Lives Left...: ");
					player.setLivesLeft(--numberOfLives);
				}

				initialize();

				//
				// if(isNew == false){
				// g2d.dispose();
				// new Bomberman();
				// isNew = true;
				// }
			}

		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}

	public void actionPerformed(ActionEvent e) {
		if (GameState.getState() == State.RUNNING) {
			player.move();
			repaint();
		}

		else if (GameState.getState() == State.PAUSE && pauseMenuOpen == false) {
			new PauseMenu(this, gameState);
			pauseMenuOpen = true;
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

	// Need a way to destroy frame....
	public void destroyPanel() {
		setVisible(false);
		g2d.dispose();
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setPauseMenuState(boolean x) {
		pauseMenuOpen = x;

	}

}
