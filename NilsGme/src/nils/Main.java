package nils;

public class Main {

	public static void main(String[] args) {
		Window window = new Window("Game");
		Paint paint = new Paint();
		window.add(paint);
		Tick tick = new Tick(10);
		tick.addActionListener(paint);
		KeyInput keyinput = new KeyInput(paint);
		paint.addKeyListener(keyinput);

	}

}
