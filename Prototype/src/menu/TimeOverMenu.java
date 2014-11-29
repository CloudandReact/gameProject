package menu;

import gameplay.Bomberman;
import gameplay.GameState;
import gameplay.GameTimer;
import gameplay.Grid;
import gameplay.Player;
import gameplay.PlayerInfo;
import gameplay.Render;
import gameplay.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TimeOverMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	JButton viewLeaderboards = new JButton("View Leaderboards");
	JButton saveGame = new JButton("Save Game");
	JButton quitGame = new JButton("Quit Game");
	JButton exitToMain = new JButton("Exit to main menu");
	JButton restartLevel = new JButton("Restart Level");

	// JButton loadLevelButton = new JButton("Load Level");

	public Render render;
	GameState state;
	Player player;
	Grid grid;

	String playersName;

	public TimeOverMenu(final Grid grid, Render r, GameState s, Player p,
			final Bomberman bomberman) {
		this.state = s;
		this.grid = grid;
		this.player = p;
		this.render = r;
		setSize(325, 230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(panel);
		setLocationRelativeTo(null);
		setVisible(true);

		panel.removeAll();
		panel.setLayout(null);
		AccountMenu.setFrameTitle("Time Over Menu");

		viewLeaderboards.setBounds(72, 5, 180, 25);
		panel.add(viewLeaderboards);

		saveGame.setBounds(72, 30, 180, 25);
		panel.add(saveGame);

		quitGame.setBounds(72, 55, 180, 25);
		panel.add(quitGame);

		exitToMain.setBounds(72, 80, 180, 25);
		panel.add(exitToMain);

		restartLevel.setBounds(72, 105, 180, 25);
		panel.add(restartLevel);

		quitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				AccountMenu.destroyFrame();
				System.exit(0);
			}

		});

		exitToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				// getContentPane().removeAll();

				render.destroyPanel();
				new MainMenu(panel, PlayerInfo.getUsername());
				bomberman.dispose();

			}

		});

		viewLeaderboards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				// getContentPane().removeAll();
				new Leaderboards();

			}

		});

		restartLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerInfo.setUsername(playersName);

				getContentPane().removeAll();
				dispose();
				render.initialize();
				// render.setPauseMenuState(false);
				GameState.setState(State.RUNNING);
				GameTimer.restartTimer();

				// Execute when button is pressed
				// getContentPane().removeAll();
				// dispose();
				// player.initializeTimer();
				// render.setPauseMenuState(false);
				// GameState.setState(State.RUNNING);

				// Execute when button is pressed

			}

		});
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				try {
					new SaveGame(grid);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

	}

}
