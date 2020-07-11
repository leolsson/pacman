package eu.olssonfamily.pacman.util;

public class RandomUtil {
	
	public static int getMinMax(int min, int max) {
		return (int) ((Math.random() * (max - min) * 1000) % (max - min + 1)) + min;
	}

}
