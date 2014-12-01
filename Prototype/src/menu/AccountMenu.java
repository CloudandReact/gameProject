package menu;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class loads the AccountMenu Gui dispaying the login Gui
 */

public class AccountMenu {

	private static JPanel panel = new JPanel();
	private static JFrame frame = new JFrame();

	public static void setFrameTitle(String title) {
		frame.setTitle(title);
	}

	/**
	 * AccountMenu displays sets the size of the frame adds a panel and displays
	 * it.
	 */
	public AccountMenu() {

		frame.setSize(325, 230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		new Login(panel);
		frame.setVisible(true);

	}

	/**
	 * destroyFrame destroys the frame and disposes of it
	 */
	public static void destroyFrame() {
		frame.setVisible(false);
		frame.dispose();
		System.out.println("DIsp");
	}

	/**
	 * calls new AccountMenu
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new AccountMenu();
	}

}
