package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gameplay.PlayerInfo;

public class LoadGame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel noGamesFound= new JLabel("Error no loaded games please go back");
	
	JButton backButton= new JButton("Back");
	JButton playButton= new JButton("Play");
	
	// load how many games then have a textbox to enter
	JTextField gameToPlayText= new JTextField();
	JPanel panelA;
	StoreStatistics checkStats= new StoreStatistics();
	int numberOfGames;
	
	
	
	public LoadGame(JPanel panel) throws IOException {
		checkStats.checkNumberOfGames();
		panel.removeAll();
		
		panel.setLayout(null);
		//open statistics read user games and display
		numberOfGames=checkStats.numberOfGames();
		if(numberOfGames>0) 
			{ 
			JLabel gameNumberLabel= new JLabel("load Game number "+ numberOfGames);
			gameNumberLabel.setBounds(40,60,120,25);
			panel.add(gameNumberLabel);
			gameToPlayText.setBounds(165,60,25,25);
			playButton.setBounds(165,105,80,25);
			backButton.setBounds(80, 105, 80, 25);
			panel.add(backButton);
			panel.add(playButton);
			panel.add(gameToPlayText);
			}
		else {
			JLabel gameNumberLabel= new JLabel("No game saved please go back");
			gameNumberLabel.setBounds(40,60,190,25);
			
			backButton.setBounds(80, 105, 80, 25);
			panel.add(backButton);
			panel.add(gameNumberLabel);
			
			}
		
		AccountMenu.setFrameTitle("Load Game");
		
		
		panel.repaint();
		panelA = panel;
		
		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				new MainMenu(panelA,PlayerInfo.usernameStatic);
			}
		});
		
		playButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				int gameNumberSelected=Integer.parseInt(gameToPlayText.getText());
				if(gameNumberSelected>numberOfGames){
					//alert error reenter else iniatilize constructor for game play
				//constructur 
					JOptionPane.showMessageDialog(null,"Please enter smaller number than equal to "+ numberOfGames+ " ", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					
				}
				//new Game()
			}
		});
		panel.setVisible(true);
		
		
		
		
}
}
