package eu.olssonfamily.EquationSolver;

import java.util.ArrayList;

import eu.olssonfamily.EquationSolver.utils.ArrayListManager;

import static java.lang.Math.*;

public class Expression {

	ArrayList<String> parts = new ArrayList<String>();

	Expression(ArrayList<String> parts) {
		this.parts = parts;
	}

	public double calculateValue(double x) {
		@SuppressWarnings("unchecked")
		ArrayList<String> partsCopy = (ArrayList<String>) parts.clone();

		replaceX(partsCopy, x);
		calculateValuesWithinParenthasis(partsCopy);
		while (partsCopy.size() > 1) {
			calculateValueWithPrioritiesAndUpdateArray(partsCopy);
		}

		return Double.parseDouble(partsCopy.get(0));

	}

	private void replaceX(ArrayList<String> parts, double x) {
		String strX = String.valueOf(x);
		for (int i = 0; i < parts.size(); i++) {
			String Value = parts.get(i);
			if (Value.equals("x")) {
				parts.set(i, strX);
			}
		}

	}

	protected ArrayList<String> calculateValuesWithinParenthasis(ArrayList<String> parts) {
		while (parts.contains("(")) {
			calculateValuesWithinOneParenthasisSection(parts);
		}
		return parts;
	}

	protected ArrayList<String> calculateValuesWithinOneParenthasisSection(ArrayList<String> parts) {
		if (parts.contains("(")) {
			int start = parts.indexOf("(") + 1;
			int end = findEndOfParenthasis(parts, start) - 1;
			parts.addAll(end + 2,
					calculateValuesWithinOneParenthasisSection(ArrayListManager.clone(parts, start, end)));
			ArrayListManager.remove(parts, start - 1, end + 1);
		}
		
		calculateValueWithPrioritiesAndUpdateArray(parts);

		return parts;

	}

	protected int findEndOfParenthasis(ArrayList<String> parts, int start) {
		int openingParenthasisesTillEnd = 0;
		for (int i = start; i < parts.size(); i++) {
			if (parts.get(i).equals(")")) {
				if (openingParenthasisesTillEnd == 0) {
					return i;
				} else {
					openingParenthasisesTillEnd--;
				}
			} else if (parts.get(i).equals("(")) {
				openingParenthasisesTillEnd++;
			}
		}

		return parts.size() - 1;
	}

	private void calculateValueWithPrioritiesAndUpdateArray(ArrayList<String> parts) {
		if (parts.contains("^")) {
			calculateValueOfNumericExpressionsAndUpdateArray(parts, "power");
		}
		if (parts.contains("*")) {
			calculateValueOfNumericExpressionsAndUpdateArray(parts, "multiply");
		}
		if (parts.contains("/")) {
			calculateValueOfNumericExpressionsAndUpdateArray(parts, "divide");
		}
		if (parts.contains("-")) {
			calculateValueOfNumericExpressionsAndUpdateArray(parts, "subtract");
		}
		if (parts.contains("+")) {
			calculateValueOfNumericExpressionsAndUpdateArray(parts, "add");
		}
		

	}

	protected void calculateValueOfNumericExpressionsAndUpdateArray(ArrayList<String> parts, String operator) {
		
		for(int i = 0; i < parts.size(); i++) {
			if (parts.get(i).equals(getOperatorByName(operator))) {
				try {
					int indexOfOperator = i;
					double operand1 = Double.parseDouble(parts.get(indexOfOperator - 1));
					double operand2 = Double.parseDouble(parts.get(indexOfOperator + 1));
					String newValue = String.valueOf(calculate(operand1, operand2, operator));
					parts.set(indexOfOperator, newValue);
					parts.remove(indexOfOperator + 1);
					parts.remove(indexOfOperator - 1);
				} catch (Exception e) {
					
				}
 			}
		}


	}

	

	protected String getOperatorByName(String operator) {
		switch (operator) {
		case "power":
			return "^";

		case "multiply":
			return "*";

		case "divide":
			return "/";

		case "add":
			return "+";

		case "subtract":
			return "-";
		}
		return "";
	}

	protected double calculate(double operand1, double operand2, String operator) {
		switch (operator) {
		case "power":
			return pow(operand1, operand2);
		case "multiply":
			return operand1 * operand2;

		case "divide":
			return operand1 / operand2;

		case "add":
			return operand1 + operand2;

		case "subtract":
			return operand1 - operand2;
		}
		return 0;
	}

}
