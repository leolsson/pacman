package eu.olssonfamily.pacman;


public class Main {
	
	public static void main(String[] args) {
		PacmanModel model = new PacmanModel();
		PacmanController controller = new PacmanController(model);
		PacmanWindow window = new PacmanWindow(controller, model);
		window.setVisible(true);
	}

}
