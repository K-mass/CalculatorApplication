import java.util.Scanner;

public class Main{
	
	public static void main(String args[]) {
		Scanner userInput = new Scanner(System.in);
		
		CalculatorLogic input = new CalculatorLogic();
		
		try {
			while(0 == 0) {
				char inputChar = userInput.nextLine().charAt(0);
			
				if (inputChar != '=') {
					input.buttonPress(inputChar);
				} else {
					System.out.println(input.evaluate());
				}
			}
		} finally {
			userInput.close();
		}
	}
}
