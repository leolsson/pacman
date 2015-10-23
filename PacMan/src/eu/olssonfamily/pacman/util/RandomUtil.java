package eu.olssonfamily.pacman.util;

public class RandomUtil {
	
	public static int getMinMax(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

}
