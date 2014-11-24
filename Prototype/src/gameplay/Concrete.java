package gameplay;

import java.awt.*;

import javax.swing.*;

public class Concrete {
/**
*
*/
	private static final long serialVersionUID = 1L;
	private String concrete = "concrete.png";
	
	private Image image;

	public Concrete(Cell[][] gridMap) {
		loadImage();
		placeConcrete(gridMap);
		
	}
	private void loadImage() {
		ImageIcon conc = new ImageIcon(getClass().getResource(concrete));
		image = conc.getImage();
}
	public Image getImage() {
		return image;
}
	private void placeConcrete(Cell[][] gridMap){
		for (int x = 2; x < Bomberman.WIDTH; x = x + 2) {
			for (int y = 2; y < Bomberman.HEIGHT; y = y + 2) {
				gridMap[x][y] = Cell.CONCRETE;
			}
		}
		for (int x = 0; x < Bomberman.WIDTH; x++) {
			for (int y = 0; y < Bomberman.HEIGHT; y++) {
				if ((x == 0) || (x == Bomberman.WIDTH - 1) || (y == 0) || (y == Bomberman.HEIGHT - 1)){
					gridMap[x][y] = Cell.CONCRETE;
				}
			}
		}	
	}			
}