package gameplay;

import java.awt.*;
import javax.swing.*;

public class Concrete extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String concrete = "concrete.png";
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