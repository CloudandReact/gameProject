package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


// Going to open in new window because I had to have PauseMenu take in a render object and we cant do new PauseMenu. Life sucks.
public class Leaderboards extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton backButton = new JButton("Back");
	JPanel panel = new JPanel();
	
	JLabel user1Label;
	JLabel user2Label;
	JLabel user3Label;
	JLabel user4Label;
	JLabel user5Label;
	JLabel user6Label;
	JLabel user7Label;
	JLabel user8Label;
	JLabel user9Label;
	JLabel user10Label;
	
	
	public Leaderboards(){
		
		CompareLeaderboards compareLeaderboards = new CompareLeaderboards();

		setSize(325, 330);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(panel);
		setVisible(true);
		panel.removeAll();
		panel.setLayout(null);
		setLocationRelativeTo(null);
		AccountMenu.setFrameTitle("Leaderboards");
	
		backButton.setBounds(130, 230, 80, 25);
		panel.add(backButton);
		
		user1Label = new JLabel(compareLeaderboards.getUserName()[0] + " " + compareLeaderboards.getUserScore()[0]);
		user1Label.setBounds(132, 10, 80, 25);
		panel.add(user1Label);
		
		user2Label = new JLabel(compareLeaderboards.getUserName()[1] + " " + compareLeaderboards.getUserScore()[1]);
		user2Label.setBounds(132, 30, 80, 25);
		panel.add(user2Label);
		
		user3Label = new JLabel(compareLeaderboards.getUserName()[2] + " " + compareLeaderboards.getUserScore()[2]);
		user3Label.setBounds(132, 50, 80, 25);
		panel.add(user3Label);
		
		user4Label = new JLabel(compareLeaderboards.getUserName()[3] + " " + compareLeaderboards.getUserScore()[3]);
		user4Label.setBounds(132, 70, 80, 25);
		panel.add(user4Label);
		
		user5Label = new JLabel(compareLeaderboards.getUserName()[4] + " " + compareLeaderboards.getUserScore()[4]);
		user5Label.setBounds(132, 90, 80, 25);
		panel.add(user5Label);
		
		user6Label = new JLabel(compareLeaderboards.getUserName()[5] + " " + compareLeaderboards.getUserScore()[5]);
		user6Label.setBounds(132, 110, 80, 25);
		panel.add(user6Label);
		
		user7Label = new JLabel(compareLeaderboards.getUserName()[6] + " " + compareLeaderboards.getUserScore()[6]);
		user7Label.setBounds(132, 130, 80, 25);
		panel.add(user7Label);
		
		user8Label = new JLabel(compareLeaderboards.getUserName()[7] + " " + compareLeaderboards.getUserScore()[7]);
		user8Label.setBounds(132, 150, 80, 25);
		panel.add(user8Label);
		
		user9Label = new JLabel(compareLeaderboards.getUserName()[8] + " " + compareLeaderboards.getUserScore()[8]);
		user9Label.setBounds(132, 170, 80, 25);
		panel.add(user9Label);
		
		user10Label = new JLabel(compareLeaderboards.getUserName()[9] + " " + compareLeaderboards.getUserScore()[9]);
		user10Label.setBounds(132, 190, 80, 25);
		panel.add(user10Label);


		
	
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
