package menu;

import gameplay.Bomb;
import gameplay.Bomberman;
import gameplay.Brick;
import gameplay.Concrete;
import gameplay.Enemy;
import gameplay.ExitWay;
import gameplay.GameState;
import gameplay.Grid;
import gameplay.Level;
import gameplay.Player;
import gameplay.PlayerInfo;
import gameplay.Game;
import gameplay.PowerUps;
import gameplay.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PauseMenu extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	JButton viewLeaderboards = new JButton("View Leaderboards");
	JButton saveGame = new JButton("Save Game");
	JButton quitGame = new JButton("Quit Game");
	JButton exitToMain = new JButton("Exit to main menu");
	JButton resumeGame = new JButton("Resume game");

	Game game;
	GameState state;
	Player player;
	Grid grid;
	Enemy enemy;


	String playersName;

	/**
	 * 
	 * @param grid
	 * @param game
	 * @param level
	 * @param enemy
	 * @param state
	 * @param player
	 * @param concrete
	 * @param brick
	 * @param powerUps
	 * @param exitWays
	 * @param bomb
	 * @param bombPass
	 * @param wallPass
	 * @param detonate
	 * @param flamePass 
	 * @param bomberman
	 */
	public PauseMenu(final Grid grid, final Game game, final Level level, final Enemy enemy, GameState state, final Player player,final Concrete concrete,final Brick brick,
			final PowerUps powerUps,final ExitWay exitWays, final Bomb bomb,final boolean bombPass,final boolean wallPass,final boolean detonate,
			final boolean flamePass,final Bomberman bomberman) {
		this.state = state;
		this.enemy = enemy;
		this.grid = grid;
		this.player = player;
		this.game = game;
		setSize(325, 230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(panel);
		setLocationRelativeTo(null);
		setVisible(true);

		panel.removeAll();
		panel.setLayout(null);
		AccountMenu.setFrameTitle("Pause Menu");

		viewLeaderboards.setBounds(72, 5, 180, 25);
		panel.add(viewLeaderboards);

		saveGame.setBounds(72, 30, 180, 25);
		panel.add(saveGame);

		quitGame.setBounds(72, 55, 180, 25);
		panel.add(quitGame);

		exitToMain.setBounds(72, 80, 180, 25);
		panel.add(exitToMain);

		resumeGame.setBounds(72, 105, 180, 25);
		panel.add(resumeGame);

		/**
		 * Quits the game
		 */
		quitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				AccountMenu.destroyFrame();
				System.exit(0);
			}

		});

		/**
		 * Quits the game and opens the main menu
		 */
		exitToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				// getContentPane().removeAll();
				
				bomberman.destroyFrameTimer();
				game.destroyPanel();
				new MainMenu(panel, PlayerInfo.getUsername());
				bomberman.dispose();

			}

		});

		/**
		 * Displays the leaderboard 
		 */
		viewLeaderboards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				// getContentPane().removeAll();
				new Leaderboards();

			}

		});

		/**
		 * Resumes the game
		 */
		resumeGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				dispose();
				game.setPauseMenuState(false);
				GameState.setState(State.RUNNING);

			}

		});

		/**
		 * Opens the save menu frame
		 */
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				try {
					new SaveGame(grid,level, enemy,player,concrete,brick,powerUps,exitWays,bomb,flamePass,bombPass,wallPass,detonate);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

	}

}
