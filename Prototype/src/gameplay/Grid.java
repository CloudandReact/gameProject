package gameplay;

import Astar.ClosestHeuristic;
import Astar.TileBasedMap;

public class Grid implements TileBasedMap {

	public Tile[][] grid;

	/**
	 * Creates a game map.
	 */
	public Grid() {

		grid = new Tile[Bomberman.WIDTH][Bomberman.HEIGHT];
	}

	/**
	 * Initializes all the Tiles in the grid to be empty.
	 * @return The empty grid.
	 */
	public Tile[][] initializeGrid() {
		
		for (int i = 0; i < Bomberman.WIDTH; i++) {
			for (int j = 0; j < Bomberman.HEIGHT; j++) {
				grid[i][j] = Tile.EMPTY;
			}
		}
		return grid;
	}
	
	/**
	 * Get the width of the tile map. The slightly odd name is used
	 * to distinguish this method from commonly used names in game maps.
	 * 
	 * @return The number of tiles across the map
	 */

	@Override
	public int getWidthInTiles() {
		return Bomberman.WIDTH;
	}
	

	/**
	 * Get the height of the tile map. The slightly odd name is used
	 * to distinguish this method from commonly used names in game maps.
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

	@Override
	public boolean blocked(Tile TileType, int x, int y) {

		if (TileType == Tile.KONDORIA || TileType == Tile.OVAPI
				|| TileType == Tile.PONTAN || TileType == Tile.KONDORIAANDBRICK
				|| TileType == Tile.OVAPIANDBRICK
				|| TileType == Tile.PONTANANDBRICK) {
			if (grid[x][y] == Tile.PONTANANDBRICK
					|| grid[x][y] == Tile.OVAPIANDBRICK
					|| grid[x][y] == Tile.KONDORIAANDBRICK
					|| grid[x][y] == Tile.BRICKANDPOWERUPS
					|| grid[x][y] == Tile.BRICKANDEXITWAY
					|| grid[x][y] == Tile.CONCRETE
					|| grid[x][y] == Tile.POWERUPS || grid[x][y] == Tile.ENEMY
					|| grid[x][y] == Tile.BRICKANDPOWERUPS
					|| grid[x][y] == Tile.BOMB
					|| grid[x][y] == Tile.PLAYERANDBOMB
					|| grid[x][y] == Tile.BALLOOM || grid[x][y] == Tile.ONEAL
					|| grid[x][y] == Tile.DOLL || grid[x][y] == Tile.MINVO
					|| grid[x][y] == Tile.KONDORIA || grid[x][y] == Tile.OVAPI
					|| grid[x][y] == Tile.PASS || grid[x][y] == Tile.PONTAN) {
				return true;
			}
			return false;
		}

		else if (grid[x][y] == Tile.PONTANANDBRICK
				|| grid[x][y] == Tile.OVAPIANDBRICK
				|| grid[x][y] == Tile.KONDORIAANDBRICK
				|| grid[x][y] == Tile.BRICKANDPOWERUPS
				|| grid[x][y] == Tile.BRICKANDEXITWAY
				|| grid[x][y] == Tile.CONCRETE || grid[x][y] == Tile.BRICK
				|| grid[x][y] == Tile.POWERUPS || grid[x][y] == Tile.ENEMY
				|| grid[x][y] == Tile.BRICKANDPOWERUPS
				|| grid[x][y] == Tile.BOMB || grid[x][y] == Tile.PLAYERANDBOMB
				|| grid[x][y] == Tile.BALLOOM || grid[x][y] == Tile.ONEAL
				|| grid[x][y] == Tile.DOLL || grid[x][y] == Tile.MINVO
				|| grid[x][y] == Tile.KONDORIA || grid[x][y] == Tile.OVAPI
				|| grid[x][y] == Tile.PASS || grid[x][y] == Tile.PONTAN) {
			return true;
		}

		return false;

	}

	@Override
	public float getCost(Tile tileType, int sx, int sy, int tx, int ty) {
		ClosestHeuristic manhattan = new ClosestHeuristic();
		return manhattan.getCost(tileType, sx, sy, tx, ty);

	}

	public void setContents(int x, int y, Tile Tiletype) {
		this.grid[x][y] = Tiletype;
	}

	public Tile getContents(int x, int y) {
		return this.grid[x][y];
	}

}
