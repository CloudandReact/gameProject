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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	JButton viewLeaderboards = new JButton("View Leaderboards");
	JButton saveGame = new JButton("Save Game");
	JButton quitGame = new JButton("Quit Game");
	JButton exitToMain = new JButton("Exit to main menu");
	JButton resumeGame = new JButton("Resume game");

	// JButton loadLevelButton = new JButton("Load Level");

	Game game;
	GameState state;
	Player player;
	Grid grid;
	Enemy enemy;


	String playersName;
	//add game if neccessary

	public PauseMenu(final Grid grid, final Game game, final Level level, final Enemy enemy, GameState s, final Player p,final Concrete concrete,final Brick brick,
			final PowerUps powerUps,final ExitWay exitWays, final Bomb bomb,final boolean bombPass,final boolean wallPass,final boolean detonate,
			final boolean flamePass,final Bomberman bomberman) {
	 //public PauseMenu(final Grid grid, final Enemy enemy, final Render r, GameState s, Player p,final Bomberman bomberman){
		this.state = s;
		this.enemy = enemy;
		this.grid = grid;
		this.player = p;
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
				bomberman.destroyFrameTimer();
				game.destroyPanel();
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

		resumeGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				dispose();
				player.initializeTimer();
				game.setPauseMenuState(false);
				GameState.setState(State.RUNNING);

			}

		});

		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				try {
					new SaveGame(grid,level, enemy,p,concrete,brick,powerUps,exitWays,bomb,flamePass,bombPass,wallPass,detonate);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

	}

}
