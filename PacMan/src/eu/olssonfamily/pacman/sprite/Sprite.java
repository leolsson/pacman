package eu.olssonfamily.pacman.sprite;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import eu.olssonfamily.pacman.PacmanModel;
import eu.olssonfamily.pacman.PacmanWindow;
import eu.olssonfamily.pacman.Square;
import eu.olssonfamily.pacman.maze.Maze;

public class Sprite {
	protected int x;
	protected int y;
	protected int dx = 1;
	protected int dy = 0;

	private int startX;
	private int startY;

	protected int width;
	protected int height;
	protected boolean vis;
	protected ArrayList<Image> images = new ArrayList<Image>();

	protected int[] frontX = new int[2];
	protected int[] frontY = new int[2];

	final int WINDOW_HEIGHT = PacmanWindow.HEIGHT;
	final int WINDOW_WIDTH = PacmanWindow.WIDTH;

	public Sprite(int xGameCoordinate, int yGameCoordinate) {

		this.x = xGameCoordinate * Square.getSquareWidth();
		this.y = yGameCoordinate * Square.getSquareHeight();
		startX = this.x;
		startY = this.y;
		vis = true;

	}

	void loadImage(String... imageNames) {

		PacmanWindow.getSquare();

		for (String imageName : imageNames) {

			URL url = getClass().getResource(imageName);
			ImageIcon ii = new ImageIcon(url);
			images.add(ii.getImage().getScaledInstance(Square.getSquareWidth(), Square.getSquareHeight(),
					Image.SCALE_DEFAULT));
		}

	}

	protected void getImageDimensions() {

		width = Square.getSquareWidth();
		height = Square.getSquareHeight();
	}

	public Image getImage(int index) {
		return images.get(index);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void goToStartPosition() {
		setX(startX);
		setY(startY);
	}

	public void teleportIfPossible() {
		if (getSquareType(frontX[0], frontY[0]) == 4) {
			teleport();
		}
	}

	private void teleport() {
		if (x < WINDOW_WIDTH / 2) {
			System.out.println(WINDOW_WIDTH);
			x = WINDOW_WIDTH - Square.getSquareWidth();
		} else {
			x = Square.getSquareWidth();
		}
	}

	protected final int RIGHT = 0;
	protected final int LEFT = 180;
	protected final int UPP = 270;
	protected final int DOWN = 90;

	public int getDirection() {
		if (dx == 1) {
			return RIGHT;
		} else if (dx == -1) {
			return LEFT;
		} else if (dy == 1) {
			return DOWN;
		} else if (dy == -1) {
			return UPP;
		} else {
			return 0;
		}
	}

	public void setFrontCoordinates() {

		switch (getDirection()) {
		case RIGHT:
			setFrontCoordinatesForRightDirection();
			break;
		case LEFT:
			setFrontCoordinatesForLeftDirection();
			break;
		case UPP:
			setFrontCoordinatesForUppDirection();
			break;
		case DOWN:
			setFrontCoordinatesForDownDirection();
			break;
		}

	}

	private void setFrontCoordinatesForRightDirection() {
		frontX[0] = x + width + 1;
		frontY[0] = y + 2;
		frontX[1] = x + width + 1;
		frontY[1] = y + height - 2;

	}

	private void setFrontCoordinatesForLeftDirection() {
		frontX[0] = x - 1;
		frontY[0] = y + 2;
		frontX[1] = x - 1;
		frontY[1] = y + height - 2;

	}

	private void setFrontCoordinatesForUppDirection() {
		frontX[0] = x + 2;
		frontY[0] = y - 1;
		frontX[1] = x + width - 2;
		frontY[1] = y - 1;

	}

	private void setFrontCoordinatesForDownDirection() {
		frontX[0] = x + 2;
		frontY[0] = y + height + 1;
		frontX[1] = x + width - 2;
		frontY[1] = y + height + 1;

	}

	public void changeDirectionIfMazeHit() {
		if (!isFrontPositionValid()) {
			setNoDirection();
		}
	}

	private void setNoDirection() {
		dx = 0;
		dy = 0;
	}

	private boolean isFrontPositionValid() {
		for (int i = 0; i < frontX.length; i++) {
			if (getSquareType(frontX[i], frontY[i]) == 1) {
				return false;
			}
		}
		return true;
	}

	int getSquareType(int xWindowCoordinate, int yWindowCoordinate) {
		int xGameCoordinate = (int) Math.ceil(xWindowCoordinate / Square.getSquareWidth());
		int yGameCoordinate = (int) Math.ceil(yWindowCoordinate / Square.getSquareWidth());
		return PacmanModel.getMaze().getMaze()[yGameCoordinate][xGameCoordinate];
	}

	public boolean isVisible() {
		return vis;
	}

	public void setVisible(Boolean visible) {
		vis = visible;
	}

}