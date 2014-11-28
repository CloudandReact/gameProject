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

	public Bomberman() {
		JFrame frameTimer = new JFrame("Counter");
        frameTimer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTimer.setLayout(new FlowLayout());
        JPanel pane1 = new JPanel();
        JPanel pane2 = new JPanel();
        JPanel pane3 = new JPanel();
        JPanel pane4 = new JPanel();
        JLabel label = new JLabel("Time Left: 200");
        JLabel label2 = new JLabel("Lives Left: 2");
        JLabel label3 = new JLabel("Score: 0");
        JLabel label4 = new JLabel("");
//        int lives = player.getLivesLeft();
//        label2.setText("Lives: " + lives);
        //p.setLayout(new FlowLayout()); 
        //p.add(lab1 = new JLabel("add JLabel"));
        //frame.add(label);
        pane1.add(label);
        pane2.add(label2);
        pane3.add(label3);
        pane4.add(label4);
        frameTimer.add(pane1, BorderLayout.NORTH);
        frameTimer.add(pane2, BorderLayout.SOUTH);
        frameTimer.add(pane3, BorderLayout.SOUTH);
        frameTimer.add(pane4, BorderLayout.SOUTH);
        //frameTimer.add(label);

        //pass the label into the MyListener constructor
        int lives = Player.getLivesLeft();
        listener = new GameTimer(label, label2, label3, label4);

        //the timer fires every 1000 MS (1 second)
        //when it does, it calls the actionPerformed() method of MyListener
        Timer timerG = new Timer(1000, listener);

        //start the timer
        timerG.start();
        
        frameTimer.setSize(270, 100);
        frameTimer.setVisible(true);
		
		add(new Render(this));
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
		JFrame frameTimer = new JFrame("Counter");
        frameTimer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTimer.setLayout(new FlowLayout());
        JPanel pane1 = new JPanel();
        JPanel pane2 = new JPanel();
        JPanel pane3 = new JPanel();
        JPanel pane4 = new JPanel();
        JLabel label = new JLabel("Time Left: 200");
        JLabel label2 = new JLabel("Lives: 2");
        JLabel label3 = new JLabel("Score: 0");
        JLabel label4 = new JLabel("");
//        int lives = Player.getLivesLeft();
//        label2.setText("Lives: " + lives);
        //p.setLayout(new FlowLayout()); 
        //p.add(lab1 = new JLabel("add JLabel"));
        //frame.add(label);
        pane1.add(label);
        pane2.add(label2);
        pane3.add(label3);
        pane4.add(label4);
        frameTimer.add(pane1, BorderLayout.NORTH);
        frameTimer.add(pane2, BorderLayout.SOUTH);
        frameTimer.add(pane3, BorderLayout.SOUTH);
        frameTimer.add(pane4, BorderLayout.SOUTH);
        //frameTimer.add(label);

        //pass the label into the MyListener constructor
        listener = new GameTimer(label,label2,label3, label4);

        //the timer fires every 1000 MS (1 second)
        //when it does, it calls the actionPerformed() method of MyListener
        Timer timerG = new Timer(1000, listener);

        //start the timer
        timerG.start();
        
        frameTimer.setSize(270, 100);
        frameTimer.setVisible(true);
		
		add(new Render(level, this));
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
		
	public static void main(String[] args) {
		new Bomberman();
	}

}
