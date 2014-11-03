package gameplay;

import java.awt.*;
import javax.swing.*;

public class Concrete extends JPanel {

	private String concrete = "wall.png";

	/*
	 * private int positionX; private int positionY; private int width; private
	 * int height; private final boolean cantPass = true;
	 */
	private Image image;

	public Concrete() {
		loadImage();

	}

	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(concrete));
		image = ii.getImage();
	}

	public Image getImage() {
		return image;
	}

}