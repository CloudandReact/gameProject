package gameplay;

import javax.swing.JFrame;

import menu.AccountMenu;

public class Bomberman extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 31;
	public static int HEIGHT = 13;
	public static int TILE_SIZE = 25;
	

	public Bomberman() {
		add(new Render(this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(15 * TILE_SIZE, HEIGHT * TILE_SIZE + 22);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
	}
	
	public Bomberman(int level){
		add(new Render(level, this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(15 * TILE_SIZE, HEIGHT * TILE_SIZE + 22);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
	}
	//changes
	public Bomberman(Grid grid, int level){
		add(new Render(grid,level, this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(15 * TILE_SIZE, HEIGHT * TILE_SIZE + 22);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
		
	}
	
	public void destroyFrame(){
		setVisible(false);
		dispose();
	}
		
	public static void main(String[] args) {
		new Bomberman();
	}

}
