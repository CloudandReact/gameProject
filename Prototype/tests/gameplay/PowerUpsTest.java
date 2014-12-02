package gameplay;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PowerUpsTest {

	private static Player player;
	private static Grid grid;
	private static Enemy enemy;
	private static Level level;
	private static PowerUps powerUps;
	private int range;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		level = new Level(1);
		grid = new Grid();
		grid.initializeGridMap();
		player = new Player(grid, null, level);
		powerUps = new PowerUps(grid, player, level);
		this.range = player.getRange();
	}

	@Test
	public void testGivePowerUp() {
		powerUps.givePowerUp();
		
		if(player.getRange() == range + 1){
			assertTrue("Range has been incremented", true);
		}
		else{
			fail("Range has not been incremented");
		}
	}

}
