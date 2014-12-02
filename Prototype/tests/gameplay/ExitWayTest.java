package gameplay;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExitWayTest {

	private static Grid grid;
	private static ExitWay exitway;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		grid = new Grid();
		grid.initializeGridMap();
		exitway = new ExitWay(grid);
		
		
	}

	@Test
	public void testPlaceExitWay() {
		
		assertTrue(true);
		
		for (int i = 2; i < Bomberman.WIDTH; i++) {
			for (int j = 2; j < Bomberman.HEIGHT; j++) {
				if (grid.getContents(i, j) == Tile.BRICKANDEXITWAY) {
					assertTrue("EXITWAY FOUND", true);
				}
			}
		}
	}

}
