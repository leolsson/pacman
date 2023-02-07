package eu.olssonfamily.pacman.sprite;

import java.awt.Graphics;


import java.awt.Graphics2D;

import eu.olssonfamily.pacman.util.RandomUtil;

public class Ghost extends Sprite {

	final int GHOST_SPEED = 1;
	
	int currentDirection = 1;

	public Ghost(int x, int y) {
		super(x, y);
		loadImage("/eu/olssonfamily/pacman/images/pacman-ghost.png");
//		loadImage("./../images/pacman-ghost.png");
		getImageDimensions();
	}
	
	public void becomeEaten() {
		goToStartPosition();
	}

	public void move() {
		super.changeDirectionIfMazeHit();
		
		x = x + dx * GHOST_SPEED;
		y = y + dy * GHOST_SPEED;
		
		setDirection(getTrajectory());
	}
	
//  0 = KEEP CURRENT DIRECTION
//	1 = RIGHT
//	2 = DOWN
//	3 = LEFT
//	4 = UPP
	
	private void setDirection(int direction) {
		switch (direction) {
		case 0: 
			break;
		case 1:
			removeDirection();
			dx = 1;
			break;
		case 3:
			removeDirection();
			dx = -1;
			break;
		case 2:
			removeDirection();
			dy = 1;
			break;
		case 4:
			removeDirection();
			dy = -1;
		} 
	}
	
	private void removeDirection() {
		dx = 0;
		dy = 0;
	}
	
	private int getTrajectory() {
		if (hasNoDirection()) {
			int newDirection = RandomUtil.getMinMax(1, 4);
			while (newDirection == calculateOppositeDirection()) {
				newDirection = RandomUtil.getMinMax(1, 4);
			}
			currentDirection = newDirection;
			return newDirection;
		} else {
			return 0;
		}
	}
	
	private boolean hasNoDirection() {
		return dx == 0 && dy == 0;
	}
	
	private int calculateOppositeDirection() {
		if (currentDirection > 2) {
			return currentDirection - 2;
		} else {
			return currentDirection + 2;
		}
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(getImage(0), getX(), getY(), null);
	}
}
