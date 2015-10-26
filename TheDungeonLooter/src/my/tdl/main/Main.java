package my.tdl.main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import my.project.gop.main.GameWindow;
import my.project.gop.main.SpriteSheet;
import my.tdl.gameloop.GameLoop;

public class Main {
	
	public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width = gd.getDisplayMode().getWidth();
	public static int height = gd.getDisplayMode().getHeight();
	
	

	public static void main(String[] args) {
		
		GameWindow frame = new GameWindow("TheDlooter", width ,height);
		frame.setFullscreen(1);
		frame.add(new GameLoop(width,height));
		frame.setVisible(true);

	}

}
