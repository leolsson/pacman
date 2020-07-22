package eu.olssonfamily.pacman;

import java.awt.Graphics;
import java.util.ArrayList;

public class Square {

	private static int squareWidth;
	private static int squareHeight;

	public Square(int windowHeight, int windowWidth, int gameHeight, int gameWidth) {
		squareWidth = windowWidth / gameWidth;
		squareHeight = windowHeight / gameHeight;

	}

	public static int getSquareWidth() {
		return squareWidth;
	}

	public static int getSquareHeight() {
		return squareHeight;
	}

	public void drawSquare(Graphics g, int xGameCoordinate, int yGameCoordinate) {
		g.fillRect(getXWindowCoordinate(xGameCoordinate), getYWindowCoordinate(yGameCoordinate), squareWidth,
				squareHeight);
	}

	private int getXWindowCoordinate(int xGameCoordinate) {
		return xGameCoordinate * squareWidth;
	}

	private int getYWindowCoordinate(int yGameCoordinate) {
		return yGameCoordinate * squareHeight;
	}

	ArrayList<int[]> findLeftSideCoordinates(int xGameCoordinate, int yGameCoordinate) {

		ArrayList<int[]> squareLeftSideCoordinates = new ArrayList<int[]>();

		for (int i = 0; i < squareHeight; i++) {
			int[] coordinates = { squareWidth * xGameCoordinate, yGameCoordinate * squareHeight + i }; // {x-coordinate,
																										// y-coordinate}
			squareLeftSideCoordinates.add(coordinates);
		}

		return squareLeftSideCoordinates;

	}

	ArrayList<int[]> findRightSideCoordinates(int xGameCoordinate, int yGameCoordinate) {

		ArrayList<int[]> squareRightSideCoordinates = new ArrayList<int[]>();

		for (int i = 0; i < squareHeight; i++) {
			int[] coordinates = { squareWidth * (xGameCoordinate+1), yGameCoordinate * squareHeight + i }; // {x-coordinate,
																										// y-coordinate}
			squareRightSideCoordinates.add(coordinates);
		}

		return squareRightSideCoordinates;

	}
	
	ArrayList<int[]> findTopSideCoordinates(int xGameCoordinate, int yGameCoordinate) {

		ArrayList<int[]> squareTopSideCoordinates = new ArrayList<int[]>();

		for (int i = 0; i < squareHeight; i++) {
			int[] coordinates = { squareWidth * xGameCoordinate + i, yGameCoordinate * squareHeight }; // {x-coordinate,
																										// y-coordinate}
			squareTopSideCoordinates.add(coordinates);
		}

		return squareTopSideCoordinates;

	}
	
	ArrayList<int[]> findBottomSideCoordinates(int xGameCoordinate, int yGameCoordinate) {

		ArrayList<int[]> squareBottomSideCoordinates = new ArrayList<int[]>();

		for (int i = 0; i < squareHeight; i++) {
			int[] coordinates = { squareWidth * xGameCoordinate + i, (yGameCoordinate + 1) * squareHeight}; // {x-coordinate,
																										// y-coordinate}
			squareBottomSideCoordinates.add(coordinates);
		}

		return squareBottomSideCoordinates;

	}

}
