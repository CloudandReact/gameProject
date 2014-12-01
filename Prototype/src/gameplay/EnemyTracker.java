package gameplay;

import java.io.Serializable;

public class EnemyTracker implements Serializable{
	
	private Tile enemyType;
	 
	private int positionX;
	private int positionY;
	
	private int directionX;
	private int directionY;
	
	private boolean movingInX;
	private boolean movingInY;
	
	/**
	 * Creates an enemy tracker which stores the position and direction of one enemy.
	 * @param positionX Enemy's initial position in x.
	 * @param positionY Enemy's initial position in y.
	 * @param enemyType Enemy's type. 
	 */
	
	public EnemyTracker(int positionX, int positionY, Tile enemyType) {
		
		this.setEnemyType(enemyType);
		this.setPositionX(positionX);
		this.setPositionY(positionY);
	
		setDirectionX(1);
		setDirectionY(1);
		setMovingInX(false);
		setMovingInY(false);
	}
	
	/**
	 * Gets the enemy type.
	 * @return The enemy type.
	 */
	public Tile getEnemyType() {
		return enemyType;
	}

	/**
	 * Sets the enemy type.
	 * @param enemyType
	 */
	public void setEnemyType(Tile enemyType) {
		this.enemyType = enemyType;
	}
	
	/**
	 * Gets the enemy's position in x.
	 * @return Enemy's position in x.
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * Gets the enemy's position in x.
	 * @param positionX
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	/**
	 * Gets the enemy's position in y.
	 * @return Enemy's position in y.
	 */
	public int getPositionY() {
		return positionY;
	}
	
	/**
	 * Sets the enemy's the position in y.
	 * @param positionY
	 */

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}


	/**
	 * Gets the enemy's direction in y.
	 * @return Enemy's direction in y.
	 */
	public int getDirectionY() {
		return directionY;
	}

	/**
	 * Sets the enemy's direction in y.
	 * @param directionY Enemy's direction in y.
	 */
	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	}
	
	/**
	 * Gets the enemy's direction in x.
	 * @return Enemy's direction in x.
	 */

	public int getDirectionX() {
		return directionX;
	}
	
	/**
	 * Sets the enemy's direction in x.
	 * @param directionX Enemy's direction in x.
	 */

	public void setDirectionX(int directionX) {
		this.directionX = directionX;
	}
	
	/**
	 * @return True if enemy is moving in x.
	 */
	public boolean isMovingInX() {
		return movingInX;
	}

	/**
	 * @param movingInX True if the enemy is moving in x.
	 */
	public void setMovingInX(boolean movingInX) {
		this.movingInX = movingInX;
	}


	

	/**
	 * @return True if enemy is moving in Y.
	 */
	public boolean isMovingInY() {
		return movingInY;
	}

	/**
	 * @param movingInY True if the enemy is moving in y.
	 */
	public void setMovingInY(boolean movingInY) {
		this.movingInY = movingInY;
	}

}