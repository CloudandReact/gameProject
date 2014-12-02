package gameplay;
import static org.junit.Assert.*;

import gameplay.Bomberman;
import gameplay.Brick;
import gameplay.Grid;
import gameplay.Tile;

import org.junit.BeforeClass;
import org.junit.Test;

public class BricksTest {
	
	private static Grid grid;
	private static Brick brick;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		grid = new Grid();
		brick = new Brick(grid);
		
	}
	@Test
	public void testPlaceBricks() {
		assertTrue("No brick at (1, 1)", grid.getContents(1, 1) == Tile.EMPTY);
		assertTrue("No brick at (1, 2)", grid.getContents(1, 2) == Tile.EMPTY);
		assertTrue("No brick at (2, 1)", grid.getContents(2, 1) == Tile.EMPTY);
	}

}
