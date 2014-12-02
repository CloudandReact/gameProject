package gameplay;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GridTest {
	
	private static Grid grid;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Before 
	public void setUp() throws Exception {
		grid = new Grid();
		
	}
	public static void testInitializeGridMap() {
		
	}
	
	@Test  
	public void testGetCost() {

		float costKondoria = grid.getCost(Tile.KONDORIA, 1, 1, 1, 2);
		float costBalloom = grid.getCost(Tile.BALLOOM, 1, 3, 1, 2);
		assertTrue(costKondoria == costBalloom);
		assertTrue(costKondoria == (float) 1);
	}
	
	@Test
	public void testBlocked() {
		grid.setContents(1, 2, Tile.BRICK);
		assertTrue("Balloom is blocked by a brick", grid.blocked(Tile.BALLOOM, 1, 2));
		assertFalse("Kondoria is not blocked by a brick", grid.blocked(Tile.KONDORIA, 1, 2));
	}


}
