package menu;

import gameplay.Bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainMenu extends JFrame{
	
	JPanel panelL;
	JButton playButton = new JButton("Play");
	public MainMenu(JPanel panel){
		panel.removeAll();
		panel.setLayout(null);
		AccountMenu.setFrameTitle("Main Menu");
		
		playButton.setBounds(90, 50, 80, 25);
		panel.add(playButton);
		panel.repaint();
		panelL = panel;
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				AccountMenu.destroyFrame();
				new Bomberman();	
			}
			
		});
		
	}
}
