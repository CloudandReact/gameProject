package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


// Going to open in new window because I had to have PauseMenu take in a render object and we cant do new PauseMenu. Life sucks.
public class Leaderboards extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton backButton = new JButton("Back");
	JPanel panel = new JPanel();
	
	public Leaderboards(){

		setSize(325, 230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(panel);
		setVisible(true);
		panel.removeAll();
		panel.setLayout(null);
		AccountMenu.setFrameTitle("Leaderboards");
	
		backButton.setBounds(80, 135, 80, 25);
		panel.add(backButton);
		
	
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
