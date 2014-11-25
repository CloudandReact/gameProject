package gameplay;

import Astar.ClosestHeuristic;
import Astar.Mover;
import Astar.TileBasedMap;

public class Grid implements TileBasedMap {
	
//	private int gridSizeX;
//	private int gridSizeY;
//	private int dimension;
    private Cell[][] gridMap;
    
    
    public Grid() {
		
		//gridSizeX = Bomberman.getDimensionX();
		//gridSizeY = Bomberman.getDimensionY();
		//dimension = 25;
		//gridMap = new Cell[gridSizeX/dimension][gridSizeY/dimension];
		gridMap = new Cell[Bomberman.WIDTH][Bomberman.HEIGHT];
	}
    
   
	public Cell[][] initializeGridMap(){
		for(int i = 0; i < Bomberman.WIDTH; i++){
			for(int j = 0; j < Bomberman.HEIGHT; j++){
				gridMap[i][j] = Cell.EMPTY;
			}
		}
		return gridMap;
	}



	@Override
	public int getWidthInTiles() {
		return Bomberman.WIDTH;
	}



	@Override
	public int getHeightInTiles() {
		return Bomberman.HEIGHT;
	}

	/**
	 * Notification that the path finder visited a given tile. This is 
	 * used for debugging new heuristics.
	 * 
	 * @param x The x coordinate of the tile that was visited
	 * @param y The y coordinate of the tile that was visited
	 */

	@Override
	public void pathFinderVisited(int x, int y) {
		
	}
	
	

	@Override
	public boolean blocked(Mover mover, int x, int y) {
		
		if (gridMap[x][y] == Cell.CONCRETE || gridMap[x][y] == Cell.BRICK || gridMap[x][y] == Cell.POWERUPS ||  gridMap[x][y] == Cell.ENEMY || gridMap[x][y] == Cell.BRICKANDPOWERUPS || gridMap[x][y] == Cell.BOMB || gridMap[x][y] == Cell.PLAYERANDBOMB){
			return true;
		}
		
		return false;
	}



	@Override
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		ClosestHeuristic manhattan = new ClosestHeuristic();
		return manhattan.getCost(mover, sx, sy, tx, ty);
		
	}
	
	public void setContents(int x, int y, Cell celltype){
		this.gridMap[x][y] = celltype;
	}
	
	public Cell getContents(int x, int y){
		return this.gridMap[x][y];
	}
	
	

	
	
}
