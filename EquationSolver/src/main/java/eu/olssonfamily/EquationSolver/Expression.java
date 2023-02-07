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
			calculateValueOfNumericExpressionsAndUpdateArray(parts, "^");
		}
		if (parts.contains("*")) {
			calculateValueOfNumericExpressionsAndUpdateArray(parts, "*");
		}
		if (parts.contains("/")) {
			calculateValueOfNumericExpressionsAndUpdateArray(parts, "/");
		}
		if (parts.contains("-")) {
			calculateValueOfNumericExpressionsAndUpdateArray(parts, "-");
		}
		if (parts.contains("+")) {
			calculateValueOfNumericExpressionsAndUpdateArray(parts, "+");
		}

	}

	protected void calculateValueOfNumericExpressionsAndUpdateArray(ArrayList<String> parts, String operator) {

		for (int i = 0; i < parts.size(); i++) {
			if (parts.get(i).equals(operator)) {

				try {
					int indexOfOperator = i;
					double operand1 = Double.parseDouble(parts.get(indexOfOperator - 1));
					double operand2 = Double.parseDouble(parts.get(indexOfOperator + 1));
					double newValue = calculate(operand1, operand2, operator);
					if (Double.isNaN(newValue)) {
						handleNotANumber(parts);
					} else if (isOtherOperatorWithPrecedence(parts, indexOfOperator)) {

					} else {
						parts.set(indexOfOperator, String.valueOf(newValue));
						parts.remove(indexOfOperator + 1);
						parts.remove(indexOfOperator - 1);
					}
				} catch (Exception e) {

				}
			}
		}

	}

	private void handleNotANumber(ArrayList<String> parts) {
		parts.clear();
		parts.add(String.valueOf(Double.NaN));
	}

	private boolean isOtherOperatorWithPrecedence(ArrayList<String> parts, int indexOfOperator) {
		int previousOperatorPrecedence = getOperatorPrecedence(parts, indexOfOperator - 2);
		int currentOperatorPrecedence = getOperatorPrecedence(parts, indexOfOperator);
		int nextOperatorPrecedence = getOperatorPrecedence(parts, indexOfOperator + 2);

		return previousOperatorPrecedence < currentOperatorPrecedence
				|| nextOperatorPrecedence < currentOperatorPrecedence;

	}

	private int getOperatorPrecedence(ArrayList<String> parts, int indexOfOperator) {
		try {
			String operator = parts.get(indexOfOperator);
			if (operator == "^" || operator == "*" || operator == "/" || operator == "-" || operator == "+") {
				return Constants.listOfPrecedence.indexOf(operator);
			}
		} catch (Exception e) {
			
		}
		return Constants.listOfPrecedence.size() + 1;
	}


	protected double calculate(double operand1, double operand2, String operator) {
		switch (operator) {
		case "^":
			return pow(operand1, operand2);
		case "*":
			return operand1 * operand2;

		case "/":
			if (operand2 != 0) {
				return operand1 / operand2;
			} else {
				return Double.NaN;
			}

		case "+":
			return operand1 + operand2;

		case "-":
			return operand1 - operand2;
		}
		return 0;
	}

}
