package nils;

import javax.swing.JFrame;

public class Window extends JFrame {
	

	public Window( String title) {
		
		setSize(Constants.width, Constants.height);
		setVisible(true);
		setTitle(title);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		
	}

}
