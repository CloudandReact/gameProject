package gameplay;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;

import menu.AccountMenu;

public class Bomberman extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 31;
	public static int HEIGHT = 13;
	public static int TILE_SIZE = 25;
	public Player player;
	private GameTimer listener;
	private JFrame frameTimer = new JFrame("Bomberman");
	
	public Bomberman() {
		
        frameTimer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTimer.setLayout(new FlowLayout());
        JPanel timePanel = new JPanel();
        JPanel livesPanel = new JPanel();
        JPanel scorePanel = new JPanel();
        JPanel timeOverPanel = new JPanel();
        JLabel timeLabel = new JLabel("Time Left: 200");
        JLabel livesLabel = new JLabel("Lives Left: 2");
        JLabel scoreLabel = new JLabel("Score: 0");
        JLabel timeOverLabel = new JLabel("");
        timePanel.add(timeLabel);
        livesPanel.add(livesLabel);
        scorePanel.add(scoreLabel);
        timeOverPanel.add(timeOverLabel);
        frameTimer.add(timePanel, BorderLayout.NORTH);
        frameTimer.add(livesPanel, BorderLayout.SOUTH);
        frameTimer.add(scorePanel, BorderLayout.SOUTH);
        frameTimer.add(timeOverPanel, BorderLayout.SOUTH);
        //frameTimer.add(label);

        //pass the label into the MyListener constructor
        int lives = Player.getLivesLeft();
        listener = new GameTimer(timeLabel, livesLabel, scoreLabel, timeOverLabel);

        //the timer fires every 1000 MS (1 second)
        //when it does, it calls the actionPerformed() method of MyListener
        Timer timerG = new Timer(1000, listener);

        //start the timer
        timerG.start();
        
        frameTimer.setSize(300, 100);
        frameTimer.setVisible(true);
		
		add(new Game(this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(15 * TILE_SIZE, HEIGHT * TILE_SIZE + 22);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
		
		
	}
	
	public void setLivesLeft(int lives){
		listener.setLives(lives);
	}
	public void setScore(int score){
		listener.setScore(score);
	}
	public void setGameOver(){
		listener.setTimeOver("Time Over");
	}
	public Bomberman(int level){
		//JFrame frameTimer = new JFrame("Bomberman");
        frameTimer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTimer.setLayout(new FlowLayout());
        JPanel timePanel = new JPanel();
        JPanel livesPanel = new JPanel();
        JPanel scorePanel = new JPanel();
        JPanel timeOverPanel = new JPanel();
        JLabel timeLabel = new JLabel("Time Left: 200");
        JLabel livesLabel = new JLabel("Lives: 2");
        JLabel scoreLabel = new JLabel("Score: 0");
        JLabel timeOverLabel = new JLabel("");
        timePanel.add(timeLabel);
        livesPanel.add(livesLabel);
        scorePanel.add(scoreLabel);
        timeOverPanel.add(timeOverLabel);
        frameTimer.add(timePanel, BorderLayout.NORTH);
        frameTimer.add(livesPanel, BorderLayout.SOUTH);
        frameTimer.add(scorePanel, BorderLayout.SOUTH);
        frameTimer.add(timeOverPanel, BorderLayout.SOUTH);
        //frameTimer.add(label);

        //pass the label into the MyListener constructor
        listener = new GameTimer(timeLabel,livesLabel,scoreLabel, timeOverLabel);

        //the timer fires every 1000 MS (1 second)
        //when it does, it calls the actionPerformed() method of MyListener
        Timer timerG = new Timer(1000, listener);

        //start the timer
        timerG.start();
        
        frameTimer.setSize(300, 100);
        frameTimer.setVisible(true);
		
		add(new Game(level, this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(15 * TILE_SIZE, HEIGHT * TILE_SIZE + 22);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
		
		
	}
	//changes
	public Bomberman(Grid grid, int level){
		add(new Game(grid,level, this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(15 * TILE_SIZE, HEIGHT * TILE_SIZE + 22);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(false);
		setVisible(true);
		
	}
	
	public void destroyFrame(){
		setVisible(false);
		dispose();
	}
		
	/**
	 * Destroys the timer frame
	 */
	public void destroyFrameTimer(){
		frameTimer.setVisible(false);
		frameTimer.dispose();
	}
	
	public static void main(String[] args) {
		new Bomberman();
	}

}
