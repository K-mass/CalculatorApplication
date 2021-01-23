import java.util.Scanner;

public class Main {
	static boolean checkValue(char input,char[] expected) {
		for (int i = 0; i <= expected.length; i++) {
			if (expected[i] == input) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		char[] type0 = {'+','-','*','/','^'};
		char[] type1 = {0,1,2,3,4,5,6,7,8,9};
		Scanner input = new Scanner(System.in); //create an object out of the scanner class
		try {
			System.out.println("Input number");
			char userNum = input.nextLine().charAt(0);
			if (checkValue(userNum, type0)) {
				buttonPress(0,userNum);
			}
		} finally {
			input.close(); //close the scanner
		}
	}
}
