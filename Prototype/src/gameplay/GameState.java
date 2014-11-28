package gameplay;

public class GameState {
	

	private static State state;
	private static int numberOfEnemiesleft;

	
	public static void setState(State newState){
		state = newState;
		validateState();
	}
	
	private static void validateState() {
		if(state == State.PLAYERDEAD){
			System.out.println("PLAYERDEAD");
			
			state = State.RUNNING;
			//new Bomberman();	
		}	
	}
	
	public static void setNumberOfEnemiesLeft(int numberOfEnemiesLeft){
		GameState.numberOfEnemiesleft = numberOfEnemiesLeft;
	}
	public static int getNumberOfEnemiesLeft(){
		return GameState.numberOfEnemiesleft;
	}
	
	public static State getState(){
		return state;
	}
	
	
}

