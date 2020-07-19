package eu.olssonfamily.pacman.sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;

import eu.olssonfamily.pacman.util.RandomUtil;

public class Ghost extends Sprite {

	int stepUntilNextTurn = 0;
	// private int dx = 1;
	// private int dy = 0;
	final int GHOST_SPEED = 1;
	
	private final int startX; 
	private final int startY;

	public Ghost(int x, int y) {
		super(x, y);
		loadImage("images/pacman-ghost.png");
		getImageDimensions();
		startX = x;
		startY = y;
	}
	
	public void becomeEaten() {
		goToStartPosition();
	}

	public void move() {
		super.changeDirectionIfMazeHit();
		x = x + dx * GHOST_SPEED;
		y = y + dy * GHOST_SPEED;

		setRandomDirection();
	}

	private void setRandomDirection() {
		stepUntilNextTurn--;

		if ((stepUntilNextTurn < 1)) {
			// just a reasonable random number of steps until next turn.
			stepUntilNextTurn = RandomUtil.getMinMax(0, WINDOW_WIDTH / 3 / GHOST_SPEED);
			//System.out.println("Number of steps until next new direction = " + stepUntilNextTurn);

			int newDirection = RandomUtil.getMinMax(1, 4);
			dx = 0;
			dy = 0;
			switch (newDirection) {
			case 1:
				dx = 1;
				break;
			case 2:
				dx = -1;
				break;
			case 3:
				dy = 1;
				break;
			case 4:
				dy = -1;
			}

			//System.out.println("New direction dx=" + dx + "  dy=" + dy);
		}
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(getImage(0), getX(), getY(), null);
	}
}
