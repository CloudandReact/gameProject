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

	private String bomb = "bomb.png";
	private String bombPlayer = "bomb&Bomberman.jpg";
	private String bombExploding = "explosion.png";
	private String bombAndExitways = "bombandexitway.png";
	private static int numberOfEnemiesKilled;
	private PowerUps powerup;
	
	List<Integer> scores; 

	private Image image;
	private Image image2;
	private Image explode;
	private Image bombandexitway;
	
	private static final int BALLOOM_SCORE = 100;
	private static final int ONEAL_SCORE = 200;
	private static final int DOLL_SCORE = 400;
	private static final int MINVO_SCORE = 800;
	private static final int KONDORIA_SCORE = 1000;
	private static final int OVAPI_SCORE = 2000;
	private static final int PASS_SCORE = 4000;
	private static final int PONTAN_SCORE = 8000;
	
	private int currentGameScore;
	private int totalGameScore;

	private Grid grid;
	
	private long startTime;
	private long currentTime;
	
	private Enemy enemy;
	private Player player;
	private int bombNumber;
	
	private boolean denotePressed;

	/*
	 * RANGE IS DEFAULTED TO 1 IN CONSTRUCTORS. TO SET RANGE USE SETTER ON BOMB
	 * OBJECT. USE THE SAME BOMB OBJECT SUPPLIED TO THE THREAD.
	 */
	
	// Constructor, Just for images
	public Bomb(Grid grid) {
	
		setTotalGameScore(0);
		scores = new ArrayList<Integer>();
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
		scores = new ArrayList<Integer>();
		loadImage();
		this.range = 1;
		this.setBombs(1);
		this.grid = grid;
		this.setDenotePressed(false);
	}
	
	
	

	public void setRange(int range) {
		this.range = range;
	}

	public void setPosition(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;

	}

	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(bomb));
		image = ii.getImage();

		ImageIcon bombP = new ImageIcon(getClass().getResource(bombPlayer));
		image2 = bombP.getImage();

		ImageIcon bombExplode = new ImageIcon(getClass().getResource(
				bombExploding));
		explode = bombExplode.getImage();

		ImageIcon bombAndExitway = new ImageIcon(getClass().getResource(
				bombAndExitways));
		bombandexitway = bombAndExitway.getImage();
	}

	public Image getImageBomb() {
		return image;
	}

	public Image getImageBombPlayer() {
		return image2;
	}

	public Image getImageBombExplode() {
		return explode;
	}

	public Image getImageBombAndExitway() {
		return bombandexitway;
	}

	public static int getNumberOfEnemiesKilled() {
		return numberOfEnemiesKilled;
	}

	public void explode() {
		
		currentGameScore = 0;
		currentRange = 0;

		while (currentRange <= range) {
			
			if (grid.getContents(posX + currentRange, posY) != Tile.CONCRETE) {
				

				if (grid.getContents(posX + currentRange, posY) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(posX + currentRange, posY, Tile.POWERUPS);
				} 
				else if (grid.getContents(posX + currentRange, posY) == Tile.BRICKANDEXITWAY) {
					grid.setContents(posX + currentRange, posY, Tile.EXITWAY);
				} 
				else if (grid.getContents(posX + currentRange, posY) == Tile.EXITWAY) {
					grid.setContents(posX + currentRange, posY, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);
				} 
				else if (grid.getContents(posX + currentRange, posY) == Tile.BALLOOM
						|| grid.getContents(posX + currentRange, posY) == Tile.ONEAL
						|| grid.getContents(posX + currentRange, posY) == Tile.DOLL
						|| grid.getContents(posX + currentRange, posY) == Tile.MINVO
						|| grid.getContents(posX + currentRange, posY) == Tile.KONDORIA
						|| grid.getContents(posX + currentRange, posY) == Tile.OVAPI
						|| grid.getContents(posX + currentRange, posY) == Tile.PASS
						|| grid.getContents(posX + currentRange, posY) == Tile.PONTAN) {
					
					if (grid.getContents(posX + currentRange, posY) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scores.add(BALLOOM_SCORE);
						grid.setContents(posX + currentRange, posY, Tile.EXPLODE);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scores.add(ONEAL_SCORE);
						grid.setContents(posX + currentRange, posY, Tile.EXPLODE);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scores.add(DOLL_SCORE);
						grid.setContents(posX + currentRange, posY, Tile.EXPLODE);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scores.add(MINVO_SCORE);
						grid.setContents(posX + currentRange, posY, Tile.EXPLODE);
					}
					if (grid.getContents(posX + currentRange, posY) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scores.add(KONDORIA_SCORE);
						grid.setContents(posX + currentRange, posY, Tile.EXPLODE);
					} 
					if (grid.getContents(posX + currentRange, posY) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scores.add(OVAPI_SCORE);
						grid.setContents(posX + currentRange, posY, Tile.EXPLODE);
					} 
					if (grid.getContents(posX + currentRange, posY) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scores.add(PASS_SCORE);
						grid.setContents(posX + currentRange, posY, Tile.EXPLODE);
					}  
					if (grid.getContents(posX + currentRange, posY) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scores.add(PONTAN_SCORE);
						grid.setContents(posX + currentRange, posY, Tile.EXPLODE);
					} 
					
				}
				//FLAME PASS POWERUP
				else if(grid.getContents(posX + currentRange, posY) == Tile.PLAYER){
					if(PowerUps.getFlamepass() == true){
						grid.setContents(posX + currentRange, posY, Tile.PLAYER);
					}
					else{
						grid.setContents(posX + currentRange, posY, Tile.EXPLODE);
					}
				}
				else {
					
					grid.setContents(posX + currentRange, posY, Tile.EXPLODE);
				}

				currentRange++;
			}

			else if (grid.getContents(posX + currentRange, posY) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(posX - currentRange, posY) != Tile.CONCRETE) {

				if (grid.getContents(posX - currentRange, posY) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(posX - currentRange, posY, Tile.POWERUPS);

				} 
				else if (grid.getContents(posX - currentRange, posY) == Tile.BRICKANDEXITWAY) {
					grid.setContents(posX - currentRange, posY, Tile.EXITWAY);
					
				} 
				else if (grid.getContents(posX - currentRange, posY) == Tile.EXITWAY) {
					grid.setContents(posX - currentRange, posY, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} 
				else if (grid.getContents(posX - currentRange, posY) == Tile.BALLOOM
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
						scores.add(BALLOOM_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scores.add(ONEAL_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scores.add(DOLL_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scores.add(MINVO_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scores.add(KONDORIA_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					} 
					if (grid.getContents(posX - currentRange, posY) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scores.add(OVAPI_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					} 
					if (grid.getContents(posX - currentRange, posY) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scores.add(PASS_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					}  
					if (grid.getContents(posX - currentRange, posY) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scores.add(PONTAN_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					}
					
					
					if (grid.getContents(posX - currentRange, posY) == Tile.KONDORIAANDBRICK) {
						numberOfEnemiesKilled++;
						scores.add(KONDORIA_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Tile.OVAPIANDBRICK) {
						numberOfEnemiesKilled++;
						scores.add(OVAPI_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Tile.PONTANANDBRICK) {
						numberOfEnemiesKilled++;
						scores.add(PONTAN_SCORE);
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					}
				} 
				else if(grid.getContents(posX - currentRange, posY) == Tile.PLAYER){
					if(PowerUps.getFlamepass() == true){
						grid.setContents(posX - currentRange, posY, Tile.PLAYER);
					}
					else{
						grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
					}
				}
				else {
					grid.setContents(posX - currentRange, posY, Tile.EXPLODE);
				}
				

				currentRange++;
			}

			else if (grid.getContents(posX - currentRange, posY) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {
			if (grid.getContents(posX, posY + currentRange) != Tile.CONCRETE) {

				if (grid.getContents(posX, posY + currentRange) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(posX, posY + currentRange, Tile.POWERUPS);

				} 
				else if (grid.getContents(posX, posY + currentRange) == Tile.BRICKANDEXITWAY) {
					grid.setContents(posX, posY + currentRange, Tile.EXITWAY);
				} 
				else if (grid.getContents(posX, posY + currentRange) == Tile.EXITWAY) {
					grid.setContents(posX, posY + currentRange, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} 
				else if (grid.getContents(posX, posY + currentRange) == Tile.BALLOOM
						|| grid.getContents(posX, posY + currentRange) == Tile.ONEAL
						|| grid.getContents(posX, posY + currentRange) == Tile.DOLL
						|| grid.getContents(posX, posY + currentRange) == Tile.MINVO
						|| grid.getContents(posX, posY + currentRange) == Tile.KONDORIA
						|| grid.getContents(posX, posY + currentRange) == Tile.OVAPI
						|| grid.getContents(posX, posY + currentRange) == Tile.PASS
						|| grid.getContents(posX, posY + currentRange) == Tile.PONTAN) {

					if (grid.getContents(posX, posY + currentRange) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scores.add(BALLOOM_SCORE);
						grid.setContents(posX, posY + currentRange, Tile.EXPLODE);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scores.add(ONEAL_SCORE);
						grid.setContents(posX, posY + currentRange, Tile.EXPLODE);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scores.add(DOLL_SCORE);
						grid.setContents(posX, posY + currentRange, Tile.EXPLODE);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scores.add(MINVO_SCORE);
						grid.setContents(posX, posY + currentRange, Tile.EXPLODE);
					}
					if (grid.getContents(posX, posY + currentRange) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scores.add(KONDORIA_SCORE);
						grid.setContents(posX, posY + currentRange, Tile.EXPLODE);
					} 
					if (grid.getContents(posX, posY + currentRange) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scores.add(OVAPI_SCORE);
						grid.setContents(posX, posY + currentRange, Tile.EXPLODE);
					} 
					if (grid.getContents(posX, posY + currentRange) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scores.add(PASS_SCORE);
						grid.setContents(posX, posY + currentRange, Tile.EXPLODE);
					}  
					if (grid.getContents(posX, posY + currentRange) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scores.add(PONTAN_SCORE);
						grid.setContents(posX, posY + currentRange, Tile.EXPLODE);
					}
				} 
				else if(grid.getContents(posX, posY + currentRange) == Tile.PLAYER){
					if(PowerUps.getFlamepass() == true){
						grid.setContents(posX, posY + currentRange, Tile.PLAYER);
					}
					else{
						grid.setContents(posX, posY + currentRange, Tile.EXPLODE);
					}
				}
				else {
					grid.setContents(posX, posY + currentRange, Tile.EXPLODE);
				}
				
				
				currentRange++;
			}

			else if (grid.getContents(posX, posY + currentRange) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {
			
			if (grid.getContents(posX, posY - currentRange) != Tile.CONCRETE) {

				if (grid.getContents(posX, posY - currentRange) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(posX, posY - currentRange, Tile.POWERUPS);

				} 
				else if (grid.getContents(posX, posY - currentRange) == Tile.BRICKANDEXITWAY) {
					grid.setContents(posX, posY - currentRange, Tile.EXITWAY);
				} 
				else if (grid.getContents(posX, posY - currentRange) == Tile.EXITWAY) {
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
						scores.add(BALLOOM_SCORE);
						grid.setContents(posX, posY - currentRange, Tile.EXPLODE);
						
					}
					if (grid.getContents(posX, posY - currentRange) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scores.add(ONEAL_SCORE);
						grid.setContents(posX, posY - currentRange, Tile.EXPLODE);
					}
					if (grid.getContents(posX, posY - currentRange) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scores.add(DOLL_SCORE);
						grid.setContents(posX, posY - currentRange, Tile.EXPLODE);
					}
					if (grid.getContents(posX, posY - currentRange) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scores.add(MINVO_SCORE);
						grid.setContents(posX, posY - currentRange, Tile.EXPLODE);
					}
					if (grid.getContents(posX, posY - currentRange) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scores.add(KONDORIA_SCORE);
						grid.setContents(posX, posY - currentRange, Tile.EXPLODE);
					} 
					if (grid.getContents(posX, posY - currentRange) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scores.add(OVAPI_SCORE);
						grid.setContents(posX, posY - currentRange, Tile.EXPLODE);
					} 
					if (grid.getContents(posX, posY - currentRange) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scores.add(PASS_SCORE);
						grid.setContents(posX, posY - currentRange, Tile.EXPLODE);
					}  
					if (grid.getContents(posX, posY - currentRange) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scores.add(PONTAN_SCORE);
						grid.setContents(posX, posY - currentRange, Tile.EXPLODE);
					}
				}
				
				else if(grid.getContents(posX, posY - currentRange) == Tile.PLAYER){
					if(PowerUps.getFlamepass() == true){
						grid.setContents(posX, posY - currentRange, Tile.PLAYER);
					}
					else{
						grid.setContents(posX, posY - currentRange, Tile.EXPLODE);
					}
				}
				
				
				else {
					grid.setContents(posX, posY - currentRange, Tile.EXPLODE);
				}

				currentRange++;
			}

			else if (grid.getContents(posX, posY - currentRange) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}

		currentRange = 0;
		
		if (scores.size() == 1) {
			currentGameScore = scores.get(0);
		}
		else if (scores.size() == 2) {
			int temp = scores.get(0);
			if (temp < scores.get(1)) {
				currentGameScore = temp * 2 + scores.get(1);
			}
			else {
				currentGameScore = temp + 2 * scores.get(1);
			}
			
		}
		else {
			for (int i = 0; i < scores.size(); i++) {
				int temp = (int) ((int) scores.get(i) * Math.pow(2,i)); 
				currentGameScore += temp;
			}
			
		}
		
		setTotalGameScore(getTotalGameScore() + currentGameScore);
		
		PlayerInfo.playerScore += getCurrentGameScore();		
		scores.clear();
		

		
		
	}

	public int getCurrentGameScore() {
		return currentGameScore;
	}

	public void setCurrentGameScore(int currentGameScore) {
		this.currentGameScore = currentGameScore;
	}

	public int getTotalGameScore() {
		return totalGameScore;
	}

	public void setTotalGameScore(int totalGameScore) {
		this.totalGameScore = totalGameScore;
	}

	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	@Override
	public void run() {

		startTime = System.currentTimeMillis();

		if(player.hasDetonate()){
			while(true){
			
				if(player.getDetonatePressed() && Player.getBombNumber() == bombNumber){
					Player.setBombsOnGround(Player.getBombsOnGround()-1);
					player.setDetonatePressed(false);
					Player.setBombNumber(Player.getBombNumber()-1);
					Player.setCurrentBombCounter(Player.getCurrentBombCounter()+1);
					this.explode();
					break;
				}
			}	
		}
		
		
		else{
			while(true){
			if((currentTime = System.currentTimeMillis()) - startTime >= 2000){
				Player.setBombsOnGround(Player.getBombsOnGround()-1);
				this.explode();
				break;
			}
				
			
		}
		}
		
		
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
