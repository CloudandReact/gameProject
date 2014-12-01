package menu;

import gameplay.Bomberman;
import gameplay.PlayerInfo;
import gameplay.Game;

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

public class LoadLevel extends JFrame {
	private static final long serialVersionUID = 1L;

	JLabel noGamesFound = new JLabel("Error no loaded games please go back");

	JButton backButton = new JButton("Back");
	JButton playButton = new JButton("Play");
	int levelAvailible = PlayerInfo.unlockedLevel;

	// load how many games then have a textbox to enter
	JTextField gameToPlayText = new JTextField();
	JPanel panelA;
	StoreStatistics checkStats = new StoreStatistics();
	int numberOfGames;

	public LoadLevel(JPanel panel) throws IOException {
		checkStats.checkNumberOfGames();
		panel.removeAll();

		panel.setLayout(null);

		// open statistics read user games and display
		numberOfGames = checkStats.numberOfGames();
		// if(levelAvailible>1)
		if (true) {
			JLabel gameNumberLabel = new JLabel("Level availible "
					+ levelAvailible);
			gameNumberLabel.setBounds(40, 60, 120, 25);
			panel.add(gameNumberLabel);
			gameToPlayText.setBounds(165, 60, 25, 25);
			playButton.setBounds(165, 105, 80, 25);
			backButton.setBounds(80, 105, 80, 25);
			panel.add(backButton);
			panel.add(playButton);
			panel.add(gameToPlayText);
		} else {
			JLabel gameNumberLabel = new JLabel("No game saved please go back");
			gameNumberLabel.setBounds(40, 60, 190, 25);

			backButton.setBounds(80, 105, 80, 25);
			panel.add(backButton);
			panel.add(gameNumberLabel);

		}

		AccountMenu.setFrameTitle("Load Level");

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
				int levelSelected = Integer.parseInt(gameToPlayText.getText());
				new Bomberman(levelSelected);
				AccountMenu.destroyFrame();

				// if(levelSelected>levelAvailible){
				// //alert error reenter else iniatilize constructor for game
				// play
				// //constructur
				// JOptionPane.showMessageDialog(null,"Please enter smaller number than equal to "+levelAvailible+
				// " ", "Error", JOptionPane.INFORMATION_MESSAGE);
				// }
				// else{
				// //assigns player current Level to different level
				// PlayerInfo.currentLevel=levelSelected;
				// }
				// new Game()
			}
		});
		panel.setVisible(true);

	}
}
