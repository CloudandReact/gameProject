package menu;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Login extends JPanel {

	JLabel usernameLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");
	JTextField userText = new JTextField();
	JPasswordField passwordText = new JPasswordField();
	JButton loginButton = new JButton("Login");
	JButton createAccountButton = new JButton("Create Account");

	public Login() {

		setLayout(null);

		usernameLabel.setBounds(10, 10, 80, 25);
		add(usernameLabel);

		userText.setBounds(100, 10, 160, 25);
		add(userText);

		passwordLabel.setBounds(10, 40, 80, 25);
		add(passwordLabel);

		passwordText.setBounds(100, 40, 160, 25);
		add(passwordText);

		loginButton.setBounds(10, 80, 80, 25);
		add(loginButton);

		createAccountButton.setBounds(150, 80, 150, 25);
		add(createAccountButton);

		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				System.out.println("You clicked the button");
			}
		});

		createAccountButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				new AccountMenu(new AccountCreation());
			}
		});

		setVisible(true);
	}
}
