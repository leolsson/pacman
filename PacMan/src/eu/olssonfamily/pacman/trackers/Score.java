package eu.olssonfamily.pacman.trackers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import eu.olssonfamily.pacman.Square;

public class Score {
	
	private int score;
	
	public Score () {
		
	}
	
	public int getScore() {
		return score;
	}
	
	public void addToScore(int amount) {
		score += amount;
	}
	
	public void writeScore(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 20)); // Creates game starting glitch
		g.drawString("SCORE: " + score, getXWindowCoordinate(3), getYWindowCoordinate(1));
	}
	
	private int getXWindowCoordinate(int xGameCoordinate) {
		return xGameCoordinate * Square.getSquareWidth();
	}

	private int getYWindowCoordinate(int yGameCoordinate) {
		return yGameCoordinate * Square.getSquareHeight() - 5;
	}
	
}
