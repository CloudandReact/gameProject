package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AccountMenu {

	private static JPanel panel = new JPanel();
	private static JFrame frame = new JFrame();

	public static void setFrameTitle(String title) {
		frame.setTitle(title);
	}

	public AccountMenu() {

		frame.setSize(325, 230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		new Login(panel);
		frame.setVisible(true);

	}

	public static void destroyFrame() {
		frame.setVisible(false);
		frame.dispose();
		System.out.println("DIsp");
	}

	public static void main(String[] args) {
		new AccountMenu();
	}


}