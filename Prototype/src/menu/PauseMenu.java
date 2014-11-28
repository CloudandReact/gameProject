package menu;

import gameplay.Bomberman;
import gameplay.GameState;
import gameplay.Grid;
import gameplay.Player;
import gameplay.PlayerInfo;
import gameplay.Render;
import gameplay.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PauseMenu {
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton viewLeaderboards = new JButton("View Leaderboards");
	JButton saveGame = new JButton("Save Game");
	JButton quitGame = new JButton("Quit Game");
	JButton exitToMain = new JButton("Exit to main menu");
	JButton resumeGame = new JButton("Resume game");

	//JButton loadLevelButton = new JButton("Load Level");
	Render render;
	GameState state;
	Player player;
	Grid grid;
	
	String playersName;
	
	public PauseMenu(Grid grid,  GameState s, Player p, final Bomberman bomberman){
		this.state = s; 
		this.grid = grid;
		this.player = p;
		
		frame.setSize(325, 230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		panel.removeAll();
		panel.setLayout(null);
		AccountMenu.setFrameTitle("Pause Menu");
		
		viewLeaderboards.setBounds(72, 5, 180, 25);
		panel.add(viewLeaderboards);
		
		saveGame.setBounds(72, 30, 180, 25);
		panel.add(saveGame);
		
		quitGame.setBounds(72, 55, 180, 25);
		panel.add(quitGame);
		
		exitToMain.setBounds(72, 80, 180, 25);
		panel.add(exitToMain);
		
		resumeGame.setBounds(72, 105, 180, 25);
		panel.add(resumeGame);
		
		quitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				AccountMenu.destroyFrame();
				System.exit(0);	
			}
			
		});
		
		
		exitToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				//getContentPane().removeAll();
				
				render.destroyPanel();
				new MainMenu(panel, PlayerInfo.getUsername());
				bomberman.dispose();//frame.destroyFrame();
					
				
			}
			
		});
		
		
		viewLeaderboards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				//getContentPane().removeAll();
				new Leaderboards();
									
			}
			
		});
		
		resumeGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				frame.getContentPane().removeAll();
				frame.dispose();
				player.initializeTimer();
				render.setPauseMenuState(false);
				state.setState(State.RUNNING);
				
				
			}
			
		});
		
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				render.destroyPanel();
				new MainMenu(panel, PlayerInfo.getUsername());
				bomberman.dispose();//frame.destroyFrame();
				
//				try {
//					//new SaveGame();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
				
			}
			
		});
		
		
		
	}
	
	
	
	
	
}
