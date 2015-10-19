package eu.olssonfamily.pacman;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import eu.olssonfamily.pacman.sprite.Pacman;

public class PacmanBoard extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private final int TICK_DELAY = 100;
	private final int PACMAN_START_X_POS = 200;
	private final int PACMAN_START_Y_POS = 200;
	
	private Timer tickTimer;
	private Pacman pacman;


	public PacmanBoard() {
		setSize(getMaximumSize());
		setFocusable(true);
//		setBackground(Color.BLACK);
//		setDoubleBuffered(true);

		pacman = new Pacman(PACMAN_START_X_POS, PACMAN_START_Y_POS);

		addKeyListener(new TAdapter());

		tickTimer = new Timer(TICK_DELAY, this);
		tickTimer.start();
	}
	
//	public void paintComponent(Graphics g) {
	public void paint(Graphics g) {
		super.paint(g);
		drawBoardSquares(g);
		pacman.drawPacman(g);
		Toolkit.getDefaultToolkit().sync();

	}
	
	public void drawBoardSquares(Graphics g) {
		g.drawRect(0, 0, 450, 450);
		g.drawLine(0,150, 450, 150);
		g.drawLine(0,300, 450, 300);
		g.drawLine(150,0, 150, 450);
		g.drawLine(300,0, 300, 450);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		pacman.move();	
		repaint(); 
	}
	
//	private class TAdapter extends KeyAdapter {
//
//		@Override
//		public void keyPressed(KeyEvent e) {
//			pacman.keyPressed(e);
//		}
//
//	}
	
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("Key released event");
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Key pressed event");
			pacman.keyPressed(e);
		}
	}


}
