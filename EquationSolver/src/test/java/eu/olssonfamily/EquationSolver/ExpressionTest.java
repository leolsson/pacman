package eu.olssonfamily.EquationSolver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ExpressionTest {

	@Nested
	class CalculateValueTest {

		@Test
		void case1() {
			ArrayList<String> parts = new ArrayList<>(Arrays.asList("1", "+", "x", "+", "x", "^", "2"));
			Expression expression = new Expression(parts);
			assertEquals(7, expression.calculateValue(2));
		}

		@Test
		void case2() {
			ArrayList<String> parts = new ArrayList<>(
					Arrays.asList("(", "x", "^", "2", "-", "9", ")", "/", "(", "2", "*", "x", "-", "12", ")"));
			Expression expression = new Expression(parts);
			assertEquals(-47.12735849056604, expression.calculateValue(-100));
		}

		@Test
		void case3() {
			ArrayList<String> parts = new ArrayList<>(Arrays.asList("1", "+", "x"));
			Expression expression = new Expression(parts);
			assertEquals(3, expression.calculateValue(2));
		}

		@Test
		void case4() {
			ArrayList<String> parts = new ArrayList<>(Arrays.asList("1", "-", "x"));
			Expression expression = new Expression(parts);
			assertEquals(-1, expression.calculateValue(2));
		}

		@Test
		void case5() {
			ArrayList<String> parts = new ArrayList<>(
					Arrays.asList("(", "(", "1", "-", "x", ")", "*", "4", ")", "^", "3.5"));
			Expression expression = new Expression(parts);
			assertEquals(128, expression.calculateValue(0));
		}

		@Test
		void case6() {
			ArrayList<String> parts = new ArrayList<>(
					Arrays.asList("(", "(", "1", "-", "x", ")", "*", "4", ")", "^", "x"));
			Expression expression = new Expression(parts);
			assertEquals(2.44140625E-4, expression.calculateValue(-3));
		}

		@Test
		void case7() {
			ArrayList<String> parts = new ArrayList<>(Arrays.asList("(", "x", "^", "2", "-", "6", "*", "x", "+", "5",
					")", "/", "(", "2", "*", "x", "-", "2", ")"));
			Expression expression = new Expression(parts);
			assertEquals(1, expression.calculateValue(7));
		}

		@Test
		void case8() {
			ArrayList<String> parts = new ArrayList<>(Arrays.asList("2", "/", "(", "x", "-", "2", ")"));
			Expression expression = new Expression(parts);
			assertEquals(Double.NaN, expression.calculateValue(2));
		}

		@Test
		void case9() {
			ArrayList<String> parts = new ArrayList<>(Arrays.asList("3", "/", "(", "3", "*", "x", "-", "9", ")", "-",
					"2", "/", "(", "2", "*", "x", "^", "2", "-", "6", "*", "x", ")"));
			Expression expression = new Expression(parts);
			assertEquals(1.4285714285714286, expression.calculateValue(3.5));
		}

		@Nested
		class CalculateValueWithPrioritiesAndUpdateArrayTest {
			void testMultiplicationAndAddition() {
				ArrayList<String> parts = new ArrayList<>(
						Arrays.asList("(", "3", "-", "2.5", ")", "+", "(", "4", "*", "3", ")"));
				Expression expression = new Expression(parts);

				ArrayList<String> expected = new ArrayList<>(Arrays.asList("0.5", "+", "12"));

				assertEquals(expected, expression.calculateValuesWithinParenthasis(parts));
			}
		}

		@Nested
		class CalculateValuesWithinParenthasisTest {
			@Test
			void testOneLevel() {
				ArrayList<String> parts = new ArrayList<>(
						Arrays.asList("(", "3", "-", "2.5", ")", "+", "(", "4", "*", "3", ")"));
				Expression expression = new Expression(parts);

				ArrayList<String> expected = new ArrayList<>(Arrays.asList("12.5"));

				assertEquals(expected, expression.calculateValuesWithinParenthasis(parts));
			}

			@Test
			void testTwoLevels() {
				ArrayList<String> parts = new ArrayList<>(
						Arrays.asList("(", "3", "-", "(", "2.5", "/", "2", ")", ")", "*", "(", "4", "*", "3", ")"));
				Expression expression = new Expression(parts);

				ArrayList<String> expected = new ArrayList<>(Arrays.asList("21.0"));

				assertEquals(expected, expression.calculateValuesWithinParenthasis(parts));
			}

			@Test
			void testThreeLevels() {
				ArrayList<String> parts = new ArrayList<>(Arrays.asList("(", "3", "-", "(", "2.5", "/", "(", "2", "^",
						"3", ")", ")", ")", "*", "(", "4", "*", "3", ")"));
				Expression expression = new Expression(parts);

				ArrayList<String> expected = new ArrayList<>(Arrays.asList("32.25"));

				assertEquals(expected, expression.calculateValuesWithinParenthasis(parts));
			}

		}

		@Nested
		class findEndOfParenthasisTest {

			@Test
			void testOneLevel() {
				ArrayList<String> parts = new ArrayList<>(Arrays.asList("(", "1", "-", "(", "x", "*", "3", ")", ")"));
				Expression expression = new Expression(parts);

				assertEquals(8, expression.findEndOfParenthasis(parts, 3));
			}

		}

		@Nested
		class calculateValueOfNumericExpressionsAndUpdateArrayTest {

			@Test
			void testDivision() {
				ArrayList<String> parts = new ArrayList<>(Arrays.asList("4", "/", "2", "+", "3"));
				Expression expression = new Expression(parts);

				expression.calculateValueOfNumericExpressionsAndUpdateArray(expression.parts, "divide");

				ArrayList<String> expected = new ArrayList<>(Arrays.asList("2.0", "+", "3"));
				assertEquals(expected, parts);
			}

			@Test
			void testAddition() {
				ArrayList<String> parts = new ArrayList<>(Arrays.asList("4", "/", "2", "+", "3"));
				Expression expression = new Expression(parts);

				expression.calculateValueOfNumericExpressionsAndUpdateArray(expression.parts, "add");

				ArrayList<String> expected = new ArrayList<>(Arrays.asList("4", "/", "2", "+", "3"));
				assertEquals(expected, parts);
			}

			@Test
			void testPower() {
				ArrayList<String> parts = new ArrayList<>(Arrays.asList("4", "/", "2", "^", "3.5"));
				Expression expression = new Expression(parts);

				expression.calculateValueOfNumericExpressionsAndUpdateArray(expression.parts, "power");

				ArrayList<String> expected = new ArrayList<>(Arrays.asList("4", "/", "11.313708498984761"));
				assertEquals(expected, parts);
			}

		}

		@Nested
		class getOperatorsByNameTest {

			Expression expression;

			@BeforeEach
			void init() {
				ArrayList<String> parts = new ArrayList<>(Arrays.asList("1", "+", "2"));
				expression = new Expression(parts);
			}

			@Test
			void testMultiplication() {
				assertEquals("*", expression.getOperatorByName("multiply"));
			}

			@Test
			void testDivision() {
				assertEquals("/", expression.getOperatorByName("divide"));
			}

			@Test
			void testAddition() {
				assertEquals("+", expression.getOperatorByName("add"));
			}

			@Test
			void testSubtraction() {
				assertEquals("-", expression.getOperatorByName("subtract"));
			}

		}

		@Nested
		class CalculateTest {

			Expression expression;

			@BeforeEach
			void init() {
				ArrayList<String> parts = new ArrayList<>(Arrays.asList("1", "+", "2"));
				expression = new Expression(parts);
			}

			@Test
			void testMultiplication() {
				assertEquals(14.68, expression.calculate(4, 3.67, "multiply"));
			}

			@Test
			void testSubtraction() {
				assertEquals(0.33000000000000007, expression.calculate(4, 3.67, "subtract"));
			}

		}
	}

}
