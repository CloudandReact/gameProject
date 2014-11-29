package gameplay;

import java.awt.*;

import javax.swing.*;

public class Concrete {
	
	private String concrete = "concrete.png";

	private Image imageConcrete;

	private Grid grid;

	public Concrete(Grid grid) {
		this.grid = grid;
		loadImage();
		placeConcrete();

	}

	private void placeConcrete() {

		for (int x = 2; x < Bomberman.WIDTH; x = x + 2) {
			for (int y = 2; y < Bomberman.HEIGHT; y = y + 2) {
				grid.setContents(x, y, Cell.CONCRETE);
			}
		}

		for (int x = 0; x < Bomberman.WIDTH; x++) {
			for (int y = 0; y < Bomberman.HEIGHT; y++) {
				if ((x == 0) || (x == Bomberman.WIDTH - 1) || (y == 0)
						|| (y == Bomberman.HEIGHT - 1)) {
					grid.setContents(x, y, Cell.CONCRETE);
				}
			}
		}
	}

	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(concrete));
		imageConcrete = ii.getImage();
	}

	public Image getImage() {
		return imageConcrete;
	}

}