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
		setDirection();
		stepUntilNextTurn--;
		
		x = x + dx * GHOST_SPEED;
		y = y + dy * GHOST_SPEED;
	}

	private void setDirection() {
		
		if ((stepUntilNextTurn < 1) ) {
			stepUntilNextTurn = RandomUtil.getMinMax(0, 700 / GHOST_SPEED);
			
			int newDirection = RandomUtil.getMinMax(1,4);
			dx=0;
			dy=0;
			switch (newDirection) {
				case 1:
					dx=1;
					break;
				case 2:
					dx=-1;
					break;
				case 3:
					dy=1;
					break;
				case 4:
					dy=-1;
			}
		}
		
		if ((x>BOARD_SIZE_X || x<BOARD_SIZE_X) && dx != 0) {
			dx = dx * -1;
		}
		if ((y>BOARD_SIZE_Y  || y<BOARD_SIZE_Y) && dy != 0) {
			dy = dy * -1;
		}

		
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(getImage(), getX(), getY(), null);
	}
}
