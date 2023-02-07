package eu.olssonfamily.EquationSolver.utils;

import java.util.ArrayList;

public class ArrayListManager {
	
	public static ArrayList<String> clone(ArrayList<String> oldArray, int startIndex, int endIndex) {
		ArrayList<String> newArray = new ArrayList<String>();
		for(int i = startIndex; i <= endIndex; i++) {
			newArray.add(oldArray.get(i));
		}
		return newArray;
	}
	
	public static void remove(ArrayList<String> array, int startIndex, int endIndex) {
		
		for (int i = startIndex; i <= endIndex; i++) {
			array.remove(startIndex);
		}
	}
	
}
