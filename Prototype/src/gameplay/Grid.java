package gameplay;

import java.io.Serializable;

import Astar.ClosestHeuristic;
import Astar.TileBasedMap;

public class Grid implements TileBasedMap, Serializable {
	
//	private int gridSizeX;
//	private int gridSizeY;
//	private int dimension;
    
	
	public Tile[][] gridMap;
    
    
    public Grid() {
		
		//gridSizeX = Bomberman.getDimensionX();
		//gridSizeY = Bomberman.getDimensionY();
		//dimension = 25;
		//gridMap = new Tile[gridSizeX/dimension][gridSizeY/dimension];
		gridMap = new Tile[Bomberman.WIDTH][Bomberman.HEIGHT];
	}
    
   
	public Tile[][] initializeGridMap(){
		for(int i = 0; i < Bomberman.WIDTH; i++){
			for(int j = 0; j < Bomberman.HEIGHT; j++){
				gridMap[i][j] = Tile.EMPTY;
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
	public boolean blocked(Tile tileType, int x, int y) {
		
		if(tileType == Tile.KONDORIA || tileType == Tile.OVAPI || tileType == Tile.PONTAN ||tileType == Tile.KONDORIAANDBRICK || tileType == Tile.OVAPIANDBRICK || tileType == Tile.PONTANANDBRICK){
			if (gridMap[x][y] == Tile.PONTANANDBRICK ||gridMap[x][y] == Tile.OVAPIANDBRICK || gridMap[x][y] == Tile.KONDORIAANDBRICK || gridMap[x][y] == Tile.BRICKANDPOWERUPS || gridMap[x][y] == Tile.BRICKANDEXITWAY || gridMap[x][y] == Tile.CONCRETE  || gridMap[x][y] == Tile.POWERUPS ||  gridMap[x][y] == Tile.ENEMY || gridMap[x][y] == Tile.BRICKANDPOWERUPS || gridMap[x][y] == Tile.BOMB || gridMap[x][y] == Tile.PLAYERANDBOMB || gridMap[x][y] == Tile.BALLOOM || gridMap[x][y] == Tile.ONEAL || gridMap[x][y] == Tile.DOLL || gridMap[x][y] == Tile.MINVO || gridMap[x][y] == Tile.KONDORIA || gridMap[x][y] == Tile.OVAPI  || gridMap[x][y] == Tile.PASS  || gridMap[x][y] == Tile.PONTAN){
				return true;
			}
			return false;
		}
		
		else if (gridMap[x][y] == Tile.PONTANANDBRICK ||gridMap[x][y] == Tile.OVAPIANDBRICK || gridMap[x][y] == Tile.KONDORIAANDBRICK || gridMap[x][y] == Tile.BRICKANDPOWERUPS || gridMap[x][y] == Tile.BRICKANDEXITWAY || gridMap[x][y] == Tile.CONCRETE || gridMap[x][y] == Tile.BRICK || gridMap[x][y] == Tile.POWERUPS ||  gridMap[x][y] == Tile.ENEMY || gridMap[x][y] == Tile.BRICKANDPOWERUPS || gridMap[x][y] == Tile.BOMB || gridMap[x][y] == Tile.PLAYERANDBOMB || gridMap[x][y] == Tile.BALLOOM || gridMap[x][y] == Tile.ONEAL || gridMap[x][y] == Tile.DOLL || gridMap[x][y] == Tile.MINVO || gridMap[x][y] == Tile.KONDORIA || gridMap[x][y] == Tile.OVAPI  || gridMap[x][y] == Tile.PASS  || gridMap[x][y] == Tile.PONTAN){
			return true;
		}

		return false;
		
		
	/*	if(gridMap[x][y] != Tile.EMPTY || gridMap[x][y] != Tile.PLAYER){
			System.out.println("true");
			return true;
		}*/
		
		
	}



	@Override
	public float getCost(Tile tileType, int sx, int sy, int tx, int ty) {
		ClosestHeuristic manhattan = new ClosestHeuristic();
		return manhattan.getCost(tileType, sx, sy, tx, ty);
		
	}
	
	public void setContents(int x, int y, Tile tiletype){
		this.gridMap[x][y] = tiletype;
	}
	
	public Tile getContents(int x, int y){
		return this.gridMap[x][y];
	}
	
	

	
	
}
