package gameplay;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel
{
	int gridLength = 31;
	int gridHeight = 13;
	
	public void initMap()
	{
		repaint();
		
	}
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i <= gridLength; i++)
		{
			g2d.drawLine(10*i, 0, 10 * i, 10 * gridHeight);
			
		}
		for (int j = 0; j <= gridHeight; j++)
		{
			g2d.drawLine(0, 10 * j, 10 * gridLength, 10 *j);
		}
		
	}
}
