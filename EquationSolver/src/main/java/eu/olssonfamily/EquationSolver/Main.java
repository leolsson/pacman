package eu.olssonfamily.EquationSolver;

import java.util.ArrayList;

public class Main {

	
	
	public static void main(String[] args) {

		
		while (1 < 2) {
			getCompileAnswerEquation();
		}
		
	}
	
	
	private static void getCompileAnswerEquation() {
		Equation equation = new Equation(UserInput.getEquation());
		
		Expression leftSideExpression = new Expression(equation.leftSide);
		Expression rightSideExpression = new Expression(equation.rightSide);
		
		ExpressionComparer expressionComparer = new ExpressionComparer(leftSideExpression, rightSideExpression);
		
		ArrayList<Double> answers = new ArrayList<>();
		answers.addAll(expressionComparer.findEqualities());
		
		System.out.println();
		
		answers.forEach(answer -> System.out.println("x(" + (answers.indexOf(answer)+1) + ") = "+ answer));
		
		System.out.println();
	}
	
	

}
