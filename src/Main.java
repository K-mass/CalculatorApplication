import java.util.Scanner;

public class Main{
	
	public static void main(String args[]) {
		Scanner userInput = new Scanner(System.in); //create a scanner object
		
		CalculatorLogic input = new CalculatorLogic(); //create an object of CalculatorLogic class
		
		System.out.println("Input an expression digit by digit. For example, '125 + 200' would be:");
		System.out.println("1");
		System.out.println("2");
		System.out.println("5");
		System.out.println("+");
		System.out.println("2");
		System.out.println("0");
		System.out.println("0");
		System.out.println("Input a '=' to calculate the expression");
		
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
