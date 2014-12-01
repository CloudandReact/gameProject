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
 * SaveGame extends JFrame and implements Serializable to be able to save the game objects to  fileWriting.
 * It will display whether or not the saving was a success
 * @author elliot
 * 
 *
 */
public class SaveGame extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();

	JLabel noGamesFound = new JLabel("Error cannot save game");

	JButton backButton = new JButton("Back");
	JButton saveButton = new JButton("Save");

	private Game game;

	
	JTextField saveGameText = new JTextField(30);
	JPanel panelA;

	int numberOfGames;
	Enemy enemy;

	Grid grid;
	/**
	 * SaveGame takes in all these inputs and shows the GUI for the user to enter a file name and tries to store all the objects 
	 * in a serialized form.
	 * @param grid
	 * 	Object of type Grid
	 * @param level
	 * 	Object of type Level
	 * @param enemy
	 * 	Object of type Enemy
	 * @param p
	 * 	Object of type Player
	 * @param concrete
	 * 	Object of type Concrete
	 * @param brick
	 * 	Object of type Brick
	 * @param powerUps
	 * 	Object of type PowerUps
	 * @param exitWays
	 * 	Object of type ExitWays
	 * @param bomb
	 *  Object of type Bomb
	 * @param flamePass
	 * 	Boolean of type flamePass as in does the user have the powerup
	 * @param bombPass
	 * 	Boolean of type bombPass
	 * @param wallPass
	 * 	Boolean of type wallPass
	 * @param detonate
	 * 	Boolean of type detonate
	 * @throws IOException
	 * 	Throws an IOException because writing to file
	 */

	public SaveGame(final Grid grid,final Level level, final Enemy enemy,final Player p,final Concrete concrete,final Brick brick, final PowerUps powerUps,
			final ExitWay exitWays, final Bomb bomb, final boolean flamePass,final boolean bombPass,final boolean wallPass,
			final boolean detonate) throws IOException {
	 
		this.grid = grid;
		this.enemy = enemy;

		setSize(450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(panel);
		setVisible(true);
		panel.removeAll();
		panel.setLayout(null);
		setLocationRelativeTo(null);
		AccountMenu.setFrameTitle("Leaderboards");

		

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
				
				getContentPane().removeAll();
				
				String gameName = (saveGameText.getText());

				// /need to write to file
				FileWriting savingTheGame = new FileWriting();
				try {
					
					savingTheGame.saveGame(gameName,level,enemy, p,concrete,brick, powerUps,exitWays,bomb,grid, 
							PlayerInfo.playerScore, PlayerInfo.currentLevel,Player.livesLeft,GameTimer.timeCount,flamePass,bombPass,wallPass,detonate);
					
					System.out.println("succes");
					getContentPane().removeAll();
					dispose();

				} catch (IOException e1) {
					
					e1.printStackTrace();
				}

				
			}
		});

		panel.repaint();

	}
}
