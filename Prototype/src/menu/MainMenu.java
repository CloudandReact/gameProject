package menu;

import gameplay.Bomberman;
import gameplay.GameTimer;
import gameplay.PlayerInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainMenu extends JFrame{
	
	JPanel panelL;
	JButton newGameButton = new JButton("New game");
	JButton loadGameButton = new JButton("Load Game");
	JButton loadLevelButton = new JButton("Load Level");
	JButton accountManagementButton = new JButton("Account Management");
	JButton leaderboardsButton = new JButton("Leaderboards");
	JButton logOutButton = new JButton("Log out");
	JButton quitButton = new JButton("Quit to Desktop");
	String playersName;
	
	/**
	 * Opens a new main menu frame.
	 * @param panel is the panel on which the buttons are added.
	 * @param username is required to know which user is logged in.
	 */
	public MainMenu(JPanel panel, String username){
		playersName = username;
				
		panel.removeAll();
		panel.setLayout(null);
		AccountMenu.setFrameTitle("Main Menu");
		
		newGameButton.setBounds(72, 10, 180, 25);
		panel.add(newGameButton);
		
		loadGameButton.setBounds(72, 35, 180, 25);
		panel.add(loadGameButton);

		loadLevelButton.setBounds(72, 60 , 180, 25);
		panel.add(loadLevelButton);
		
		accountManagementButton.setBounds(72, 85, 180, 25);
		panel.add(accountManagementButton);
		
		leaderboardsButton.setBounds(72, 110, 180, 25);
		panel.add(leaderboardsButton);
		
		logOutButton.setBounds(72, 135, 180, 25);
		panel.add(logOutButton);
		
		quitButton.setBounds(72, 160, 180, 25);
		panel.add(quitButton);
		
		
		panel.repaint();
		panelL = panel;
		
		/*
		 * Displays the leaderboard 
		 */
		leaderboardsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Leaderboards();	
			}
			
		});
		
		/*
		 * Starts the new game from level 1
		 */
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				AccountMenu.destroyFrame();
				PlayerInfo.setUsername(playersName);
				GameTimer.restartTimer();
				new Bomberman();	
			}
			
		});
		
		/*
		 * Logs out the player
		 */
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				AccountMenu.destroyFrame();
				new AccountMenu();	
			}
			
		});
		
		/*
		 * Quits the game 
		 */
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				AccountMenu.destroyFrame();
				System.exit(0);
			}
			
		});
		
		/*
		 * Opens the account management frame 
		 */
		accountManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				//AccountMenu.destroyFrame();
				new AccountModifications(panelL);
				
			}
			
		});
		
		/*
		 * Opens load game frame 
		 */
		loadGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				try {
					new LoadGame(panelL);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		/*
		 * Opens the load level frame
		 */
		loadLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				
				try {
					PlayerInfo.setUsername(playersName);
					new LoadLevel(panelL);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
			}
			
		});

	}
}
