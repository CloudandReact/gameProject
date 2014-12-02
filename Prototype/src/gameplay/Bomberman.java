package gameplay;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.io.Serializable;

import menu.AccountMenu;

public class Bomberman extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 31;
	public static int HEIGHT = 13;
	public static int TILE_SIZE = 25;
	public Player player;
	private GameTimer listener;
	private JFrame frameTimer = new JFrame("Bomberman");
	private Timer timerG = new Timer(1000, listener);


	/**
	 * Constructor that initializes a <code>Bomberman</code> object that
	 * initializes the game frame and the second frame.
	 */
	public Bomberman() {

		secondFrame();
		add(new Game(this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(15 * TILE_SIZE, HEIGHT * TILE_SIZE + 22);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Constructor that initializes a <code>Bomberman</code> object that
	 * initializes the game frame and the second frame.
	 * 
	 * @param level
	 *            an object of <code>Level</code> which contains the required
	 *            information about the level.
	 */
	public Bomberman(int level) {
		secondFrame();
		add(new Game(level, this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(15 * TILE_SIZE, HEIGHT * TILE_SIZE + 22);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Constructor that initializes a <code>Bomberman</code> object
	 * 
	 * @param grid
	 *            an object of <code>Grid</code> which contains the game's map
	 *            and required methods access and alter it.
	 * @param levelLoad
	 *            an object of <code>Level</code> which contains the required
	 *            information about the level.
	 * @param enemy
	 *            an object of <code>Enemy</code> which contains information
	 *            about the enemies on the map.
	 * @param brick
	 *            an object of <code>Brick</code> which contains information
	 *            regarding bricks
	 * @param concrete
	 *            an object of <code>Concrete</code> which contains information
	 *            regarding concrete walls
	 * @param powerUps
	 *            an object of <code>PowerUps</code> which contains information
	 *            regarding concrete walls
	 * @param exitWays
	 *            an object of <code>Exitways</code> which contains information
	 *            regarding exitways
	 * @param bomb
	 *            an object of <code>Bomb</code> which contains information
	 *            regarding bomb
	 * @param player2
	 * 
	 * @param currentScore
	 *            the player's current score
	 * @param currentLevel
	 *            the
	 * @param currentLives
	 *            the player's number of lives left
	 * @param timer
	 *            the time left
	 * @param flamePass
	 *            true if the player has the flame-pass power up
	 * @param bombPass
	 *            true if the player has the bomb-pass power up
	 * @param wallPass
	 *            true if the player has the wall-pass power up
	 * @param detonate
	 *            true if the player has the detonator power up
	 */
	// changes
	public Bomberman(Grid grid, Level levelLoad, Enemy enemy, Brick brick,
			Concrete concrete, PowerUps powerUps, ExitWay exitWays, Bomb bomb,
			Player player2, int currentScore, int currentLevel,
			int currentLives, int timer, boolean flamePass, boolean bombPass,
			boolean wallPass, boolean detonate) {

		secondFrame();

		add(new Game(grid, levelLoad, enemy, brick, concrete, powerUps,
				exitWays, bomb, player2, currentScore, currentLevel,
				currentLives, timer, flamePass, bombPass, wallPass, detonate,
				this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(15 * TILE_SIZE, HEIGHT * TILE_SIZE + 22);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Method that makes the second frame which contains the time count, number
	 * of lives left of the player and the current score. It also displays a
	 * string "Game Over" when the player loses all three lives or when time is
	 * over. This method
	 */
	public void secondFrame() {
		frameTimer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTimer.setLayout(new FlowLayout());
		JPanel timePanel = new JPanel();
		JPanel livesPanel = new JPanel();
		JPanel scorePanel = new JPanel();
		JPanel timeOverPanel = new JPanel();
		JLabel timeLabel = new JLabel("Time Left: 200");
		JLabel livesLabel = new JLabel("Lives Left: 2");
		JLabel scoreLabel = new JLabel("Score: 0");
		JLabel timeOverLabel = new JLabel("");
		timePanel.add(timeLabel);
		livesPanel.add(livesLabel);
		scorePanel.add(scoreLabel);
		timeOverPanel.add(timeOverLabel);
		frameTimer.add(timePanel);
		frameTimer.add(livesPanel);
		frameTimer.add(scorePanel);
		frameTimer.add(timeOverPanel);
		listener = new GameTimer(timeLabel, livesLabel, scoreLabel,
				timeOverLabel);
		timerG.start();
		frameTimer.setSize(300, 100);
		frameTimer.setVisible(true);
	}

	/**
	 * Sets the number of lives left in the second frame.
	 * 
	 * @param lives
	 */
	public void setLivesLeft(int lives) {
		listener.setLives(lives);
	}

	/**
	 * Sets the score of the player to display in the second frame.
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		listener.setScore(score);
	}

	/**
	 * Sets a string "Time Over" in the second frame when game is over.
	 */
	public void setGameOver() {
		listener.setTimeOver("Game Over");
	}

	/**
	 * Destroys the game frame.
	 */
	public void destroyFrame() {
		setVisible(false);
		dispose();
	}

	/**
	 * Destroys the second frame.
	 */
	public void destroyFrameTimer() {
		frameTimer.setVisible(false);
		frameTimer.dispose();
	}

	/**
	 * Stops the game timer when required.
	 */
	public void stopTimer() {
		timerG.stop();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Bomberman();
	}

}