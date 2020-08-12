package eu.olssonfamily.pacman.trackers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import eu.olssonfamily.pacman.Square;
import eu.olssonfamily.pacman.util.FileManager;

public class HighScore {
	
	FileManager highScoreFile;
	int highScore;
	
	public HighScore () {
		highScoreFile = new FileManager("highscore.txt");
		highScore = Integer.parseInt(highScoreFile.read());
	}
	
	public void updateHighScore(int newScore) {
		if (newScore > highScore) {
			setNewHighScore(newScore);
		}
	}
	
	private void setNewHighScore(int newScore) {
		highScoreFile.write(Integer.toString(newScore));
		highScore = Integer.parseInt(highScoreFile.read());
		
	}

	public void write(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 20)); // Creates game starting glitch
		g.drawString("HIGHSCORE: " + highScore, getXWindowCoordinate(15), getYWindowCoordinate(1));
	}
	
	private int getXWindowCoordinate(int xGameCoordinate) {
		return xGameCoordinate * Square.getSquareWidth();
	}

	private int getYWindowCoordinate(int yGameCoordinate) {
		return yGameCoordinate * Square.getSquareHeight() - 5;
	}
	
}
