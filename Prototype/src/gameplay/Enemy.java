package gameplay;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import Astar.*;

public class Enemy {

	private String Balloom = "Balloom.png";
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


	private Image imageBalloom;
	private Image imageOneal;
	private Image imageDoll;
	private Image imageMinvo;
	private Image imageKondoria;
	private Image imageOvapi;
	private Image imagePass;
	private Image imagePontan;
	private Image imageKondoriaAndBrick;
	private Image imageOvapiAndBrick;
	private Image imagePontanAndBrick;
	


	private Grid grid;
	private Grid tempGrid;

	private Render render;
	private Level level;

	private int enemyCount;
	private final int ENEMY_COUNT_EXITWAY = 8; 

	private int numberOfBallooms;
	private int numberOfOneals;
	private int numberOfDolls;
	private int numberOfMinvos;
	private int numberOfKondorias;
	private int numberOfOvapis;
	private int numberOfPasses;
	private int numberOfPontans;
	
	private Path path;
	private PathFinder finder;

	private ArrayList<EnemyTracker> enemiesInitial; 
	private ArrayList<EnemyTracker> enemiesAlive; 
	
	private EnemyTracker tracker;
	private EnemyTracker livingEnemy;
	
	private int count;
	
	private boolean isExitwayBlownUp;
	private Tile highestLevelEnemy;
	
	
	private final int numberOfEnemiesAfterExitwayIsBlownUp;
	
	
	public Enemy(Grid grid, Render render, Level level) {
		this.level = level;
		this.enemyCount = 0;
		this.render = render;
		this.loadImage();
		this.grid = grid;
		this.tempGrid = new Grid();
		this.isExitwayBlownUp = false;
		numberOfEnemiesAfterExitwayIsBlownUp = 8;
		
		count = 0;
		
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

		System.out.println("Current Level..: " + level.getLevel());
	}
	

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

	
	public void move(int targetX, int targetY) {
		
		if(isExitwayBlownUp){
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
				if(count % 3 == 0){
					moveWithChance(enemiesInitial.get(i), targetX, targetY, 0, 0);
				}
				continue;
			case ONEAL:
				if(count % 2 == 0){
					moveWithChance(enemiesInitial.get(i), targetX, targetY, 10, 2);
				}
				continue;
			case DOLL:
				if(count % 2 == 0){
					moveWithChance(enemiesInitial.get(i), targetX, targetY, 0, 0);
				}
				continue;
			case MINVO:
				//if(count%2 == 0){
					moveWithChance(enemiesInitial.get(i), targetX, targetY, 10, 2);
				//}
				continue;
			case KONDORIA:
				if(count % 4 == 0){
					wallPassMove(enemiesInitial.get(i), targetX, targetY, 2, 3);
				}
				continue;
			case KONDORIAANDBRICK:
				if(count % 4 == 0){
					wallPassMove(enemiesInitial.get(i), targetX, targetY, 2, 3);
				}
				continue;
			case OVAPI:
				if(count % 3 == 0){
					wallPassMove(enemiesInitial.get(i), targetX, targetY, 10, 2);
				}
				continue;
			case OVAPIANDBRICK:
				if(count % 3 == 0){
					wallPassMove(enemiesInitial.get(i), targetX, targetY, 10, 2);
				}
				continue;
			case PASS:
				//if(count%2 == 0){
					moveWithChance(enemiesInitial.get(i), targetX, targetY, 2, 3);
				//}
				continue;
			case PONTAN:
				//if(count%2 == 0){
					wallPassMove(enemiesInitial.get(i), targetX, targetY, 2, 2);
				//}
				continue;
			case PONTANANDBRICK:
				//if(count%2 == 0){
					wallPassMove(enemiesInitial.get(i), targetX, targetY, 2, 2);
				//}
				continue;
			default:
				break;
			}	
		}
		
		count++;	

	}

	

	
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
	

	
	private void verifyTracker(int posX, int posY, Tile enemyType){
	
		
		for(int i = 0; i < enemiesInitial.size(); i++){	
			if(enemiesInitial.get(i).getEnemyType() == enemyType && enemiesInitial.get(i).getxPosition() == posX && enemiesInitial.get(i).getyPosition() == posY ){
						
				livingEnemy = new EnemyTracker(posX, posY, enemiesInitial.get(i).getEnemyType());
				livingEnemy.setxDirection(enemiesInitial.get(i).getxDirection());
				livingEnemy.setyDirection(enemiesInitial.get(i).getyDirection());
				livingEnemy.setMovingInX(enemiesInitial.get(i).isMovingInX());
				livingEnemy.setMovingInY(enemiesInitial.get(i).isMovingInY());
				enemiesAlive.add(livingEnemy);		
				
			}
		}
	
	}
	

	
	

	private void moveWithChance(EnemyTracker tracker, int targetX, int targetY, int chance, int aStarRange){
		
		
		boolean canMoveInX = false;
		boolean canMoveInY = false;
		boolean usingAStar = false;
	
		int enemyDirectionX = tracker.getxDirection();
		int enemyDirectionY = tracker.getyDirection();
		int posX = tracker.getxPosition();
		int posY = tracker.getyPosition();
		int randChance = 0;
		
		
		if(aStarRange > 0){
			finder = new AStarPathFinder(grid, aStarRange);
			path = finder.findPath(tracker.getEnemyType(), posX, posY, targetX, targetY);
			
			if(path!=null){
				
				usingAStar = true;
				
			
				
				if(posX == path.getX(1)){
					canMoveInY = true;
					enemyDirectionY = path.getY(1) - posY;
					tracker.setyDirection(enemyDirectionY);

				}	
				else{
					canMoveInX = true;
					enemyDirectionX =  path.getX(1) - posX;
					tracker.setxDirection(enemyDirectionX);
					
				}
				
				grid.setContents(posX, posY, Tile.EMPTY);
				
				if(canMoveInX){
					
					if(grid.getContents(posX + enemyDirectionX, posY) == Tile.PLAYER){
						grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());	
						GameState.setState(State.PLAYERDEAD);
						render.setIsPlayerAlive(false);
					}

					grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());
					tracker.setxPosition(posX + enemyDirectionX);
					tracker.setMovingInX(true);
					tracker.setMovingInY(false);
				
					//tracker.setxDirection(enemyDirectionX);
					
				}
				
				else{
					if(grid.getContents(posX, posY  + enemyDirectionY) == Tile.PLAYER){
						grid.setContents(posX, posY  + enemyDirectionY, tracker.getEnemyType());	
						GameState.setState(State.PLAYERDEAD);
						render.setIsPlayerAlive(false);
					}
					
					grid.setContents(posX, posY  + enemyDirectionY, tracker.getEnemyType());
					tracker.setyPosition(posY + enemyDirectionY);
					tracker.setMovingInY(true);
					tracker.setMovingInX(false);
					//tracker.setyDirection(enemyDirectionY);
								
				}
				
			}
		}
		
		if(!usingAStar){
			
			if(chance!=0){
				randChance = randInt(1, chance);
			}
			
			
			if(grid.getContents(posX + enemyDirectionX,posY) == Tile.EMPTY || grid.getContents(posX + enemyDirectionX,posY) == Tile.PLAYER){
				canMoveInX = true;
				
			}
			
			else if(grid.getContents(posX - enemyDirectionX,posY) == Tile.EMPTY || grid.getContents(posX - enemyDirectionX,posY) == Tile.PLAYER){
				tracker.setxDirection(-enemyDirectionX); 
				enemyDirectionX = tracker.getxDirection();
				canMoveInX = true;
				
			}
			
			
			
			if(grid.getContents(posX,posY + enemyDirectionY) == Tile.EMPTY || grid.getContents(posX,posY + enemyDirectionY) == Tile.PLAYER){
				canMoveInY = true;
				// move in this direction
			}
			
			else if(grid.getContents(posX,posY - enemyDirectionY) == Tile.EMPTY || grid.getContents(posX,posY - enemyDirectionY) == Tile.PLAYER){
				tracker.setyDirection(-enemyDirectionY); 
				enemyDirectionY = tracker.getyDirection();
				canMoveInY = true;
				
			}
			// x% chance randChance is 1, and check if at intersection)
			if(randChance == 1 && canMoveInX && canMoveInY){
				if(tracker.isMovingInX()){
					canMoveInX = false;
					
				}
				else if(tracker.isMovingInY()){
					canMoveInY = false;
				}
				
			}
			
			if(canMoveInX){
				
				if(grid.getContents(posX + enemyDirectionX, posY) == Tile.PLAYER){
					grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());	
					GameState.setState(State.PLAYERDEAD);
					render.setIsPlayerAlive(false);
				}
				
				grid.setContents(posX, posY, Tile.EMPTY);
				grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());
				tracker.setxPosition(posX + enemyDirectionX);
				tracker.setMovingInX(true);
				tracker.setMovingInY(false);

				
			}
			
			if(canMoveInY && !canMoveInX){
				
				if(grid.getContents(posX, posY  + enemyDirectionY) == Tile.PLAYER){
					grid.setContents(posX, posY  + enemyDirectionY, tracker.getEnemyType());	
					GameState.setState(State.PLAYERDEAD);
					render.setIsPlayerAlive(false);
				}
				
				grid.setContents(posX, posY, Tile.EMPTY);
				grid.setContents(posX, posY + enemyDirectionY, tracker.getEnemyType());
				tracker.setyPosition(posY + enemyDirectionY);
				tracker.setMovingInY(true);
				tracker.setMovingInX(false);


			}
			
		}
		
	}
	
	
	
	
	private void wallPassMove(EnemyTracker tracker, int targetX, int targetY, int chance, int aStarRange){
		
		boolean canMoveInX = false;
		boolean canMoveInY = false;
		boolean usingAStar = false;
	
		int enemyDirectionX = tracker.getxDirection();
		int enemyDirectionY = tracker.getyDirection();
		int posX = tracker.getxPosition();
		int posY = tracker.getyPosition();
		int randChance = 0;
		
		
		if(aStarRange > 0){
			finder = new AStarPathFinder(grid, aStarRange);
			path = finder.findPath(tracker.getEnemyType(), posX, posY, targetX, targetY);
			

			if(path!=null){
				
				usingAStar = true;
				
			
				
				if(posX == path.getX(1)){
					canMoveInY = true;
					enemyDirectionY = path.getY(1) - posY;
					tracker.setyDirection(enemyDirectionY);
				}	
				else{
					canMoveInX = true;
					enemyDirectionX =  path.getX(1) - posX;
					tracker.setxDirection(enemyDirectionX);
					
				}
				
				if(grid.getContents(posX, posY) == Tile.KONDORIAANDBRICK || grid.getContents(posX, posY) == Tile.OVAPIANDBRICK || grid.getContents(posX, posY) == Tile.PONTANANDBRICK){
					grid.setContents(posX,posY,Tile.BRICK);					
					
				}
				
				else{
					grid.setContents(posX, posY, Tile.EMPTY);

				}
				
				if(canMoveInX){
					
			
					if(grid.getContents(posX + enemyDirectionX, posY) == Tile.PLAYER){
				
						grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());
						GameState.setState(State.PLAYERDEAD);
						render.setIsPlayerAlive(false);
					}
					
				
					else if(grid.getContents(posX + enemyDirectionX, posY) == Tile.BRICK){
						switch(tracker.getEnemyType()){
							case KONDORIA:
								grid.setContents(posX + enemyDirectionX, posY, Tile.KONDORIAANDBRICK);
								tracker.setEnemyType(Tile.KONDORIAANDBRICK);
								break;
							case OVAPI:
								grid.setContents(posX + enemyDirectionX, posY, Tile.OVAPIANDBRICK);
								tracker.setEnemyType(Tile.OVAPIANDBRICK);

								break;
							case PONTAN:
								grid.setContents(posX + enemyDirectionX, posY, Tile.PONTANANDBRICK);
								tracker.setEnemyType(Tile.PONTANANDBRICK);
								break;
								
							case KONDORIAANDBRICK:
								grid.setContents(posX + enemyDirectionX, posY, Tile.KONDORIAANDBRICK);
								tracker.setEnemyType(Tile.KONDORIAANDBRICK);
								break;
							case OVAPIANDBRICK:
								grid.setContents(posX + enemyDirectionX, posY, Tile.OVAPIANDBRICK);
								tracker.setEnemyType(Tile.OVAPIANDBRICK);

								break;
							case PONTANANDBRICK:
								grid.setContents(posX + enemyDirectionX, posY, Tile.PONTANANDBRICK);
								tracker.setEnemyType(Tile.PONTANANDBRICK);
								break;
								
								
							default:
								break;
						}
																		
					}
					
					else{
						// we've set initial pos to brick, next pos tile isnt a brick, so if kondoriaandbrick ... we set back to condoria
						switch(tracker.getEnemyType()){
						case KONDORIAANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Tile.KONDORIA);
							tracker.setEnemyType(Tile.KONDORIA);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Tile.OVAPI);
							tracker.setEnemyType(Tile.OVAPI);

							break;
						case PONTANANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Tile.PONTAN);
							tracker.setEnemyType(Tile.PONTAN);

							break;
						default:
							break;
						}
						grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());
					}
					
					tracker.setxPosition(posX + enemyDirectionX);
					tracker.setMovingInX(true);
					tracker.setMovingInY(false);
				
					//tracker.setxDirection(enemyDirectionX);
					
				}
				
				else{
					if(grid.getContents(posX, posY  + enemyDirectionY) == Tile.PLAYER){
						grid.setContents(posX, posY  + enemyDirectionY, tracker.getEnemyType());
						GameState.setState(State.PLAYERDEAD);
						render.setIsPlayerAlive(false);
					}
					
						
					else if(grid.getContents(posX, posY + enemyDirectionY) == Tile.BRICK){
						switch(tracker.getEnemyType()){
						case KONDORIA:
							grid.setContents(posX, posY + enemyDirectionY, Tile.KONDORIAANDBRICK);
							tracker.setEnemyType(Tile.KONDORIAANDBRICK);
							break;
						case OVAPI:
							grid.setContents(posX, posY + enemyDirectionY, Tile.OVAPIANDBRICK);
							tracker.setEnemyType(Tile.OVAPIANDBRICK);
							break;
						case PONTAN:
							grid.setContents(posX, posY + enemyDirectionY, Tile.PONTANANDBRICK);
							tracker.setEnemyType(Tile.PONTANANDBRICK);	
							break;
						case KONDORIAANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Tile.KONDORIAANDBRICK);
							tracker.setEnemyType(Tile.KONDORIAANDBRICK);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Tile.OVAPIANDBRICK);
							tracker.setEnemyType(Tile.OVAPIANDBRICK);
							break;
						case PONTANANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Tile.PONTANANDBRICK);
							tracker.setEnemyType(Tile.PONTANANDBRICK);	
							break;					
						default:
							break;
						}
																			
					}

					else{
						
						switch(tracker.getEnemyType()){
						case KONDORIAANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Tile.KONDORIA);
							tracker.setEnemyType(Tile.KONDORIA);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Tile.OVAPI);
							tracker.setEnemyType(Tile.OVAPI);
							break;
						case PONTANANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Tile.PONTAN);
							tracker.setEnemyType(Tile.PONTAN);	
							break;
						default:
							break;
						}
						
						
						grid.setContents(posX, posY + enemyDirectionY, tracker.getEnemyType());
						
					}
					
					
					tracker.setyPosition(posY + enemyDirectionY);
					tracker.setMovingInY(true);
					tracker.setMovingInX(false);
					//tracker.setyDirection(enemyDirectionY);
								
				}
				
			}
		}
		if(!usingAStar){
			
			if(chance!=0){
				randChance = randInt(1, chance);
			}
			
			if(grid.getContents(posX + enemyDirectionX,posY) == Tile.EMPTY || grid.getContents(posX + enemyDirectionX,posY) == Tile.PLAYER || grid.getContents(posX + enemyDirectionX,posY) == Tile.BRICK){
					canMoveInX = true;
			}
			else if(grid.getContents(posX - enemyDirectionX,posY) == Tile.EMPTY || grid.getContents(posX - enemyDirectionX,posY) == Tile.PLAYER || grid.getContents(posX - enemyDirectionX,posY) == Tile.BRICK ){
				tracker.setxDirection(-enemyDirectionX); 
				enemyDirectionX = tracker.getxDirection();	
				canMoveInX = true;
			}
				
			if(grid.getContents(posX,posY + enemyDirectionY) == Tile.EMPTY || grid.getContents(posX,posY + enemyDirectionY) == Tile.PLAYER || grid.getContents(posX,posY + enemyDirectionY) == Tile.BRICK){
					canMoveInY = true;
			}
			else if(grid.getContents(posX,posY - enemyDirectionY) == Tile.EMPTY || grid.getContents(posX,posY - enemyDirectionY) == Tile.PLAYER || grid.getContents(posX,posY - enemyDirectionY) == Tile.BRICK ){
				tracker.setyDirection(-enemyDirectionY); 
				enemyDirectionY = tracker.getyDirection();
				canMoveInY = true;
			}
			

		
			// x% chance randChance is 1, and check if at intersection)
			if(randChance == 1 && canMoveInX && canMoveInY){
				if(tracker.isMovingInX()){
					canMoveInX = false;
					
				}
				else if(tracker.isMovingInY()){
					canMoveInY = false;
				}
				
			}
			
			if(canMoveInX){
				
				if(grid.getContents(posX + enemyDirectionX, posY) == Tile.PLAYER){
					grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					render.setIsPlayerAlive(false);
				}
				
				if(grid.getContents(posX, posY) == Tile.KONDORIAANDBRICK || grid.getContents(posX, posY) == Tile.OVAPIANDBRICK || grid.getContents(posX, posY) == Tile.PONTANANDBRICK){
					grid.setContents(posX,posY,Tile.BRICK);
				}
				
				else{
					grid.setContents(posX, posY, Tile.EMPTY);

				}
				
				if(grid.getContents(posX + enemyDirectionX, posY) == Tile.BRICK){
						switch(tracker.getEnemyType()){
						case KONDORIA:
							grid.setContents(posX + enemyDirectionX, posY, Tile.KONDORIAANDBRICK);
							tracker.setEnemyType(Tile.KONDORIAANDBRICK);
							break;
						case OVAPI:
							grid.setContents(posX + enemyDirectionX, posY, Tile.OVAPIANDBRICK);
							tracker.setEnemyType(Tile.OVAPIANDBRICK);

							break;
						case PONTAN:
							grid.setContents(posX + enemyDirectionX, posY, Tile.PONTANANDBRICK);
							tracker.setEnemyType(Tile.PONTANANDBRICK);
							break;
						case KONDORIAANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Tile.KONDORIAANDBRICK);
							tracker.setEnemyType(Tile.KONDORIAANDBRICK);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Tile.OVAPIANDBRICK);
							tracker.setEnemyType(Tile.OVAPIANDBRICK);

							break;
						case PONTANANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Tile.PONTANANDBRICK);
							tracker.setEnemyType(Tile.PONTANANDBRICK);
							break;
							
						default:
							break;
					}
																	
				}
				
				else{
					// we've set initial pos to brick or empty, next pos tile isnt a brick, so if kondoriaandbrick ... we set back to kondoria
					switch(tracker.getEnemyType()){
					case KONDORIAANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY, Tile.KONDORIA);
						tracker.setEnemyType(Tile.KONDORIA);
						break;
					case OVAPIANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY, Tile.OVAPI);
						tracker.setEnemyType(Tile.OVAPI);

						break;
					case PONTANANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY, Tile.PONTAN);
						tracker.setEnemyType(Tile.PONTAN);

						break;
					default:
						break;
					}
					grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());
				}
				
				tracker.setxPosition(posX + enemyDirectionX);
				tracker.setMovingInX(true);
				tracker.setMovingInY(false);
			}
			
			if(canMoveInY && !canMoveInX){
				
				if(grid.getContents(posX, posY  + enemyDirectionY) == Tile.PLAYER){
					grid.setContents(posX, posY  + enemyDirectionY, tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					render.setIsPlayerAlive(false);
				}
				
				if(grid.getContents(posX, posY) == Tile.KONDORIAANDBRICK || grid.getContents(posX, posY) == Tile.OVAPIANDBRICK || grid.getContents(posX, posY) == Tile.PONTANANDBRICK){
					grid.setContents(posX,posY,Tile.BRICK);
				}
				
				else{
					grid.setContents(posX, posY, Tile.EMPTY);

				}
				
				
				if(grid.getContents(posX, posY  + enemyDirectionY) == Tile.PLAYER){
					grid.setContents(posX, posY  + enemyDirectionY, tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					render.setIsPlayerAlive(false);
				}
				
					
				else if(grid.getContents(posX, posY + enemyDirectionY) == Tile.BRICK){
					switch(tracker.getEnemyType()){
					case KONDORIA:
						grid.setContents(posX, posY + enemyDirectionY, Tile.KONDORIAANDBRICK);
						tracker.setEnemyType(Tile.KONDORIAANDBRICK);
						break;
					case OVAPI:
						grid.setContents(posX, posY + enemyDirectionY, Tile.OVAPIANDBRICK);
						tracker.setEnemyType(Tile.OVAPIANDBRICK);
						break;
					case PONTAN:
						grid.setContents(posX, posY + enemyDirectionY, Tile.PONTANANDBRICK);
						tracker.setEnemyType(Tile.PONTANANDBRICK);	
						break;
					case KONDORIAANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Tile.KONDORIAANDBRICK);
						tracker.setEnemyType(Tile.KONDORIAANDBRICK);
						break;
					case OVAPIANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Tile.OVAPIANDBRICK);
						tracker.setEnemyType(Tile.OVAPIANDBRICK);
						break;
					case PONTANANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Tile.PONTANANDBRICK);
						tracker.setEnemyType(Tile.PONTANANDBRICK);	
						break;				
					default:
						break;
					}
																		
				}

				else{	
					switch(tracker.getEnemyType()){
					case KONDORIAANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Tile.KONDORIA);
						tracker.setEnemyType(Tile.KONDORIA);
						break;
					case OVAPIANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Tile.OVAPI);
						tracker.setEnemyType(Tile.OVAPI);
						break;
					case PONTANANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Tile.PONTAN);
						tracker.setEnemyType(Tile.PONTAN);	
						break;
					default:
						break;
					}
					grid.setContents(posX, posY + enemyDirectionY, tracker.getEnemyType());
				}
				
				
				tracker.setyPosition(posY + enemyDirectionY);
				tracker.setMovingInY(true);
				tracker.setMovingInX(false);
				
			}
			
		}
			
	}
	
	private void exitwayLogic(){
		
		clearEnemies();
		placeEnemies(highestLevelEnemy, numberOfEnemiesAfterExitwayIsBlownUp);		
		setEnemyCount(ENEMY_COUNT_EXITWAY);
		Bomb.setNumberOfEnemiesKilled(0);
	}

	private void clearEnemies(){

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
	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
		
	}
	

	private void loadImage() {
		
		ImageIcon first = new ImageIcon(getClass().getResource(Balloom));
		imageBalloom = first.getImage();

		ImageIcon second = new ImageIcon(getClass().getResource(Oneal));
		imageOneal = second.getImage();

		ImageIcon third = new ImageIcon(getClass().getResource(Doll));
		imageDoll = third.getImage();

		ImageIcon fourth = new ImageIcon(getClass().getResource(Minvo));
		imageMinvo = fourth.getImage();

		ImageIcon fifth = new ImageIcon(getClass().getResource(Kondoria));
		imageKondoria = fifth.getImage();

		ImageIcon sixth = new ImageIcon(getClass().getResource(Ovapi));
		imageOvapi = sixth.getImage();

		ImageIcon seventh = new ImageIcon(getClass().getResource(Pass));
		imagePass = seventh.getImage();

		ImageIcon eighth = new ImageIcon(getClass().getResource(Pontan));
		imagePontan = eighth.getImage();
		
		ImageIcon nine = new ImageIcon(getClass().getResource(KondoriaAndBrick));
		imageKondoriaAndBrick = nine.getImage();
		
		ImageIcon ten = new ImageIcon(getClass().getResource(OvapiAndBrick));
		imageOvapiAndBrick = ten.getImage();
		
		ImageIcon eleven = new ImageIcon(getClass().getResource(PontanAndBrick));
		imagePontanAndBrick = eleven.getImage();
		
	}

	public Image getImageBalloom() {
		return imageBalloom;
	}
	
	
	public Image getImageOneal() {
		return imageOneal;
	}

	public Image getImageDoll() {
		return imageDoll;
	}

	public Image getImageMinvo() {
		return imageMinvo;
	}

	public Image getImageKondoria() {
		return imageKondoria;
	}
	
	public Image getImageKondoriaAndBrick() {
		return imageKondoriaAndBrick;
	}

	public Image getImageOvapi() {
		return imageOvapi;
	}
	
	public Image getImageOvapiAndBrick() {
		return imageOvapiAndBrick;
	}


	public Image getImagePass() {
		return imagePass;
	}

	public Image getImagePontan() {
		return imagePontan;
	}
	
	public Image getImagePontanAndBrick() {
		return imagePontanAndBrick;
	}
	
	public int getEnemyCount() {
		return enemyCount;
	}
	
	public void setEnemyCount(int newEnemyCount){
		this.enemyCount = newEnemyCount;
	}
	
	
	public void setIsExitwayBlownUp(boolean b) {
		this.isExitwayBlownUp = b;
		
	}
					
}
