package gameplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JLabel;

/**
 * <p>
 * The <code>GameTimer</code> keeps track of time during the game to determine
 * when the game should end.
 * </p>
 * 
 * @author Rakinul Huq
 */

public class GameTimer implements ActionListener, Serializable {


	private static final long serialVersionUID = 1L;
	private int lives = 2;
	private int score;

	public static int timeCount = 200;
	private String over = "Time Over!";
	private JLabel timeLabel;
	private JLabel livesLabel;
	private JLabel scoreLabel;
	private JLabel timeOverLabel;

	/**
	 * <p>
	 * Constructs an object of type <code>GameTimer</code> which counts down the
	 * time remaining for each game.
	 * </p>
	 * 
	 * @param timeLabel
	 *            the label for the time count
	 * @param livesLabel
	 *            the label for the number of lives left
	 * @param scoreLabel
	 *            the label for the players score
	 * @param timeOverLabel
	 *            the label to show show "Time Over" when time count becomes 0
	 */
	public GameTimer(JLabel timeLabel, JLabel livesLabel, JLabel scoreLabel,
			JLabel timeOverLabel) {
		this.timeLabel = timeLabel;
		this.livesLabel = livesLabel;
		this.scoreLabel = scoreLabel;
		this.timeOverLabel = timeOverLabel;
	}

	/**
	 * Counts down time as long as the timer has not reached zero and that the
	 * game is not paused. Displays a message when the timer does eventually
	 * reach zero.
	 */
	public void actionPerformed(ActionEvent e) {
		if (timeCount != 0) {
			if (GameState.getState() != State.PAUSE) {
				if (Player.getLivesLeft() != 0) {
					timeCount--;
				}

			}
		}
		timeLabel.setText("Time Left: " + timeCount);

		if (timeCount == 0) {
			setTimeOver(over);
			if (GameState.getState() == State.RUNNING) {
				GameState.setState(State.TIMEOVER);
			}
		}
	}

	/**
	 * Sets the player's number of lives remaining.
	 * 
	 * @param livesLeft
	 *            Number of lives remaining
	 */
	public void setLives(int livesLeft) {
		this.lives = livesLeft;
		livesLabel.setText("Lives Left: " + lives);
	}

	/**
	 * Restarts the timer.
	 */
	public static void restartTimer() {
		timeCount = 200;
	}

	/**
	 * Sets the player's score to an integer value.
	 * 
	 * @param score
	 *            player's updated score
	 */

	public void setScore(int score) {
		this.score = score;
		scoreLabel.setText("Score: " + score);
	}

	/**
	 * Sets the timer to zero and displays
	 * 
	 * @param over
	 */
	public void setTimeOver(String over) {
		this.over = over;
		timeOverLabel.setText("Time Over");
	}
}
