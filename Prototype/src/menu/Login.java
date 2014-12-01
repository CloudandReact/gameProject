package menu;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.*;

import gameplay.Player;
import gameplay.PlayerInfo;
/**
 * Login class displays the login Gui where the user enters his username and password
 * The if succesful he is logged in. His input is sent back to fileWriting to check if the 
 * credentials are valid
 * @author Elliot Gimple
 * @author Leonardo Siracusa
 *
 */
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
	/**
	 * Login takes the panel removes everything from the panel and then renders it for login.
	 * It calls the filWriting to validate the username and password.
	 * @param panel
	 */
   
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
				
				username = userText.getText();
				
				password = String.valueOf(passwordText.getPassword());
				System.out.println("username" + username + "password "
						+ password);
				FileWriting loginValidity = new FileWriting();
				loginValidity.openFile();

				if (loginValidity.loginIsValid(username, password)) {
					
					new MainMenu(panelL, username);
					 
					PlayerInfo.usernameStatic = username;
					
					try {
						// store all player statistics which is required for
						// leaderboards
						loginValidity.openStatistics();
						PlayerInfo.allUserStatistics = loginValidity
								.allPlayersStats();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Incorrect username or password. Please retry.",
							password, JOptionPane.INFORMATION_MESSAGE);

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
