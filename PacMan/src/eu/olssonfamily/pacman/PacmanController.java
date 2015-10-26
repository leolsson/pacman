package eu.olssonfamily.pacman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PacmanController extends KeyAdapter {

	PacmanModel pacmanModel;
	
	public PacmanController(PacmanModel model) {
		pacmanModel = model;
	}
	
	@Override
	public void keyPressed(KeyEvent keyEvent) {
		super.keyPressed(keyEvent);
		pacmanModel.getPacman().changeDirection(keyEvent);
	}

}
