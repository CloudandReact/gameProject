package gameplay;

import javax.swing.JFrame;

public class Bomberman extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int dimensionX;
	private static int dimensionY;
	

	public Bomberman() {
		add(new Render());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDimensionX(825);
		setDimensionY(395);
		setSize(dimensionX, dimensionY);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
	}
	
	public static void setDimensionX(int x){
		dimensionX = x;
	}
	public static void setDimensionY(int y){
		dimensionY = y;
	}
	public static int getDimensionX(){
		return dimensionX;
	}
	
	public static int getDimensionY(){
		return dimensionY;
	}	

}
