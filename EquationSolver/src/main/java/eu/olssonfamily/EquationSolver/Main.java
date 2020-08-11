package eu.olssonfamily.EquationSolver;

import java.util.ArrayList;
import java.util.Arrays;

import eu.olssonfamily.EquationSolver.UserInput;

public class Main {

	
	
	public static void main(String[] args) {

		
		Equation equation = new Equation(UserInput.getEquation());
		
		Expression leftSideExpression = new Expression(equation.leftSide);
		Expression rightSideExpression = new Expression(equation.rightSide);
		
		ExpressionComparer expressionComparer = new ExpressionComparer(leftSideExpression, rightSideExpression);
		System.out.println(expressionComparer.findEqualities());
		
	}
	
	

}
