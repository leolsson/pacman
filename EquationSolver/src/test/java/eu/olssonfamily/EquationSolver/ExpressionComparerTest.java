package eu.olssonfamily.EquationSolver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.olssonfamily.EquationSolver.ExpressionComparer;

public class ExpressionComparerTest {

	@Nested
	class FindEqualitiesTest {
		 
		@Test
		@Disabled
		void case1() {
			Expression expression1 = new Expression(new ArrayList<>(Arrays.asList("1", "+", "x")));
			Expression expression2 = new Expression(new ArrayList<>(Arrays.asList("7")));
			ExpressionComparer expressionComparer = new ExpressionComparer(expression1, expression2);
			
			ArrayList<Double> expected = new ArrayList<>(Arrays.asList(6.0));
			
			assertEquals(expected, expressionComparer.findEqualities());
		}
		
		@Test
		void case2() {
			Expression expression1 = new Expression(new ArrayList<>(Arrays.asList("1", "+", "x", "+", "x", "^", "2")));
			Expression expression2 = new Expression(new ArrayList<>(Arrays.asList("7")));
			ExpressionComparer expressionComparer = new ExpressionComparer(expression1, expression2);
			
			ArrayList<Double> expected = new ArrayList<>(Arrays.asList(-3.0, 2.0));
			
			assertEquals(expected, expressionComparer.findEqualities());
		}
		
		@Test
		void case3() {
			Expression expression1 = new Expression(new ArrayList<>(Arrays.asList("1000", "*", "1.7", "^", "x")));
			Expression expression2 = new Expression(new ArrayList<>(Arrays.asList("10000")));
			ExpressionComparer expressionComparer = new ExpressionComparer(expression1, expression2);
			
			ArrayList<Double> expected = new ArrayList<>(Arrays.asList(4.3394));
			
			assertEquals(expected, expressionComparer.findEqualities());
		}
		
		@Test
		void case4() {
			Expression expression1 = new Expression(new ArrayList<>(Arrays.asList("3", "/", "(", "x", "-", "2", ")", "+", "2", "/", "x", "+", "3", "/", "(", "x", "+", "2", ")")));
			Expression expression2 = new Expression(new ArrayList<>(Arrays.asList("0")));
			ExpressionComparer expressionComparer = new ExpressionComparer(expression1, expression2);
			
			ArrayList<Double> expected = new ArrayList<>(Arrays.asList(-1.0, 1.0));
			
			assertEquals(expected, expressionComparer.findEqualities());
		}
		
		@Test
		void case5() {
			Equation equation = new Equation("(x^2-6*x+5)/(2*x-2)=1");
			
			Expression leftSideExpression = new Expression(equation.leftSide);
			Expression rightSideExpression = new Expression(equation.rightSide);
			
			ExpressionComparer expressionComparer = new ExpressionComparer(leftSideExpression, rightSideExpression);
			
			ArrayList<Double> expected = new ArrayList<>(Arrays.asList(7.0));
			
			assertEquals(expected, expressionComparer.findEqualities());
		}
		
		@Test
		void case6() {
			Equation equation = new Equation("(x^2-6*x+5)/(2*x-2)=1");
			
			Expression leftSideExpression = new Expression(equation.leftSide);
			Expression rightSideExpression = new Expression(equation.rightSide);
			
			ExpressionComparer expressionComparer = new ExpressionComparer(leftSideExpression, rightSideExpression);
			
			ArrayList<Double> expected = new ArrayList<>(Arrays.asList(7.0));
			
			assertEquals(expected, expressionComparer.findEqualities());
		}

	}

}
