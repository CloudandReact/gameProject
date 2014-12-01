package gameplay;

import java.io.Serializable;

/**
 * <p>
 * The <code>GameState</code> is a simple class used to keep track of the state
 * of the game.
 * </p>
 * 
 * @author Leonardo Siracusa
 * @see State
 * @see Game
 */

public class GameState implements Serializable {

	private static final long serialVersionUID = 1L;
	private static State state;

	/**
	 * Gets the current state of the game.
	 * 
	 * @return the <code>State</code> which represents the current game state.
	 */

	public static State getState() {
		return state;
	}

	/**
	 * Set the state of the game.
	 * <p>
	 * Note: Once it the state is updated, validateState() is called.
	 * </p>
	 * 
	 * @param newState
	 *            New state of the game.
	 */

	public static void setState(State newState) {
		state = newState;
		validateState();
	}

	/**
	 * Simple method which sets the state back to RUNNING if the player is found
	 * to be dead.
	 */

	private static void validateState() {
		if (state == State.PLAYERDEAD) {
			state = State.RUNNING;
		}
	}

}
