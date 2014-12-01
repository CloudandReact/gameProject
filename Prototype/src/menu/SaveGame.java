package menu;

import gameplay.Bomb;
import gameplay.Brick;
import gameplay.Concrete;
import gameplay.Enemy;
import gameplay.ExitWay;
import gameplay.GameTimer;
import gameplay.Grid;
import gameplay.Level;
import gameplay.Player;
import gameplay.PlayerInfo;
import gameplay.Game;
import gameplay.PowerUps;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gameplay.PlayerInfo;
/**
 * 
 * @author elliot
 *
 */
public class SaveGame extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();

	JLabel noGamesFound = new JLabel("Error cannot save game");

	JButton backButton = new JButton("Back");
	JButton saveButton = new JButton("Save");

	private Game game;

	// load how many games then have a textbox to enter
	JTextField saveGameText = new JTextField(30);
	JPanel panelA;
	StoreStatistics checkStats = new StoreStatistics();
	int numberOfGames;
	Enemy enemy;

	Grid grid;

	public SaveGame(final Grid grid,final Level level, final Enemy enemy,final Player p,final Concrete concrete,final Brick brick, final PowerUps powerUps,
			final ExitWay exitWays, final Bomb bomb, final boolean flamePass,final boolean bombPass,final boolean wallPass,
			final boolean detonate) throws IOException {
	  //public SaveGame(final Render r) throws IOException {
		this.grid = grid;
		this.enemy = enemy;
		checkStats.checkNumberOfGames();
		setSize(450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(panel);
		setVisible(true);
		panel.removeAll();
		panel.setLayout(null);
		setLocationRelativeTo(null);
		AccountMenu.setFrameTitle("Leaderboards");

		// open statistics read user games and display

		JLabel gameNumberLabel = new JLabel("enter name of save game");
		gameNumberLabel.setBounds(20, 50, 145, 25);
		panel.add(gameNumberLabel);
		saveGameText.setBounds(170, 55, 60, 25);
		saveButton.setBounds(165, 105, 80, 25);
		backButton.setBounds(80, 105, 80, 25);
		panel.add(backButton);
		panel.add(saveButton);
		panel.add(saveGameText);

		AccountMenu.setFrameTitle("Save Game");

		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				dispose();

			}

		});

		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				// create savin game object
				String gameName = (saveGameText.getText());

				// /need to write to file
				FileWriting savingTheGame = new FileWriting();
				try {
					//change to add in currentLives
					//object save everything
					//enemy.getEnemiesInitial()
					savingTheGame.saveGame(gameName,level,enemy, p,concrete,brick, powerUps,exitWays,bomb,grid, 
							PlayerInfo.playerScore, PlayerInfo.currentLevel,Player.livesLeft,GameTimer.timeCount,flamePass,bombPass,wallPass,detonate);
					//savingTheGame.saveGame(gameName,r);
					System.out.println("succes");
					getContentPane().removeAll();
					dispose();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// new Game()
			}
		});

		panel.repaint();

	}
}
