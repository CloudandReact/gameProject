package gameplay;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;

import menu.MainMenu;
import menu.PauseMenu;
import menu.GameOverMenu;

public class Game extends JPanel implements ActionListener,Serializable {

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
	private int leftMostVisibleTile;
	private int rightMostVisibleTile;

	private int countPlayer = 0;


	private Boolean isPlayerAlive;
	private Boolean pauseMenuOpen;
	private Boolean timeOverMenuOpen;
	
	private Bomberman bomberman;

	// private String username;
	// Concrete
	// private String concrete = "concrete.png";
	// private Image imgConcrete;

	Grid grid = new Grid();

	transient Graphics2D g2d;
	
	/**
	 * This is the constructor used for new games.
	 * @param bomberman 
	 */

	public Game(Bomberman bomberman) {
		
		this.bomberman = bomberman;
		System.out.println("HELLO MY NAME IS...: " + PlayerInfo.getUsername());
		this.currentLevel = 1;
		countPlayer = 0;
		
		
		initialize();
		numberOfLives = 2;
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.darkGray);
		setDoubleBuffered(true);
		setFocusable(true);

	}
	
	// Load from a level
	public Game(int level, Bomberman bomberman) {
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
	
	// Load from a previous saved game
	public Game(Grid grid2, Level levelLoad,Enemy enemy, Brick brick2,
			Concrete concrete2, PowerUps powerUps2, ExitWay exitWays,
			Bomb bomb2, Player player2, int currentScore, int currentLevel2,
			int currentLives,int timerPlaying,boolean flamePass, boolean bombPass, boolean wallPass,boolean detonate, Bomberman bomberman){
			GameState.setState(State.RUNNING);
			grid=grid2;
			level=levelLoad;
			brick=brick2;
			concrete=concrete2;
			powerups=powerUps2;
			exitway=exitWays;
			this.enemy=enemy;
			bomb=bomb2;
			Player.livesLeft=currentLives;
			PlayerInfo.playerScore=currentScore;
			PowerUps.bombpass=bombPass;
			PowerUps.flamepass=flamePass;
			PowerUps.wallpass=wallPass;
			PowerUps.detonate=detonate;
			GameTimer.timeCount=timerPlaying;
		    
			this.currentLevel = currentLevel2;
			numberOfLives=currentLives;
			player=player2;
			this.bomberman=bomberman;
			this.isPlayerAlive = true;
			numberOfLives=currentLives;
			//PowerUps.setClevel(currentLevel2);
			addKeyListener(new TAdapter());
			setFocusable(true);
			setBackground(Color.darkGray);
			setDoubleBuffered(true);
			setFocusable(true);
			pauseMenuOpen = false;
			timeOverMenuOpen = false;
			timer = new Timer(100, this);
			timer.start();
			
			
		
	}
	public void initialize() {

		
		GameState.setState(State.RUNNING);
		count = 0;
		grid.initializeGridMap();
		
		level = new Level(currentLevel);
		brick = new Brick(grid);
		concrete = new Concrete(grid);
		exitway = new ExitWay(grid);
		enemy = new Enemy(grid,this, level);
		bomb = new Bomb(grid);
		powerups = new PowerUps(grid, this.level);
		player = new Player(grid, bomb,enemy, level);
		PowerUps.setClevel(currentLevel);
		isPlayerAlive = true;
		pauseMenuOpen = false;
		timeOverMenuOpen = false;
		timer = new Timer(100, this);
		timer.start();
		
	}

	public void paint(Graphics g) {

		super.paint(g);
		g2d = (Graphics2D) g;
		g2d.setColor(Color.darkGray);
		
		getVisibleRange();
		
		checkIfPlayerAlive();
		
		checkIfAllEnemiesDead();
		
		
		bomberman.setLivesLeft(numberOfLives);
		bomberman.setScore(PlayerInfo.playerScore);
		
		System.out.println("ENEMY COUNT..." + enemy.getEnemyCount() + "KILLED COUNT" + Bomb.getNumberOfEnemiesKilled());
		if(GameState.getState() == State.TIMEOVER){
			
		}
		
		if (GameState.getState() == State.PAUSE) {


		} else if(GameState.getState() == State.TIMEOVER){
			
		} else {

			isPlayerAlive = false;
			for (int i = leftMostVisibleTile; i <= rightMostVisibleTile; i++) {
				for (int j = 0; j < Bomberman.HEIGHT; j++) {
					switch (grid.getContents(i, j)) {
					case PLAYER:
						g2d.drawImage(player.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						isPlayerAlive = true;
						continue;
					case BOMB:
						g2d.drawImage(bomb.getImageBomb(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case CONCRETE:
						g2d.drawImage(concrete.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case BRICK:
						g2d.drawImage(brick.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case PLAYERANDBRICK:
						g2d.drawImage(player.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case PLAYERANDBRICKANDEXITWAY:
						g2d.drawImage(player.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case PLAYERANDBRICKANDPOWERUPS:
						g2d.drawImage(player.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case PLAYERANDBOMB:
						g2d.drawImage(bomb.getImageBombPlayer(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						isPlayerAlive = true;
						continue;
//					case BOMBANDEXITWAY:
//						g2d.drawImage(bomb.getImageBombPlayer(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
//						continue;
					case EXPLOSION:
						g2d.drawImage(bomb.getImageExplosion(),Bomberman.TILE_SIZE * (i - leftMostVisibleTile),Bomberman.TILE_SIZE * j, this);
						grid.setContents(i, j, Tile.EMPTY);
						continue;
					case BALLOOM:
						g2d.drawImage(enemy.getImageBalloom(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case ONEAL:
						g2d.drawImage(enemy.getImageOneal(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case DOLL:
						g2d.drawImage(enemy.getImageDoll(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;	
					case MINVO:
						g2d.drawImage(enemy.getImageMinvo(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;		
					case KONDORIA:
						g2d.drawImage(enemy.getImageKondoria(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;	
					case KONDORIAANDBRICK:
						g2d.drawImage(enemy.getImageKondoriaAndBrick(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;	
					case OVAPI:
						g2d.drawImage(enemy.getImageOvapi(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;	
					case OVAPIANDBRICK:
						g2d.drawImage(enemy.getImageOvapiAndBrick(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case PASS:
						g2d.drawImage(enemy.getImagePass(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;		   
					case PONTAN:
						g2d.drawImage(enemy.getImagePontan(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;		
					case PONTANANDBRICK:
						g2d.drawImage(enemy.getImagePontanAndBrick(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case BRICKANDPOWERUPS:
						g2d.drawImage(brick.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case POWERUPS:
						powerupPrint(i, j);
						continue;
					case BRICKANDEXITWAY:
						g2d.drawImage(brick.getImage(), Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
						continue;
					case PLAYERANDEXITWAY:
						g2d.drawImage(exitway.getImagePlayerAndExitway(),Bomberman.TILE_SIZE * (i - leftMostVisibleTile),Bomberman.TILE_SIZE * j, this);
						isPlayerAlive = true;
						
						if(GameState.getState() == State.RUNNINGANDLEVELOVER){
							GameState.setState(State.RUNNING);
							currentLevel++;
							level.setLevel(currentLevel);
							timer.stop();
							initialize();
							GameTimer.restartTimer();
						}
						
						continue;
					case EXITWAY:
						g2d.drawImage(
								exitway.getImageExitway(),
								Bomberman.TILE_SIZE * (i - leftMostVisibleTile),
								Bomberman.TILE_SIZE * j, this);
						continue;

					default:

						break;
					}
				}
			}
			
			
			if (count == 3) {
				
				enemy.move(player.getX() / Bomberman.TILE_SIZE, player.getY() / Bomberman.TILE_SIZE);
				count = 0;
			}
			count++;

		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}
	
	/**
	 * Method to print the appropriate powerup on the grid depending on which level the player is in
	 * @param i
	 * @param j
	 */
	public void powerupPrint(int i, int j){
		
		// FLAMES
		if (level.getCurrentLevel() == 1 || level.getCurrentLevel() == 7 || level.getCurrentLevel() == 15
				|| level.getCurrentLevel() == 27 || level.getCurrentLevel() == 38) {
			g2d.drawImage(powerups.getFlamesImage(),
					Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
		}

		// BOMB
		else if (level.getCurrentLevel() == 2 || level.getCurrentLevel() == 5
				|| level.getCurrentLevel() == 6 || level.getCurrentLevel() == 11 || level.getCurrentLevel() == 12
				|| level.getCurrentLevel() == 17 || level.getCurrentLevel() == 19 || level.getCurrentLevel() == 23
				|| level.getCurrentLevel() == 28 || level.getCurrentLevel() == 32) {
			g2d.drawImage(powerups.getBombsImage(),
					Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
		}
		
		// SPEED
		else if (level.getCurrentLevel() == 4) {
			g2d.drawImage(powerups.getSpeedImage(),
					Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
			
		}
		
		// DETONATOR
		else if (level.getCurrentLevel() == 3 || level.getCurrentLevel() == 8 || level.getCurrentLevel() == 13
				|| level.getCurrentLevel() == 20 || level.getCurrentLevel() == 22 || level.getCurrentLevel() == 24
				|| level.getCurrentLevel() == 29 || level.getCurrentLevel() == 33 || level.getCurrentLevel() == 27
				|| level.getCurrentLevel() == 41 || level.getCurrentLevel() == 44 || level.getCurrentLevel() == 48) {
			g2d.drawImage(powerups.getDetonatorsImage(),
					Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
		}
		
		// BOMBPASS
		else if (level.getCurrentLevel() == 9 || level.getCurrentLevel() == 14 || level.getCurrentLevel() == 18
				|| level.getCurrentLevel() == 21 || level.getCurrentLevel() == 25 || level.getCurrentLevel() == 35
				|| level.getCurrentLevel() == 43 || level.getCurrentLevel() == 47) {
			g2d.drawImage(powerups.getBombPassImage(),
					Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
		}
		
		// WALLPASS
		else if (level.getCurrentLevel() == 10 || level.getCurrentLevel() == 16 || level.getCurrentLevel() == 31
				|| level.getCurrentLevel() == 39 || level.getCurrentLevel() == 42 || level.getCurrentLevel() == 46) {
			g2d.drawImage(powerups.getWallPassImage(),
					Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
			
		}
		// FLAMEPASS
		else if (level.getCurrentLevel() == 30 || level.getCurrentLevel() == 36 || level.getCurrentLevel() == 49) {
			g2d.drawImage(powerups.getFlamePassImage(),
					Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
		}
		
		// MYSTERY
		else if (level.getCurrentLevel() == 26 || level.getCurrentLevel() == 34 || level.getCurrentLevel() == 40
				|| level.getCurrentLevel() == 45 || level.getCurrentLevel() == 50) {
			// g2d.drawImage(powerups.getMysteryImage(),
			// Bomberman.TILE_SIZE * (i - leftMostVisibleTile), Bomberman.TILE_SIZE * j, this);
			// becomes immune to monsters and explosions
			// (not included in our gameplay so I dont know what
			// to do with this)
		}
	}
	
	private void checkIfAllEnemiesDead() {
		if (enemy.getEnemyCount() == Bomb.getNumberOfEnemiesKilled()){
			GameState.setState(State.RUNNINGANDLEVELOVER);		
			System.out.println("LEVEL IS OVER HEAD TO THE EXITWAY");
		}
		
	}

	private void getVisibleRange(){
		
		leftMostVisibleTile = player.getX() / 25 - 7;
		rightMostVisibleTile = player.getX() / 25 + 7;

		if (leftMostVisibleTile < 0) {
			rightMostVisibleTile = rightMostVisibleTile - leftMostVisibleTile;
			leftMostVisibleTile = 0;
		}

		if (rightMostVisibleTile > 30) {
			leftMostVisibleTile = leftMostVisibleTile
					- (rightMostVisibleTile - 30);
			rightMostVisibleTile = 30;
		} 
		
	}
	
	private void checkIfPlayerAlive() {
		Player.setLivesLeft(numberOfLives);
		if (isPlayerAlive == false) {
			GameState.setState(State.PLAYERDEAD);
			System.out.println("Player dies here!!");
			GameState.setState(State.RUNNING);
			timer.stop();
			powerups.setWallpass(false);
			powerups.setBombpass(false);
			powerups.setFlamepass(false);
			powerups.setDetonate(false);
			
			System.out.println("Lives Left...: " + Player.getLivesLeft());
			if (Player.getLivesLeft() == 0) {
				System.out.println("No more lives left sorry friend");
				// START FROM LEVEL 1... ADD LEVEL LOGIC, NEXT LEVEL LIVES
				// RESTORED TO 3
				PlayerInfo.playerScore -= bomb.getTotalGameScore();
				bomb.setTotalGameScore(0);
				GameState.setState(State.TIMEOVER);
				System.out.println(GameState.getState());
				new GameOverMenu(grid, this, gameState, player, bomberman, level);
				timeOverMenuOpen = true;
			}

			else {
				numberOfLives--;
				Player.setLivesLeft(numberOfLives);

			}

			initialize();
		}
		
	}

	public void setIsPlayerAlive(Boolean bool){
		isPlayerAlive = bool;
	}

	public void actionPerformed(ActionEvent e) {

		if ((GameState.getState() == State.RUNNING || GameState.getState() == State.RUNNINGANDLEVELOVER)) {
			player.move();
			repaint();
		}

		else if (GameState.getState() == State.PAUSE && pauseMenuOpen == false) {

			new PauseMenu(grid,level, enemy, gameState, player,concrete,brick, powerups, exitway,bomb,
					PowerUps.getBombpass(),PowerUps.getWallpass(),PowerUps.haveDetonate(),PowerUps.getFlamepass(), bomberman);
			pauseMenuOpen = true;
		}
		else if(GameState.getState() == State.TIMEOVER && timeOverMenuOpen == false){
			new GameOverMenu(grid, this, gameState, player, bomberman, level);
			timeOverMenuOpen = true;
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

	public void setPauseMenuState(boolean isPauseMenuOpen) {
		this.pauseMenuOpen = isPauseMenuOpen;
	}
	public void setTimeOverState(boolean isTimeOverMenuOpen){
		this.timeOverMenuOpen = isTimeOverMenuOpen;
	}
	public Boolean getTimeOverState(){
		return timeOverMenuOpen;
	}

}