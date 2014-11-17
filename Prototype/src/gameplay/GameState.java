package gameplay;

public class GameState {
	
	private State state = State.RUNNING;
	
	public GameState(){
		
	}
	
	public void setState(State newState){
		state = newState;
	}
	
	public State getState(){
		return state;
	}
}

