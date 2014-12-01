package gameplay;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import Astar.*;

/**
 * 
 * <p>
 * The <code>Enemy</code> class stores all the required functions done by all
 * enemy types. The required movement in the specifications has been
 * implemented. A specific enemies location on the grid are stored using the
 * EnemyTracker class. Additionally, ExitWay blown up logic is done here.
 * </p>
 * 
 * @author Leonardo Siracusa
 * @see EnemyTracker
 */

public class Enemy implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Balloom = "Balloom.png";

	private final int ENEMY_COUNT_EXITWAY = 8;

	private String Oneal = "Oneal.png";
	private String Doll = "Doll.png";
	private String Minvo = "Minvo.png";
	private String Kondoria = "Kondoria.png";
	private String KondoriaAndBrick = "KondoriaAndBrick.jpg";
	private String Ovapi = "Ovapi.png";
	private String OvapiAndBrick = "OvapiAndBrick.jpg";
	private String Pass = "Pass.png";
	private String Pontan = "Pontan.png";
	private String PontanAndBrick = "PontanAndBrick.jpg";

	private int numberOfBallooms;
	private int numberOfOneals;
	private int numberOfDolls;
	private int numberOfMinvos;
	private int numberOfKondorias;
	private int numberOfOvapis;
	private int numberOfPasses;
	private int numberOfPontans;
	private int count;
	private int enemyCount;

	private Grid grid;
	private Grid tempGrid;
	private Game game;
	private Level level;

	private Tile highestLevelEnemy;
	private Path path;
	private PathFinder finder;
	private EnemyTracker tracker;
	private EnemyTracker livingEnemy;
	private ArrayList<EnemyTracker> enemiesInitial;
	private ArrayList<EnemyTracker> enemiesAlive;

	private boolean isExitwayBlownUp;

	ImageIcon first;
	ImageIcon second;
	ImageIcon third;
	ImageIcon fourth;
	ImageIcon fifth;
	ImageIcon sixth;
	ImageIcon seventh;
	ImageIcon eigth;
	ImageIcon nine;
	ImageIcon ten;
	ImageIcon eleven;

	private final int numberOfEnemiesAfterExitwayIsBlownUp;

	/**
	 * Constructor that initializes an <code>Enemy</code> object which
	 * initializes the required attributes and loads the enemies on the grid.
	 * Game object is required for player alive and dead logic, while the level
	 * object allows us to know which enemies we should place on the grid, as it
	 * is level dependent. The constructor calls the validateEnemies() method,
	 * and the enemy logic is continued from thereon after.
	 * 
	 * @param grid
	 *            The grid on which enemies are placed.
	 * @param game
	 *            The current game.
	 * @param level
	 *            The level in which enemies are being placed.
	 */

	public Enemy(Grid grid, Game game, Level level) {
		this.grid = grid;
		this.game = game;
		this.level = level;

		this.numberOfEnemiesAfterExitwayIsBlownUp = 8;
		this.enemyCount = 0;
		this.isExitwayBlownUp = false;
		this.tempGrid = new Grid();
		this.loadImage();
		this.count = 0;

		enemiesInitial = new ArrayList<EnemyTracker>();
		enemiesAlive = new ArrayList<EnemyTracker>();

		numberOfBallooms = level.getnumberOfBallooms();
		numberOfOneals = level.getnumberOfOneals();
		numberOfDolls = level.getnumberOfDolls();
		numberOfMinvos = level.getnumberOfMinvos();
		numberOfKondorias = level.getnumberOfKondorias();
		numberOfOvapis = level.getnumberOfOvapis();
		numberOfPasses = level.getnumberOfPasses();
		numberOfPontans = level.getnumberOfPontans();

		validateEnemies();

	}

	/**
	 * For each enemy type, if it is present on the grid, map them in the lists.
	 * <p>
	 * Note: This Method also keeps track of one enemy type higher than the
	 * current highest enemy on the level for the exitway logic
	 * </p>
	 * .
	 */
	private void validateEnemies() {

		if (numberOfBallooms > 0) {
			placeEnemies(Tile.BALLOOM, numberOfBallooms);
			highestLevelEnemy = Tile.ONEAL;
		}

		if (numberOfOneals > 0) {
			placeEnemies(Tile.ONEAL, numberOfOneals);
			highestLevelEnemy = Tile.DOLL;

		}

		if (numberOfDolls > 0) {
			placeEnemies(Tile.DOLL, numberOfDolls);
			highestLevelEnemy = Tile.MINVO;

		}

		if (numberOfMinvos > 0) {
			placeEnemies(Tile.MINVO, numberOfMinvos);
			highestLevelEnemy = Tile.KONDORIA;

		}

		if (numberOfKondorias > 0) {
			placeEnemies(Tile.KONDORIA, numberOfKondorias);
			highestLevelEnemy = Tile.OVAPI;

		}

		if (numberOfOvapis > 0) {
			placeEnemies(Tile.OVAPI, numberOfOvapis);
			highestLevelEnemy = Tile.PASS;

		}

		if (numberOfPasses > 0) {
			placeEnemies(Tile.PASS, numberOfPasses);
			highestLevelEnemy = Tile.PONTAN;

		}

		if (numberOfPontans > 0) {
			placeEnemies(Tile.PONTAN, numberOfPontans);
			highestLevelEnemy = Tile.PONTAN;

		}

	}

	/**
	 * Method which places the required enemies in the enemiesInitial list,
	 * creating the required trackers, and setting them on the grid. This is
	 * called once when enemies are initialized, and again if the Player blows
	 * up the exitway. The enemies are placed pseudo-randomly on the grid.
	 * 
	 * @param enemyType
	 *            The enemy type on the tile.
	 * @param numberToPlace
	 *            The number of enemies to place on the grid.
	 */
	private void placeEnemies(Tile enemyType, int numberToPlace) {

		int numberOfEnemies = 0;
		while (numberOfEnemies < numberToPlace) {
			int randX = randInt(4, 30);
			int randY = randInt(1, 12);

			if (grid.getContents(randX, randY) == Tile.EMPTY) {

				grid.setContents(randX, randY, enemyType);
				tracker = new EnemyTracker(randX, randY, enemyType);
				enemiesInitial.add(tracker);
				numberOfEnemies++;

			}

		}

		enemyCount += numberOfEnemies;

	}

	/**
	 * Basic move function which calls the moveWithChance method with the
	 * correct parameters depending on the enemy type. These parameters include
	 * the chance to change direction at an intersection, the AStar range, and
	 * the player's location.
	 * <p>
	 * Note: Additionally, every time the enemies move, a check is made to see
	 * if the exitway has been blown up by a bomb. If so, exitwayLogic() is
	 * called.
	 * </p>
	 * 
	 * @param targetX
	 *            Player's X location for AStar movement.
	 * @param targetY
	 *            Player's Y location for AStar movement.
	 */
	public void move(int targetX, int targetY) {

		if (isExitwayBlownUp) {
			exitwayLogic();
			isExitwayBlownUp = false;
		}

		copyGridAndVerifyTracker();

		enemiesInitial.clear();

		enemiesInitial.addAll(enemiesAlive);

		enemiesAlive.clear();

		for (int i = 0; i < enemiesInitial.size(); i++) {
			switch (enemiesInitial.get(i).getEnemyType()) {
			case BALLOOM:
				if (count % 3 == 0) {
					moveWithChance(enemiesInitial.get(i), targetX, targetY, 0,
							0);
				}
				continue;
			case ONEAL:
				if (count % 2 == 0) {
					moveWithChance(enemiesInitial.get(i), targetX, targetY, 10,
							2);
				}
				continue;
			case DOLL:
				if (count % 2 == 0) {
					moveWithChance(enemiesInitial.get(i), targetX, targetY, 0,
							0);
				}
				continue;
			case MINVO:
				moveWithChance(enemiesInitial.get(i), targetX, targetY, 10, 2);
				continue;
			case KONDORIA:
				if (count % 4 == 0) {
					wallPassMove(enemiesInitial.get(i), targetX, targetY, 2, 3);
				}
				continue;
			case KONDORIAANDBRICK:
				if (count % 4 == 0) {
					wallPassMove(enemiesInitial.get(i), targetX, targetY, 2, 3);
				}
				continue;
			case OVAPI:
				if (count % 3 == 0) {
					wallPassMove(enemiesInitial.get(i), targetX, targetY, 10, 2);
				}
				continue;
			case OVAPIANDBRICK:
				if (count % 3 == 0) {
					wallPassMove(enemiesInitial.get(i), targetX, targetY, 10, 2);
				}
				continue;
			case PASS:
				moveWithChance(enemiesInitial.get(i), targetX, targetY, 2, 3);
				continue;
			case PONTAN:
				wallPassMove(enemiesInitial.get(i), targetX, targetY, 2, 2);
				continue;
			case PONTANANDBRICK:
				wallPassMove(enemiesInitial.get(i), targetX, targetY, 2, 2);
				continue;
			default:
				break;
			}
		}

		count++;

	}

	/**
	 * First, the grid is copied into the temp grid. Then, the contents of the
	 * grid are verified for the enemies. When an enemy is found, the
	 * verifyTracker method is called.
	 */
	private void copyGridAndVerifyTracker() {

		for (int posX = 0; posX < Bomberman.WIDTH; posX++) {
			for (int posY = 0; posY < Bomberman.HEIGHT; posY++) {
				tempGrid.setContents(posX, posY, grid.getContents(posX, posY));

				switch (grid.getContents(posX, posY)) {
				case BALLOOM:
					verifyTracker(posX, posY, Tile.BALLOOM);
					continue;
				case ONEAL:
					verifyTracker(posX, posY, Tile.ONEAL);
					continue;
				case DOLL:
					verifyTracker(posX, posY, Tile.DOLL);
					continue;
				case MINVO:
					verifyTracker(posX, posY, Tile.MINVO);
					continue;
				case KONDORIA:
					verifyTracker(posX, posY, Tile.KONDORIA);
					continue;
				case KONDORIAANDBRICK:
					verifyTracker(posX, posY, Tile.KONDORIAANDBRICK);
					continue;
				case OVAPI:
					verifyTracker(posX, posY, Tile.OVAPI);
					continue;
				case OVAPIANDBRICK:
					verifyTracker(posX, posY, Tile.OVAPIANDBRICK);
					continue;
				case PASS:
					verifyTracker(posX, posY, Tile.PASS);
					continue;
				case PONTAN:
					verifyTracker(posX, posY, Tile.PONTAN);
					continue;
				case PONTANANDBRICK:
					verifyTracker(posX, posY, Tile.PONTANANDBRICK);
					continue;
				default:
					break;
				}
			}
		}

	}

	/**
	 * This method iterates through the enemiesInitial list to find the tracker
	 * which matches the given enemies location on the grid. If it is found,
	 * this tracker is added to the enemiesAlive list. The enemies in the
	 * enemiesInitial list that have not been matched are considered dead. This
	 * is how we update and keep track of the alive enemies on the grid.
	 *
	 * @param posX
	 *            The enemies position along the X axis.
	 * @param posY
	 *            The enemies position along the Y axis.
	 * @param enemyType
	 *            The enemies type.
	 */
	private void verifyTracker(int posX, int posY, Tile enemyType) {

		for (int i = 0; i < enemiesInitial.size(); i++) {
			if (enemiesInitial.get(i).getEnemyType() == enemyType
					&& enemiesInitial.get(i).getPositionX() == posX
					&& enemiesInitial.get(i).getPositionY() == posY) {

				livingEnemy = new EnemyTracker(posX, posY, enemiesInitial
						.get(i).getEnemyType());
				livingEnemy
						.setDirectionX(enemiesInitial.get(i).getDirectionX());
				livingEnemy
						.setDirectionY(enemiesInitial.get(i).getDirectionY());
				livingEnemy.setMovingInX(enemiesInitial.get(i).isMovingInX());
				livingEnemy.setMovingInY(enemiesInitial.get(i).isMovingInY());
				enemiesAlive.add(livingEnemy);

			}
		}

	}

	/**
	 * 
	 * Updates the alive enemies locations on the grid, based on their
	 * intelligence.
	 * 
	 * @param tracker
	 *            Tracker containing the enemies information.
	 * @param targetX
	 *            Player's tile location on the x axis. Required for AStar.
	 * @param targetY
	 *            Player's tile location on the y axis. Required for AStar.
	 * @param chance
	 *            Enemies chance to change direction.
	 * @param aStarRange
	 *            The range of for which the enemy will begin following the
	 *            Player.
	 */

	private void moveWithChance(EnemyTracker tracker, int targetX, int targetY,
			int chance, int aStarRange) {

		boolean canMoveInX = false;
		boolean canMoveInY = false;
		boolean usingAStar = false;

		int enemyDirectionX = tracker.getDirectionX();
		int enemyDirectionY = tracker.getDirectionY();
		int posX = tracker.getPositionX();
		int posY = tracker.getPositionY();
		int randChance = 0;

		if (aStarRange > 0) {
			finder = new AStarPathFinder(grid, aStarRange);
			path = finder.findPath(tracker.getEnemyType(), posX, posY, targetX,
					targetY);

			if (path != null) {

				usingAStar = true;

				if (posX == path.getX(1)) {
					canMoveInY = true;
					enemyDirectionY = path.getY(1) - posY;
					tracker.setDirectionY(enemyDirectionY);

				} else {
					canMoveInX = true;
					enemyDirectionX = path.getX(1) - posX;
					tracker.setDirectionX(enemyDirectionX);

				}

				grid.setContents(posX, posY, Tile.EMPTY);

				if (canMoveInX) {

					if (grid.getContents(posX + enemyDirectionX, posY) == Tile.PLAYER) {
						grid.setContents(posX + enemyDirectionX, posY,
								tracker.getEnemyType());
						GameState.setState(State.PLAYERDEAD);
						game.setIsPlayerAlive(false);
					}

					grid.setContents(posX + enemyDirectionX, posY,
							tracker.getEnemyType());
					tracker.setPositionX(posX + enemyDirectionX);
					tracker.setMovingInX(true);
					tracker.setMovingInY(false);

				}

				else {
					if (grid.getContents(posX, posY + enemyDirectionY) == Tile.PLAYER) {
						grid.setContents(posX, posY + enemyDirectionY,
								tracker.getEnemyType());
						GameState.setState(State.PLAYERDEAD);
						game.setIsPlayerAlive(false);
					}

					grid.setContents(posX, posY + enemyDirectionY,
							tracker.getEnemyType());
					tracker.setPositionY(posY + enemyDirectionY);
					tracker.setMovingInY(true);
					tracker.setMovingInX(false);

				}

			}
		}

		if (!usingAStar) {

			if (chance != 0) {
				randChance = randInt(1, chance);
			}

			if (grid.getContents(posX + enemyDirectionX, posY) == Tile.EMPTY
					|| grid.getContents(posX + enemyDirectionX, posY) == Tile.PLAYER) {
				canMoveInX = true;

			}

			else if (grid.getContents(posX - enemyDirectionX, posY) == Tile.EMPTY
					|| grid.getContents(posX - enemyDirectionX, posY) == Tile.PLAYER) {
				tracker.setDirectionX(-enemyDirectionX);
				enemyDirectionX = tracker.getDirectionX();
				canMoveInX = true;

			}

			if (grid.getContents(posX, posY + enemyDirectionY) == Tile.EMPTY
					|| grid.getContents(posX, posY + enemyDirectionY) == Tile.PLAYER) {
				canMoveInY = true;
				// move in this direction
			}

			else if (grid.getContents(posX, posY - enemyDirectionY) == Tile.EMPTY
					|| grid.getContents(posX, posY - enemyDirectionY) == Tile.PLAYER) {
				tracker.setDirectionY(-enemyDirectionY);
				enemyDirectionY = tracker.getDirectionY();
				canMoveInY = true;

			}

			if (randChance == 1 && canMoveInX && canMoveInY) {
				if (tracker.isMovingInX()) {
					canMoveInX = false;

				} else if (tracker.isMovingInY()) {
					canMoveInY = false;
				}

			}

			if (canMoveInX) {

				if (grid.getContents(posX + enemyDirectionX, posY) == Tile.PLAYER) {
					grid.setContents(posX + enemyDirectionX, posY,
							tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					game.setIsPlayerAlive(false);
				}

				grid.setContents(posX, posY, Tile.EMPTY);
				grid.setContents(posX + enemyDirectionX, posY,
						tracker.getEnemyType());
				tracker.setPositionX(posX + enemyDirectionX);
				tracker.setMovingInX(true);
				tracker.setMovingInY(false);

			}

			if (canMoveInY && !canMoveInX) {

				if (grid.getContents(posX, posY + enemyDirectionY) == Tile.PLAYER) {
					grid.setContents(posX, posY + enemyDirectionY,
							tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					game.setIsPlayerAlive(false);
				}

				grid.setContents(posX, posY, Tile.EMPTY);
				grid.setContents(posX, posY + enemyDirectionY,
						tracker.getEnemyType());
				tracker.setPositionY(posY + enemyDirectionY);
				tracker.setMovingInY(true);
				tracker.setMovingInX(false);

			}

		}

	}

	/**
	 * Same principle as the moveWithChance method, updated for wall passing
	 * enemies. Updates the alive enemies locations on the grid, based on their
	 * intelligence.
	 * 
	 * @param tracker
	 *            Tracker containing the enemies information.
	 * @param targetX
	 *            Player's tile location on the x axis. Required for AStar.
	 * @param targetY
	 *            Player's tile location on the y axis. Required for AStar.
	 * @param chance
	 *            Enemies chance to change direction.
	 * @param aStarRange
	 *            The range of for which the enemy will begin following the
	 *            Player.
	 */

	private void wallPassMove(EnemyTracker tracker, int targetX, int targetY,
			int chance, int aStarRange) {

		boolean canMoveInX = false;
		boolean canMoveInY = false;
		boolean usingAStar = false;

		int enemyDirectionX = tracker.getDirectionX();
		int enemyDirectionY = tracker.getDirectionY();
		int posX = tracker.getPositionX();
		int posY = tracker.getPositionY();
		int randChance = 0;

		if (aStarRange > 0) {
			finder = new AStarPathFinder(grid, aStarRange);
			path = finder.findPath(tracker.getEnemyType(), posX, posY, targetX,
					targetY);

			if (path != null) {

				usingAStar = true;

				if (posX == path.getX(1)) {
					canMoveInY = true;
					enemyDirectionY = path.getY(1) - posY;
					tracker.setDirectionY(enemyDirectionY);
				} else {
					canMoveInX = true;
					enemyDirectionX = path.getX(1) - posX;
					tracker.setDirectionX(enemyDirectionX);

				}

				if (grid.getContents(posX, posY) == Tile.KONDORIAANDBRICK
						|| grid.getContents(posX, posY) == Tile.OVAPIANDBRICK
						|| grid.getContents(posX, posY) == Tile.PONTANANDBRICK) {
					grid.setContents(posX, posY, Tile.BRICK);

				}

				else {
					grid.setContents(posX, posY, Tile.EMPTY);

				}

				if (canMoveInX) {

					if (grid.getContents(posX + enemyDirectionX, posY) == Tile.PLAYER) {

						grid.setContents(posX + enemyDirectionX, posY,
								tracker.getEnemyType());
						GameState.setState(State.PLAYERDEAD);
						game.setIsPlayerAlive(false);
					}

					else if (grid.getContents(posX + enemyDirectionX, posY) == Tile.BRICK) {
						switch (tracker.getEnemyType()) {
						case KONDORIA:
							grid.setContents(posX + enemyDirectionX, posY,
									Tile.KONDORIAANDBRICK);
							tracker.setEnemyType(Tile.KONDORIAANDBRICK);
							break;
						case OVAPI:
							grid.setContents(posX + enemyDirectionX, posY,
									Tile.OVAPIANDBRICK);
							tracker.setEnemyType(Tile.OVAPIANDBRICK);

							break;
						case PONTAN:
							grid.setContents(posX + enemyDirectionX, posY,
									Tile.PONTANANDBRICK);
							tracker.setEnemyType(Tile.PONTANANDBRICK);
							break;

						case KONDORIAANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY,
									Tile.KONDORIAANDBRICK);
							tracker.setEnemyType(Tile.KONDORIAANDBRICK);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY,
									Tile.OVAPIANDBRICK);
							tracker.setEnemyType(Tile.OVAPIANDBRICK);

							break;
						case PONTANANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY,
									Tile.PONTANANDBRICK);
							tracker.setEnemyType(Tile.PONTANANDBRICK);
							break;

						default:
							break;
						}

					}

					else {
						switch (tracker.getEnemyType()) {
						case KONDORIAANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY,
									Tile.KONDORIA);
							tracker.setEnemyType(Tile.KONDORIA);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY,
									Tile.OVAPI);
							tracker.setEnemyType(Tile.OVAPI);

							break;
						case PONTANANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY,
									Tile.PONTAN);
							tracker.setEnemyType(Tile.PONTAN);

							break;
						default:
							break;
						}
						grid.setContents(posX + enemyDirectionX, posY,
								tracker.getEnemyType());
					}

					tracker.setPositionX(posX + enemyDirectionX);
					tracker.setMovingInX(true);
					tracker.setMovingInY(false);

				}

				else {
					if (grid.getContents(posX, posY + enemyDirectionY) == Tile.PLAYER) {
						grid.setContents(posX, posY + enemyDirectionY,
								tracker.getEnemyType());
						GameState.setState(State.PLAYERDEAD);
						game.setIsPlayerAlive(false);
					}

					else if (grid.getContents(posX, posY + enemyDirectionY) == Tile.BRICK) {
						switch (tracker.getEnemyType()) {
						case KONDORIA:
							grid.setContents(posX, posY + enemyDirectionY,
									Tile.KONDORIAANDBRICK);
							tracker.setEnemyType(Tile.KONDORIAANDBRICK);
							break;
						case OVAPI:
							grid.setContents(posX, posY + enemyDirectionY,
									Tile.OVAPIANDBRICK);
							tracker.setEnemyType(Tile.OVAPIANDBRICK);
							break;
						case PONTAN:
							grid.setContents(posX, posY + enemyDirectionY,
									Tile.PONTANANDBRICK);
							tracker.setEnemyType(Tile.PONTANANDBRICK);
							break;
						case KONDORIAANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY,
									Tile.KONDORIAANDBRICK);
							tracker.setEnemyType(Tile.KONDORIAANDBRICK);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY,
									Tile.OVAPIANDBRICK);
							tracker.setEnemyType(Tile.OVAPIANDBRICK);
							break;
						case PONTANANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY,
									Tile.PONTANANDBRICK);
							tracker.setEnemyType(Tile.PONTANANDBRICK);
							break;
						default:
							break;
						}

					}

					else {

						switch (tracker.getEnemyType()) {
						case KONDORIAANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY,
									Tile.KONDORIA);
							tracker.setEnemyType(Tile.KONDORIA);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY,
									Tile.OVAPI);
							tracker.setEnemyType(Tile.OVAPI);
							break;
						case PONTANANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY,
									Tile.PONTAN);
							tracker.setEnemyType(Tile.PONTAN);
							break;
						default:
							break;
						}

						grid.setContents(posX, posY + enemyDirectionY,
								tracker.getEnemyType());

					}

					tracker.setPositionY(posY + enemyDirectionY);
					tracker.setMovingInY(true);
					tracker.setMovingInX(false);

				}

			}
		}
		if (!usingAStar) {

			if (chance != 0) {
				randChance = randInt(1, chance);
			}

			if (grid.getContents(posX + enemyDirectionX, posY) == Tile.EMPTY
					|| grid.getContents(posX + enemyDirectionX, posY) == Tile.PLAYER
					|| grid.getContents(posX + enemyDirectionX, posY) == Tile.BRICK) {
				canMoveInX = true;
			} else if (grid.getContents(posX - enemyDirectionX, posY) == Tile.EMPTY
					|| grid.getContents(posX - enemyDirectionX, posY) == Tile.PLAYER
					|| grid.getContents(posX - enemyDirectionX, posY) == Tile.BRICK) {
				tracker.setDirectionX(-enemyDirectionX);
				enemyDirectionX = tracker.getDirectionX();
				canMoveInX = true;
			}

			if (grid.getContents(posX, posY + enemyDirectionY) == Tile.EMPTY
					|| grid.getContents(posX, posY + enemyDirectionY) == Tile.PLAYER
					|| grid.getContents(posX, posY + enemyDirectionY) == Tile.BRICK) {
				canMoveInY = true;
			} else if (grid.getContents(posX, posY - enemyDirectionY) == Tile.EMPTY
					|| grid.getContents(posX, posY - enemyDirectionY) == Tile.PLAYER
					|| grid.getContents(posX, posY - enemyDirectionY) == Tile.BRICK) {
				tracker.setDirectionY(-enemyDirectionY);
				enemyDirectionY = tracker.getDirectionY();
				canMoveInY = true;
			}

			if (randChance == 1 && canMoveInX && canMoveInY) {
				if (tracker.isMovingInX()) {
					canMoveInX = false;

				} else if (tracker.isMovingInY()) {
					canMoveInY = false;
				}

			}

			if (canMoveInX) {

				if (grid.getContents(posX + enemyDirectionX, posY) == Tile.PLAYER) {
					grid.setContents(posX + enemyDirectionX, posY,
							tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					game.setIsPlayerAlive(false);
				}

				if (grid.getContents(posX, posY) == Tile.KONDORIAANDBRICK
						|| grid.getContents(posX, posY) == Tile.OVAPIANDBRICK
						|| grid.getContents(posX, posY) == Tile.PONTANANDBRICK) {
					grid.setContents(posX, posY, Tile.BRICK);
				}

				else {
					grid.setContents(posX, posY, Tile.EMPTY);

				}

				if (grid.getContents(posX + enemyDirectionX, posY) == Tile.BRICK) {
					switch (tracker.getEnemyType()) {
					case KONDORIA:
						grid.setContents(posX + enemyDirectionX, posY,
								Tile.KONDORIAANDBRICK);
						tracker.setEnemyType(Tile.KONDORIAANDBRICK);
						break;
					case OVAPI:
						grid.setContents(posX + enemyDirectionX, posY,
								Tile.OVAPIANDBRICK);
						tracker.setEnemyType(Tile.OVAPIANDBRICK);

						break;
					case PONTAN:
						grid.setContents(posX + enemyDirectionX, posY,
								Tile.PONTANANDBRICK);
						tracker.setEnemyType(Tile.PONTANANDBRICK);
						break;
					case KONDORIAANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY,
								Tile.KONDORIAANDBRICK);
						tracker.setEnemyType(Tile.KONDORIAANDBRICK);
						break;
					case OVAPIANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY,
								Tile.OVAPIANDBRICK);
						tracker.setEnemyType(Tile.OVAPIANDBRICK);

						break;
					case PONTANANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY,
								Tile.PONTANANDBRICK);
						tracker.setEnemyType(Tile.PONTANANDBRICK);
						break;

					default:
						break;
					}

				}

				else {
					switch (tracker.getEnemyType()) {
					case KONDORIAANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY,
								Tile.KONDORIA);
						tracker.setEnemyType(Tile.KONDORIA);
						break;
					case OVAPIANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY,
								Tile.OVAPI);
						tracker.setEnemyType(Tile.OVAPI);

						break;
					case PONTANANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY,
								Tile.PONTAN);
						tracker.setEnemyType(Tile.PONTAN);

						break;
					default:
						break;
					}
					grid.setContents(posX + enemyDirectionX, posY,
							tracker.getEnemyType());
				}

				tracker.setPositionX(posX + enemyDirectionX);
				tracker.setMovingInX(true);
				tracker.setMovingInY(false);
			}

			if (canMoveInY && !canMoveInX) {

				if (grid.getContents(posX, posY + enemyDirectionY) == Tile.PLAYER) {
					grid.setContents(posX, posY + enemyDirectionY,
							tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					game.setIsPlayerAlive(false);
				}

				if (grid.getContents(posX, posY) == Tile.KONDORIAANDBRICK
						|| grid.getContents(posX, posY) == Tile.OVAPIANDBRICK
						|| grid.getContents(posX, posY) == Tile.PONTANANDBRICK) {
					grid.setContents(posX, posY, Tile.BRICK);
				}

				else {
					grid.setContents(posX, posY, Tile.EMPTY);

				}

				if (grid.getContents(posX, posY + enemyDirectionY) == Tile.PLAYER) {
					grid.setContents(posX, posY + enemyDirectionY,
							tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					game.setIsPlayerAlive(false);
				}

				else if (grid.getContents(posX, posY + enemyDirectionY) == Tile.BRICK) {
					switch (tracker.getEnemyType()) {
					case KONDORIA:
						grid.setContents(posX, posY + enemyDirectionY,
								Tile.KONDORIAANDBRICK);
						tracker.setEnemyType(Tile.KONDORIAANDBRICK);
						break;
					case OVAPI:
						grid.setContents(posX, posY + enemyDirectionY,
								Tile.OVAPIANDBRICK);
						tracker.setEnemyType(Tile.OVAPIANDBRICK);
						break;
					case PONTAN:
						grid.setContents(posX, posY + enemyDirectionY,
								Tile.PONTANANDBRICK);
						tracker.setEnemyType(Tile.PONTANANDBRICK);
						break;
					case KONDORIAANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY,
								Tile.KONDORIAANDBRICK);
						tracker.setEnemyType(Tile.KONDORIAANDBRICK);
						break;
					case OVAPIANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY,
								Tile.OVAPIANDBRICK);
						tracker.setEnemyType(Tile.OVAPIANDBRICK);
						break;
					case PONTANANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY,
								Tile.PONTANANDBRICK);
						tracker.setEnemyType(Tile.PONTANANDBRICK);
						break;
					default:
						break;
					}

				}

				else {
					switch (tracker.getEnemyType()) {
					case KONDORIAANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY,
								Tile.KONDORIA);
						tracker.setEnemyType(Tile.KONDORIA);
						break;
					case OVAPIANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY,
								Tile.OVAPI);
						tracker.setEnemyType(Tile.OVAPI);
						break;
					case PONTANANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY,
								Tile.PONTAN);
						tracker.setEnemyType(Tile.PONTAN);
						break;
					default:
						break;
					}
					grid.setContents(posX, posY + enemyDirectionY,
							tracker.getEnemyType());
				}

				tracker.setPositionY(posY + enemyDirectionY);
				tracker.setMovingInY(true);
				tracker.setMovingInX(false);

			}

		}

	}

	/**
	 * A check is made every time the move method is called to see if the
	 * exitway has been blown up by a bomb. If so, this method is called. The
	 * enemies on the map are cleared, and are replaced by eight enemies of type
	 * one higher than the ones present at that level.
	 */
	private void exitwayLogic() {

		clearEnemies();
		placeEnemies(highestLevelEnemy, numberOfEnemiesAfterExitwayIsBlownUp);
		setEnemyCount(ENEMY_COUNT_EXITWAY);
		Bomb.setNumberOfEnemiesKilled(0);
	}

	/**
	 * Iterates through the grid, removing the enemies.
	 */
	private void clearEnemies() {

		for (int posX = 0; posX < Bomberman.WIDTH; posX++) {
			for (int posY = 0; posY < Bomberman.HEIGHT; posY++) {
				tempGrid.setContents(posX, posY, grid.getContents(posX, posY));
				switch (grid.getContents(posX, posY)) {
				case BALLOOM:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				case ONEAL:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				case DOLL:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				case MINVO:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				case KONDORIA:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				case KONDORIAANDBRICK:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				case OVAPI:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				case OVAPIANDBRICK:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				case PASS:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				case PONTAN:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				case PONTANANDBRICK:
					grid.setContents(posX, posY, Tile.EMPTY);
					continue;
				default:
					break;
				}
			}

		}
	}

	/**
	 * Given two integers, min and max, this method will return a pseudo-random
	 * integer value contained between these two integers.
	 * 
	 * @param min
	 *            Minimum value which may be returned.
	 * @param max
	 *            Maximum value which may be returned.
	 * @return an <code>int</code> corresponding to the integer between min and
	 *         max inclusively.
	 */

	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;

	}

	/**
	 * Method used to load the different images for all the enemy types.
	 */

	private void loadImage() {
		first = new ImageIcon(getClass().getResource(Balloom));
		second = new ImageIcon(getClass().getResource(Oneal));
		third = new ImageIcon(getClass().getResource(Doll));
		fourth = new ImageIcon(getClass().getResource(Minvo));
		fifth = new ImageIcon(getClass().getResource(Kondoria));
		sixth = new ImageIcon(getClass().getResource(Ovapi));
		seventh = new ImageIcon(getClass().getResource(Pass));
		eigth = new ImageIcon(getClass().getResource(Pontan));
		nine = new ImageIcon(getClass().getResource(KondoriaAndBrick));
		ten = new ImageIcon(getClass().getResource(OvapiAndBrick));
		eleven = new ImageIcon(getClass().getResource(PontanAndBrick));
	}

	/**
	 * Gets the image for the enemy type Balloom.
	 * 
	 * @return the <code>Image</code> shown when Balloom is in a tile by itself.
	 */
	public Image getImageBalloom() {
		return first.getImage();
	}

	/**
	 * Gets the image for the enemy type Oneal.
	 * 
	 * @return the <code>Image</code> shown when Oneal is in a tile by itself.
	 */

	public Image getImageOneal() {
		return second.getImage();
	}

	/**
	 * Gets the image for the enemy type Doll.
	 * 
	 * @return the <code>Image</code> shown when Doll is in a tile by itself.
	 */

	public Image getImageDoll() {
		return third.getImage();
	}

	/**
	 * Gets the image for the enemy type Minvo.
	 * 
	 * @return the <code>Image</code> shown when Minvo is in a tile by itself.
	 */

	public Image getImageMinvo() {
		return fourth.getImage();
	}

	/**
	 * Gets the image for the enemy type Kondoria.
	 * 
	 * @return the <code>Image</code> shown when Kondoria is in a tile by
	 *         itself.
	 */

	public Image getImageKondoria() {
		return fifth.getImage();
	}

	/**
	 * Gets the image for the enemy type Kondoria.
	 * 
	 * @return the <code>Image</code> shown when Kondoria is in a tile with a
	 *         brick.
	 */

	public Image getImageKondoriaAndBrick() {
		return nine.getImage();
	}

	/**
	 * Gets the image for the enemy type Ovapi.
	 * 
	 * @return the <code>Image</code> shown when Ovapi is in a tile by itself.
	 */

	public Image getImageOvapi() {
		return sixth.getImage();
	}

	/**
	 * Gets the image for the enemy type Ovapi.
	 * 
	 * @return the <code>Image</code> shown when Ovapi is in a tile with a
	 *         brick.
	 */

	public Image getImageOvapiAndBrick() {
		return ten.getImage();
	}

	/**
	 * Gets the image for the enemy type Pass.
	 * 
	 * @return the <code>Image</code> shown when Pass is in a tile by itself.
	 */

	public Image getImagePass() {
		return seventh.getImage();
	}

	/**
	 * Gets the image for the enemy type Pontan.
	 * 
	 * @return the <code>Image</code> shown when Pontan is in a tile by itself.
	 */

	public Image getImagePontan() {
		return eigth.getImage();
	}

	/**
	 * Gets the image for the enemy type Pontan.
	 * 
	 * @return the <code>Image</code> shown when Pontan is in a tile with a
	 *         brick.
	 */

	public Image getImagePontanAndBrick() {
		return eleven.getImage();
	}

	/**
	 * Gets the enemy count which corresponds to the number of enemies on the
	 * grid.
	 * 
	 * @return the <code>int</code> representing the number of enemies left on
	 *         the grid.
	 */

	public int getEnemyCount() {
		return enemyCount;
	}

	/**
	 * Sets the number of enemies on the grid.
	 * 
	 * @param newEnemyCount
	 *            an int corresponding to the number of enemies on the grid.
	 */
	public void setEnemyCount(int newEnemyCount) {
		this.enemyCount = newEnemyCount;
	}

	/**
	 * Set if the exitway has been blown up by a bomb. Move checks the exitway
	 * condition on each call. If this is set to true, the exitway logic is ran.
	 * 
	 * @param b
	 *            true if the exitway has been blown up by a bomb.
	 */
	public void setIsExitwayBlownUp(boolean b) {
		this.isExitwayBlownUp = b;

	}

}
