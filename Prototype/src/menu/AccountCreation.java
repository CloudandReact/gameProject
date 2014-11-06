package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AccountCreation extends JFrame{

	JLabel userRealNameLabel = new JLabel("Real Name");
	JTextField userNameText = new JTextField();
	JLabel usernameLabel = new JLabel("Username");
	JTextField userText = new JTextField();
	JLabel passwordLabel = new JLabel("Password");
	JPasswordField passwordText = new JPasswordField(20);
	JLabel verifyPasswordLabel = new JLabel("Verify Password");
	JPasswordField verifyPasswordText = new JPasswordField(20);
	JButton createButton = new JButton("Create Account");
	
	JPanel panelA;


	public AccountCreation(JPanel panel) {

		panel.removeAll();

		panel.setLayout(null);
		
		setTitle("Create Account");


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

		createButton.setBounds(70, 135, 160, 25);
		panel.add(createButton);
		panel.repaint();
		panelA = panel;


		createButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				new Login(panelA);
			}
		});

		panel.setVisible(true);

	}

}
