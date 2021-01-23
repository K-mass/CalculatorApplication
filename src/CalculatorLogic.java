public class CalculatorLogic {
	float num1 = "";
	float num2 = "";
	char operator = 0;
	float answer = "";
	boolean operatorOn = false;
	boolean answerOn = false;
	boolean decimalOn = false;
	
	public boolean SixDigitCheck(float value) {
		if (String.valueOf(value).length() >= 6) {
			return true;
		}
		return false;
	}
	
	public int decimalDigits(float value) {
		return String.valueOf(value - Math.floor(value)).length();
	}
	
	public int concatenate(int value1, char value2) { //concatenate two values
		if (SixDigitCheck(value1)) {
			if (!decimalOn) {
				return value1 * 10 + value2;
			} else {
				
			}
		}
	}
	
	static boolean undefinedCheck(int value) {
			if (value != 0) {
				return false;
			}
		return true;
	}
	
	static int removeChar(int[] var) {
		for (int i = 10; i <= 100000;i *= 10) { //shift digits to the right
			if (value1 < i) {
				return value1 * i + value2;
			}
		}
	}
	
	public void buttonPress(int type, char value) { //code to be executed when any button is pressed
		switch (type) { //determine which type of button was pressed (operator (0), digit (1), backspace (2), equals (3)
		case 0: //operator is pressed
			operator = value;
			operatorOn = true;
			break;
		case 1: //digit is pressed
			if (operatorOn == false) { //determine whether to modify first or second number
				num1 = concatenate(num1, value); //concatenate the button value with num1
			} else {
				if (answerOn == true) {
					num1 = setValue(0,0,0,0,0,0);
					num2 = setValue(0,0,0,0,0,0);
					operator = 0;
					num1 = concatenate(num1, value); //start a new expression and reset all variables
				} else {
					num2 = concatenate(num2, value); //concatenate the button value with num2
				}
			}
			break;
		case 2:
			if (operatorOn == false) {
				num1 = removeChar(num1);
			} else if (operatorOn == true && answerOn == false && undefinedCheck(num2) == true) {
				num1 = removeChar(num1);
			} else if (operatorOn == true && answerOn == false && undefinedCheck(num2) == false) {
				num2 = removeChar(num2);
			}
			break;
		}
	}
}
