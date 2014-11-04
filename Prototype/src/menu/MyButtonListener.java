package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyButtonListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		//JOptionPane.showMessageDialog(source, source.getText() + " button has been pressed");
		
		JFrame frame = new JFrame("Register");
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		placeComponents(panel);
		
		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {
		panel.setLayout(null);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(10, 10, 80, 25);
		panel.add(usernameLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);
		
		JButton createButton = new JButton("Create Account");
		createButton.setBounds(10, 80,1600, 25);
		panel.add(createButton);
		
		
	}
}
