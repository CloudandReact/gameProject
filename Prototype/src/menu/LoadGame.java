package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;

import org.apache.commons.csv.CSVRecord;

import gameplay.Bomberman;
import gameplay.Grid;
import gameplay.PlayerInfo;
import gameplay.Bomb;
import gameplay.Brick;
import gameplay.Concrete;
import gameplay.Enemy;

import gameplay.ExitWay;

import gameplay.Level;
import gameplay.Player;

import gameplay.PowerUps;

/**
 * LoadGame implements Serializable thereby able to load a game and it impmenets
 * the gui neccesary for the user to load a game
 * 
 * @author elliot
 *
 */

public class LoadGame extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	JLabel noGamesFound = new JLabel("Error no loaded games please go back");

	JButton backButton = new JButton("Back");
	JButton playButton = new JButton("Play");

	// load how many games then have a textbox to enter
	JTextField gameToPlayText = new JTextField();
	JPanel panelA;

	private int numberOfGames;
	private ArrayList<CSVRecord> loadedGameArrayList;
	// grid loadedGrid
	private Grid loadedGrid = new Grid();
	private Grid grid;
	private Level level;
	private Brick brick;
	private Concrete concrete;
	private PowerUps powerUps;
	private ExitWay exitWays;
	private Bomb bomb;
	private Player player;
	private int currentScore;
	private int currentLevel;
	private int currentLives;
	private int timer;
	private boolean loadFlamePass;
	private boolean loadBombPass;
	private boolean loadDetonate;
	private boolean loadWallPass;
	private Enemy enemy;

	/**
	 * The LoadGame method sets up the loadGame gui and with the play button and
	 * a textbox for loading the user's input file
	 * 
	 * @param panel
	 *            Stores the panel destroys the old one and creates the LoadGame
	 *            panel
	 * @throws IOException
	 */

	public LoadGame(JPanel panel) throws IOException {

		panel.removeAll();

		panel.setLayout(null);

		JLabel gameNumberLabel = new JLabel("Enter game name");
		gameNumberLabel.setBounds(40, 60, 120, 25);
		panel.add(gameNumberLabel);
		gameToPlayText.setBounds(165, 60, 80, 25);
		playButton.setBounds(165, 105, 80, 25);
		backButton.setBounds(80, 105, 80, 25);
		panel.add(backButton);
		panel.add(playButton);
		panel.add(gameToPlayText);

		AccountMenu.setFrameTitle("Load Game");

		panel.repaint();
		panelA = panel;

		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				new MainMenu(panelA, PlayerInfo.usernameStatic);
			}
		});

		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Execute when button is pressed
				getContentPane().removeAll();
				String gameName = gameToPlayText.getText();
				File gameFile = new File(gameName);
				if (gameFile.exists()) {
					FileWriting openGameFile = new FileWriting();
					try {
						openGameFile.loadGame(gameName);
						grid = openGameFile.getGrid();
						level = openGameFile.getLevel();
						grid = openGameFile.getGrid();
						brick = openGameFile.getBrick();
						concrete = openGameFile.getConcrete();
						powerUps = openGameFile.getPowerUps();
						exitWays = openGameFile.getExitWays();
						bomb = openGameFile.getBomb();
						player = openGameFile.getPlayer();
						currentScore = openGameFile.getPlayerScore();
						currentLevel = openGameFile.getPlayerLevel();
						currentLives = openGameFile.getPlayerLives();
						timer = openGameFile.getTimer();
						enemy = openGameFile.getEnemy();
						loadFlamePass = openGameFile.getLoadFlamePass();
						loadWallPass = openGameFile.getLoadWallPass();
						loadBombPass = openGameFile.getLoadBombPass();
						loadDetonate = openGameFile.getLoadDetonate();

					} catch (ClassNotFoundException e1) {

						e1.printStackTrace();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					getContentPane().removeAll();
					new Bomberman(grid, level, enemy, brick, concrete,
							powerUps, exitWays, bomb, player, currentScore,
							currentLevel, currentLives, timer, loadFlamePass,
							loadBombPass, loadBombPass, loadDetonate);
					AccountMenu.destroyFrame();
				}

				else {

					JOptionPane.showMessageDialog(null,
							"Please enter correct fileName not found ",
							"Error", JOptionPane.INFORMATION_MESSAGE);
				}

			}

		});
		panel.setVisible(true);

	}

}
