package gameplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class GameTimer implements ActionListener{

    private static int timeCount = 200;
    private int lives = 2;
    private String over = "Time Over!";
    private JLabel timeLabel;
    private JLabel livesLabel;
    private JLabel scoreLabel;
    private JLabel timeOverLabel;
    private int score = 0;
    private Bomb bomb;
    private Game game;

    /**
     * GameTimer constructor 
     * @param timeLabel is the label for the time count
     * @param livesLabel is the label for the number of lives left
     * @param scoreLabel is the label for the players score
     * @param timeOverLabel is the label to show show "Time Over" when time count becomes 0
     */
    public GameTimer(JLabel timeLabel, JLabel livesLabel, JLabel scoreLabel, JLabel timeOverLabel){
        this.timeLabel = timeLabel;
        this.livesLabel = livesLabel;
        this.scoreLabel = scoreLabel;
        this.timeOverLabel = timeOverLabel;
    }

    /**
     * 
     */
    public void actionPerformed(ActionEvent e) {
    	if(timeCount != 0){
    		if(GameState.getState() != State.PAUSE ){
    			if(Player.getLivesLeft() != 0){
    				timeCount--;
    			}
        
    		}
    	}
        timeLabel.setText("Time Left: " + timeCount);
        if(timeCount == 0){
        	setTimeOver(over);
        	if (GameState.getState() == State.RUNNING) {
				GameState.setState(State.TIMEOVER);
			} 
        }
    }
    
    public void setLives(int livesLeft){
    	this.lives = livesLeft;
    	livesLabel.setText("Lives Left: " + lives);
    }
    
    public static void restartTimer(){
    	timeCount = 200;
    }
    
    public void setScore(int score){
    	this.score = score;
    	scoreLabel.setText("Score: " + score);
    }
    
    public void setTimeOver(String over){
    	this.over = over;
    	timeOverLabel.setText("Time Over");
    }
}