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
	private EnemyTracker tracker;
	
	private ArrayList<EnemyTracker> enemiesAlive; 
	private EnemyTracker livingEnemy;
	
	private int count;
	
	private boolean isExitwayBlownUp;
	private Cell highestLevelEnemy;
	
	
	int dynamicNumberOfEnemies;
	
	
	public Enemy(Grid grid, Render render, Level level) {
		this.level = level;
		this.enemyCount = 0;
		this.render = render;
		this.loadImage();
		this.grid = grid;
		this.tempGrid = new Grid();
		this.isExitwayBlownUp = false;
		
		count = 0;
		
		enemiesInitial = new ArrayList<EnemyTracker>();
		enemiesAlive = new ArrayList<EnemyTracker>();
		

		System.out.println("Current Level..: " + level.getLevel());

		numberOfBallooms = level.getnumberOfBallooms();
		//System.out.println("Number of enemyType1..: " + level.getnumberOfBallooms());

		numberOfOneals = level.getnumberOfOneals();
		//System.out.println("Number of enemyType2..: " + level.getnumberOfOneals());

		numberOfDolls = level.getnumberOfDolls();
		//System.out.println("Number of enemyType3..: " + level.getnumberOfDolls());

		numberOfMinvos = level.getnumberOfMinvos();
		//System.out.println("Number of enemyType4..: " + level.getnumberOfMinvos());

		numberOfKondorias = level.getnumberOfKondorias();	

		//System.out.println("Number of enemyType5..: " + level.getnumberOfKondorias());

		numberOfOvapis = level.getnumberOfOvapis();
		//System.out.println("Number of enemyType6..: " + level.getnumberOfOvapis());

		numberOfPasses = level.getnumberOfPasses();
		//System.out.println("Number of enemyType7..: " + level.getnumberOfPasses());

		numberOfPontans = level.getnumberOfPontans();
		//System.out.println("Number of enemyType8..: " + level.getnumberOfPontans());

		validateEnemies();
		
		//System.out.println("Number of Enemies..: " + enemyCount);

	}

	private void validateEnemies() {

		if (numberOfBallooms > 0) {
			placeEnemies(Cell.BALLOOM, numberOfBallooms);
			highestLevelEnemy = Cell.ONEAL;
		}

		if (numberOfOneals > 0) {
			placeEnemies(Cell.ONEAL, numberOfOneals);
			highestLevelEnemy = Cell.DOLL;

		}

		if (numberOfDolls > 0) {
			placeEnemies(Cell.DOLL, numberOfDolls);
			highestLevelEnemy = Cell.MINVO;

		}

		if (numberOfMinvos > 0) {
			placeEnemies(Cell.MINVO, numberOfMinvos);
			highestLevelEnemy = Cell.KONDORIA;

		}

		if (numberOfKondorias > 0) {
			placeEnemies(Cell.KONDORIA, numberOfKondorias);
			highestLevelEnemy = Cell.OVAPI;

			
		}

		if (numberOfOvapis > 0) {
			placeEnemies(Cell.OVAPI, numberOfOvapis);
			highestLevelEnemy = Cell.PASS;

		}

		if (numberOfPasses > 0) {
			placeEnemies(Cell.PASS, numberOfPasses);
			highestLevelEnemy = Cell.PONTAN;

		}

		if (numberOfPontans > 0) {
			placeEnemies(Cell.PONTAN, numberOfPontans);
			highestLevelEnemy = Cell.PONTAN;

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
		
		
		// Change to AndBrick
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
					
	private void placeEnemies(Cell enemyType, int numberToPlace) {

		int numberOfEnemies = 0;
		while (numberOfEnemies < numberToPlace) {
			int randX = randInt(4, 30);
			int randY = randInt(1, 12);

			if (grid.getContents(randX, randY) == Cell.EMPTY) {
				
				grid.setContents(randX, randY, enemyType);
				tracker = new EnemyTracker(randX, randY, enemyType);
				enemiesInitial.add(tracker);				
				numberOfEnemies++;
				System.out.println("Enemy " +numberOfEnemies+ enemyType);

			}
			
		}
	
		
		enemyCount += numberOfEnemies;
		
	}
	
	
	private void copyGridAndVerifyTracker() {
		dynamicNumberOfEnemies = 0;
		for (int posX = 0; posX < Bomberman.WIDTH; posX++) {
			for (int posY = 0; posY < Bomberman.HEIGHT; posY++) {
				tempGrid.setContents(posX, posY, grid.getContents(posX, posY));
				
				switch (grid.getContents(posX, posY)) {
				case BALLOOM:						
					verifyTracker(posX, posY, Cell.BALLOOM);	
					continue;
				case ONEAL:
					verifyTracker(posX, posY, Cell.ONEAL);
					continue;
				case DOLL:
					verifyTracker(posX, posY, Cell.DOLL);
					continue;
				case MINVO:
					verifyTracker(posX, posY, Cell.MINVO);
					continue;
				case KONDORIA:
					verifyTracker(posX, posY, Cell.KONDORIA);
					continue;
				case KONDORIAANDBRICK:
					verifyTracker(posX, posY, Cell.KONDORIAANDBRICK);
					continue;
				case OVAPI:
					verifyTracker(posX, posY, Cell.OVAPI);
					continue;
				case OVAPIANDBRICK:
					verifyTracker(posX, posY, Cell.OVAPIANDBRICK);
					continue;
				case PASS:
					verifyTracker(posX, posY, Cell.PASS);
					continue;
				case PONTAN:
					verifyTracker(posX, posY, Cell.PONTAN);
					continue;
				case PONTANANDBRICK:
					verifyTracker(posX, posY, Cell.PONTANANDBRICK);
					continue;					
				default:
					break;
				}						
			}
		}
		if(isExitwayBlownUp){
			System.out.println("DYNAMIC NUMBER>>>>>>>>" + dynamicNumberOfEnemies);
			placeEnemies(highestLevelEnemy, 8-dynamicNumberOfEnemies);
			setIsExitwayBlownUp(false);

		}
	}
	
	private void copyGrid() {
		for (int posX = 0; posX < Bomberman.WIDTH; posX++) {
			for (int posY = 0; posY < Bomberman.HEIGHT; posY++) {
				tempGrid.setContents(posX, posY, grid.getContents(posX, posY));
							
			}
		}
	}
	
	private void exitwayLogic(){
		
		
		
		
		/*switch(highestLevelEnemy){
		case BALLOOM:	
			System.out.println("Changing enemies");
			enemiesInitial.get(index).setEnemyType(Cell.ONEAL); 
			break;
		case ONEAL:
			enemiesInitial.get(index).setEnemyType(Cell.DOLL); 
			break;
		case DOLL:
			enemiesInitial.get(index).setEnemyType(Cell.MINVO); 
			break;
		case MINVO:
			enemiesInitial.get(index).setEnemyType(Cell.KONDORIA); 
			break;
		case KONDORIA:
			enemiesInitial.get(index).setEnemyType(Cell.OVAPI); 
			break;
		case KONDORIAANDBRICK:
			enemiesInitial.get(index).setEnemyType(Cell.OVAPI); 
			break;
		case OVAPI:
			enemiesInitial.get(index).setEnemyType(Cell.PASS); 
			break;
		case OVAPIANDBRICK:
			enemiesInitial.get(index).setEnemyType(Cell.PASS); 
			break;
		case PASS:
			enemiesInitial.get(index).setEnemyType(Cell.PONTAN); 
			break;
		case PONTAN:
			enemiesInitial.get(index).setEnemyType(Cell.PONTAN); 
			break;
		case PONTANANDBRICK:
			enemiesInitial.get(index).setEnemyType(Cell.PONTAN); 
			break;					
		default:
			break; 
		}*/
	}
	
	
	private void verifyTracker(int posX, int posY, Cell enemyType){
	
		
		for(int i = 0; i < enemiesInitial.size(); i++){	
			//System.out.println(i);
			if(enemiesInitial.get(i).getEnemyType() == enemyType && enemiesInitial.get(i).getxPosition() == posX && enemiesInitial.get(i).getyPosition() == posY ){
						
				
				if(isExitwayBlownUp){
					System.out.println(highestLevelEnemy + "SLAdpasldlasdas;lkdas;lkdfal;sd");
					enemiesInitial.get(i).setEnemyType(highestLevelEnemy);
					System.out.println("yes alllllllllllllllll enemies");
					
				}
				
				livingEnemy = new EnemyTracker(posX, posY, enemiesInitial.get(i).getEnemyType());
				livingEnemy.setxDirection(enemiesInitial.get(i).getxDirection());
				livingEnemy.setyDirection(enemiesInitial.get(i).getyDirection());
				livingEnemy.setMovingInX(enemiesInitial.get(i).isMovingInX());
				livingEnemy.setMovingInY(enemiesInitial.get(i).isMovingInY());
				enemiesAlive.add(livingEnemy);
				
				dynamicNumberOfEnemies++;
							
	
			}
		}
		
		
		

		
		//System.out.println("Size of enemiesAlive list..:" + enemiesAlive.size());
	
	}
	

	
	
	public void move(int targetX, int targetY) {
		
		copyGridAndVerifyTracker();
		
		enemiesInitial.clear();
		
		enemiesInitial.addAll(enemiesAlive);
						
		enemiesAlive.clear();
		
		//System.out.println("enemiesInitial..:" + enemiesInitial.size());
		
		//System.out.println("List size...: " + enemiesInitial.size());
		
		
		
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

	
	public void aStarMovement(int targetX, int targetY, Cell cellType) {
		
		finder = new AStarPathFinder(grid, 10);

		// Create the needed enemy types, when we find an enemy on the grid we
		// call the appropriate move method.. Kappa

		copyGrid();
		

		for (int posX = 1; posX < Bomberman.WIDTH - 1; posX++) {
			for (int posY = 1; posY < Bomberman.HEIGHT - 1; posY++) {
				if (tempGrid.getContents(posX, posY) == cellType) {

					path = finder.findPath(cellType, posX, posY, targetX, targetY);
					
					if (path != null) {
						
						if(grid.getContents(posX, posY) == Cell.KONDORIAANDBRICK || grid.getContents(posX, posY) == Cell.OVAPIANDBRICK || grid.getContents(posX, posY) == Cell.PONTANANDBRICK){
							grid.setContents(posX,posY,Cell.BRICK);
						}
						
						else{
							grid.setContents(posX, posY, Cell.EMPTY);

						}
						
						// System.out.println("Player position: X: " + targetX +
						// " Y: " + targetY + " xTarget IS: " + path.getX(1) +
						// " yTarget IS: " + path.getY(1));
						
						// player killing
						if (grid.getContents(path.getX(1), path.getY(1)) == Cell.PLAYER) {
							grid.setContents(path.getX(1), path.getY(1), cellType);
							GameState.setState(State.PLAYERDEAD);
							render.setIsPlayerAlive(false);
						}

						grid.setContents(path.getX(1), path.getY(1), cellType);
						path = null;

						// ENEMYMOVE

					}

					else {
						// System.out.println("Path is null");
					}
				}
			}
		}
	}
	
	private void moveWithChance(EnemyTracker tracker, int targetX, int targetY, int chance, int aStarRange){
		
		//System.out.println("Size of enemiesAlive list..:" + enemiesAlive.size());
		//System.out.println("Size of enemiesInitial list..:" + enemiesInitial.size());
		
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
			
/*			System.out.println( "Target X...: " +targetX + " Target Y...: " + targetY);
			System.out.println( "EnemyX...: " +posX + " EnemyY...: " + posY);
*/
			
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
				
				grid.setContents(posX, posY, Cell.EMPTY);
				
				if(canMoveInX){
					
					if(grid.getContents(posX + enemyDirectionX, posY) == Cell.PLAYER){
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
					if(grid.getContents(posX, posY  + enemyDirectionY) == Cell.PLAYER){
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
			
			
			if(grid.getContents(posX + enemyDirectionX,posY) == Cell.EMPTY || grid.getContents(posX + enemyDirectionX,posY) == Cell.PLAYER){
				canMoveInX = true;
				
			}
			
			else if(grid.getContents(posX - enemyDirectionX,posY) == Cell.EMPTY || grid.getContents(posX - enemyDirectionX,posY) == Cell.PLAYER){
				tracker.setxDirection(-enemyDirectionX); 
				enemyDirectionX = tracker.getxDirection();
				canMoveInX = true;
				
			}
			
			
			
			if(grid.getContents(posX,posY + enemyDirectionY) == Cell.EMPTY || grid.getContents(posX,posY + enemyDirectionY) == Cell.PLAYER){
				canMoveInY = true;
				// move in this direction
			}
			
			else if(grid.getContents(posX,posY - enemyDirectionY) == Cell.EMPTY || grid.getContents(posX,posY - enemyDirectionY) == Cell.PLAYER){
				tracker.setyDirection(-enemyDirectionY); 
				enemyDirectionY = tracker.getyDirection();
				canMoveInY = true;
				
			}
			// x% chance randChance is 1, and check if at intersection)
			if(randChance == 1 && canMoveInX && canMoveInY){
				if(tracker.isMovingInX()){
					canMoveInX = false;
					//System.out.println("PLS");
					
				}
				else if(tracker.isMovingInY()){
					canMoveInY = false;
				}
				else{
					System.out.println("HOW");
				}
			}
			
			if(canMoveInX){
				
				if(grid.getContents(posX + enemyDirectionX, posY) == Cell.PLAYER){
					grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());	
					GameState.setState(State.PLAYERDEAD);
					render.setIsPlayerAlive(false);
				}
				
				grid.setContents(posX, posY, Cell.EMPTY);
				grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());
				tracker.setxPosition(posX + enemyDirectionX);
				tracker.setMovingInX(true);
				tracker.setMovingInY(false);

				
			}
			
			if(canMoveInY && !canMoveInX){
				
				if(grid.getContents(posX, posY  + enemyDirectionY) == Cell.PLAYER){
					grid.setContents(posX, posY  + enemyDirectionY, tracker.getEnemyType());	
					GameState.setState(State.PLAYERDEAD);
					render.setIsPlayerAlive(false);
				}
				
				grid.setContents(posX, posY, Cell.EMPTY);
				grid.setContents(posX, posY + enemyDirectionY, tracker.getEnemyType());
				tracker.setyPosition(posY + enemyDirectionY);
				tracker.setMovingInY(true);
				tracker.setMovingInX(false);


			}
			
		}
		
		
		
		
	
		
	}
	
	private void wallPassMove(EnemyTracker tracker, int targetX, int targetY, int chance, int aStarRange){
		
		//System.out.println("Size of enemiesAlive list..:" + enemiesAlive.size());
		//System.out.println("Size of enemiesInitial list..:" + enemiesInitial.size());
		
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
				
				if(grid.getContents(posX, posY) == Cell.KONDORIAANDBRICK || grid.getContents(posX, posY) == Cell.OVAPIANDBRICK || grid.getContents(posX, posY) == Cell.PONTANANDBRICK){
					grid.setContents(posX,posY,Cell.BRICK);					
					
				}
				
				else{
					grid.setContents(posX, posY, Cell.EMPTY);

				}
				
				if(canMoveInX){
					
			
					if(grid.getContents(posX + enemyDirectionX, posY) == Cell.PLAYER){
				
						grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());
						GameState.setState(State.PLAYERDEAD);
						render.setIsPlayerAlive(false);
					}
					
				
					else if(grid.getContents(posX + enemyDirectionX, posY) == Cell.BRICK){
						switch(tracker.getEnemyType()){
							case KONDORIA:
								grid.setContents(posX + enemyDirectionX, posY, Cell.KONDORIAANDBRICK);
								tracker.setEnemyType(Cell.KONDORIAANDBRICK);
								break;
							case OVAPI:
								grid.setContents(posX + enemyDirectionX, posY, Cell.OVAPIANDBRICK);
								tracker.setEnemyType(Cell.OVAPIANDBRICK);

								break;
							case PONTAN:
								grid.setContents(posX + enemyDirectionX, posY, Cell.PONTANANDBRICK);
								tracker.setEnemyType(Cell.PONTANANDBRICK);
								break;
								
							case KONDORIAANDBRICK:
								grid.setContents(posX + enemyDirectionX, posY, Cell.KONDORIAANDBRICK);
								tracker.setEnemyType(Cell.KONDORIAANDBRICK);
								break;
							case OVAPIANDBRICK:
								grid.setContents(posX + enemyDirectionX, posY, Cell.OVAPIANDBRICK);
								tracker.setEnemyType(Cell.OVAPIANDBRICK);

								break;
							case PONTANANDBRICK:
								grid.setContents(posX + enemyDirectionX, posY, Cell.PONTANANDBRICK);
								tracker.setEnemyType(Cell.PONTANANDBRICK);
								break;
								
								
							default:
								break;
						}
																		
					}
					
					else{
						// we've set initial pos to brick, next pos tile isnt a brick, so if kondoriaandbrick ... we set back to condoria
						switch(tracker.getEnemyType()){
						case KONDORIAANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Cell.KONDORIA);
							tracker.setEnemyType(Cell.KONDORIA);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Cell.OVAPI);
							tracker.setEnemyType(Cell.OVAPI);

							break;
						case PONTANANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Cell.PONTAN);
							tracker.setEnemyType(Cell.PONTAN);

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
					if(grid.getContents(posX, posY  + enemyDirectionY) == Cell.PLAYER){
						grid.setContents(posX, posY  + enemyDirectionY, tracker.getEnemyType());
						GameState.setState(State.PLAYERDEAD);
						render.setIsPlayerAlive(false);
					}
					
						
					else if(grid.getContents(posX, posY + enemyDirectionY) == Cell.BRICK){
						switch(tracker.getEnemyType()){
						case KONDORIA:
							grid.setContents(posX, posY + enemyDirectionY, Cell.KONDORIAANDBRICK);
							tracker.setEnemyType(Cell.KONDORIAANDBRICK);
							break;
						case OVAPI:
							grid.setContents(posX, posY + enemyDirectionY, Cell.OVAPIANDBRICK);
							tracker.setEnemyType(Cell.OVAPIANDBRICK);
							break;
						case PONTAN:
							grid.setContents(posX, posY + enemyDirectionY, Cell.PONTANANDBRICK);
							tracker.setEnemyType(Cell.PONTANANDBRICK);	
							break;
						case KONDORIAANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Cell.KONDORIAANDBRICK);
							tracker.setEnemyType(Cell.KONDORIAANDBRICK);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Cell.OVAPIANDBRICK);
							tracker.setEnemyType(Cell.OVAPIANDBRICK);
							break;
						case PONTANANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Cell.PONTANANDBRICK);
							tracker.setEnemyType(Cell.PONTANANDBRICK);	
							break;					
						default:
							break;
						}
																			
					}

					else{
						
						switch(tracker.getEnemyType()){
						case KONDORIAANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Cell.KONDORIA);
							tracker.setEnemyType(Cell.KONDORIA);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Cell.OVAPI);
							tracker.setEnemyType(Cell.OVAPI);
							break;
						case PONTANANDBRICK:
							grid.setContents(posX, posY + enemyDirectionY, Cell.PONTAN);
							tracker.setEnemyType(Cell.PONTAN);	
							break;
						default:
							break;
						}
						
						
						grid.setContents(posX, posY + enemyDirectionY, tracker.getEnemyType());
						//System.out.println(tracker.getEnemyType());
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
			
			if(grid.getContents(posX + enemyDirectionX,posY) == Cell.EMPTY || grid.getContents(posX + enemyDirectionX,posY) == Cell.PLAYER || grid.getContents(posX + enemyDirectionX,posY) == Cell.BRICK){
					canMoveInX = true;
			}
			else if(grid.getContents(posX - enemyDirectionX,posY) == Cell.EMPTY || grid.getContents(posX - enemyDirectionX,posY) == Cell.PLAYER || grid.getContents(posX - enemyDirectionX,posY) == Cell.BRICK ){
				tracker.setxDirection(-enemyDirectionX); 
				enemyDirectionX = tracker.getxDirection();	
				canMoveInX = true;
			}
				
			if(grid.getContents(posX,posY + enemyDirectionY) == Cell.EMPTY || grid.getContents(posX,posY + enemyDirectionY) == Cell.PLAYER || grid.getContents(posX,posY + enemyDirectionY) == Cell.BRICK){
					canMoveInY = true;
			}
			else if(grid.getContents(posX,posY - enemyDirectionY) == Cell.EMPTY || grid.getContents(posX,posY - enemyDirectionY) == Cell.PLAYER || grid.getContents(posX,posY - enemyDirectionY) == Cell.BRICK ){
				tracker.setyDirection(-enemyDirectionY); 
				enemyDirectionY = tracker.getyDirection();
				canMoveInY = true;
			}
			

		
			// x% chance randChance is 1, and check if at intersection)
			if(randChance == 1 && canMoveInX && canMoveInY){
				if(tracker.isMovingInX()){
					canMoveInX = false;
					//System.out.println("PLS");
					
				}
				else if(tracker.isMovingInY()){
					canMoveInY = false;
				}
				else{
					System.out.println("HOW");
				}
			}
			
			if(canMoveInX){
				
				if(grid.getContents(posX + enemyDirectionX, posY) == Cell.PLAYER){
					grid.setContents(posX + enemyDirectionX, posY, tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					render.setIsPlayerAlive(false);
				}
				
				if(grid.getContents(posX, posY) == Cell.KONDORIAANDBRICK || grid.getContents(posX, posY) == Cell.OVAPIANDBRICK || grid.getContents(posX, posY) == Cell.PONTANANDBRICK){
					grid.setContents(posX,posY,Cell.BRICK);
				}
				
				else{
					grid.setContents(posX, posY, Cell.EMPTY);

				}
				
				if(grid.getContents(posX + enemyDirectionX, posY) == Cell.BRICK){
						switch(tracker.getEnemyType()){
						case KONDORIA:
							grid.setContents(posX + enemyDirectionX, posY, Cell.KONDORIAANDBRICK);
							tracker.setEnemyType(Cell.KONDORIAANDBRICK);
							break;
						case OVAPI:
							grid.setContents(posX + enemyDirectionX, posY, Cell.OVAPIANDBRICK);
							tracker.setEnemyType(Cell.OVAPIANDBRICK);

							break;
						case PONTAN:
							grid.setContents(posX + enemyDirectionX, posY, Cell.PONTANANDBRICK);
							tracker.setEnemyType(Cell.PONTANANDBRICK);
							break;
						case KONDORIAANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Cell.KONDORIAANDBRICK);
							tracker.setEnemyType(Cell.KONDORIAANDBRICK);
							break;
						case OVAPIANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Cell.OVAPIANDBRICK);
							tracker.setEnemyType(Cell.OVAPIANDBRICK);

							break;
						case PONTANANDBRICK:
							grid.setContents(posX + enemyDirectionX, posY, Cell.PONTANANDBRICK);
							tracker.setEnemyType(Cell.PONTANANDBRICK);
							break;
							
						default:
							break;
					}
																	
				}
				
				else{
					// we've set initial pos to brick or empty, next pos tile isnt a brick, so if kondoriaandbrick ... we set back to kondoria
					switch(tracker.getEnemyType()){
					case KONDORIAANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY, Cell.KONDORIA);
						tracker.setEnemyType(Cell.KONDORIA);
						break;
					case OVAPIANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY, Cell.OVAPI);
						tracker.setEnemyType(Cell.OVAPI);

						break;
					case PONTANANDBRICK:
						grid.setContents(posX + enemyDirectionX, posY, Cell.PONTAN);
						tracker.setEnemyType(Cell.PONTAN);

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
				
				if(grid.getContents(posX, posY  + enemyDirectionY) == Cell.PLAYER){
					grid.setContents(posX, posY  + enemyDirectionY, tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					render.setIsPlayerAlive(false);
				}
				
				if(grid.getContents(posX, posY) == Cell.KONDORIAANDBRICK || grid.getContents(posX, posY) == Cell.OVAPIANDBRICK || grid.getContents(posX, posY) == Cell.PONTANANDBRICK){
					grid.setContents(posX,posY,Cell.BRICK);
				}
				
				else{
					grid.setContents(posX, posY, Cell.EMPTY);

				}
				
				
				if(grid.getContents(posX, posY  + enemyDirectionY) == Cell.PLAYER){
					grid.setContents(posX, posY  + enemyDirectionY, tracker.getEnemyType());
					GameState.setState(State.PLAYERDEAD);
					render.setIsPlayerAlive(false);
				}
				
					
				else if(grid.getContents(posX, posY + enemyDirectionY) == Cell.BRICK){
					switch(tracker.getEnemyType()){
					case KONDORIA:
						grid.setContents(posX, posY + enemyDirectionY, Cell.KONDORIAANDBRICK);
						tracker.setEnemyType(Cell.KONDORIAANDBRICK);
						break;
					case OVAPI:
						grid.setContents(posX, posY + enemyDirectionY, Cell.OVAPIANDBRICK);
						tracker.setEnemyType(Cell.OVAPIANDBRICK);
						break;
					case PONTAN:
						grid.setContents(posX, posY + enemyDirectionY, Cell.PONTANANDBRICK);
						tracker.setEnemyType(Cell.PONTANANDBRICK);	
						break;
					case KONDORIAANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Cell.KONDORIAANDBRICK);
						tracker.setEnemyType(Cell.KONDORIAANDBRICK);
						break;
					case OVAPIANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Cell.OVAPIANDBRICK);
						tracker.setEnemyType(Cell.OVAPIANDBRICK);
						break;
					case PONTANANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Cell.PONTANANDBRICK);
						tracker.setEnemyType(Cell.PONTANANDBRICK);	
						break;				
					default:
						break;
					}
																		
				}

				else{
					
					switch(tracker.getEnemyType()){
					case KONDORIAANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Cell.KONDORIA);
						tracker.setEnemyType(Cell.KONDORIA);
						break;
					case OVAPIANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Cell.OVAPI);
						tracker.setEnemyType(Cell.OVAPI);
						break;
					case PONTANANDBRICK:
						grid.setContents(posX, posY + enemyDirectionY, Cell.PONTAN);
						tracker.setEnemyType(Cell.PONTAN);	
						break;
					default:
						break;
					}
					
					
					grid.setContents(posX, posY + enemyDirectionY, tracker.getEnemyType());
					//System.out.println(tracker.getEnemyType());
				}
				
				
				tracker.setyPosition(posY + enemyDirectionY);
				tracker.setMovingInY(true);
				tracker.setMovingInX(false);
				


			}
			
		}
		
		
		
	}

	public void setIsExitwayBlownUp(boolean b) {
		this.isExitwayBlownUp = b;
		
	}
	
	

}
