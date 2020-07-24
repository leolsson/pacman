package eu.olssonfamily.pacman.sprite;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import eu.olssonfamily.pacman.util.Measure;
import eu.olssonfamily.pacman.util.ImageModifier;
import eu.olssonfamily.pacman.PacmanConstants;
import eu.olssonfamily.pacman.PacmanModel;
import eu.olssonfamily.pacman.Square;
import eu.olssonfamily.pacman.Tick;
import eu.olssonfamily.pacman.maze.Dots;
import eu.olssonfamily.pacman.maze.Maze;
import eu.olssonfamily.pacman.maze.PowerDots;
import eu.olssonfamily.pacman.trackers.Lives;
import eu.olssonfamily.pacman.trackers.Score;

public class Pacman extends Sprite implements ActionListener {

	final int PACMAN_SPEED = 1;
	Lives lives;
	Score score;
	int pacmanImageIndex = 0;
	boolean stateCanEatGhosts = false;
	Tick pacmanImageUpdate = new Tick(100);
	float pacmanImageBrightness = 1f;

	public Pacman(int x, int y, Lives lives, Score score) {
		super(x, y);
		loadImage("images/pacman.png", "images/pacman-halfopen.png", "images/pacman-open.png",
				"images/pacman-halfopen.png");
		getImageDimensions();
		pacmanImageUpdate.addActionListener(this);
		this.lives = lives;
		lives.createLivesImage("images/pacman-halfopen.png");
		this.score = score;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updatePacmanImageIndex();
		if (stateCanEatGhosts) {
			makePacmanBlink();
		} else {
			pacmanImageBrightness = 1f;
		}
	}

	private void updatePacmanImageIndex() {
		if (pacmanImageIndex < images.size() - 1) {
			pacmanImageIndex++;
		} else {
			pacmanImageIndex = 0;
		}
	}
	
	private void makePacmanBlink() {
		if (pacmanImageBrightness == 1) {
			pacmanImageBrightness = 0.7f;
		} else {
			pacmanImageBrightness = 1f;
		}
	}

	public void drawPacman(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			Image pacmanImage = ImageModifier.setBrightness(ImageModifier.rotate(getImage(pacmanImageIndex), getDirection()), pacmanImageBrightness);
			g2.drawImage(pacmanImage, getX(), getY(), null);
	}

	public void move() {
		super.changeDirectionIfMazeHit();
		x = x + dx * PACMAN_SPEED;
		y = y + dy * PACMAN_SPEED;
	}

	public void changeDirection(KeyEvent e) {
		
		PacmanModel.startGame();

		int key = e.getKeyCode();

		dx = 0;
		dy = 0;

		switch (key) {
		case KeyEvent.VK_LEFT:
			dx = -1;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 1;
			break;
		case KeyEvent.VK_UP:
			dy = -1;
			break;
		case KeyEvent.VK_DOWN:
			dy = 1;
		}
	}
	

	public void stopIfHitGhost(Ghost[] ghosts) {
		for (Ghost ghost : ghosts) {
			if (Measure.getDistance(x, y, ghost.getX(), ghost.getY()) < width) {
				if (stateCanEatGhosts) {
					eatGhost(ghost);
				} else {
					lives.updateLives();
					PacmanModel.stopGame();
				}
			}
		}
	}

	private void eatGhost(Ghost ghost) {
		ghost.becomeEaten();
		score.addToScore(1);
	}

	public void eatAndChangeStateIfPowerDotHit() {
		if (getSquareType(frontX[0], frontY[0]) == 3) {
			activatePowerWithTimer();
			PacmanModel.getMaze().updateMaze(frontX[0] / Square.getSquareWidth(), frontY[0] / Square.getSquareHeight(), 0);
		}
	}

	private void activatePowerWithTimer() {
		stateCanEatGhosts = true;
		Timer countdown = new Timer(10 * PacmanConstants.ONE_SECOND, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stateCanEatGhosts = false;
			}
		});
		countdown.start();
		countdown.setRepeats(false);
	}

	public void eatDotIfDotHit() {
		if (getSquareType(getPacmanCenterX(x), getPacmanCenterY(y)) == 2) {
			score.addToScore(1);
			PacmanModel.getMaze().updateMaze(getPacmanCenterX(x) / Square.getSquareWidth(), getPacmanCenterY(y) / Square.getSquareHeight(), 0);
		}
	}

	private int getPacmanCenterX(int x) {
		return x + Square.getSquareWidth() / 2;
	}

	private int getPacmanCenterY(int y) {
		return y + Square.getSquareWidth() / 2;
	}
}
