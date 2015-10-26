package nils;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Paint extends JPanel implements ActionListener{

	public int x = 200;
	public int y = 200;
	public boolean rightArrowPressed = false;
	
	public void paint(Graphics g) {

		g.clearRect(0, 0, Constants.width, Constants.height);
		g.fillRect(x, y, 50, 50);

		System.out.println("Hello");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(rightArrowPressed == true){
			x++;
		}
		
		repaint();
	}

}
