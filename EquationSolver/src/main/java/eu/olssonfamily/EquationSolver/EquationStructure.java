package eu.olssonfamily.EquationSolver;

public class EquationStructure {

	UserInput userinput = new UserInput();
	
	String[] partsOfX;
	String[] partsOfY;
	
	
	void SplitEquation (String equation) {
		
		String[] equationParts = userinput.splitAtEqualSign(equation);
		
		partsOfX =	userinput.SeparateParts(equationParts[0]);
		partsOfY =	userinput.SeparateParts(equationParts[1]);
		
		for (int i = 0; i < partsOfX.length; i++) {
			System.out.println(partsOfX[i]);
		}
		
	}
	
	void StructurePartX () {
		int[] parenthesisArray = null;
		int nextIndex = 0;
		
		// Looks for ( or ) in the left part of the equation and marks at witch position such signs are found
		for (int i = 0; i < partsOfX.length;i++) {
			if (partsOfX[i].contains("(") || partsOfX[i].contains(")")) {
				parenthesisArray[nextIndex++] = i;
			}
		}
		
		for (int i = 0; i < parenthesisArray.length/2; i++) {
			if (partsOfX[parenthesisArray[i] + 1] == "x") {
				
			} else {
				
				
			}
		}
		
	}
	
	
	
	
	
}
