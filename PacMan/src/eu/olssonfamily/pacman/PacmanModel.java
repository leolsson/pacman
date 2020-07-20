package eu.olssonfamily.pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eu.olssonfamily.pacman.sprite.Ghost;
import eu.olssonfamily.pacman.sprite.Pacman;
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
	Pacman pacman = new Pacman(PacmanConstants.PACMAN_START_X_POS, PacmanConstants.PACMAN_START_Y_POS, lives, score);
	Ghost ghost = new Ghost(150, 150);
	Ghost ghost1 = new Ghost(150, 150);
	Maze maze = new Maze();
	Dots dots = new Dots(maze, GAME_HEIGHT, GAME_WIDTH);
	PowerDots powerDots = new PowerDots(maze, GAME_HEIGHT, GAME_WIDTH);
	static boolean stateGameIsOn = false;
	final static int GAME_HEIGHT = 20;
	final static int GAME_WIDTH = 30;
	
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

	Ghost getGhost() {
		return ghost;
	}
	
	Ghost getGhost1() {
		return ghost1;
	}
	
	Maze getMaze() {
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (stateGameIsOn) {
			pacman.eatDotIfDotHit();
			pacman.eatAndChangeStateIfPowerDotHit();
			pacman.teleportIfPossible();
			pacman.setFrontCoordinates();
			pacman.move();
			pacman.stopIfHitGhost(new Ghost[] {ghost, ghost1});
			ghost.teleportIfPossible();
			ghost.setFrontCoordinates();
			ghost.move();
			ghost1.teleportIfPossible();
			ghost1.setFrontCoordinates();
			ghost1.move();
			checkGameState();
		}
	}
	
	private void checkGameState () {
		if (!stateGameIsOn) {
			if(lives.getLives() > 0) {
				restartGame();
			} else {
				
			}
		}
	}
	
	private void restartGame() {
		pacman.goToStartPosition();
		ghost.goToStartPosition();
		ghost1.goToStartPosition();
	}
	
}
