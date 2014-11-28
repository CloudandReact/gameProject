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
	private Level level;
	
	private long currentTime;
	private int currentLevel;
	private int numberOfLives;
	private int count;
	private int leftMostVisibleCell;
	private int rightMostVisibleCell;


	private Boolean isPlayerAlive;
	private Boolean pauseMenuOpen;

	
	private Bomberman bomberman;

	// private String username;
	// Concrete
	// private String concrete = "concrete.png";
	// private Image imgConcrete;

	Grid grid = new Grid();

	Graphics2D g2d;

	public Render(Bomberman bomberman) {
		
		this.bomberman = bomberman;
		System.out.println("HELLO MY NAME IS...: " + PlayerInfo.getUsername());
		this.currentLevel = 1;
		PowerUps.setClevel(1);
		
		initialize();
		numberOfLives = 2;
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.darkGray);
		setDoubleBuffered(true);
		setFocusable(true);

	}
	
	public Render(int level, Bomberman bomberman) {
		this.bomberman = bomberman;
		this.currentLevel = level;
		initialize();
		numberOfLives = 2;
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.darkGray);
		setDoubleBuffered(true);
		setFocusable(true);
		
	}

	public void initialize() {

		
		GameState.setState(State.RUNNING);
		count = 0;
		
		grid.initializeGridMap();
		
		level = new Level(currentLevel);
		
		brick = new Brick(grid);
		bomb = new Bomb(grid);
		concrete = new Concrete(grid);
		powerups = new PowerUps(grid, player);
		exitway = new ExitWay(grid);
		player = new Player(grid, bomb);
		enemy = new Enemy(grid,this, level);
		PowerUps.setClevel(currentLevel);
		isPlayerAlive = true;
		pauseMenuOpen = false;
		timer = new Timer(100, this);
		timer.start();
		
	}

	public void paint(Graphics g) {

		super.paint(g);
		g2d = (Graphics2D) g;
		g2d.setColor(Color.darkGray);
		
		getVisibleRange();
		
		checkIfPlayerAlive();
		
		

		if (player.getBombStatus() && (currentTime = System.currentTimeMillis()) - player.getInitialTime() >= 2000) {

			bomb.explode();
			player.setBombStatus(false);
		}

		if (GameState.getState() == State.PAUSE) {

		} 
		
		else {
			isPlayerAlive = false;
			for (int i = leftMostVisibleCell; i <= rightMostVisibleCell; i++) {
				for (int j = 0; j < Bomberman.HEIGHT; j++) {
					switch (grid.getContents(i, j)) {
					case PLAYER:
						g2d.drawImage(player.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						isPlayerAlive = true;
						continue;
					case BOMB:
						g2d.drawImage(bomb.getImageBomb(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;
					case CONCRETE:
						g2d.drawImage(concrete.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;
					case BRICK:
						g2d.drawImage(brick.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;
					case PLAYERANDBOMB:
						g2d.drawImage(bomb.getImageBombPlayer(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						isPlayerAlive = true;
						continue;
					case BOMBANDEXITWAY:
						g2d.drawImage(bomb.getImageBombPlayer(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;
					case EXPLODE:
						g2d.drawImage(bomb.getImageBombExplode(),Bomberman.TILE_SIZE * (i - leftMostVisibleCell),Bomberman.TILE_SIZE * j, this);
						grid.setContents(i, j, Cell.EMPTY);
						continue;
					case BALLOOM:
						g2d.drawImage(enemy.getImageBalloom(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;
					case ONEAL:
						g2d.drawImage(enemy.getImageOneal(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;
					case DOLL:
						g2d.drawImage(enemy.getImageDoll(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;	
					case MINVO:
						g2d.drawImage(enemy.getImageMinvo(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;		
					case KONDORIA:
						g2d.drawImage(enemy.getImageKondoria(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;	
					case KONDORIAANDBRICK:
						g2d.drawImage(enemy.getImageKondoriaAndBrick(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;	
					case OVAPI:
						g2d.drawImage(enemy.getImageOvapi(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;	
					case OVAPIANDBRICK:
						g2d.drawImage(enemy.getImageOvapiAndBrick(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;
					case PASS:
						g2d.drawImage(enemy.getImagePass(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;		   
					case PONTAN:
						g2d.drawImage(enemy.getImagePontan(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;		
					case PONTANANDBRICK:
						g2d.drawImage(enemy.getImagePontanAndBrick(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;
					case BRICKANDPOWERUPS:
						g2d.drawImage(brick.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleCell), Bomberman.TILE_SIZE * j, this);
						continue;
					case POWERUPS:
						
						int level = 1;

						// FLAMES
						if (level == 1 || level == 7 || level == 15
								|| level == 27 || level == 38) {
							g2d.drawImage(powerups.getFlamesImage(), Bomberman.TILE_SIZE
									* i, Bomberman.TILE_SIZE * j, this);
							continue;
						}

						// BOMB
						if (level == 1 || level == 2 || level == 5
								|| level == 6 || level == 11 || level == 12
								|| level == 17 || level == 19 || level == 23
								|| level == 28 || level == 32) {
							g2d.drawImage(powerups.getBombsImage(), Bomberman.TILE_SIZE
									* i, Bomberman.TILE_SIZE * j, this);
							continue;
						}
						// SPEED
						else if (level == 4) {
							g2d.drawImage(powerups.getSpeedImage(), Bomberman.TILE_SIZE
									* i, Bomberman.TILE_SIZE * j, this);
							continue;
						}
						// DETONATOR
						else if (level == 3 || level == 8 || level == 13
								|| level == 20 || level == 22 || level == 24
								|| level == 29 || level == 33 || level == 27
								|| level == 41 || level == 44 || level == 48) {
							g2d.drawImage(powerups.getDetonatorsImage(),
									Bomberman.TILE_SIZE * i, Bomberman.TILE_SIZE * j, this);
							continue;
						}
						// BOMBPASS
						else if (level == 9 || level == 14 || level == 18
								|| level == 21 || level == 25 || level == 35
								|| level == 43 || level == 47) {
							g2d.drawImage(powerups.getBombPassImage(),
									Bomberman.TILE_SIZE * i, Bomberman.TILE_SIZE * j, this);
							continue;
						}
						// WALLPASS
						else if (level == 10 || level == 16 || level == 31
								|| level == 39 || level == 42 || level == 46) {
							g2d.drawImage(powerups.getWallPassImage(),
									Bomberman.TILE_SIZE * i, Bomberman.TILE_SIZE * j, this);
							continue;
						}
						// FLAMEPASS
						else if (level == 30 || level == 36 || level == 49) {
							g2d.drawImage(powerups.getFlamePassImage(),
									Bomberman.TILE_SIZE * i, Bomberman.TILE_SIZE * j, this);
							continue;
						}
						// MYSTERY
						else if (level == 26 || level == 34 || level == 40
								|| level == 45 || level == 50) {
							// g2d.drawImage(powerups.getMysteryImage(),
							// Bomberman.TILE_SIZE * i, Bomberman.TILE_SIZE * j, this);
							// continue;
							// becomes immune to monsters and explosions
							// (not included in our gameplay so I dont know what
							// to do with this)
						}

						continue;
					case BRICKANDEXITWAY:
						g2d.drawImage(brick.getImage(), Bomberman.TILE_SIZE
								* (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;
					case PLAYERANDEXITWAY:
						g2d.drawImage(
								exitway.getImagePlayerAndExitway(),
								Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						isPlayerAlive = true;
						continue;
					case EXITWAY:
						g2d.drawImage(
								exitway.getImageExitway(),
								Bomberman.TILE_SIZE * (i - leftMostVisibleCell),
								Bomberman.TILE_SIZE * j, this);
						continue;

					default:

						break;
					}
				}
			}
			
			//enemy.move(player.getX() / Bomberman.TILE_SIZE, player.getY() / Bomberman.TILE_SIZE);
			//\enemy.aStarMovement(player.getX() / Bomberman.TILE_SIZE, player.getY() / Bomberman.TILE_SIZE, Cell.KONDORIA);
			
			if (count == 3) {
				
				enemy.move(player.getX() / Bomberman.TILE_SIZE, player.getY() / Bomberman.TILE_SIZE);
				count = 0;
			}
			count++;

		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}
	
	private void getVisibleRange(){
		
		leftMostVisibleCell = player.getX() / 25 - 7;
		rightMostVisibleCell = player.getX() / 25 + 7;

		if (leftMostVisibleCell < 0) {
			rightMostVisibleCell = rightMostVisibleCell - leftMostVisibleCell;
			leftMostVisibleCell = 0;
		}

		if (rightMostVisibleCell > 30) {
			leftMostVisibleCell = leftMostVisibleCell
					- (rightMostVisibleCell - 30);
			rightMostVisibleCell = 30;
		} 
		
	}
	
	private void checkIfPlayerAlive() {
		Player.setLivesLeft(numberOfLives);
		if (isPlayerAlive == false) {
			GameState.setState(State.PLAYERDEAD);
			GameState.setState(State.RUNNING);
			timer.stop();
			powerups.setWallpass(false);
			powerups.setBombpass(false);
			powerups.setWallpass(false);
			powerups.setDetonate(false);
			
			System.out.println("Lives Left...: " + Player.getLivesLeft());
			if (Player.getLivesLeft() == 0) {
				System.out.println("No more lives left sorry friend");
				// START FROM LEVEL 1... ADD LEVEL LOGIC, NEXT LEVEL LIVES
				// RESTORED TO 3
				PlayerInfo.playerScore -= bomb.getTotalGameScore();
				bomb.setTotalGameScore(0);
			}

			else {
				numberOfLives--;
				Player.setLivesLeft(numberOfLives);

			}
			
			initialize();
		}
		
		//System.out.println("number of lives..." + numberOfLives);

		
		
	}

	public void setIsPlayerAlive(Boolean bool){
		isPlayerAlive = bool;
	}

	public void actionPerformed(ActionEvent e) {
		if (GameState.getState() == State.RUNNING) {
			player.move();
			repaint();
		}

		else if (GameState.getState() == State.PAUSE && pauseMenuOpen == false) {
			new PauseMenu(grid,this, gameState, player);
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
