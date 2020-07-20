package eu.olssonfamily.pacman.maze;

import java.awt.Graphics;
import java.util.ArrayList;

import eu.olssonfamily.pacman.PacmanWindow;
import eu.olssonfamily.pacman.Square;

public class Dots {
	
	int[][] maze;
	
	int dotWidth;
	int dotHeight;
	
	static int GAME_HEIGHT;
	static int GAME_WIDTH;
	
	
	public Dots (Maze maze, int GAME_HEIGHT, int GAME_WIDTH) {
		this.maze = maze.getMaze();
		Dots.GAME_HEIGHT = GAME_HEIGHT;
		Dots.GAME_WIDTH = GAME_WIDTH;
		
		dotWidth = PacmanWindow.WIDTH / GAME_WIDTH / 5;
		dotHeight = PacmanWindow.HEIGHT / GAME_HEIGHT / 5;
	}
	
	public void drawDotsOnEmptySquares(Graphics g) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
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
	
	
}
