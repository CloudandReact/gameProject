package gameplay;

public class EnemyTracker {
	
	private int enemyID;
	private int xPosition;
	private int yPosition;
	private int xDirection;
	private int yDirection;
	
	public EnemyTracker(int enemyID, int xPosition, int yPosition) {
		
		this.enemyID = enemyID;
		this.setxPosition(xPosition);
		this.setyPosition(yPosition);
		setxDirection(0);
		yDirection = 0;
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public int getxDirection() {
		return xDirection;
	}

	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}
	

}