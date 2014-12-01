package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * This class Account creation displays the GUI for Account Creation
 *
 */

public class AccountCreation extends JFrame {

	JLabel userRealNameLabel = new JLabel("Real Name");

	JTextField userNameText = new JTextField();
	JLabel usernameLabel = new JLabel("Username");
	JTextField userText = new JTextField();
	JLabel passwordLabel = new JLabel("Password");
	JPasswordField passwordText = new JPasswordField();
	JLabel verifyPasswordLabel = new JLabel("Verify Password");
	JPasswordField verifyPasswordText = new JPasswordField();
	JButton createButton = new JButton("Create");
	JButton backButton = new JButton("Back");
	String realName;
	String username;
	String password;
	String retypePassword;

	JPanel panelA;

	/**
	 * This AccountCreation method destroys to login JPanel and creats a creat
	 * account Jpanel Displaying create realname, username, passowrd, retype
	 * password with textboxes all on seperate lines. It as well calls the
	 * appropriate methods to see if the new user account was creater otherwise
	 * displays the correct error message. E.g username is not 6 characters
	 * 
	 * @param panel
	 *            Takes the panel as input and removes the panel and creates the
	 *            AccountCreation Panel
	 */

	public AccountCreation(JPanel panel) {

		panel.removeAll();

		panel.setLayout(null);

		AccountMenu.setFrameTitle("Create Account");

		userRealNameLabel.setBounds(10, 10, 80, 25);
		panel.add(userRealNameLabel);

		userNameText.setBounds(130, 10, 160, 25);
		panel.add(userNameText);

		usernameLabel.setBounds(10, 40, 80, 25);
		panel.add(usernameLabel);

		userText.setBounds(130, 40, 160, 25);
		panel.add(userText);

		passwordLabel.setBounds(10, 70, 80, 25);
		panel.add(passwordLabel);

		passwordText.setBounds(130, 70, 160, 25);
		panel.add(passwordText);

		verifyPasswordLabel.setBounds(10, 100, 160, 25);
		panel.add(verifyPasswordLabel);

		verifyPasswordText.setBounds(130, 100, 160, 25);
		panel.add(verifyPasswordText);

		createButton.setBounds(165, 135, 80, 25);
		panel.add(createButton);

		backButton.setBounds(80, 135, 80, 25);
		panel.add(backButton);

		panel.repaint();
		panelA = panel;

		createButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.out.println("action comman" + e.getActionCommand());
				realName = userNameText.getText();
				username = userText.getText();
				password = String.valueOf(passwordText.getPassword());
				retypePassword = String.valueOf(verifyPasswordText
						.getPassword());
				System.out.println(realName);
				String error = "Error";
				FileWriting writing = new FileWriting();
				writing.openFile();

				if (writing.checkIfValid(realName, username, password,
						retypePassword)
						&& writing.isUserNameAvailible(username)) {
					try {
						writing.writeToFile(realName, username, password,
								retypePassword);
						System.out.println("hello wordll2344");
						createButton.setEnabled(true);
						getContentPane().removeAll();
						new Login(panelA);
					} catch (Exception e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(null,
								"Could not write to file.", "error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

				else {
					if (!writing.isRealNameValid()) {
						JOptionPane
								.showMessageDialog(
										null,
										"Incorrect input. Realname should be only letters.",
										error, JOptionPane.INFORMATION_MESSAGE);
					} else if (!writing.isUserNameValid()) {

						JOptionPane
								.showMessageDialog(
										null,
										"Username should consist of one word that is at least 6 characters long.",
										error, JOptionPane.INFORMATION_MESSAGE);

					} else if (!writing.isPasswordValid()) {

						JOptionPane
								.showMessageDialog(
										null,
										"Password should be atleast 8 characters long containing at least one upper case character ,number and special char.",
										error, JOptionPane.INFORMATION_MESSAGE);

					} else if (!writing.arePasswordSame()) {
						JOptionPane.showMessageDialog(null,
								"Passwords do not match.", error,
								JOptionPane.INFORMATION_MESSAGE);

					} else if (!writing.isUserNameAvailible(username)) {
						JOptionPane.showMessageDialog(null,
								"Username is already taken.", error,
								JOptionPane.INFORMATION_MESSAGE);

					}
					return;
				}

			}
		});

		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				getContentPane().removeAll();
				new Login(panelA);
			}
		});

		panel.setVisible(true);

	}

}