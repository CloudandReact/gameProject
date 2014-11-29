package menu;

import gameplay.PlayerInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Leaderboards extends JFrame {

	private JButton backButton;
	private JPanel panel;
	private JLabel usernameLabel;
	private JLabel scoreLabel;

	CompareLeaderboards compareLeaderboards;

	public Leaderboards() {

		setSize(450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.removeAll();
		panel.setLayout(null);
		add(panel);

		backButton = new JButton("Back");
		backButton.setBounds(185, 390, 80, 25);
		panel.add(backButton);

		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
		usernameLabel.setBounds(73, 10, 100, 25);
		panel.add(usernameLabel);

		scoreLabel = new JLabel("Score");
		scoreLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
		scoreLabel.setBounds(287, 10, 100, 25);
		panel.add(scoreLabel);

		compareLeaderboards = new CompareLeaderboards();

		AccountMenu.setFrameTitle("Leaderboards");

		compareLeaderboards.placeInMap();
		compareLeaderboards.sortPlayers();

		ArrayList<String> usernames = compareLeaderboards.getUsernames();
		ArrayList<Integer> userScores = compareLeaderboards.getUserScores();

		int topScores = 10;
		
		if (userScores.size() >= 10) {

		} else {
			topScores = userScores.size();
		}
		boolean scorePlaced = false;
		
		for (int i = 0; i < topScores; i++) {
			if (PlayerInfo.playerScore >= userScores.get(i) && !scorePlaced) {

				usernameLabel = new JLabel(PlayerInfo.usernameStatic);
				usernameLabel.setBounds(73, 50 + i * 20, 150, 25);
				panel.add(usernameLabel);

				scoreLabel = new JLabel(String.valueOf(PlayerInfo.playerScore));
				scoreLabel.setBounds(287, 50 + i * 20, 150, 25);
				panel.add(scoreLabel);

				topScores = topScores - 1;
				scorePlaced = true;
			} else {

				usernameLabel = new JLabel(usernames.get(i));
				usernameLabel.setBounds(73, 50 + i * 20, 150, 25);
				panel.add(usernameLabel);

				scoreLabel = new JLabel(String.valueOf(userScores.get(i)));
				scoreLabel.setBounds(287, 50 + i * 20, 150, 25);
				panel.add(scoreLabel);

			}

		}

		usernameLabel = new JLabel(PlayerInfo.usernameStatic);
		usernameLabel.setBounds(73, 200, 150, 25);
		panel.add(usernameLabel);

		scoreLabel = new JLabel(String.valueOf(PlayerInfo.playerScore));
		scoreLabel.setBounds(287, 200, 150, 25);
		panel.add(scoreLabel);
		
		
		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				dispose();
			}
		});
		panel.repaint();
	}

}
