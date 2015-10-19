package eu.olssonfamily.pacman;

import javax.swing.JFrame;

public class PacmanWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public PacmanWindow() {
		setTitle("Pacman");
		setVisible(true);
		setSize(650, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
