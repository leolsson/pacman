package eu.olssonfamily.pacman.uitl;

import eu.olssonfamily.pacman.util.RandomUtil;

public class Test {

	public static void main(String[] args) {
		testRandomUtilGetMinMax();
	}

	private static void testRandomUtilGetMinMax() {
		int LOW = 1, HIGH=4, SAMPLES=100;
		int randomNumbers[] = new int[HIGH+1];
		
		for (int i=LOW; i<HIGH; i++) {
			randomNumbers[i]=0;
		}
		
		for (int i=0; i<SAMPLES; i++) {
			int randomNumber = RandomUtil.getMinMax(LOW, HIGH);
			if (randomNumber < LOW || randomNumber > HIGH) {
				System.out.println("Incorrect random value " + randomNumber + " expected a number between 1 and 10");
				return;
			}
			
			randomNumbers[randomNumber]++;
		}

		for (int i=LOW; i<=HIGH; i++) {
			if (randomNumbers[i] == 0) {
				System.out.println("Error no random numbers equals " + i);
			}
		}
		
		System.out.print("Distribution of the samples: ");
		for (int i=LOW; i<=HIGH; i++) {
			System.out.print("," + randomNumbers[i]);
		}
		
	}

}
