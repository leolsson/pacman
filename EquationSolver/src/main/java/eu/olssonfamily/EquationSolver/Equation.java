package eu.olssonfamily.EquationSolver;

import java.util.ArrayList;

public class Equation {

	ArrayList<String> leftSide = new ArrayList<String>();
	ArrayList<String> rightSide = new ArrayList<String>();

	Equation(String equation) {
		split(equation);
	}

	private void split(String equation) {

		splitSide(equation.split("=")[0], true);
		splitSide(equation.split("=")[1], false);

	}

	private void splitSide(String side, boolean left) {

		for (int i = 0; i < side.length(); i++) {

			if (isDigitOrPeriod(side.charAt(i))) {
				String holder = "";
				while ((i <= side.length() - 1) && (isDigitOrPeriod(side.charAt(i)))) {
					holder += side.charAt(i);
					i++;
				}
				addToRightSide(holder, left);
			}
			if (i > side.length() - 1) break;
			if (Character.getType(side.charAt(i)) != CharTypes.WHITESPACE && !isDigitOrPeriod(side.charAt(i))) {
				String holder = "";
				holder += side.charAt(i);
				addToRightSide(holder, left);
			}

		}

	}

	private void addToRightSide(String holder, boolean left) {
		if (holder != "") {
			if (left) {
				leftSide.add(holder);
			} else {
				rightSide.add(holder);
			}
		}
	}

	private boolean isDigitOrPeriod(char c) {
		return Character.isDigit(c) || c == '.';
	}

	public void print() {
		for (int i = 0; i < leftSide.size(); i++) {
			System.out.println(leftSide.get(i));
		}
		for (int i = 0; i < rightSide.size(); i++) {
			System.out.println(rightSide.get(i));
		}
	}

}
