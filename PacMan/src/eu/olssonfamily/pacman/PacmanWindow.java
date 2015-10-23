package eu.olssonfamily.pacman;

import javax.swing.JFrame;

public class PacmanWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final int HEIGHT = 700;
	public static final int WIDTH =  700;
	public static final int LOCATION_X = 50;
	public static final int LOCATION_Y = 50;
	
	PacmanBoard pacmanBoard;

	public PacmanWindow(PacmanController controller, PacmanModel model) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setTitle("Pacman");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		pacmanBoard = new PacmanBoard(controller, model);
		add(pacmanBoard);
	}
}
