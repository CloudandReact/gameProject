package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.csv.CSVRecord;

import gameplay.Bomberman;
import gameplay.Tile;
import gameplay.Grid;
import gameplay.PlayerInfo;

public class LoadGame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel noGamesFound = new JLabel("Error no loaded games please go back");

	JButton backButton = new JButton("Back");
	JButton playButton = new JButton("Play");

	// load how many games then have a textbox to enter
	JTextField gameToPlayText = new JTextField();
	JPanel panelA;
	StoreStatistics checkStats = new StoreStatistics();
	int numberOfGames;
	ArrayList<CSVRecord> loadedGameArrayList;
	//grid loadedGrid
	Grid loadedGrid = new Grid();

	public LoadGame(JPanel panel) throws IOException {
		checkStats.checkNumberOfGames();
		panel.removeAll();
		
		panel.setLayout(null);
		//open statistics read user games and display
		numberOfGames=checkStats.numberOfGames();
		if(numberOfGames>0) 
			{ 
			JLabel gameNumberLabel= new JLabel("Enter game name");
			gameNumberLabel.setBounds(40,60,120,25);
			panel.add(gameNumberLabel);
			gameToPlayText.setBounds(165,60,80,25);
			playButton.setBounds(165,105,80,25);
			backButton.setBounds(80, 105, 80, 25);
			panel.add(backButton);
			panel.add(playButton);
			panel.add(gameToPlayText);
			}
		else {
			JLabel gameNumberLabel= new JLabel("No game saved please go back");
			gameNumberLabel.setBounds(40,60,190,25);
			
			backButton.setBounds(80, 105, 80, 25);
			panel.add(backButton);
			panel.add(gameNumberLabel);
			
			}
		
		AccountMenu.setFrameTitle("Load Game");
		
		
		panel.repaint();
		panelA = panel;
		
		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				getContentPane().removeAll();
				new MainMenu(panelA,PlayerInfo.usernameStatic);
			}
		});
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					// Execute when button is pressed
					getContentPane().removeAll();
					String gameName=gameToPlayText.getText();
					File gameFile= new File(gameName);
					if(gameFile.exists()){
						FileWriting openGameFile= new FileWriting();
						openGameFile.loadGame(gameName);
						loadedGameArrayList=openGameFile.loadedGame();
						int playerLevel;
						int playerScore;
						//change loaded game to type Tile
						for(int i=0;i<31;i++){
							for(int j=0;j<13;j++){
								//change to multiply...
								
								//System.out.println(Tile.valueOf(loadedGameArrayList.get(i).get(0))+ "  C1`134Type");
								
								System.out.println(Tile.valueOf(loadedGameArrayList.get(i+31*j).get(0)));
								//loadedGrid.setContents(i+j*31, j, Tile.CONCRETE);
								loadedGrid.setContents(i,j, Tile.valueOf(loadedGameArrayList.get(i+31*j).get(0)));
							}
							
						}
						//player lives
						playerLevel=Integer.parseInt(loadedGameArrayList.get(403).get(0));
						playerScore=Integer.parseInt(loadedGameArrayList.get(404).get(0));
						//load the game
						getContentPane().removeAll();
						new Bomberman(loadedGrid,playerLevel);
						AccountMenu.destroyFrame();
					}
					else{
						//error
						JOptionPane.showMessageDialog(null,"Please enter correct fileName not found ", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
					
					//new Game()
				}
				
			
			
	
	
		});
		panel.setVisible(true);
		
		
		
		
}
	public Tile setGridType(String positionType)
	{
		
		System.out.println(positionType);
		Tile typeOfTile=Tile.valueOf(positionType);
		System.out.println(typeOfTile +"Tiletype");
		return typeOfTile;
		/*if(positionType.equals("CONCRETE")){
			typeOfTile=Tile.CONCRETE;
		}
		else if(positionType.equals("BRICK")){
			typeOfTile=Tile.BRICK;
		}
		else if(positionType.equals("PLAYER")){
			typeOfTile=Tile.PLAYER;
		}
		else if(positionType.equals("EMPTY")){
			typeOfTile=Tile.EMPTY;
		}
		else if(positionType.equals("BOMB")){
			typeOfTile=Tile.BOMB;
		}
		else if(positionType.equals("EXITWAY")){
			typeOfTile=Tile.EXITWAY;
		}
		else if(positionType.equals("EXITWAY")){
			typeOfTile=Tile.EXITWAY;
		}
		else if(positionType.equals("EXITWAY")){
			typeOfTile=Tile.EXITWAY;
		}
		else if(positionType.equals("EXITWAY")){
			typeOfTile=Tile.EXITWAY;
		}
		return typeOfTile;
		PLAYERANDBOMB*/
	
}
	}


