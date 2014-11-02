package test;

public class Player {
	int speed;
	int lives;
	int positionX,positionY;
	boolean canMove;
	boolean isUpPressed,isDownPressed,isLeftPressed,isRightPressed,
	isZPressed,isXPressed;
	// last 2 wont be modified for this delivarable of booleans
	int score=0;
	int time;
	public Player(int positionX,int positionY){
		this.positionX=positionX;
		this.positionY=positionY;
	}
	public int playerHorizontal(){
		return positionX;
	}
	public int PlayerVertical(){
		return positionY;
	}
	public void changePosition(int x, int y){
		positionX=x;
		positionY=y;
	}
	
}
