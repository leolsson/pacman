package eu.olssonfamily.pacman.trackers;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import eu.olssonfamily.pacman.Square;

public class Lives {
	private int lives;
	
	private Image pacmanImage;
	
	public Lives (int lives) {
		this.lives = lives;
	}
	
	public void createLivesImage (String imageSrc) {
		URL url = getClass().getResource(imageSrc);
		ImageIcon pacmanImageIcon = new ImageIcon(url);
		pacmanImage  = pacmanImageIcon.getImage().getScaledInstance(Square.getSquareWidth(), Square.getSquareHeight(), Image.SCALE_DEFAULT);
	}
	 
	public void updateLives() {
		lives--;
	}
	
	public int getLives() {
		return lives;
	}
	
	public void drawLives(Graphics g) {
		for(int i = 0; i < lives; i++) {
			drawPacmanImage(g, 3 + i, 23);
		}
	}
	
	private void drawPacmanImage(Graphics g, int xGameCoordinate, int yGameCoordinate) {
		g.drawImage(pacmanImage, getXWindowCoordinate(xGameCoordinate), getYWindowCoordinate(yGameCoordinate), null);
	}
	
	private int getXWindowCoordinate(int xGameCoordinate) {
		return xGameCoordinate * Square.getSquareWidth();
	}

	private int getYWindowCoordinate(int yGameCoordinate) {
		return yGameCoordinate * Square.getSquareHeight();
	}
}
