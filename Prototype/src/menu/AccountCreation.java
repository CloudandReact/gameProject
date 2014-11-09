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

public class AccountCreation extends JFrame {

	JLabel userRealNameLabel = new JLabel("Real Name");
	// note changed could modify
	JTextField userNameText = new JTextField("firstname lastname");
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
		
		// this adds in a focus for info
		userNameText.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (userNameText.getText().equals("firstname lastname")) {
					userNameText.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				// nothing
				if (userNameText.getText().length() == 0) {
					// System.out.println(userNameText.getText()+ " "+
					// userNameText.getText().length());
					userNameText.setText("firstname lastname");
				}
			}
		});

		createButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// createButton.setEnabled(false);
				// Execute when button is pressed
				System.out.println("action comman" + e.getActionCommand());
				// getContentPane().removeAll();
				// new Login(panelA);
				realName = userNameText.getText();
				username = userText.getText();
				password = String.valueOf(passwordText.getPassword());
				retypePassword = String.valueOf(verifyPasswordText.getPassword());
				System.out.println(realName);
				String error = "Error";
				FileWriting writing = new FileWriting();

				if (writing.checkIfValid(realName, username, password, retypePassword)) {
					try {writing.writeToFile(realName, username, password, retypePassword);
						JOptionPane.showMessageDialog(null,"Registration complete, please login.", "Success!", JOptionPane.INFORMATION_MESSAGE);
						createButton.setEnabled(true);
						getContentPane().removeAll();
						new Login(panelA);
					} catch (Exception e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(null,"Could not write to file.", password, JOptionPane.INFORMATION_MESSAGE);
					}
				}

				else {
					if (!writing.isRealNameValid()) {
						JOptionPane.showMessageDialog(null,"Incorrect input. Realname should be two words.",error, JOptionPane.INFORMATION_MESSAGE);
					} else if (!writing.isUserNameValid()) {
						
						JOptionPane.showMessageDialog(null,"Username should consist of one word that is at least 6 characters long.",error, JOptionPane.INFORMATION_MESSAGE);
						
					} else if (!writing.isPasswordValid()) {
						
						JOptionPane.showMessageDialog(null,"Password should be atleast 8 characters long containing at least one upper case character and number.",error, JOptionPane.INFORMATION_MESSAGE);
						
					} else if (!writing.arePasswordSame()){
						JOptionPane.showMessageDialog(null,"Passwords do not match.",error,JOptionPane.INFORMATION_MESSAGE);

					}
					// JOptionPane.showMessageDialog(null,
					// "could not register fix the error please reenter",password,
					// JOptionPane.INFORMATION_MESSAGE);

					return;
				}

			}
		});

		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				new Login(panelA);
			}
		});

		panel.setVisible(true);

	}

}