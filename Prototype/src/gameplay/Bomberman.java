package gameplay;

import javax.swing.JFrame;

public class Bomberman extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bomberman() {
		add(new Map());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(775, 346);
		setLocationRelativeTo(null);
		setTitle("Bobmerman");
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Bomberman();
	}
}
