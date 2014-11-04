package menu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AccountCreation extends JPanel {
	
	JLabel userRealNameLabel = new JLabel("Real Name");
	JTextField userNameText = new JTextField();
	JLabel usernameLabel = new JLabel("Username");
	JTextField userText = new JTextField();
	JLabel passwordLabel = new JLabel("Password");
	JPasswordField passwordText = new JPasswordField(20);
	JLabel verifyPasswordLabel = new JLabel("Verify Password");
	JPasswordField verifyPasswordText = new JPasswordField(20);
	JButton createButton = new JButton("Create Account");
	
	
	public AccountCreation() {
		
		setLayout(null);
		
		userRealNameLabel.setBounds(10, 10, 80, 25);
		add(userRealNameLabel);

		
		userNameText.setBounds(130, 10, 160, 25);
		add(userNameText);

		usernameLabel.setBounds(10, 40, 80, 25);
		add(usernameLabel);

		userText.setBounds(130, 40, 160, 25);
		add(userText);

		passwordLabel.setBounds(10, 70, 80, 25);
		add(passwordLabel);

		passwordText.setBounds(130, 70, 160, 25);
		add(passwordText);

		verifyPasswordLabel.setBounds(10, 100, 160, 25);
		add(verifyPasswordLabel);

		verifyPasswordText.setBounds(130, 100, 160, 25);
		add(verifyPasswordText);

		createButton.setBounds(70,135,160,25);
		add(createButton);
		
		setVisible(true);
		
	}

}
