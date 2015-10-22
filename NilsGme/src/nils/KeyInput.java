package nils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	Paint paint;
	
	public KeyInput(Paint p){
	
		paint = p;
	}

	@Override
	public void keyPressed(KeyEvent e) {		
		super.keyPressed(e);
		
		paint.rightArrowPressed = true;
		
		System.out.println("KeyPressed" + e.getKeyChar());
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
		paint.rightArrowPressed = false;
		
		super.keyReleased(e);
	}
	
	
}
