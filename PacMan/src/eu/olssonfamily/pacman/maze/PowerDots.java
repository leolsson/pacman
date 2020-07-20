package eu.olssonfamily.pacman.maze;

import java.awt.Graphics;
import java.util.ArrayList;

import eu.olssonfamily.pacman.PacmanWindow;
import eu.olssonfamily.pacman.Square;

public class PowerDots {

static int[][] maze;
	
	int dotWidth;
	int dotHeight;
	
	static int GAME_HEIGHT;
	static int GAME_WIDTH;
		
	public PowerDots (Maze maze, int GAME_HEIGHT, int GAME_WIDTH) {
		this.maze = maze.getMaze();
		PowerDots.GAME_HEIGHT = GAME_HEIGHT;
		PowerDots.GAME_WIDTH = GAME_WIDTH;
		
		dotWidth = PacmanWindow.WIDTH / GAME_WIDTH;
		dotHeight = PacmanWindow.HEIGHT / GAME_HEIGHT;
	}
	
	public void drawDotsOnDedicatedSquares(Graphics g) {
		for (int i = 0; i < GAME_HEIGHT; i++) {
			for (int j = 0; j < GAME_WIDTH; j++) {
				if (maze[i][j] == 3) {
					drawDot(g, j, i);
				}
			}
		}
	}
	
	private void drawDot (Graphics g, int xGameCoordinate, int yGameCoordinate) {
		g.fillOval(getXWindowCoordinate(xGameCoordinate), getYWindowCoordinate(yGameCoordinate), dotWidth, dotHeight);
	}
	
	
	private static int getXWindowCoordinate(int xGameCoordinate) {
		return xGameCoordinate * Square.getSquareWidth();
	}

	private static int getYWindowCoordinate(int yGameCoordinate) {
		return yGameCoordinate * Square.getSquareHeight();
	}

}
