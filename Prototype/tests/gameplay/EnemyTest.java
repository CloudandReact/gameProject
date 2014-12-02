package gameplay;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EnemyTest {
	
	private static Grid grid;
	private static Level level;
	private static Enemy enemyTest;
	private static Concrete concrete;

	
	private static Level levelAStar;
	private static Enemy enemyAStarTest;

	
	private static int enemyPosX;
	private static int enemyPosY;
	
	private static int enemyAStarPosX;
	private static int enemyAStarPosY;
	

	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		grid = new Grid();
		grid.initializeGridMap();
		
		level = new Level(1);
		concrete = new Concrete(grid);
		enemyTest = new Enemy(grid, null, level);
		
		levelAStar = new Level(10);
		enemyAStarTest = new Enemy(grid, null, levelAStar);
		
		
		
	}

	@Test
	public void testMove() {
		
		/*
		 * Set the number of enemies to 1 on the grid since it was defaulted to 6. 
		 */
		System.out.println("INITIAL ENEMY COUNT" + enemyTest.getEnemyCount());
		while(enemyTest.getEnemyCount() > 1){
			for(int x = 0; x < Bomberman.WIDTH; x++){
				for(int y = 0; y < Bomberman.HEIGHT; y++){
					if(enemyTest.getEnemyCount() > 1 && grid.getContents(x,y) == Tile.BALLOOM){
						grid.setContents(x,y,Tile.EMPTY);
						enemyTest.setEnemyCount(enemyTest.getEnemyCount()-1);

					}
				}
			}
			
		}
		
		
		/*
		 * Find the position of the remaining enemy.
		 */
		for(int i = 0; i < Bomberman.WIDTH; i++){
			for(int j = 0; j < Bomberman.HEIGHT; j++){
				if(grid.getContents(i,j) == Tile.BALLOOM){
					enemyPosX = i;
					enemyPosY = j;
				}
			}
		}
		
		/*
		 * Call the move function. Called repeatedly since there is a count which will not call the required move method until the
		 * mod evaluates to 0. 
		 */
		enemyTest.move(5, 5);
		enemyTest.move(5, 5);
		enemyTest.move(5, 5);
		enemyTest.move(5, 5);
		
		/*
		 * Perform the required checks. Note if move works, tracker is working.  
		 */
		if(grid.getContents(enemyPosX, enemyPosY) == Tile.BALLOOM){
			fail("Enemy didn't move");
			System.out.println("YES");
		}
			
		else{
			assertTrue("Enemy moved", true);

		}
		
	}
	
	@Test
	public void testAStarMove(){
		
		while(enemyAStarTest.getEnemyCount() > 1){
			for(int i = 0; i < Bomberman.WIDTH; i++){
				for(int j = 0; j < Bomberman.HEIGHT; j++){
					if(enemyAStarTest.getEnemyCount() > 1 && (grid.checkIfEnemy(i, j) && grid.getContents(i,j) != Tile.KONDORIA)){
						grid.setContents(i,j,Tile.EMPTY);
						enemyAStarTest.setEnemyCount(enemyAStarTest.getEnemyCount()-1);
					}
				}
			}
		}	
		
		/*
		 * Find the position of the remaining enemy.
		 */
		for(int i = 0; i < Bomberman.WIDTH; i++){
			for(int j = 0; j < Bomberman.HEIGHT; j++){
				if(grid.getContents(i,j) == Tile.KONDORIA){
					enemyAStarPosX = i;
					enemyAStarPosY = j;
					System.out.println('X' + enemyPosX );
				}
			}
		}
		Player player = new Player(grid,enemyAStarTest,levelAStar);

		player.setX(enemyAStarPosX * Bomberman.WIDTH);
		player.setY((enemyAStarPosY-1) * (Bomberman.HEIGHT));
		
		/*
		 * This may cause array out of bounds error. Needs to be called multiple times because of the count and modulo which are used
		 * to represent movement speeds. 
		 */
		while(grid.getContents(player.getX()/Bomberman.TILE_SIZE, player.getY()/Bomberman.TILE_SIZE) == Tile.PLAYER){
			System.out.println("X" + player.getX()/Bomberman.TILE_SIZE);
			System.out.println("Y" + player.getY()/Bomberman.TILE_SIZE);

			enemyAStarTest.move(player.getX()/Bomberman.TILE_SIZE, player.getY()/Bomberman.TILE_SIZE);
			System.out.println("lasdlasd");
		}

		if(grid.getContents(player.getX()/Bomberman.TILE_SIZE, player.getY()/Bomberman.TILE_SIZE) != Tile.PLAYER){
			assertTrue("A star is functional", true);
		}
		else{
			fail("player alive");
		}
	}
	
}
