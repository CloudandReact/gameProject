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
import gameplay.PlayerInfo;
/**
 * AccountModfications extends JFrame and displays the Gui for modifying a users account.
 * The user can only modify his realname,and password
 *
 */
public class AccountModifications extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel userRealNameLabel = new JLabel("Real Name");
	// note changed could modify
	JTextField realNameText = new JTextField();
	JLabel passwordLabel = new JLabel("Password");
	JPasswordField passwordText = new JPasswordField();
	JLabel verifyPasswordLabel = new JLabel("Verify Password");
	JPasswordField verifyPasswordText = new JPasswordField();
	JButton modifyButton = new JButton("Modify"); 
	JButton backButton = new JButton("Back");
	String realName;
	String username;
	String password;
	String retypePassword;

	JPanel panelA;
	/**
	 * AccountModifications creates the jpanel for displaying the gui.By first deleteing the old gui and now creating the new one.
	 * It calls FileWriting to check if the account modifications are valid
	 * @param panel
	 */

	public AccountModifications(JPanel panel) {

		panel.removeAll();

		panel.setLayout(null);

		AccountMenu.setFrameTitle("Account modification");

		userRealNameLabel.setBounds(10, 10, 80, 25);
		panel.add(userRealNameLabel);

		realNameText.setBounds(130, 10, 160, 25);
		panel.add(realNameText);

		/*usernameLabel.setBounds(10, 40, 80, 25);
		panel.add(usernameLabel);

		userText.setBounds(130, 40, 160, 25);
		panel.add(userText);*/

		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText.setBounds(130, 40, 160, 25);
		panel.add(passwordText);

		verifyPasswordLabel.setBounds(10, 70, 160, 25);
		panel.add(verifyPasswordLabel);

		verifyPasswordText.setBounds(130, 70, 160, 25);
		panel.add(verifyPasswordText);

		modifyButton.setBounds(165, 105, 80, 25);
		panel.add(modifyButton);

		backButton.setBounds(80, 105, 80, 25);
		panel.add(backButton);
		
		panel.repaint();
		panelA = panel;
		
		// this adds in a focus for info
		
		modifyButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// createButton.setEnabled(false);
				// Execute when button is pressed
				System.out.println("action comman" + e.getActionCommand());
				// getContentPane().removeAll();
				// new Login(panelA);
				realName = realNameText.getText();
				String username=PlayerInfo.getUsername();
				password = String.valueOf(passwordText.getPassword());
				retypePassword = String.valueOf(verifyPasswordText.getPassword());
				System.out.println(realName);
				String error = "Error";
				FileWriting writing = new FileWriting();
		 		writing.openFile();
		 		
				if (writing.checkIfValid(realName, username, password, retypePassword)) {
					try {writing.overwriteToFileString(realName, username, password, retypePassword);
						JOptionPane.showMessageDialog(null,"Modification Complete.", "Success!", JOptionPane.INFORMATION_MESSAGE);
						modifyButton.setEnabled(true);
						getContentPane().removeAll();
						new MainMenu(panelA,username);
					} catch (Exception e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(null,"Could not write to file.", password, JOptionPane.INFORMATION_MESSAGE);
					}
				}

				else {
					if (!writing.isRealNameValid()) {
						JOptionPane.showMessageDialog(null,"Incorrect input. Realname should be two words.",error, JOptionPane.INFORMATION_MESSAGE);
					}
					else if (!writing.isUserNameValid()) {
						
						JOptionPane.showMessageDialog(null,"Username should consist of one word that is at least 6 characters long.",error, JOptionPane.INFORMATION_MESSAGE);
						
					}  
					else if (!writing.isPasswordValid()) {
						
						JOptionPane.showMessageDialog(null,"Password should be atleast 8 characters long containing at least one upper case character and number.",error, JOptionPane.INFORMATION_MESSAGE);
						
					} 
					else if (!writing.arePasswordSame()){
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
				new MainMenu(panelA,username);
			}
		});

		panel.setVisible(true);

	}

}