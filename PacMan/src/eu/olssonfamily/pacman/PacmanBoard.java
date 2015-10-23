package eu.olssonfamily.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class PacmanBoard extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	PacmanModel pacmanModel;
	Tick repaintTimer;

	public PacmanBoard(KeyListener keyListener, PacmanModel model) {
		setSize(getMaximumSize());
		addKeyListener(keyListener);
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		pacmanModel = model;

		repaintTimer = new Tick(10);
		repaintTimer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		pacmanModel.getPacman().drawPacman(g);
		pacmanModel.getGhost().draw(g);
	}


}
