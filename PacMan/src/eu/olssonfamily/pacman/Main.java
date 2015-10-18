package eu.olssonfamily.pacman;


public class Main {

	static PacmanWindow window = new PacmanWindow();
	
	public static void main(String[] args) {
		BoardView boardView = new BoardView();
		window.add(boardView);
	}

}
