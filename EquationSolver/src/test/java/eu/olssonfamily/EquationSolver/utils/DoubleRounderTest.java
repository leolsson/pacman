package eu.olssonfamily.EquationSolver.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DoubleRounderTest {
	@Nested
	class RoundDigitsTest {

		@Test
		void case1() {
			assertEquals(4,DoubleRounder.roundValidDigits(3.64, 1));
		}
		
		@Test
		void case2() {
			assertEquals(37000,DoubleRounder.roundValidDigits(36667.64345, 2));
		}
		
		@Test
		@Disabled
		// Not yet ready
		void case3() {
			assertEquals(0.6,DoubleRounder.roundValidDigits(0.56, 1));
		}

	}
}
