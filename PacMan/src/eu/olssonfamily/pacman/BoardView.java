package eu.olssonfamily.pacman;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardView extends JPanel {

	public BoardView() {
		setBorder(getBorder());
	}
	
	
	public void paint(Graphics g) {
		drawBoardSquares(g);
		drawPacman(g, 100, 100);
	}
	
	public void drawPacman(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		
		ImageIcon ii = new ImageIcon("images/pacman.png");
		g2.drawImage(ii.getImage(),x, y, null);
	}

	public void drawBoardSquares(Graphics g) {
		g.drawRect(0, 0, 450, 450);
		g.drawLine(0,150, 450, 150);
		g.drawLine(0,300, 450, 300);
		g.drawLine(150,0, 150, 450);
		g.drawLine(300,0, 300, 450);
	}
}
