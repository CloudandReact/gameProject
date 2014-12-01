package gameplay;

import java.io.Serializable;

/**
 * This enum defines type <code>State</code> which determines the current state of the game.
 *
 */

public enum State implements Serializable{
    RUNNING, PAUSE, PLAYERDEAD, RUNNINGANDLEVELOVER, PAUSEANDLEVELOVER, TIMEOVER
}
