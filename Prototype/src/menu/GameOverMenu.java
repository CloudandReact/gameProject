package menu;

import gameplay.Bomberman;
import gameplay.GameState;
import gameplay.GameTimer;
import gameplay.Grid;
import gameplay.Level;
import gameplay.Player;
import gameplay.PlayerInfo;
import gameplay.Game;
import gameplay.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOverMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	JButton viewLeaderboards = new JButton("View Leaderboards");
	JButton saveGame = new JButton("Save Game");
	JButton quitGame = new JButton("Quit Game");
	JButton exitToMain = new JButton("Exit to main menu");
	JButton restartLevel = new JButton("Restart Level");

	public Game game;
	GameState state;
	Player player;
	Grid grid;
	private Level level;

	String playersName;

	/**
	 * 
	 * @param grid is the grid for the whole game
	 * @param game is required to destroy the game panel when player presses exitToMain button
	 * @param gamestate is required to change the game state when player restarts game
	 * @param player is required to get players username 
	 * @param bomberman is required to restart a level and to destroy the timer frame
	 * @param level is required to get which level the player is in
	 */
	public GameOverMenu(final Grid grid, Game g, GameState gamestate, Player player,
			final Bomberman bomberman, final Level level) {
		this.state = gamestate;
		this.grid = grid;
		this.player = player;
		this.game = g;
		setSize(325, 230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(panel);
		setLocationRelativeTo(null);
		setVisible(true);

		panel.removeAll();
		panel.setLayout(null);
		AccountMenu.setFrameTitle("Time Over Menu");

		viewLeaderboards.setBounds(72, 30, 180, 25);
		panel.add(viewLeaderboards);

		quitGame.setBounds(72, 55, 180, 25);
		panel.add(quitGame);

		exitToMain.setBounds(72, 80, 180, 25);
		panel.add(exitToMain);

		restartLevel.setBounds(72, 105, 180, 25);
		panel.add(restartLevel);

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
		 * Quits the game and displays main menu
		 */
		exitToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bomberman.stopTimer();
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
				new Leaderboards();

			}

		});

		/**
		 * Restarts the same level
		 */
		restartLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerInfo.setUsername(playersName);

				getContentPane().removeAll();
				dispose();
				new Bomberman(level.getCurrentLevel());
				GameState.setState(State.RUNNING);
				GameTimer.restartTimer();

			}

		});

	}

}
