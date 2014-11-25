package gameplay;

public class GameState {
	

	private static State state;

	
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

	public static State getState(){
		return state;
	}
	
	
}

