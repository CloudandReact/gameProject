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
		setSize(825, 396);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
	}

}
