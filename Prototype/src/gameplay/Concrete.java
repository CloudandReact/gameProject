package gameplay;

import java.awt.*;

import javax.swing.*;

public class Concrete {
/**
*
*/
	private static final long serialVersionUID = 1L;
	private String concrete = "concrete.png";
	private int gridLength = 32;
	private int gridHeight = 14;
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
		for (int x = 2; x < gridLength; x = x + 2) {
			for (int y = 2; y < gridHeight; y = y + 2) {
				gridMap[x][y] = Cell.CONCRETE;
			}
		}
		for (int x = 0; x <= gridLength; x++) {
			for (int y = 0; y <= gridHeight; y++) {
				if ((x == 0) || (x == gridLength) || (y == 0) || (y == gridHeight)){
					gridMap[x][y] = Cell.CONCRETE;
				}
			}
		}	
	}			
}