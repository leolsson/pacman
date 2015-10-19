package eu.olssonfamily.pacman.sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Pacman extends Sprite {

	private int dx = 1;
	private int dy = 0;
	final int PACMAN_SPEED = 5;

	public Pacman(int x, int y) {
		super(x, y);
		loadImage("images/pacman.png");
		getImageDimensions();
	}

	public void move() {
		x = x + dx * PACMAN_SPEED;
		y = y + dy * PACMAN_SPEED;
	}
	
	public void drawPacman(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(getImage(), getX(), getY(), null);
	}


	public void keyPressed(KeyEvent e) {

		System.out.println("Pacman key event recieved");
		
		int key = e.getKeyCode();

		dx = 0;
		dy = 0;
		
		switch (key) {
		case KeyEvent.VK_LEFT:
			dx = -1;
			break;
		case KeyEvent.VK_RIGHT: 
			dx = 1;
			break;
		case KeyEvent.VK_UP:
			dy = -1;
			break;
		case KeyEvent.VK_DOWN:
			dy = 1;
		}
	}
}
