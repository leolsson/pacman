package eu.olssonfamily.EquationSolver.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class DoubleRounder {
	
	public static double roundDecimals(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		 
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	

	public static double roundValidDigits(double value, int validDigits) {
		
		int size = calculateSize(value); 
		
		ArrayList<Character> digits = new ArrayList<>();
		
		for(int i = 0; i < String.valueOf(value).toCharArray().length; i++) {
			digits.add(String.valueOf(value).toCharArray()[i]);
		}
		
		for(int i = 0; i < digits.size(); i++) {
			if (digits.get(i).equals('.')) digits.remove(i);
		}
		
		digits.add(0, '.');
		digits.add(0, '0');
		
		double roundedValue = roundDecimals(Double.parseDouble(createString(digits)), validDigits);
		
		ArrayList<Character> roundedDigits = new ArrayList<>();
		
		for(int i = 0; i < String.valueOf(roundedValue).toCharArray().length; i++) {
			roundedDigits.add(String.valueOf(roundedValue).toCharArray()[i]);
		}
		
		roundedDigits.remove(0);
		roundedDigits.remove(0);

			while (roundedDigits.size() < size) {
				roundedDigits.add('0');
			}
		
		roundedDigits.add(size, '.');
		
		
		return roundDecimals(Double.parseDouble(createString(roundedDigits)), validDigits);
	}	
	
	private static int calculateSize(double value) {
		return String.valueOf(value).indexOf('.');
	}
	
	private static String createString(ArrayList<Character> array) {
		String str = "";
		for (int i = 0; i < array.size(); i++) {
			str += array.get(i);
		}
		return str;
	}
}
