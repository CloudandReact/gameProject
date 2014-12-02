package gameplay;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {
	
	private static Player player;
	private static Grid grid;
	private static Enemy enemy;
	private static Level level;
	private static Game game;
	private static Bomberman bomberman;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		level = new Level(1);
		
		grid = new Grid();
		grid.initializeGridMap();
		enemy = new Enemy(grid, null, level);
		player = new Player(grid, enemy, level);
		
	}

	@Test
	public void moveTest() {
		
		// Pseudo right-key input, which would change dx to 25. 
		player.setDx(25);
		// Player is set to (1,1) on the grid. 
		player.setX(25);
		player.setY(25);
		// Toggle and calling move twice is required 
		player.toggleMovementSpeed();
		player.move();
		player.move();
		
		if(grid.getContents(2, 1) == Tile.PLAYER){
			assertTrue("Player moved right, as expected.", true);
		}
		
		else{
			fail("Player did not move");
		}
	}
	
	@Test 
	public void toggleMovementSpeedTest() {
		player.toggleMovementSpeed();
		if(player.getMovementSpeed() == 2){
			assertTrue("The movement speed has changed from 1 to 2", true);
		}
	}
	

}
