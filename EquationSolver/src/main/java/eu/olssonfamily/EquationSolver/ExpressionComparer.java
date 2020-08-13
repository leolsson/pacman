package eu.olssonfamily.EquationSolver;

import java.util.ArrayList;

import eu.olssonfamily.EquationSolver.utils.DoubleRounder;

import static java.lang.Math.pow;

public class ExpressionComparer {

	int decimalPlaces = 4;
	double bottomLimit = -100;
	double topLimit = 100;

	Expression expression1;
	Expression expression2;

	ExpressionComparer(Expression expression1, Expression expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	public ArrayList<Double> findEqualities() {
		ArrayList<Double> answers = new ArrayList<>();

		for (double x = bottomLimit; x <= topLimit; x += 0.1) {

			if (functionsIntersect(x)) {
				answers.add(DoubleRounder.roundDecimals(determineDecimals(x), decimalPlaces));
			}

		}

		return answers;
	}

	private boolean functionsIntersect(double x) {
		if (calculateDifference(x) == 0) {
			return true;
		} else {
			if (calculateDifference(x - 0.1) > 0) {
				return calculateDifference(x) < 0;
			} else if (calculateDifference(x - 0.1) < 0) {
				double a = calculateDifference(x);
				return calculateDifference(x) > 0;
			}
		}
		return false;
	}

	private double determineDecimals(double x) {
		for (int i = 2; i <= decimalPlaces; i++) {
			double currentDifference = 0;
			double previousDifference = 1000000000;
			x -= 1 / pow(10, i - 1);
			for (int j = 0; j < 20; j++) {
				currentDifference = pow(calculateDifference(x + j * (1 / pow(10, i))), 2);

				if (previousDifference < currentDifference) {
					x += (j - 1) * (1 / pow(10, i));
					break;
				}

				previousDifference = currentDifference;
			}
		}

		return x;
	}

	private double calculateDifference(double x) {
		double difference = expression1.calculateValue(DoubleRounder.roundDecimals(x, 6)) - expression2.calculateValue(DoubleRounder.roundDecimals(x, 2));
		if (isNumber(difference)) {
			return DoubleRounder.roundDecimals(difference, 5);
		} else {
			return Double.NaN;
		}
	}
	
	private boolean isNumber(double num) {
		return !Double.isNaN(num);
	}

}
