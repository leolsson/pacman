package eu.olssonfamily.EquationSolver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class EquationTest {

	@Test
	void case1() {
		Equation equation = new Equation("1000*1.3^x=2000");
		
		ArrayList<String> expected = new ArrayList<>(Arrays.asList("1000", "*", "1.3", "^", "x"));
		
		assertEquals(expected, equation.leftSide);
	}

}
