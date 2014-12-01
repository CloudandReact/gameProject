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

/**
 * LoadLevel displays the GUI for the level loaded and checks if the level the
 * user attemps to load is valid for the user
 * 
 * @author elliot
 *
 */
public class LoadLevel extends JFrame {
	private static final long serialVersionUID = 1L;

	JLabel noGamesFound = new JLabel("Error no loaded games please go back");

	JButton backButton = new JButton("Back");
	JButton playButton = new JButton("Play");
	int levelAvailible = PlayerInfo.unlockedLevel;

	JTextField gameToPlayText = new JTextField();
	JPanel panelA;
	int numberOfGames;

	/**
	 * LoadLevel removes the old Jpanel and displays the new one displaying the
	 * level available and a text box for selecting a level it stores the input
	 * from the file so it throws an exception
	 * 
	 * @param panel
	 *            Stores the previous panel and modifies it
	 * @throws IOException
	 */
	public LoadLevel(JPanel panel) throws IOException {
		panel.removeAll();

		panel.setLayout(null);

		JLabel gameNumberLabel = new JLabel("Level availible " + levelAvailible);
		gameNumberLabel.setBounds(40, 60, 120, 25);
		panel.add(gameNumberLabel);
		gameToPlayText.setBounds(165, 60, 25, 25);
		playButton.setBounds(165, 105, 80, 25);
		backButton.setBounds(80, 105, 80, 25);
		panel.add(backButton);
		panel.add(playButton);
		panel.add(gameToPlayText);

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

			}
		});
		panel.setVisible(true);

	}
}
