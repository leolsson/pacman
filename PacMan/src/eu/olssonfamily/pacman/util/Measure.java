package eu.olssonfamily.pacman.util;

import java.lang.Math;;

public class Measure {
	
	public static double getDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
	}
	
}
