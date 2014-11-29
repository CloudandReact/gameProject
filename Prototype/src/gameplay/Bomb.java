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
	private int numberOfEnemiesKilled;
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
	

	/*
	 * RANGE IS DEFAULTED TO 1 IN CONSTRUCTORS. TO SET RANGE USE SETTER ON BOMB
	 * OBJECT. USE THE SAME BOMB OBJECT SUPPLIED TO THE THREAD.
	 */

	public Bomb(Grid grid, Enemy enemy) {
		this.enemy = enemy;
		setTotalGameScore(0);
		scores = new ArrayList<Integer>();
		loadImage();
		this.range = 1;
		this.setBombs(1);
		this.grid = grid;
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

	public int getNumberOfEnemiesKilled() {
		return numberOfEnemiesKilled;
	}

	public void explode() {
		
		currentGameScore = 0;
		
		

		currentRange = 0;
		System.out.println("Explode method");

		while (currentRange <= range) {
			System.out.println("PosX is ..:" + posX + "PosY is. ..: " + posY);
			System.out.println(currentRange);
			
			if (grid.getContents(posX + currentRange, posY) != Cell.CONCRETE) {
				

				if (grid.getContents(posX + currentRange, posY) == Cell.BRICKANDPOWERUPS) {
					grid.setContents(posX + currentRange, posY, Cell.POWERUPS);
				} 
				else if (grid.getContents(posX + currentRange, posY) == Cell.BRICKANDEXITWAY) {
					grid.setContents(posX + currentRange, posY, Cell.EXITWAY);
				} 
				else if (grid.getContents(posX + currentRange, posY) == Cell.EXITWAY) {
					grid.setContents(posX + currentRange, posY, Cell.EMPTY);
					enemy.setIsExitwayBlownUp(true);
				} 
				else if (grid.getContents(posX + currentRange, posY) == Cell.BALLOOM
						|| grid.getContents(posX + currentRange, posY) == Cell.ONEAL
						|| grid.getContents(posX + currentRange, posY) == Cell.DOLL
						|| grid.getContents(posX + currentRange, posY) == Cell.MINVO
						|| grid.getContents(posX + currentRange, posY) == Cell.KONDORIA
						|| grid.getContents(posX + currentRange, posY) == Cell.OVAPI
						|| grid.getContents(posX + currentRange, posY) == Cell.PASS
						|| grid.getContents(posX + currentRange, posY) == Cell.PONTAN) {
					
					if (grid.getContents(posX + currentRange, posY) == Cell.BALLOOM) {
						numberOfEnemiesKilled++;
						scores.add(BALLOOM_SCORE);
						grid.setContents(posX + currentRange, posY, Cell.EXPLODE);
					}
					if (grid.getContents(posX + currentRange, posY) == Cell.ONEAL) {
						numberOfEnemiesKilled++;
						scores.add(ONEAL_SCORE);
						grid.setContents(posX + currentRange, posY, Cell.EXPLODE);
					}
					if (grid.getContents(posX + currentRange, posY) == Cell.DOLL) {
						numberOfEnemiesKilled++;
						scores.add(DOLL_SCORE);
						grid.setContents(posX + currentRange, posY, Cell.EXPLODE);
					}
					if (grid.getContents(posX + currentRange, posY) == Cell.MINVO) {
						numberOfEnemiesKilled++;
						scores.add(MINVO_SCORE);
						grid.setContents(posX + currentRange, posY, Cell.EXPLODE);
					}
					if (grid.getContents(posX + currentRange, posY) == Cell.KONDORIA) {
						numberOfEnemiesKilled++;
						scores.add(KONDORIA_SCORE);
						grid.setContents(posX + currentRange, posY, Cell.EXPLODE);
					} 
					if (grid.getContents(posX + currentRange, posY) == Cell.OVAPI) {
						numberOfEnemiesKilled++;
						scores.add(OVAPI_SCORE);
						grid.setContents(posX + currentRange, posY, Cell.EXPLODE);
					} 
					if (grid.getContents(posX + currentRange, posY) == Cell.PASS) {
						numberOfEnemiesKilled++;
						scores.add(PASS_SCORE);
						grid.setContents(posX + currentRange, posY, Cell.EXPLODE);
					}  
					if (grid.getContents(posX + currentRange, posY) == Cell.PONTAN) {
						numberOfEnemiesKilled++;
						scores.add(PONTAN_SCORE);
						grid.setContents(posX + currentRange, posY, Cell.EXPLODE);
					} 
					
				}
				//FLAME PASS POWERUP
				else if(grid.getContents(posX + currentRange, posY) == Cell.PLAYER){
					if(PowerUps.getFlamepass() == true){
						grid.setContents(posX + currentRange, posY, Cell.PLAYER);
					}
					else{
						grid.setContents(posX + currentRange, posY, Cell.EXPLODE);
					}
				}
				else {
					
					grid.setContents(posX + currentRange, posY, Cell.EXPLODE);
				}

				currentRange++;
			}

			else if (grid.getContents(posX + currentRange, posY) == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(posX - currentRange, posY) != Cell.CONCRETE) {

				if (grid.getContents(posX - currentRange, posY) == Cell.BRICKANDPOWERUPS) {
					grid.setContents(posX - currentRange, posY, Cell.POWERUPS);

				} 
				else if (grid.getContents(posX - currentRange, posY) == Cell.BRICKANDEXITWAY) {
					grid.setContents(posX - currentRange, posY, Cell.EXITWAY);
					
				} 
				else if (grid.getContents(posX - currentRange, posY) == Cell.EXITWAY) {
					grid.setContents(posX - currentRange, posY, Cell.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} 
				else if (grid.getContents(posX - currentRange, posY) == Cell.BALLOOM
						|| grid.getContents(posX - currentRange, posY) == Cell.ONEAL
						|| grid.getContents(posX - currentRange, posY) == Cell.DOLL
						|| grid.getContents(posX - currentRange, posY) == Cell.MINVO
						|| grid.getContents(posX - currentRange, posY) == Cell.KONDORIA
						|| grid.getContents(posX - currentRange, posY) == Cell.OVAPI
						|| grid.getContents(posX - currentRange, posY) == Cell.PASS
						|| grid.getContents(posX - currentRange, posY) == Cell.PONTAN
						|| grid.getContents(posX - currentRange, posY) == Cell.KONDORIAANDBRICK
						|| grid.getContents(posX - currentRange, posY) == Cell.OVAPIANDBRICK
						|| grid.getContents(posX - currentRange, posY) == Cell.PONTANANDBRICK) {

					if (grid.getContents(posX - currentRange, posY) == Cell.BALLOOM) {
						numberOfEnemiesKilled++;
						scores.add(BALLOOM_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Cell.ONEAL) {
						numberOfEnemiesKilled++;
						scores.add(ONEAL_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Cell.DOLL) {
						numberOfEnemiesKilled++;
						scores.add(DOLL_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Cell.MINVO) {
						numberOfEnemiesKilled++;
						scores.add(MINVO_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Cell.KONDORIA) {
						numberOfEnemiesKilled++;
						scores.add(KONDORIA_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					} 
					if (grid.getContents(posX - currentRange, posY) == Cell.OVAPI) {
						numberOfEnemiesKilled++;
						scores.add(OVAPI_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					} 
					if (grid.getContents(posX - currentRange, posY) == Cell.PASS) {
						numberOfEnemiesKilled++;
						scores.add(PASS_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					}  
					if (grid.getContents(posX - currentRange, posY) == Cell.PONTAN) {
						numberOfEnemiesKilled++;
						scores.add(PONTAN_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					}
					
					
					if (grid.getContents(posX - currentRange, posY) == Cell.KONDORIAANDBRICK) {
						numberOfEnemiesKilled++;
						scores.add(KONDORIA_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Cell.OVAPIANDBRICK) {
						numberOfEnemiesKilled++;
						scores.add(OVAPI_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					}
					if (grid.getContents(posX - currentRange, posY) == Cell.PONTANANDBRICK) {
						numberOfEnemiesKilled++;
						scores.add(PONTAN_SCORE);
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					}
				} 
				else if(grid.getContents(posX - currentRange, posY) == Cell.PLAYER){
					if(PowerUps.getFlamepass() == true){
						grid.setContents(posX - currentRange, posY, Cell.PLAYER);
					}
					else{
						grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
					}
				}
				else {
					grid.setContents(posX - currentRange, posY, Cell.EXPLODE);
				}
				

				currentRange++;
			}

			else if (grid.getContents(posX - currentRange, posY) == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {
			System.out.println("CURRENT RANGE IN Y: ..." + currentRange);
			if (grid.getContents(posX, posY + currentRange) != Cell.CONCRETE) {

				if (grid.getContents(posX, posY + currentRange) == Cell.BRICKANDPOWERUPS) {
					grid.setContents(posX, posY + currentRange, Cell.POWERUPS);

				} 
				else if (grid.getContents(posX, posY + currentRange) == Cell.BRICKANDEXITWAY) {
					grid.setContents(posX, posY + currentRange, Cell.EXITWAY);
				} 
				else if (grid.getContents(posX, posY + currentRange) == Cell.EXITWAY) {
					grid.setContents(posX, posY + currentRange, Cell.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} 
				else if (grid.getContents(posX, posY + currentRange) == Cell.BALLOOM
						|| grid.getContents(posX, posY + currentRange) == Cell.ONEAL
						|| grid.getContents(posX, posY + currentRange) == Cell.DOLL
						|| grid.getContents(posX, posY + currentRange) == Cell.MINVO
						|| grid.getContents(posX, posY + currentRange) == Cell.KONDORIA
						|| grid.getContents(posX, posY + currentRange) == Cell.OVAPI
						|| grid.getContents(posX, posY + currentRange) == Cell.PASS
						|| grid.getContents(posX, posY + currentRange) == Cell.PONTAN) {

					if (grid.getContents(posX, posY + currentRange) == Cell.BALLOOM) {
						numberOfEnemiesKilled++;
						scores.add(BALLOOM_SCORE);
						grid.setContents(posX, posY + currentRange, Cell.EXPLODE);
					}
					if (grid.getContents(posX, posY + currentRange) == Cell.ONEAL) {
						numberOfEnemiesKilled++;
						scores.add(ONEAL_SCORE);
						grid.setContents(posX, posY + currentRange, Cell.EXPLODE);
					}
					if (grid.getContents(posX, posY + currentRange) == Cell.DOLL) {
						numberOfEnemiesKilled++;
						scores.add(DOLL_SCORE);
						grid.setContents(posX, posY + currentRange, Cell.EXPLODE);
					}
					if (grid.getContents(posX, posY + currentRange) == Cell.MINVO) {
						numberOfEnemiesKilled++;
						scores.add(MINVO_SCORE);
						grid.setContents(posX, posY + currentRange, Cell.EXPLODE);
					}
					if (grid.getContents(posX, posY + currentRange) == Cell.KONDORIA) {
						numberOfEnemiesKilled++;
						scores.add(KONDORIA_SCORE);
						grid.setContents(posX, posY + currentRange, Cell.EXPLODE);
					} 
					if (grid.getContents(posX, posY + currentRange) == Cell.OVAPI) {
						numberOfEnemiesKilled++;
						scores.add(OVAPI_SCORE);
						grid.setContents(posX, posY + currentRange, Cell.EXPLODE);
					} 
					if (grid.getContents(posX, posY + currentRange) == Cell.PASS) {
						numberOfEnemiesKilled++;
						scores.add(PASS_SCORE);
						grid.setContents(posX, posY + currentRange, Cell.EXPLODE);
					}  
					if (grid.getContents(posX, posY + currentRange) == Cell.PONTAN) {
						numberOfEnemiesKilled++;
						scores.add(PONTAN_SCORE);
						grid.setContents(posX, posY + currentRange, Cell.EXPLODE);
					}
				} 
				else if(grid.getContents(posX, posY + currentRange) == Cell.PLAYER){
					if(PowerUps.getFlamepass() == true){
						grid.setContents(posX, posY + currentRange, Cell.PLAYER);
					}
					else{
						grid.setContents(posX, posY + currentRange, Cell.EXPLODE);
					}
				}
				else {
					grid.setContents(posX, posY + currentRange, Cell.EXPLODE);
				}
				
				
				currentRange++;
			}

			else if (grid.getContents(posX, posY + currentRange) == Cell.CONCRETE) {
				// Come out of the loop, we don't want to explode across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {
			
			if (grid.getContents(posX, posY - currentRange) != Cell.CONCRETE) {

				if (grid.getContents(posX, posY - currentRange) == Cell.BRICKANDPOWERUPS) {
					grid.setContents(posX, posY - currentRange, Cell.POWERUPS);

				} 
				else if (grid.getContents(posX, posY - currentRange) == Cell.BRICKANDEXITWAY) {
					grid.setContents(posX, posY - currentRange, Cell.EXITWAY);
				} 
				else if (grid.getContents(posX, posY - currentRange) == Cell.EXITWAY) {
					grid.setContents(posX, posY - currentRange, Cell.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} 
				
				else if (grid.getContents(posX, posY - currentRange) == Cell.BALLOOM
						|| grid.getContents(posX, posY - currentRange) == Cell.ONEAL
						|| grid.getContents(posX, posY - currentRange) == Cell.DOLL
						|| grid.getContents(posX, posY - currentRange) == Cell.MINVO
						|| grid.getContents(posX, posY - currentRange) == Cell.KONDORIA
						|| grid.getContents(posX, posY - currentRange) == Cell.OVAPI
						|| grid.getContents(posX, posY - currentRange) == Cell.PASS
						|| grid.getContents(posX, posY - currentRange) == Cell.PONTAN) {

					if (grid.getContents(posX, posY - currentRange) == Cell.BALLOOM) {
						numberOfEnemiesKilled++;
						scores.add(BALLOOM_SCORE);
						grid.setContents(posX, posY - currentRange, Cell.EXPLODE);
						
					}
					if (grid.getContents(posX, posY - currentRange) == Cell.ONEAL) {
						numberOfEnemiesKilled++;
						scores.add(ONEAL_SCORE);
						grid.setContents(posX, posY - currentRange, Cell.EXPLODE);
					}
					if (grid.getContents(posX, posY - currentRange) == Cell.DOLL) {
						numberOfEnemiesKilled++;
						scores.add(DOLL_SCORE);
						grid.setContents(posX, posY - currentRange, Cell.EXPLODE);
					}
					if (grid.getContents(posX, posY - currentRange) == Cell.MINVO) {
						numberOfEnemiesKilled++;
						scores.add(MINVO_SCORE);
						grid.setContents(posX, posY - currentRange, Cell.EXPLODE);
					}
					if (grid.getContents(posX, posY - currentRange) == Cell.KONDORIA) {
						numberOfEnemiesKilled++;
						scores.add(KONDORIA_SCORE);
						grid.setContents(posX, posY - currentRange, Cell.EXPLODE);
					} 
					if (grid.getContents(posX, posY - currentRange) == Cell.OVAPI) {
						numberOfEnemiesKilled++;
						scores.add(OVAPI_SCORE);
						grid.setContents(posX, posY - currentRange, Cell.EXPLODE);
					} 
					if (grid.getContents(posX, posY - currentRange) == Cell.PASS) {
						numberOfEnemiesKilled++;
						scores.add(PASS_SCORE);
						grid.setContents(posX, posY - currentRange, Cell.EXPLODE);
					}  
					if (grid.getContents(posX, posY - currentRange) == Cell.PONTAN) {
						numberOfEnemiesKilled++;
						scores.add(PONTAN_SCORE);
						grid.setContents(posX, posY - currentRange, Cell.EXPLODE);
					}
				}
				
				else if(grid.getContents(posX, posY - currentRange) == Cell.PLAYER){
					if(PowerUps.getFlamepass() == true){
						grid.setContents(posX, posY - currentRange, Cell.PLAYER);
					}
					else{
						grid.setContents(posX, posY - currentRange, Cell.EXPLODE);
					}
				}
				
				
				else {
					grid.setContents(posX, posY - currentRange, Cell.EXPLODE);
					System.out.println("HERE");
				}

				currentRange++;
			}

			else if (grid.getContents(posX, posY - currentRange) == Cell.CONCRETE) {
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
				//setCurrentGameScore(getCurrentGameScore() + temp);
				currentGameScore += temp;
			}
			
		}
		
		setTotalGameScore(getTotalGameScore() + currentGameScore);
		
		PlayerInfo.playerScore += getCurrentGameScore();
		System.out.println(PlayerInfo.playerScore); 
		
		System.out.println(scores.size());
		

		scores.clear();
		
		System.out.println(scores.size());
		
		
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
		
		while(true){
			if((currentTime = System.currentTimeMillis()) - startTime >= 2000){
				Player.setBombsOnGround(Player.getBombsOnGround()-1);
				System.out.println("BLOWING UP NOW");
				this.explode();
				break;
			}
				
			
		}
		
	}

}
