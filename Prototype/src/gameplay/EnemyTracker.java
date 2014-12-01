package gameplay;

public class EnemyTracker {
	
	private int xPosition;
	private int yPosition;
	private int xDirection;
	private int yDirection;
	private Tile enemyType;
	private boolean movingInX;
	private boolean movingInY;
	
	public EnemyTracker(int xPosition, int yPosition, Tile enemyType) {
		
		this.setEnemyType(enemyType);
		this.setxPosition(xPosition);
		this.setyPosition(yPosition);
	
		setxDirection(1);
		setyDirection(1);
		setMovingInX(false);
		setMovingInY(false);
	}
	
	public Tile getEnemyType() {
		return enemyType;
	}


	public void setEnemyType(Tile enemyType) {
		this.enemyType = enemyType;
	}


	public int getyDirection() {
		return yDirection;
	}
	
	public void updateTracker(int xPosition, int yPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}


	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
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

	public boolean isMovingInY() {
		return movingInY;
	}

	public void setMovingInY(boolean movingInY) {
		this.movingInY = movingInY;
	}

	public boolean isMovingInX() {
		return movingInX;
	}

	public void setMovingInX(boolean movingInX) {
		this.movingInX = movingInX;
	}


	

}