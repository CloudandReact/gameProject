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

	public void actionPerformed(ActionEvent e) {
		//JButton source = (JButton) e.getSource();
		//JOptionPane.showMessageDialog(source, source.getText() + " button has been pressed");
		
		JFrame frame = new JFrame("Register");
		frame.setSize(300, 185);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		registerScreen(panel);
		
		frame.setVisible(true);
		
	}

	private static void registerScreen(JPanel panel) {
		panel.setLayout(null);
		
		JLabel userRealNameLabel = new JLabel("Real Name");
		userRealNameLabel.setBounds(10, 10, 80, 25);
		panel.add(userRealNameLabel);

		JTextField userNameText = new JTextField(20);
		userNameText.setBounds(130, 10, 160, 25);
		panel.add(userNameText);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(10, 40, 80, 25);
		panel.add(usernameLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(130, 40, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 70, 80, 25);
		panel.add(passwordLabel);
		
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(130, 70, 160, 25);
		panel.add(passwordText);
		
		JLabel verifyPasswordLabel = new JLabel("Verify Password");
		verifyPasswordLabel.setBounds(10, 100, 160, 25);
		panel.add(verifyPasswordLabel);
		
		JPasswordField verifyPasswordText = new JPasswordField(20);
		verifyPasswordText.setBounds(130, 100, 160, 25);
		panel.add(verifyPasswordText);
		
		JButton createButton = new JButton("Create Account");
		createButton.setBounds(70,135,160,25);
		panel.add(createButton);
		
		
	}
}
