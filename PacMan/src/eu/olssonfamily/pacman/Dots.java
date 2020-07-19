package eu.olssonfamily.pacman;

import java.awt.Graphics;
import java.util.ArrayList;

public class Dots {
	
	static int[][] maze;
	
	int dotWidth;
	int dotHeight;
	
	static int GAME_HEIGHT;
	static int GAME_WIDTH;
	
	public static ArrayList<int[]> dotCoordinates = new ArrayList<int[]>();
	
	public Dots (Maze Maze, int GAME_HEIGHT, int GAME_WIDTH) {
		maze = new Maze().getMaze();
		Dots.GAME_HEIGHT = GAME_HEIGHT;
		Dots.GAME_WIDTH = GAME_WIDTH;
		
		dotWidth = PacmanWindow.WIDTH / GAME_WIDTH / 5;
		dotHeight = PacmanWindow.HEIGHT / GAME_HEIGHT / 5;
		
		setDotCoordinates();
	}
	
	public void drawDotsOnEmptySquares(Graphics g) {
		for (int i = 0; i < GAME_HEIGHT; i++) {
			for (int j = 0; j < GAME_WIDTH; j++) {
				if (maze[i][j] == 2) {
					drawDot(g, j, i);
				}
			}
		}
	}
	
	private void drawDot (Graphics g, int xGameCoordinate, int yGameCoordinate) {
		g.fillRect(getXWindowCoordinate(xGameCoordinate), getYWindowCoordinate(yGameCoordinate), dotWidth, dotHeight);
	}
	
	
	private static int getXWindowCoordinate(int xGameCoordinate) {
		return xGameCoordinate * Square.getSquareWidth() + Square.getSquareWidth() * 2 / 5;
	}

	private static int getYWindowCoordinate(int yGameCoordinate) {
		return yGameCoordinate * Square.getSquareHeight() + Square.getSquareWidth() * 2 / 5;
	}
	
	public static void setDotCoordinates () {
		dotCoordinates.clear();
		for (int i = 0; i < GAME_HEIGHT; i++) {
			for (int j = 0; j < GAME_WIDTH; j++) {
				if (maze[i][j] == 2) {
					int[] holder = {getXWindowCoordinate(j), getYWindowCoordinate(i)};
					dotCoordinates.add(holder);
				}
			}
		}
	}
	
	
}
