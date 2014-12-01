package gameplay;

import java.io.Serializable;

import Astar.ClosestHeuristic;
import Astar.TileBasedMap;

/**
 * Enum class which defines the <code>Tile</code> type which determines which object 
 * is in each tile of the map.
 */

public enum Tile implements Serializable {
	
	EMPTY, 
	PLAYER, 
	CONCRETE,
	BRICK,
	PLAYERANDBRICK,
	BOMB,
	PLAYERANDBOMB,
	EXPLOSION,
	BOMBANDEXITWAY,
	EXITWAY,
	BRICKANDEXITWAY,
	PLAYERANDBRICKANDEXITWAY,
	PLAYERANDEXITWAY,
	POWERUPS,
	BRICKANDPOWERUPS,
	PLAYERANDBRICKANDPOWERUPS,
	PLAYERONBRICK,
	PLAYERONBOMB,
	ENEMY,
    BALLOOM,
    ONEAL,
    DOLL,
    MINVO,
    KONDORIA,
    KONDORIAANDBRICK,
    OVAPI,
    OVAPIANDBRICK,
    PASS,
    PONTAN,
    PONTANANDBRICK;
	

}
