package gameplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class GameTimer implements ActionListener{

    private static int count = 200;
    private int lives = 2;
    private String over = "Time Over!";
    private JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private int score = 0;
    private Bomb bomb;
    private Game game;

    public GameTimer(JLabel label, JLabel label2, JLabel label3, JLabel label4){
        this.label = label;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
//        this.lives = lives;
//        this.score = score;
    }

    public void actionPerformed(ActionEvent e) {
    	if(count != 0){
    		if(GameState.getState() != State.PAUSE || game.getTimeOverState() == true){
        count--;
    		}
    	}
        label.setText("Time Left: " + count);
        if(count == 0){
        	setTimeOver(over);
        	//System.exit(1);
        	if (GameState.getState() == State.RUNNING) {
				GameState.setState(State.TIMEOVER);
			} 
        }
    }
    
    public void setLives(int livesLeft){
    	this.lives = livesLeft;
    	label2.setText("Lives Left: " + lives);
    }
    
    public static void restartTimer(){
    	count = 200;
    }
    public void setScore(int score){
    	this.score = score;
    	label3.setText("Score: " + score);
    }
    public void setTimeOver(String over){
    	this.over = over;
    	label4.setText("Time Over");
    }
}