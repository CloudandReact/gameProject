package gameplay;

import java.awt.Image;

import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bomb implements Runnable {

	private int range;
	private int bombs;
	private int currentRange;
	private int counter;

	private int posX;
	private int posY;

	private String imageNameBomb = "bomb.png";
	private String imageNameBombAndBomberman = "bomb&Bomberman.jpg";
	private String imageNameExplosion = "explosion.png";
	private String imageNameBombAndExitway = "bombandexitway.png";

	private static int numberOfEnemiesKilled;

	List<Integer> scoreForEachEnemyKilledWithThisBomb;

	private Image imageBomb;
	private Image imageBombAndBomberman;
	private Image imageExplosion;
	private Image imageBombAndExitway;

	private static final int BALLOOM_SCORE = 100;
	private static final int ONEAL_SCORE = 200;
	private static final int DOLL_SCORE = 400;
	private static final int MINVO_SCORE = 800;
	private static final int KONDORIA_SCORE = 1000;
	private static final int OVAPI_SCORE = 2000;
	private static final int PASS_SCORE = 4000;
	private static final int PONTAN_SCORE = 8000;

	private int pointsScoredWithThisBomb;
	private int totalGameScore;

	private Grid grid;

	private Enemy enemy;
	private Player player;

	private int bombNumber;

	private static final int BOMB_TIMER_IN_MILLISECONDS = 2000;

	private boolean denotePressed;

	/*
	 * RANGE IS DEFAULTED TO 1 IN CONSTRUCTORS. TO SET RANGE USE SETTER ON BOMB
	 * OBJECT. USE THE SAME BOMB OBJECT SUPPLIED TO THE THREAD.
	 */

	// Constructor, Just for images

	/**
	 * Initializes all the values pertaining to bomb.
	 * @param grid The map on which bombs appear.
	 */
	public Bomb(Grid grid) {

		setTotalGameScore(0);
		scoreForEachEnemyKilledWithThisBomb = new ArrayList<Integer>();
		loadImage();
		this.range = 1;
		this.setBombs(1);
		this.grid = grid;
		this.setDenotePressed(false);
		Bomb.numberOfEnemiesKilled = 0;

	}

	public Bomb(Grid grid, Enemy enemy, Player player) {
		
		this.player = player;
		this.enemy = enemy;
		setTotalGameScore(0);
		scoreForEachEnemyKilledWithThisBomb = new ArrayList<Integer>();
		loadImage();
		this.range = 1;
		this.setBombs(1);
		this.grid = grid;
		this.setDenotePressed(false);
	}

	/**
	 * Loads the images related to bombs.
	 */

	private void loadImage() {
		ImageIcon firstImageIcon = new ImageIcon(getClass().getResource(
				imageNameBomb));
		imageBomb = firstImageIcon.getImage();

		ImageIcon secondImageIcon = new ImageIcon(getClass().getResource(
				imageNameBombAndBomberman));
		imageBombAndBomberman = secondImageIcon.getImage();

		ImageIcon thirdImageIcon = new ImageIcon(getClass().getResource(
				imageNameExplosion));
		imageExplosion = thirdImageIcon.getImage();

		ImageIcon fourthImageIcon = new ImageIcon(getClass().getResource(
				imageNameBombAndExitway));
		imageBombAndExitway = fourthImageIcon.getImage();
	}

	/**
	 * Gets the image for the bomb.
	 * 
	 * @return Image shown when bomb is in a tile by itself.
	 */
	public Image getImageBomb() {
		return imageBomb;
	}

	/**
	 * Gets the image shown when Bomberman and the bomb are in the same tile.
	 * 
	 * @return The image shown when Bomberman and the bomb are in the same tile.
	 */
	public Image getImageBombPlayer() {
		return imageBombAndBomberman;
	}

	/**
	 * Gets the image shown when a bomb EXPLOSIONs.
	 * 
	 * @return The image shown when a bomb EXPLOSIONs.
	 */

	public Image getImageExplosion() {
		return imageExplosion;
	}

	public static int getNumberOfEnemiesKilled() {
		return numberOfEnemiesKilled;
	}

	public void explode() {

		pointsScoredWithThisBomb = 0;
		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(posX + currentRange, posY) != Tile.CONCRETE) {

				if (grid.getContents(posX + currentRange, posY) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(posX + currentRange, posY, Tile.POWERUPS);
				} else if (grid.getContents(posX + currentRange, posY) == Tile.BRICKANDEXITWAY) {
					grid.setContents(posX + currentRange, posY, Tile.EXITWAY);
				} else if (grid.getContents(posX + currentRange, posY) == Tile.EXITWAY) {
					grid.setContents(posX + currentRange, posY, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);
				} else if (grid.getContents(posX + currentRange, posY) == Tile.BALLOOM
						|| grid.getContents(posX + currentRange, posY) == Tile.ONEAL
						|| grid.getContents(posX + currentRange, posY) == Tile.DOLL
						|| grid.getContents(posX + currentRange, posY) == Tile.MINVO
						|| grid.getContents(posX + currentRange, posY) == Tile.KONDORIA
						|| grid.getContents(posX + currentRange, posY) == Tile.OVAPI
						|| grid.getContents(posX + currentRange, posY) == Tile.PASS
						|| grid.getContents(posX + currentRange, posY) == Tile.PONTAN) {

					if (grid.getContents(posX + currentRange, posY) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(BALLOOM_SCORE);
						grid.setContents(posX + currentRange, posY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(ONEAL_SCORE);
						grid.setContents(posX + currentRange, posY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(DOLL_SCORE);
						grid.setContents(posX + currentRange, posY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(MINVO_SCORE);
						grid.setContents(posX + currentRange, posY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(KONDORIA_SCORE);
						grid.setContents(posX + currentRange, posY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(OVAPI_SCORE);
						grid.setContents(posX + currentRange, posY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PASS_SCORE);
						grid.setContents(posX + currentRange, posY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PONTAN_SCORE);
						grid.setContents(posX + currentRange, posY,
								Tile.EXPLOSION);
					}

				}
				// FLAME PASS POWERUP
				else if (grid.getContents(posX + currentRange, posY) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(posX + currentRange, posY, Tile.PLAYER);
					} else {
						grid.setContents(posX + currentRange, posY,
								Tile.EXPLOSION);
					}
				} else {

					grid.setContents(posX + currentRange, posY, Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(posX + currentRange, posY) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to EXPLOSION across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(posX - currentRange, posY) != Tile.CONCRETE) {

				if (grid.getContents(posX - currentRange, posY) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(posX - currentRange, posY, Tile.POWERUPS);

				} else if (grid.getContents(posX - currentRange, posY) == Tile.BRICKANDEXITWAY) {
					grid.setContents(posX - currentRange, posY, Tile.EXITWAY);

				} else if (grid.getContents(posX - currentRange, posY) == Tile.EXITWAY) {
					grid.setContents(posX - currentRange, posY, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} else if (grid.getContents(posX - currentRange, posY) == Tile.BALLOOM
						|| grid.getContents(posX - currentRange, posY) == Tile.ONEAL
						|| grid.getContents(posX - currentRange, posY) == Tile.DOLL
						|| grid.getContents(posX - currentRange, posY) == Tile.MINVO
						|| grid.getContents(posX - currentRange, posY) == Tile.KONDORIA
						|| grid.getContents(posX - currentRange, posY) == Tile.OVAPI
						|| grid.getContents(posX - currentRange, posY) == Tile.PASS
						|| grid.getContents(posX - currentRange, posY) == Tile.PONTAN
						|| grid.getContents(posX - currentRange, posY) == Tile.KONDORIAANDBRICK
						|| grid.getContents(posX - currentRange, posY) == Tile.OVAPIANDBRICK
						|| grid.getContents(posX - currentRange, posY) == Tile.PONTANANDBRICK) {

					if (grid.getContents(posX - currentRange, posY) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(BALLOOM_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(posX - currentRange, posY) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(ONEAL_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(posX - currentRange, posY) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(DOLL_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(posX - currentRange, posY) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(MINVO_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(posX - currentRange, posY) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(KONDORIA_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(posX - currentRange, posY) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(OVAPI_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(posX - currentRange, posY) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PASS_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(posX - currentRange, posY) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PONTAN_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(posX - currentRange, posY) == Tile.KONDORIAANDBRICK) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(KONDORIA_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(posX - currentRange, posY) == Tile.OVAPIANDBRICK) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(OVAPI_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(posX - currentRange, posY) == Tile.PONTANANDBRICK) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PONTAN_SCORE);
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
					
				} else if (grid.getContents(posX - currentRange, posY) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(posX - currentRange, posY, Tile.PLAYER);
					} else {
						grid.setContents(posX - currentRange, posY,
								Tile.EXPLOSION);
					}
				} else {
					grid.setContents(posX - currentRange, posY, Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(posX - currentRange, posY) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to EXPLOSION across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {
			if (grid.getContents(posX, posY + currentRange) != Tile.CONCRETE) {

				if (grid.getContents(posX, posY + currentRange) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(posX, posY + currentRange, Tile.POWERUPS);

				} else if (grid.getContents(posX, posY + currentRange) == Tile.BRICKANDEXITWAY) {
					grid.setContents(posX, posY + currentRange, Tile.EXITWAY);
				} else if (grid.getContents(posX, posY + currentRange) == Tile.EXITWAY) {
					grid.setContents(posX, posY + currentRange, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} else if (grid.getContents(posX, posY + currentRange) == Tile.BALLOOM
						|| grid.getContents(posX, posY + currentRange) == Tile.ONEAL
						|| grid.getContents(posX, posY + currentRange) == Tile.DOLL
						|| grid.getContents(posX, posY + currentRange) == Tile.MINVO
						|| grid.getContents(posX, posY + currentRange) == Tile.KONDORIA
						|| grid.getContents(posX, posY + currentRange) == Tile.OVAPI
						|| grid.getContents(posX, posY + currentRange) == Tile.PASS
						|| grid.getContents(posX, posY + currentRange) == Tile.PONTAN) {

					if (grid.getContents(posX, posY + currentRange) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(BALLOOM_SCORE);
						grid.setContents(posX, posY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(ONEAL_SCORE);
						grid.setContents(posX, posY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(DOLL_SCORE);
						grid.setContents(posX, posY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(MINVO_SCORE);
						grid.setContents(posX, posY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(KONDORIA_SCORE);
						grid.setContents(posX, posY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(OVAPI_SCORE);
						grid.setContents(posX, posY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PASS_SCORE);
						grid.setContents(posX, posY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PONTAN_SCORE);
						grid.setContents(posX, posY + currentRange,
								Tile.EXPLOSION);
					}
				} else if (grid.getContents(posX, posY + currentRange) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(posX, posY + currentRange, Tile.PLAYER);
					} else {
						grid.setContents(posX, posY + currentRange,
								Tile.EXPLOSION);
					}
				} else {
					grid.setContents(posX, posY + currentRange, Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(posX, posY + currentRange) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to EXPLOSION across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(posX, posY - currentRange) != Tile.CONCRETE) {

				if (grid.getContents(posX, posY - currentRange) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(posX, posY - currentRange, Tile.POWERUPS);

				} else if (grid.getContents(posX, posY - currentRange) == Tile.BRICKANDEXITWAY) {
					grid.setContents(posX, posY - currentRange, Tile.EXITWAY);
				} else if (grid.getContents(posX, posY - currentRange) == Tile.EXITWAY) {
					grid.setContents(posX, posY - currentRange, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				}

				else if (grid.getContents(posX, posY - currentRange) == Tile.BALLOOM
						|| grid.getContents(posX, posY - currentRange) == Tile.ONEAL
						|| grid.getContents(posX, posY - currentRange) == Tile.DOLL
						|| grid.getContents(posX, posY - currentRange) == Tile.MINVO
						|| grid.getContents(posX, posY - currentRange) == Tile.KONDORIA
						|| grid.getContents(posX, posY - currentRange) == Tile.OVAPI
						|| grid.getContents(posX, posY - currentRange) == Tile.PASS
						|| grid.getContents(posX, posY - currentRange) == Tile.PONTAN) {

					if (grid.getContents(posX, posY - currentRange) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(BALLOOM_SCORE);
						grid.setContents(posX, posY - currentRange,
								Tile.EXPLOSION);

					}
					if (grid.getContents(posX, posY - currentRange) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(ONEAL_SCORE);
						grid.setContents(posX, posY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY - currentRange) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(DOLL_SCORE);
						grid.setContents(posX, posY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY - currentRange) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(MINVO_SCORE);
						grid.setContents(posX, posY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY - currentRange) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(KONDORIA_SCORE);
						grid.setContents(posX, posY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY - currentRange) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(OVAPI_SCORE);
						grid.setContents(posX, posY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY - currentRange) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PASS_SCORE);
						grid.setContents(posX, posY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(posX, posY - currentRange) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PONTAN_SCORE);
						grid.setContents(posX, posY - currentRange,
								Tile.EXPLOSION);
					}
				}

				else if (grid.getContents(posX, posY - currentRange) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(posX, posY - currentRange, Tile.PLAYER);
					} else {
						grid.setContents(posX, posY - currentRange,
								Tile.EXPLOSION);
					}
				}

				else {
					grid.setContents(posX, posY - currentRange, Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(posX, posY - currentRange) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to EXPLOSION across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		if (scoreForEachEnemyKilledWithThisBomb.size() == 1) {
			pointsScoredWithThisBomb = scoreForEachEnemyKilledWithThisBomb
					.get(0);
		} else if (scoreForEachEnemyKilledWithThisBomb.size() == 2) {

			int temp = scoreForEachEnemyKilledWithThisBomb.get(0);

			if (temp < scoreForEachEnemyKilledWithThisBomb.get(1)) {
				pointsScoredWithThisBomb = temp * 2
						+ scoreForEachEnemyKilledWithThisBomb.get(1);
			} else {
				pointsScoredWithThisBomb = temp + 2
						* scoreForEachEnemyKilledWithThisBomb.get(1);
			}

		} else {
			for (int i = 0; i < scoreForEachEnemyKilledWithThisBomb.size(); i++) {
				int temp = (int) ((int) scoreForEachEnemyKilledWithThisBomb
						.get(i) * Math.pow(2, i));
				pointsScoredWithThisBomb += temp;
			}

		}

		setTotalGameScore(getTotalGameScore() + pointsScoredWithThisBomb);

		PlayerInfo.playerScore += getPointsScoredWithThisBomb();
		scoreForEachEnemyKilledWithThisBomb.clear();

	}

	@Override
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
	 * Sets the range of the bomb.
	 * @param range The range of the bomb.
	 */

	public void setRange(int range) {
		this.range = range;
	}
	
	/**
	 * Sets the bomb's position in x and y.
	 * @param posX Bomb's x position.
	 * @param posY Bomb's y position.
	 */

	public void setPosition(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;

	}
	
	/**
	 * Gets the points scored with this one particular bomb.
	 * @return
	 */

	public int getPointsScoredWithThisBomb() {
		return pointsScoredWithThisBomb;
	}
	
	/**
	 * Gets points scored with one bomb.
	 * @param pointsScoredWithThisBomb Points scored with this one bomb.
	 */

	public void setPointsScoredWithThisBomb(int pointsScoredWithThisBomb) {
		this.pointsScoredWithThisBomb = pointsScoredWithThisBomb;
	}
	
	/**
	 * Gets the total score earned by the player in the current game.
	 * @return Score for the current game.
	 */

	public int getTotalGameScore() {
		return totalGameScore;
	}

	/**
	 * Sets the total score earned by the player in the current game.
	 * @param totalGameScore Score for the current game.
	 */
	public void setTotalGameScore(int totalGameScore) {
		this.totalGameScore = totalGameScore;
	}

	/**
	 * Gets the number of bombs available to the player.
	 * @return Number of bombs.
	 */
	public int getBombs() {
		return bombs;
	}
	
	/**
	 * Sets the number of bombs available to the player.
	 * @param bombs Number of bombs available to the player.
	 */

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	public boolean isDenotePressed() {
		return denotePressed;
	}

	public void setDenotePressed(boolean denotePressed) {
		this.denotePressed = denotePressed;
	}

	public void setBombNumber(int bombNumber) {
		this.bombNumber = bombNumber;

	}

	public static void setNumberOfEnemiesKilled(int i) {
		Bomb.numberOfEnemiesKilled = i;

	}

}