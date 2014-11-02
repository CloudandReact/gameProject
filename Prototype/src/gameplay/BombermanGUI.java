package gameplay;

import javax.swing.*;
import java.awt.*;

public class BombermanGUI
{

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Bomberman");
		Map map = new Map();
		frame.setLayout(new BorderLayout());;
		frame.add(map, BorderLayout.CENTER);
		map.initMap();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setVisible(true);
		
	}

}
