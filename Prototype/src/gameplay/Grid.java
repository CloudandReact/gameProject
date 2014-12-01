package gameplay;

import java.io.Serializable;

import Astar.ClosestHeuristic;
import Astar.TileBasedMap;

/**
 * 
 */
/**
 * <p>
 * The <code>Grid</code> is a simple class used to imitate a map. All objects
 * are use this map of tiles to function.
 * </p>
 * The implementation of a tile based map.
 * 
 * @author Leonardo Siracusa
 * @see Tile
 * @see TileBasedMap
 */

public class Grid implements TileBasedMap, Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a game map.
	 */
	public Tile[][] gridMap;

	/**
	 * Creates the game map as a 2-D array of <object>Tile</object> objects.
	 */
	public Grid() {
		gridMap = new Tile[Bomberman.WIDTH][Bomberman.HEIGHT];
	}

	/**
	 * Initializes all the Tiles in the grid to be empty.
	 * 
	 * @return The empty grid.
	 */
	public Tile[][] initializeGridMap() {
		for (int i = 0; i < Bomberman.WIDTH; i++) {
			for (int j = 0; j < Bomberman.HEIGHT; j++) {
				gridMap[i][j] = Tile.EMPTY;
			}
		}
		return gridMap;
	}

	/**
	 * Get the width of the tile map. The slightly odd name is used to
	 * distinguish this method from commonly used names in game maps.
	 * 
	 * @return The number of tiles across the map
	 */

	@Override
	public int getWidthInTiles() {
		return Bomberman.WIDTH;
	}

	/**
	 * Get the height of the tile map. The slightly odd name is used to
	 * distinguish this method from commonly used names in game maps.
	 * 
	 * @return The number of tiles down the map
	 */
	@Override
	public int getHeightInTiles() {
		return Bomberman.HEIGHT;
	}

	/**
	 * Notification that the path finder visited a given tile. This is used for
	 * debugging new heuristics.
	 * 
	 * @param x
	 *            The x coordinate of the tile that was visited
	 * @param y
	 *            The y coordinate of the tile that was visited
	 */

	@Override
	public void pathFinderVisited(int x, int y) {

	}

	/**
	 * Check if the given location is blocked, i.e. blocks movement of the
	 * supplied mover.
	 * 
	 * @param tileType
	 *            The enemy type that is potentially moving through the
	 *            specified tile.
	 * @param x
	 *            The x coordinate of the tile to check
	 * @param y
	 *            The y coordinate of the tile to check
	 * @return True if the location is blocked
	 */

	@Override
	public boolean blocked(Tile tileType, int x, int y) {

		if (tileType == Tile.KONDORIA || tileType == Tile.OVAPI
				|| tileType == Tile.PONTAN || tileType == Tile.KONDORIAANDBRICK
				|| tileType == Tile.OVAPIANDBRICK
				|| tileType == Tile.PONTANANDBRICK) {
			if (gridMap[x][y] == Tile.PONTANANDBRICK
					|| gridMap[x][y] == Tile.OVAPIANDBRICK
					|| gridMap[x][y] == Tile.KONDORIAANDBRICK
					|| gridMap[x][y] == Tile.BRICKANDPOWERUPS
					|| gridMap[x][y] == Tile.BRICKANDEXITWAY
					|| gridMap[x][y] == Tile.CONCRETE
					|| gridMap[x][y] == Tile.POWERUPS
					|| gridMap[x][y] == Tile.ENEMY
					|| gridMap[x][y] == Tile.BRICKANDPOWERUPS
					|| gridMap[x][y] == Tile.BOMB
					|| gridMap[x][y] == Tile.PLAYERANDBOMB
					|| gridMap[x][y] == Tile.BALLOOM
					|| gridMap[x][y] == Tile.ONEAL
					|| gridMap[x][y] == Tile.DOLL
					|| gridMap[x][y] == Tile.MINVO
					|| gridMap[x][y] == Tile.KONDORIA
					|| gridMap[x][y] == Tile.OVAPI
					|| gridMap[x][y] == Tile.PASS
					|| gridMap[x][y] == Tile.PONTAN) {
				return true;
			}
			return false;
		}

		else if (gridMap[x][y] == Tile.PONTANANDBRICK
				|| gridMap[x][y] == Tile.OVAPIANDBRICK
				|| gridMap[x][y] == Tile.KONDORIAANDBRICK
				|| gridMap[x][y] == Tile.BRICKANDPOWERUPS
				|| gridMap[x][y] == Tile.BRICKANDEXITWAY
				|| gridMap[x][y] == Tile.CONCRETE
				|| gridMap[x][y] == Tile.BRICK
				|| gridMap[x][y] == Tile.POWERUPS
				|| gridMap[x][y] == Tile.ENEMY
				|| gridMap[x][y] == Tile.BRICKANDPOWERUPS
				|| gridMap[x][y] == Tile.BOMB
				|| gridMap[x][y] == Tile.PLAYERANDBOMB
				|| gridMap[x][y] == Tile.BALLOOM || gridMap[x][y] == Tile.ONEAL
				|| gridMap[x][y] == Tile.DOLL || gridMap[x][y] == Tile.MINVO
				|| gridMap[x][y] == Tile.KONDORIA
				|| gridMap[x][y] == Tile.OVAPI || gridMap[x][y] == Tile.PASS
				|| gridMap[x][y] == Tile.PONTAN) {
			return true;
		}

		return false;

	}

	/**
	 * Get the cost of moving through the given tile. This can be used to make
	 * certain areas more desirable. A simple and valid implementation of this
	 * method would be to return 1 in all cases.
	 * 
	 * @param tileType
	 *            The enemy that is trying to move across the tile
	 * @param sx
	 *            The x coordinate of the tile we're moving from
	 * @param sy
	 *            The y coordinate of the tile we're moving from
	 * @param tx
	 *            The x coordinate of the tile we're moving to
	 * @param ty
	 *            The y coordinate of the tile we're moving to
	 * @return The relative cost of moving across the given tile
	 */
	@Override
	public float getCost(Tile tileType, int sx, int sy, int tx, int ty) {
		ClosestHeuristic manhattan = new ClosestHeuristic();
		return manhattan.getCost(tileType, sx, sy, tx, ty);

	}

	/**
	 * Sets the contents of the tile.
	 * 
	 * @param x
	 *            index of the tile in x
	 * @param y
	 *            index of the tile in y
	 * @param tiletype
	 *            type to which the tile is set to
	 */

	public void setContents(int x, int y, Tile tiletype) {
		this.gridMap[x][y] = tiletype;
	}

	/**
	 * Gets the contents of the tile.
	 * 
	 * @param x
	 *            index of the tile in x
	 * @param y
	 *            index of the tile in y
	 * @return the type of the tile at given indexes
	 */
	public Tile getContents(int x, int y) {
		return this.gridMap[x][y];
	}

	/**
	 * Checks if the given tile contains an enemy.
	 * 
	 * @param posX
	 *            index of the tile in x
	 * @param posY
	 *            index of the tile in y
	 * @return
	 */
	public boolean checkIfEnemy(int posX, int posY) {
		if (gridMap[posX][posY] == Tile.BALLOOM
				|| gridMap[posX][posY] == Tile.ONEAL
				|| gridMap[posX][posY] == Tile.DOLL
				|| gridMap[posX][posY] == Tile.MINVO
				|| gridMap[posX][posY] == Tile.KONDORIA
				|| gridMap[posX][posY] == Tile.OVAPI
				|| gridMap[posX][posY] == Tile.PASS
				|| gridMap[posX][posY] == Tile.PONTAN
				|| gridMap[posX][posY] == Tile.KONDORIAANDBRICK
				|| gridMap[posX][posY] == Tile.OVAPIANDBRICK
				|| gridMap[posX][posY] == Tile.PONTANANDBRICK) {
			return true;
		}

		return false;
	}

}
