package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AccountMenu extends JFrame {

	private static JPanel panel = new JPanel();
	
	public void setFrameTitle(){
		setTitle("Create Account");
	}

	public AccountMenu() {

		setTitle("Login");
		setSize(300, 185);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		add(panel);
		new Login(panel);
		setVisible(true);

	}

	public static void main(String[] args) {
		new AccountMenu();
	}

	public class LoginButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane
					.showMessageDialog(null, "login button has been pressed");
		}
	}
}