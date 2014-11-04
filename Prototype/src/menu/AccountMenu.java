package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AccountMenu extends JFrame {
	
	public AccountMenu(Class<AccountCreation> accountCreation) {
		
		getContentPane().removeAll();
		setTitle("Login");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(new AccountCreation());
		setVisible(true);
		repaint();
	}

	public AccountMenu() {
		getContentPane().removeAll();
		setTitle("Login");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(new Login());
		setVisible(true);
		repaint();
	}

	public static void main(String[] args) {
		new AccountMenu();
	}
	
	public class LoginButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "login button has been pressed");
		}
	}
}