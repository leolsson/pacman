package eu.olssonfamily.pacman;


public class Main {
	static PacmanWindow window = new PacmanWindow();
	
	public static void main(String[] args) {
		Tick gameTick = new Tick(PacmanConstants.DELAY);
		PacmanBoard boardView = new PacmanBoard();
		gameTick.addActionListener(boardView);
		window.add(boardView);
	}

}
