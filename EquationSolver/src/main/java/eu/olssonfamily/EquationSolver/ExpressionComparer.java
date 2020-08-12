package eu.olssonfamily.EquationSolver;

import java.util.ArrayList;

import org.decimal4j.util.DoubleRounder;

import static java.lang.Math.pow;

public class ExpressionComparer {

	int decimalPlaces = 4;
	int bottomLimit = -100;
	int topLimit = 100;

	Expression expression1;
	Expression expression2;

	ExpressionComparer(Expression expression1, Expression expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	double previousDifference = 0;
	double currentDifference = 0;
	double nextDifference = 0; 
	
	public ArrayList<Double> findEqualities() {
		ArrayList<Double> answers = new ArrayList<>();
		
		boolean approachingAnswer = (calculateDifference(bottomLimit - 1) <= calculateDifference(bottomLimit));

		for (double x = bottomLimit; x <= topLimit; x += 0.1) {

			setDifferences(x);

			if (approachingAnswer) {
				if ((previousDifference < currentDifference && currentDifference < nextDifference)) {
					answers.add(DoubleRounder.round(determineDecimals(x), decimalPlaces));
					approachingAnswer = false;
				}
			} else {
				if (previousDifference > currentDifference) {
					approachingAnswer = !approachingAnswer;
				}
			}

		}

		return answers;
	}

	
	private void setDifferences(double newX) {
		previousDifference = pow(calculateDifference(newX - 0.1), 2);
		currentDifference = pow(calculateDifference(newX), 2);
		nextDifference = pow(calculateDifference(newX + 0.1), 2);
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
