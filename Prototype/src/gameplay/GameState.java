package gameplay;

import java.io.Serializable;

public class GameState implements Serializable {

	private static State state;
	private static int numberOfEnemiesleft;
	
	/**
	 * Set the state to newState.
	 * @param newState New state of the game.
	 */

	public static void setState(State newState) {
		state = newState;
		validateState();
	}
	
	/**
	 * ??????????????????????????????????????
	 */

	private static void validateState() {
		if (state == State.PLAYERDEAD) {

			state = State.RUNNING;
		}
	}

	/**
	 * Sets the number of enemies left.
	 * @param numberOfEnemiesLeft Number of enemies left.
	 */
	public static void setNumberOfEnemiesLeft(int numberOfEnemiesLeft) {
		GameState.numberOfEnemiesleft = numberOfEnemiesLeft;
	}

	/**
	 * Gets the number of enemies left.
	 * @return The number of enemies left.
	 */
	
	public static int getNumberOfEnemiesLeft() {
		return GameState.numberOfEnemiesleft;
	}
	
	/**
	 * Gets the current state of the game.
	 * @return Current game state.
	 */

	public static State getState() {
		return state;
	}

}
