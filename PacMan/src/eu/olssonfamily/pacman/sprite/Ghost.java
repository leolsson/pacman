package eu.olssonfamily.pacman.sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;

import eu.olssonfamily.pacman.util.RandomUtil;

public class Ghost extends Sprite {

	int stepUntilNextTurn = 0;
	private int dx = 1;
	private int dy = 0;
	final int GHOST_SPEED = 10;
	final int BOARD_SIZE_X = 700;
	final int BOARD_SIZE_Y = 700;

	public Ghost(int x, int y) {
		super(x, y);
		loadImage("images/pacman-ghost.png");
		getImageDimensions();
	}

	public void move() {
		setRandomDirection();
		changeXdirectionIfXBoarderHit();
		changeYDirectionIfYBoarderHit();

		x = x + dx * GHOST_SPEED;
		y = y + dy * GHOST_SPEED;
	}

	private void setRandomDirection() {
		stepUntilNextTurn--;

		if ((stepUntilNextTurn < 1)) {
			// just a reasonable random number of steps until next turn.
			stepUntilNextTurn = RandomUtil.getMinMax(0, BOARD_SIZE_X / 3 / GHOST_SPEED);
			System.out.println("Number of steps until next new direction = " + stepUntilNextTurn);

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

			System.out.println("New direction dx=" + dx + "  dy=" + dy);
		}
	}

	private void changeYDirectionIfYBoarderHit() {
		if ((y > (BOARD_SIZE_Y - 16) || y < 0) && dy != 0) {
			System.out.println("Hit Y  boarder");
			dy = dy * -1;
		}
	}

	private void changeXdirectionIfXBoarderHit() {
		if ((x > BOARD_SIZE_X - 16 || x < 0) && dx != 0) {
			System.out.println("Hit X  boarder");
			dx = dx * -1;
		}
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(getImage(), getX(), getY(), null);
	}
}
