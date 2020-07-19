package eu.olssonfamily.pacman;

import javax.swing.JFrame;

public class PacmanWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final int HEIGHT = 600;
	public static final int WIDTH =  900;
	public static final int WIDTH_WITH_EDGES =  WIDTH + 14; 
	public static final int HEIGHT_WITH_EDGES =  HEIGHT + 37;
	public static final int LOCATION_X = 50;
	public static final int LOCATION_Y = 50;
	static Square square = new Square(HEIGHT, WIDTH, PacmanModel.GAME_HEIGHT, PacmanModel.GAME_WIDTH);
	
	PacmanBoard pacmanBoard;

	public PacmanWindow(PacmanController controller, PacmanModel model) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH_WITH_EDGES, HEIGHT_WITH_EDGES);
		setResizable(false);
		setTitle("Pacman");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		pacmanBoard = new PacmanBoard(controller, model);
		add(pacmanBoard);
	}
	
	public static Square getSquare () {
		return square;
	}
	
}
