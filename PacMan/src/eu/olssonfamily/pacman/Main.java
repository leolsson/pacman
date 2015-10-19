package eu.olssonfamily.pacman;


public class Main {

	static PacmanWindow window = new PacmanWindow();
	
	public static void main(String[] args) {
		PacmanBoard boardView = new PacmanBoard();
		window.add(boardView);
	}

}
