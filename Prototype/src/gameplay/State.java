package gameplay;

import java.io.Serializable;

public enum State implements Serializable{
    RUNNING, PAUSE, PLAYERDEAD, RUNNINGANDLEVELOVER, PAUSEANDLEVELOVER, TIMEOVER
}
