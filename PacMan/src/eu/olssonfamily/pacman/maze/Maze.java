package eu.olssonfamily.pacman.maze;

import java.awt.Color;


import java.awt.Graphics;

import eu.olssonfamily.pacman.PacmanWindow;

public class Maze {
 
	int[][] maze;
	public int dotsToEat = 0;
	
	public Maze() {
		maze = java.util.Arrays.stream(originalMaze).map(el -> el.clone()).toArray(int[][]::new);
		dotsToEat = calculateDotsToEat();
	} 
	
	
	private final int originalMaze[][] = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1},
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{ 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1},
			{ 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1},
			{ 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0},
			{ 1, 1, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 1, 1, 1, 1, 1, 1},
			{ 4, 4, 0, 0, 0, 0, 0, 2, 2, 0, 0, 1, 1, 1, 1, 1, 0, 0, 2, 2, 0, 0, 0, 0, 0, 4, 4},
			{ 1, 1, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 1, 1, 1, 1, 1, 1},
			{ 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0},
			{ 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1},
			{ 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1},
			{ 1, 2, 2, 2, 2, 3, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 3, 2, 2, 2, 2, 1},
			{ 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1},
			{ 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1},
			{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} 
		};
	
	
	public int[][] getMaze() {
		return maze;
	}

	public void updateMaze(int x, int y, int newValue) {
		if (maze[y][x] == 2 || maze[y][x] == 3) {
			dotsToEat--;
		}
		maze[y][x] = newValue;
	}
	
	private int calculateDotsToEat() {
		int dots = 0;
		for(int i = 0; i < maze.length; i++) {
			for( int j = 0; j < maze[i].length; j++) {
				if(maze[i][j] == 2 || maze[i][j] == 3) {
					dots++;
				}
			}
		}
		return dots;
	}
	
	public void resetMaze() {
		maze = java.util.Arrays.stream(originalMaze).map(el -> el.clone()).toArray(int[][]::new);
		dotsToEat = calculateDotsToEat();
	}
	

	public void drawMaze(Graphics g) {
		g.setColor(new Color(255, 255, 255));

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 1) {
					PacmanWindow.getSquare().drawSquare(g, j, i);
				}
			}
		}

	}

}
