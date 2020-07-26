package eu.olssonfamily.pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eu.olssonfamily.pacman.sprite.Ghost;
import eu.olssonfamily.pacman.sprite.Pacman;
import eu.olssonfamily.pacman.trackers.HighScore;
import eu.olssonfamily.pacman.trackers.Lives;
import eu.olssonfamily.pacman.trackers.Score;
import eu.olssonfamily.pacman.PacmanConstants;
import eu.olssonfamily.pacman.maze.Dots;
import eu.olssonfamily.pacman.maze.Maze;
import eu.olssonfamily.pacman.maze.PowerDots;

public class PacmanModel implements ActionListener {
	
	Tick pacmanEvent = new Tick(10);
	Lives lives = new Lives(3);
	Score score = new Score();
	HighScore highScore = new HighScore();
	Ghost ghost = new Ghost(0, 0);
	Pacman pacman = new Pacman(PacmanConstants.PACMAN_START_X_POS, PacmanConstants.PACMAN_START_Y_POS, lives, score);
	Ghost[] ghosts= {new Ghost(11, 10), new Ghost(12, 10), new Ghost(14, 10), new Ghost(15, 10)};
	static Maze maze = new Maze();
	Dots dots = new Dots(GAME_HEIGHT, GAME_WIDTH);
	PowerDots powerDots = new PowerDots(GAME_HEIGHT, GAME_WIDTH);
	static boolean stateGameIsOn = false;
	final static int GAME_HEIGHT = 24;
	final static int GAME_WIDTH = 27;
	
	public PacmanModel() {
		pacmanEvent.addActionListener(this);
	}

	
	public static void startGame() {
		stateGameIsOn = true;
	}
	
	public static void stopGame() {
		stateGameIsOn = false;
	}
	
	
	Pacman getPacman() {
		return pacman;
	}

	Ghost[] getGhosts() {
		return ghosts;
	}
	
	public static Maze getMaze() {
		return maze;
	}

	Dots getDots() {
		return dots;
	}
	
	PowerDots getPowerDots() {
		return powerDots;
	}
	
	Lives getLives() {
		return lives;
	}
	
	Score getScore() {
		return score;
	}
	
	HighScore getHighScore() {
		return highScore;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (stateGameIsOn) {
			pacman.eatDotIfDotHit();
			pacman.eatAndChangeStateIfPowerDotHit();
			pacman.teleportIfPossible();
			pacman.setFrontCoordinates();
			pacman.move();
			pacman.stopIfHitGhost(ghosts);
			for (Ghost ghost : ghosts) {
				ghost.teleportIfPossible();
				ghost.setFrontCoordinates();
				ghost.move();
			}
			checkGameState();
			if (maze.dotsToEat == 0) {
				stateGameIsOn = false;
				resetGame();
			}
		}
	}
	
	private void checkGameState () {
		if (!stateGameIsOn) {
			if(lives.getLives() > 0) {
				restartGame();
			} else {
				highScore.updateHighScore(score.getScore());
			}
		}
	}
	
	private void restartGame() {
		pacman.goToStartPosition();
		for (Ghost ghost : ghosts) {
			ghost.goToStartPosition();
		}
	}
	
	private void resetGame() {
		maze.resetMaze();
		restartGame();
	}
	
}
