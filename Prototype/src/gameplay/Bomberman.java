package gameplay;

import javax.swing.JFrame;

public class Bomberman extends JFrame {
	public Bomberman() {
		add(new Map());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(780, 353);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Bomberman();
	}
}
