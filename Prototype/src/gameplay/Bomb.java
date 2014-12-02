package gameplay;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Bomb class deals with explosions and Player scoring, and contains all
 * attributes required for a bomb object.
 * 
 * @author Leonardo Siracusa
 */

public class Bomb implements Runnable, Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final int BALLOOM_SCORE = 100;
	private static final int ONEAL_SCORE = 200;
	private static final int DOLL_SCORE = 400;
	private static final int MINVO_SCORE = 800;
	private static final int KONDORIA_SCORE = 1000;
	private static final int OVAPI_SCORE = 2000;
	private static final int PASS_SCORE = 4000;
	private static final int PONTAN_SCORE = 8000;
	private static final int BOMB_TIMER_IN_MILLISECONDS = 2000;

	private String imageNameBomb = "bomb.png";
	private String imageNameBombAndBomberman = "bomb&Bomberman.jpg";
	private String imageNameExplosion = "explosion.png";

	private static int numberOfEnemiesKilled;
	private int pointsScoredWithThisBomb;
	private int totalGameScore;
	private int range;
	private int numberOfBombs;
	private int currentRange;
	private int bombNumber;
	private int positionX;
	private int positionY;

	private List<Integer> scoreForBomb;
	private Grid grid;
	private Enemy enemy;
	private Player player;
	ImageIcon firstImageIcon;
	ImageIcon secondImageIcon;
	ImageIcon thirdImageIcon;

	/**
	 * Constructor that initializes a <code>Bomb</code> object which initializes
	 * all the attributes pertaining to bombs. This constructor is primarily
	 * used to get the bomb's image.
	 * 
	 * @param grid
	 *            The map on which bombs appear. The bomb must alter the grid.
	 */

	public Bomb(Grid grid) {
		this.grid = grid;
		initialize();
	}

	/**
	 * Constructor that initializes a <code>Bomb</code> object which initializes
	 * all the attributes pertaining to bombs, initializes the score list and
	 * loads the bomb's image.
	 * 
	 * @param grid
	 *            an object of <code>Grid</code> which contains the map on which
	 *            bombs appear. The bomb must alter the grid.
	 * @param enemy
	 *            an object of <code>Enemy</code>. Used for when the ExitWay is
	 *            hit by a bomb.
	 * @param player
	 *            an object of <code>Player</code> which allows the bomb class
	 *            to access the player's attributes pertaining to bombs.
	 */

	public Bomb(Grid grid, Enemy enemy, Player player) {

		this.player = player;
		this.enemy = enemy;
		this.grid = grid;
		initialize();
	}

	/**
	 * Method to initialize all attributes pertaining to bomb.
	 */
	private void initialize() {
		scoreForBomb = new ArrayList<Integer>();
		setTotalGameScore(0);
		loadImage();
		setRange(1);
		setNumberOfBombs(1);
		Bomb.setNumberOfEnemiesKilled(0);
	}

	/**
	 * </p> This method deals with the bomb exploding logic, and alters the grid
	 * to include the cell type EXPLOSION where possible. It loops through the
	 * positions in each direction, checking the conditions for which it should
	 * explode. Looping stops when the range has been reached in that direction.
	 * Hitting a concrete before reaching the final value for range will break
	 * the loop, as we do not want to explode across concrete. This method also
	 * deals with player scoring; depending on the enemy type, the player's score
	 * is updated accordingly.</p>
	 */

	public void explode() {

		pointsScoredWithThisBomb = 0;
		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(positionX + currentRange, positionY) != Tile.CONCRETE) {

				if (grid.getContents(positionX + currentRange, positionY) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(positionX + currentRange, positionY,
							Tile.POWERUPS);
				} else if (grid
						.getContents(positionX + currentRange, positionY) == Tile.BRICKANDEXITWAY) {
					grid.setContents(positionX + currentRange, positionY,
							Tile.EXITWAY);
				} else if (grid
						.getContents(positionX + currentRange, positionY) == Tile.EXITWAY) {
					grid.setContents(positionX + currentRange, positionY,
							Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);
				} else if (grid.checkIfEnemy(positionX + currentRange,
						positionY)) {

					if (grid.getContents(positionX + currentRange, positionY) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(BALLOOM_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX + currentRange, positionY) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(ONEAL_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX + currentRange, positionY) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(DOLL_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX + currentRange, positionY) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(MINVO_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX + currentRange, positionY) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(KONDORIA_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX + currentRange, positionY) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(OVAPI_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX + currentRange, positionY) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(PASS_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX + currentRange, positionY) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(PONTAN_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}
				}

				else if (grid.getContents(positionX + currentRange, positionY) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(positionX + currentRange, positionY,
								Tile.PLAYER);
					} else {
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}
				} else {

					grid.setContents(positionX + currentRange, positionY,
							Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(positionX + currentRange, positionY) == Tile.CONCRETE) {

				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(positionX - currentRange, positionY) != Tile.CONCRETE) {

				if (grid.getContents(positionX - currentRange, positionY) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(positionX - currentRange, positionY,
							Tile.POWERUPS);

				} else if (grid
						.getContents(positionX - currentRange, positionY) == Tile.BRICKANDEXITWAY) {
					grid.setContents(positionX - currentRange, positionY,
							Tile.EXITWAY);

				} else if (grid
						.getContents(positionX - currentRange, positionY) == Tile.EXITWAY) {
					grid.setContents(positionX - currentRange, positionY,
							Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} else if (grid.checkIfEnemy(positionX - currentRange,
						positionY)) {

					if (grid.getContents(positionX - currentRange, positionY) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(BALLOOM_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(ONEAL_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(DOLL_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(MINVO_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(KONDORIA_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(OVAPI_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(PASS_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(PONTAN_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.KONDORIAANDBRICK) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(KONDORIA_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.OVAPIANDBRICK) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(OVAPI_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.PONTANANDBRICK) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(PONTAN_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

				} else if (grid
						.getContents(positionX - currentRange, positionY) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(positionX - currentRange, positionY,
								Tile.PLAYER);
					} else {
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
				} else {
					grid.setContents(positionX - currentRange, positionY,
							Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(positionX - currentRange, positionY) == Tile.CONCRETE) {
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {
			if (grid.getContents(positionX, positionY + currentRange) != Tile.CONCRETE) {

				if (grid.getContents(positionX, positionY + currentRange) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(positionX, positionY + currentRange,
							Tile.POWERUPS);

				} else if (grid
						.getContents(positionX, positionY + currentRange) == Tile.BRICKANDEXITWAY) {
					grid.setContents(positionX, positionY + currentRange,
							Tile.EXITWAY);
				} else if (grid
						.getContents(positionX, positionY + currentRange) == Tile.EXITWAY) {
					grid.setContents(positionX, positionY + currentRange,
							Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} else if (grid.checkIfEnemy(positionX, positionY
						+ currentRange)) {

					if (grid.getContents(positionX, positionY + currentRange) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(BALLOOM_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(ONEAL_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(DOLL_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(MINVO_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(KONDORIA_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(OVAPI_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(PASS_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(PONTAN_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
				} else if (grid
						.getContents(positionX, positionY + currentRange) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(positionX, positionY + currentRange,
								Tile.PLAYER);
					} else {
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
				} else {
					grid.setContents(positionX, positionY + currentRange,
							Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(positionX, positionY + currentRange) == Tile.CONCRETE) {
				// Here we come out of the loop as we don't want to EXPLOSION
				// across the concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(positionX, positionY - currentRange) != Tile.CONCRETE) {

				if (grid.getContents(positionX, positionY - currentRange) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(positionX, positionY - currentRange,
							Tile.POWERUPS);

				} else if (grid
						.getContents(positionX, positionY - currentRange) == Tile.BRICKANDEXITWAY) {
					grid.setContents(positionX, positionY - currentRange,
							Tile.EXITWAY);
				} else if (grid
						.getContents(positionX, positionY - currentRange) == Tile.EXITWAY) {
					grid.setContents(positionX, positionY - currentRange,
							Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				}

				else if (grid.checkIfEnemy(positionX, positionY - currentRange)) {

					if (grid.getContents(positionX, positionY - currentRange) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(BALLOOM_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);

					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(ONEAL_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(DOLL_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(MINVO_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(KONDORIA_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(OVAPI_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(PASS_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForBomb.add(PONTAN_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
				}

				else if (grid.getContents(positionX, positionY - currentRange) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(positionX, positionY - currentRange,
								Tile.PLAYER);
					} else {
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
				}

				else {
					grid.setContents(positionX, positionY - currentRange,
							Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(positionX, positionY - currentRange) == Tile.CONCRETE) {
				// Here we come out of the loop as we don't want to EXPLOSION
				// across the concrete
				break;
			}
		}

		currentRange = 0;

		if (scoreForBomb.size() == 1) {
			pointsScoredWithThisBomb = scoreForBomb.get(0);
		} else if (scoreForBomb.size() == 2) {

			int temp = scoreForBomb.get(0);

			if (temp < scoreForBomb.get(1)) {
				pointsScoredWithThisBomb = temp * 2 + scoreForBomb.get(1);
			} else {
				pointsScoredWithThisBomb = temp + 2 * scoreForBomb.get(1);
			}

		} else {
			for (int i = 0; i < scoreForBomb.size(); i++) {
				int temp = (int) ((int) scoreForBomb.get(i) * Math.pow(2, i));
				pointsScoredWithThisBomb += temp;
			}

		}

		setTotalGameScore(getTotalGameScore() + pointsScoredWithThisBomb);
		PlayerInfo.playerScore += getPointsScoredWithThisBomb();
		scoreForBomb.clear();

	}

	@Override
	/**
	 * Run method which is executed when the thread is started. Deals with bomb logic for the detonate powerup, as well as the timer
	 * for regular bomb explosions. This method calls explode(). 
	 */
	public void run() {

		long startTime;
		startTime = System.currentTimeMillis();

		if (player.hasDetonate()) {
			while (true) {

				if (player.getDetonatePressed()
						&& Player.getBombNumber() == bombNumber) {
					Player.setBombsOnGround(Player.getBombsOnGround() - 1);
					player.setDetonatePressed(false);
					Player.setBombNumber(Player.getBombNumber() - 1);
					Player.setCurrentBombCounter(Player.getCurrentBombCounter() + 1);
					this.explode();
					break;
				}
			}
		}

		else {
			while (true) {
				long currentTime = System.currentTimeMillis() - startTime;
				if (currentTime >= BOMB_TIMER_IN_MILLISECONDS) {
					Player.setBombsOnGround(Player.getBombsOnGround() - 1);
					this.explode();
					break;
				}

			}
		}

	}

	/**
	 * Loads the images related to bombs.
	 */

	private void loadImage() {
		firstImageIcon = new ImageIcon(getClass().getResource(imageNameBomb));

		secondImageIcon = new ImageIcon(getClass().getResource(
				imageNameBombAndBomberman));

		thirdImageIcon = new ImageIcon(getClass().getResource(
				imageNameExplosion));
	}

	/**
	 * Sets the range of the bomb.
	 * 
	 * @param range
	 *            The range of the bomb.
	 */

	public void setRange(int range) {
		this.range = range;
	}

	/**
	 * Sets the bomb's position in x and y.
	 * 
	 * @param positionX
	 *            Bomb's x position.
	 * @param positionY
	 *            Bomb's y position.
	 */

	public void setPosition(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;

	}

	/**
	 * Gets the points scored with this one particular bomb.
	 * 
	 * @return the <code>int</code> represents the points scored with this bomb.
	 */

	public int getPointsScoredWithThisBomb() {
		return pointsScoredWithThisBomb;
	}

	/**
	 * Gets points scored with one bomb.
	 * 
	 * @param pointsScoredWithThisBomb
	 *            Points scored with this one bomb.
	 */

	public void setPointsScoredWithThisBomb(int pointsScoredWithThisBomb) {
		this.pointsScoredWithThisBomb = pointsScoredWithThisBomb;
	}

	/**
	 * Gets the total score earned by the player in the current game.
	 * 
	 * @return the <code>int</code> representing the Score for the current game.
	 */

	public int getTotalGameScore() {
		return totalGameScore;
	}

	/**
	 * Sets the total score earned by the player in the current game.
	 * 
	 * @param totalGameScore
	 *            Score for the current game.
	 */
	public void setTotalGameScore(int totalGameScore) {
		this.totalGameScore = totalGameScore;
	}

	/**
	 * Gets the number of bombs available to the player.
	 * 
	 * @return the <code>int</code> which represents the number of bombs.
	 */
	public int getNumberOfBombs() {
		return numberOfBombs;
	}

	/**
	 * Sets the number of bombs available to the player.
	 * 
	 * @param bombs number of bombs available to the player.
	 */

	public void setNumberOfBombs(int numberOfBombs) {
		this.numberOfBombs = numberOfBombs;
	}

	/**
	 * <p>
	 * Used for detonate logic.
	 * </p>
	 * Set the bombNumber pertaining to this bomb object.
	 * 
	 * @param bombNumber
	 *            which represents the bomb's bombNumber.
	 */

	public void setBombNumber(int bombNumber) {
		this.bombNumber = bombNumber;
	}

	/**
	 * Gets the number of enemies killed.
	 * 
	 * @return the <code>int</code> representing the number of enemies that have
	 *         been killed.
	 */

	public static int getNumberOfEnemiesKilled() {
		return numberOfEnemiesKilled;
	}

	/**
	 * Sets the number of enemies killed.
	 * 
	 * @param the int representing the number of enemies that have been killed.
	 */

	public static void setNumberOfEnemiesKilled(int enemiesKilled) {
		Bomb.numberOfEnemiesKilled = enemiesKilled;
	}

	/**
	 * Gets the image for the bomb.
	 * 
	 * @return Image shown when bomb is in a tile by itself.
	 */
	public Image getImageBomb() {
		return firstImageIcon.getImage();
	}

	/**
	 * Gets the image shown when Bomberman and the bomb are in the same tile.
	 * 
	 * @return The image shown when Bomberman and the bomb are in the same tile.
	 */
	public Image getImageBombPlayer() {
		return secondImageIcon.getImage();
	}

	/**
	 * Gets the image shown when a bomb explodes.
	 * 
	 * @return The image shown when a bomb explodes.
	 */

	public Image getImageExplosion() {
		return thirdImageIcon.getImage();
	}

}