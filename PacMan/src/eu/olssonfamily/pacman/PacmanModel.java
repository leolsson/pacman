package eu.olssonfamily.pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eu.olssonfamily.pacman.sprite.Ghost;
import eu.olssonfamily.pacman.sprite.Pacman;

public class PacmanModel implements ActionListener {
	
	Tick pacmanEvent = new Tick(100);
	Pacman pacman = new Pacman(100, 100);
	Ghost ghost = new Ghost(150, 150);
	boolean  stateGameIsOn = false;
	
	public PacmanModel() {
		pacmanEvent.addActionListener(this);
	}

	public void startGame() {
		stateGameIsOn = true;
	}
	
	public void stopGame() {
		stateGameIsOn = false;
	}
	
	Pacman getPacman() {
		return pacman;
	}

	Ghost getGhost() {
		return ghost;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		pacman.move();
		ghost.move();
	}
	
}
