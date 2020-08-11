package eu.olssonfamily.EquationSolver;

import java.util.ArrayList;

import org.decimal4j.util.DoubleRounder;

import static java.lang.Math.pow;

public class ExpressionComparer {

	int decimalPlaces = 4;
	int negativeXLimit = -300;
	int positiveXLimit = 300;

	Expression expression1;
	Expression expression2;

	ExpressionComparer(Expression expression1, Expression expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	public ArrayList<Double> findEqualities() {
		ArrayList<Double> answers = new ArrayList<>();

		
		double previousDifference = 0;
		double currentDifference = 0;
		double nextDifference = 0;
		
		boolean answerFound = (calculateDifference(negativeXLimit - 1) > calculateDifference(negativeXLimit));

		for (double x = negativeXLimit; x <= positiveXLimit; x += 0.1) {

			previousDifference = pow(calculateDifference(x - 0.1), 2);
			currentDifference = pow(calculateDifference(x), 2);
			nextDifference = pow(calculateDifference(x + 0.1), 2);

			if (!answerFound) {
				if ((previousDifference < currentDifference && currentDifference < nextDifference)) {
					answers.add(DoubleRounder.round(determineDecimals(x), decimalPlaces));
					answerFound = true;
				}
			} else {
				if (previousDifference > currentDifference) {
					answerFound = !answerFound;
				}
			}

		}

		return answers;
	}

	
	private double determineDecimals(double x) {
		for (int i = 2; i <= decimalPlaces; i++) {
			double currentDifference = 0;
			double previousDifference = 1000000000;
			x -= 1 / pow(10, i - 1);
			for (int j = 0; j < 20; j++) {
				currentDifference = pow(calculateDifference(x + j * (1 / pow(10, i))), 2);
				if (currentDifference == 0) return x;

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
		return expression1.calculateValue(x) - expression2.calculateValue(x);
	}

}
