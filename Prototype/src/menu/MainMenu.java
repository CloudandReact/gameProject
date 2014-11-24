package menu;

import gameplay.Bomberman;
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
	JButton controlsButton = new JButton("Controls");
	JButton accountManagementButton = new JButton("Account Management");
	JButton leaderboardsButton = new JButton("Leaderboards");
	JButton logOutButton = new JButton("Log out");
	JButton quitButton = new JButton("Quit to Desktop");
	String playersName;
	
			
	public MainMenu(JPanel panel, String username){
		playersName = username;
				
		panel.removeAll();
		panel.setLayout(null);
		AccountMenu.setFrameTitle("Main Menu");
		
		newGameButton.setBounds(72, 5, 180, 25);
		panel.add(newGameButton);
		
		loadGameButton.setBounds(72, 30, 180, 25);
		panel.add(loadGameButton);

		loadLevelButton.setBounds(72, 55 , 180, 25);
		panel.add(loadLevelButton);
		
		controlsButton.setBounds(72, 80, 180, 25);
		panel.add(controlsButton);
		
		accountManagementButton.setBounds(72, 105, 180, 25);
		panel.add(accountManagementButton);
		
		leaderboardsButton.setBounds(72, 130, 180, 25);
		panel.add(leaderboardsButton);
		
		logOutButton.setBounds(72, 155, 180, 25);
		panel.add(logOutButton);
		
		quitButton.setBounds(72, 180, 180, 25);
		panel.add(quitButton);
		
		
		panel.repaint();
		panelL = panel;
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				AccountMenu.destroyFrame();
				PlayerInfo.setUsername(playersName);
				new Bomberman();	
			}
			
		});
		
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				AccountMenu.destroyFrame();
				new AccountMenu();	
			}
			
		});
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				AccountMenu.destroyFrame();
				System.exit(0);
			}
			
		});
		

		accountManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				//AccountMenu.destroyFrame();
				new AccountModifications(panelL);
				
			}
			
		});
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
		loadLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				if(PlayerInfo.unlockedLevel==1){
					JOptionPane.showMessageDialog(null,"Please start a new game no levels availible", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					
				}
				
			}
			
		});

	}
}
