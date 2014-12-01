package Astar;

import gameplay.Tile;


/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile.
 * 
 * @author Kevin Glass
 * @author Leonardo Siracusa
 */
public class ClosestHeuristic implements AStarHeuristic {
	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, Mover, int, int, int, int)
	 */
	public float getCost(Tile cellType, int x, int y, int tx, int ty) {		
		
		// Manhattan Distance
		float dx = Math.abs(tx - x);
	    float dy = Math.abs(ty - y);
	    float result = dx+dy;
	    
	    return result;
	    
	}

}