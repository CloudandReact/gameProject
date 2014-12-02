package gameplay;

import static org.junit.Assert.*;
import gameplay.Bomb;
import gameplay.Grid;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BombTest {

	private static Bomb bombTest;
	private static Grid grid;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		grid = new Grid();
		grid.initializeGridMap();
		bombTest = new Bomb(grid);
		
	}

	@Test
	public void testExplodeWithBasicRange() {
		bombTest.setRange(1);
		bombTest.setPosition(1, 1);
		bombTest.explode();
		
		assertTrue("Tile is set to explode", grid.getContents(0,1) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(1,0) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(1,1) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(2,1) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(1,2) == Tile.EXPLOSION);
	}

	@Test
	public void testExplodeHigherRange(){
		
		bombTest.setRange(2);
		bombTest.setPosition(2,2);
		bombTest.explode();
		
		assertTrue("Tile is set to explode",grid.getContents(0,2) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(1,2) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(2,2) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(2,3) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(2,4) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(2,1) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(2,0) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(3,2) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(4,2) == Tile.EXPLOSION);
	}
	
	@Test
	public void testCollisionConcrete(){
		bombTest.setRange(2);
		bombTest.setPosition(2,2);
		
		grid.setContents(1,2, Tile.CONCRETE);
		bombTest.explode();
		
		assertTrue("Tile is set to explode",grid.getContents(0,2) == Tile.EMPTY);
		assertTrue("Tile is set to explode",grid.getContents(1,2) == Tile.CONCRETE);
		assertTrue("Tile is set to explode",grid.getContents(2,2) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(2,3) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(2,4) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(2,1) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(2,0) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(3,2) == Tile.EXPLOSION);
		assertTrue("Tile is set to explode",grid.getContents(4,2) == Tile.EXPLOSION);
		
	}

}
