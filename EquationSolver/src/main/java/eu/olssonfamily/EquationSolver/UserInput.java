package eu.olssonfamily.EquationSolver;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInput {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static String getEquation () {

		printEquationRules();
		String enteredEquation = scanner.nextLine();
		
		if (!isEquationValid(enteredEquation)) {
			System.out.println("You entered an invalid equation. Try again.");
			getEquation();
		}
		
		return enteredEquation;
		
	}
	
	private static void printEquationRules() {
		System.out.println("Enter your equation according to the following rules:");
		System.out.println("1. It has to contain ONE =");
		System.out.println("2. Name your unknown variable lower-case x");
		System.out.println("3. Allowed operators are + - * / ^");
		System.out.println("4. To enter a decimal number seperate by .");
		System.out.println("Enter here:");
		
	}
	
	private static boolean isEquationValid(String equation) {
		return equation.contains("x") && equation.contains("=") && !containsInvalidCharacthers(equation);
	}

	private static boolean containsInvalidCharacthers(String equation) {
		return Pattern.compile("[^0-9.x=+-/*^()\\s]").matcher(equation).find();
	}
	
	public String[] splitAtEqualSign (String equation) {
		
		System.out.println(equation);
		
		String[] equationParts = equation.split(" = ");
		
		return (String[]) equationParts;
		
		
	} 
	
	public String[] SeparateParts (String equationPart) {
		
		System.out.println(equationPart);
		
		String[] partsOfX = equationPart.split(" ");
		
		
		
		return partsOfX;
	}
	
}
