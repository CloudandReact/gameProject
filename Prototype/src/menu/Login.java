package menu;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import javax.swing.*;

public class Login {

	JLabel usernameLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");
	JTextField userText = new JTextField();
	JPasswordField passwordText = new JPasswordField();
	JButton loginButton = new JButton("Login");
	JButton createAccountButton = new JButton("Create Account");
	private String username = "";
	private String password = "";

	JPanel panelL;

	public Login(JPanel panel) {

		panel.removeAll();
		panel.setLayout(null);
		
		AccountMenu.setFrameTitle("Login");

		usernameLabel.setBounds(10, 40, 80, 25);
		panel.add(usernameLabel);

		userText.setBounds(130, 40, 160, 25);
		panel.add(userText);

		passwordLabel.setBounds(10, 70, 80, 25);
		panel.add(passwordLabel);

		passwordText.setBounds(130, 70, 160, 25);
		panel.add(passwordText);

		loginButton.setBounds(50, 135, 80, 25);
		panel.add(loginButton);

		createAccountButton.setBounds(140, 135, 125, 25);
		panel.add(createAccountButton);
		
		panel.repaint();
		panelL = panel;

		loginButton.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				username = userText.getText();
				// makes password into a string converts from char array
				// check that username and password arent null
				password = String.valueOf(passwordText.getPassword());
				System.out.println("username" + username + "password " + password);
				FileWriting loginValidity = new FileWriting();
				loginValidity.openFile();
				//String error = "Error";
				//String success = "Success";

				if (loginValidity.loginIsValid(username, password)) {
					// go to menu
					new MainMenu(panelL, username);
				} else {
					JOptionPane.showMessageDialog(null,"Incorrect username or password. Please retry.",password, JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});

		createAccountButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				new AccountCreation(panelL);
			}
		});

		panel.setVisible(true);
	}
}
