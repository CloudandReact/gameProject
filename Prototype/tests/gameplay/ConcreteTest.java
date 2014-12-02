package gameplay;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConcreteTest {

	private static Grid grid;
	private static Concrete concrete;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		grid = new Grid();
		concrete = new Concrete(grid);
	}

	@Test
	public void TestPlaceConcreteOnTheMap() {

		for (int x = 2; x < Bomberman.WIDTH; x = x + 2) {
			for (int y = 2; y < Bomberman.HEIGHT; y = y + 2) {
				assertTrue(grid.getContents(x, y) == Tile.CONCRETE);
			}
		}
	}

	@Test
	public void TestPlaceConcreteOnTheBorder() {

		for (int x = 0; x < Bomberman.WIDTH; x++) {
			for (int y = 0; y < Bomberman.HEIGHT; y++) {
				if ((x == 0) || (x == Bomberman.WIDTH - 1) || (y == 0)
						|| (y == Bomberman.HEIGHT - 1)) {
					assertTrue(grid.getContents(x, y) == Tile.CONCRETE);
				}
			}
		}

	}

}
